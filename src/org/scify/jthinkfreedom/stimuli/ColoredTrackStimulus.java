/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.stimuli;

import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import com.googlecode.javacv.cpp.opencv_imgproc;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_MEDIAN;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetCentralMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetSpatialMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvMoments;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvSmooth;
import org.scify.jthinkfreedom.sensors.ISensor;

/**
 *Class that implements colored object tracking using a webcam sensor and the
 * opencv technology.Check if there is a particular color in the picture and get the coordinates.
 * @author alexisz
 */
public class ColoredTrackStimulus extends StimulusAdapter<IplImage> {

    protected static int lastposX = 0;
    protected static int lastposY = 0;
    protected static int posX = 0;
    protected static int posY = 0;
    //int ii = 0;

    public ColoredTrackStimulus() {
        super();

    }

    protected boolean shouldReact(int piOld, int piNew) {
        return piNew < piOld;
    }

    CvScalar rgba_min = cvScalar(0, 0, 130, 0);// RED wide dabur birko
    CvScalar rgba_max = cvScalar(80, 80, 255, 0);
    IplImage detectThrs = null;
    protected IplImage grabbedImage = null;

    public IplImage getGrabbedImage() {
        return grabbedImage;
    }

    @Override
    public void onDataReceived() {

        for (ISensor<IplImage> isCurSensor : lSensors) {
            grabbedImage = isCurSensor.getData();
            if (grabbedImage != null) {
                // show image on window
                cvFlip(grabbedImage, grabbedImage, 1);// l-r = 90_degrees_steps_anti_clockwise
                detectThrs = getThresholdImage(grabbedImage);
                detectThrs = cvCreateImage(cvGetSize(grabbedImage), 8, 1);

                opencv_imgproc.CvMoments moments = new opencv_imgproc.CvMoments();
                cvMoments(detectThrs, moments, 1);
                double mom10 = cvGetSpatialMoment(moments, 1, 0);
                double mom01 = cvGetSpatialMoment(moments, 0, 1);
                double area = cvGetCentralMoment(moments, 0, 0);
                posX = (int) (mom10 / area);
                posY = (int) (mom01 / area);
                if (lastposX == 0 && lastposY == 0 && posX != 0 && posY != 0) {
                    lastposX = posX; // Update last X
                    lastposY = posY; // Update last Y
                    continue; // And go on
                }
                // only if its a valid position
                if (shouldReact(lastposX, posX) || shouldReact(lastposY, posY)) {
                    callReactors();
                }
                // Also update last info
                lastposX = posX;
                lastposY = posY;
            }
        }
    }

    /**
     * Check if there is a particular color in the picture.
     *
     * @param grabbedImage The image received from the sensor.
     * @return imgThreashold A black and white image thresholding the original
     * picture on the detected color.
     */
    private IplImage getThresholdImage(IplImage grabbedImage) {
        IplImage imgThreshold = cvCreateImage(cvGetSize(grabbedImage), 8, 1);
        //
        cvInRangeS(grabbedImage, rgba_min, rgba_max, imgThreshold);// red

        cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 15);
//        cvSaveImage(++ii + "dsmthreshold.jpg", imgThreshold);
        return imgThreshold;
    }

}
