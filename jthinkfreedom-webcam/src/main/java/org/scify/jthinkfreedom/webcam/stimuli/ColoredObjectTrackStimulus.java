package org.scify.jthinkfreedom.webcam.stimuli;

import java.awt.Toolkit;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_core.cvGetSize;
import static org.bytedeco.javacpp.opencv_core.cvInRangeS;
import static org.bytedeco.javacpp.opencv_core.cvScalar;
import org.bytedeco.javacpp.opencv_imgproc.CvMoments;
import static org.bytedeco.javacpp.opencv_imgproc.cvGetCentralMoment;
import static org.bytedeco.javacpp.opencv_imgproc.cvGetSpatialMoment;
import static org.bytedeco.javacpp.opencv_imgproc.cvMoments;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.Coordinates;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAnnotation;

/**
 * Class that implements tracking of colored objects. It detects red color by
 * default, unless specified otherwise by the user.
 *
 * @author peustr
 */
@StimulusAnnotation(sensorClass = "org.scify.jthinkfreedom.webcam.sensors.WebcamSensor")
public class ColoredObjectTrackStimulus extends StimulusAdapter implements Coordinates {

    private static final int SCR_WIDTH
            = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCR_HEIGHT
            = Toolkit.getDefaultToolkit().getScreenSize().height;

    // The coordinates
    private int posX, posY;

    private IplImage grabbedImage, thresholdImage;
    private CvScalar rgbaMin, rgbaMax;

    public ColoredObjectTrackStimulus() {
        super();
        posX = 0;
        posY = 0;
        rgbaMin = cvScalar(0, 0, 130, 0);
        rgbaMax = cvScalar(80, 80, 255, 0);
    }

    public ColoredObjectTrackStimulus(CvScalar rgbaMin, CvScalar rgbaMax) {
        super();
        posX = 0;
        posY = 0;
        this.rgbaMin = rgbaMin;
        this.rgbaMax = rgbaMax;
    }

    @Override
    public void onDataReceived() {

        for (Sensor<IplImage> sensor : sensors) {
            grabbedImage = sensor.getData();
            if (grabbedImage != null) {
                cvFlip(grabbedImage, grabbedImage, 1);

                thresholdImage = detectThreshold(grabbedImage);

                CvMoments moments = new CvMoments();
                cvMoments(thresholdImage, moments, 1);
                double mom10 = cvGetSpatialMoment(moments, 1, 0);
                double mom01 = cvGetSpatialMoment(moments, 0, 1);
                double area = cvGetCentralMoment(moments, 0, 0);

                posX = (int) (mom10 / area) * (SCR_WIDTH / grabbedImage.width());
                posY = (int) (mom01 / area) * (SCR_HEIGHT / grabbedImage.height());

                if (shouldReact()) {
                    callReactors();
                }
            }
        }

    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    public IplImage getThresholdImage() {
        return thresholdImage;
    }

    public void setThresholdImage(IplImage thresholdImage) {
        this.thresholdImage = thresholdImage;
    }

    private IplImage detectThreshold(IplImage originalImage) {
        IplImage imgThreshold = cvCreateImage(cvGetSize(originalImage), 8, 1);

        cvInRangeS(originalImage, rgbaMin, rgbaMax, imgThreshold);

        return imgThreshold;
    }

    @Override
    public boolean shouldReact() {
        return posX > 0 && posY > 0;
    }
}
