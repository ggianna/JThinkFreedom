package org.scify.jthinkfreedom.webcam.sensors;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FrameGrabber;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;

/**
 *
 * @author peustr
 */
public class WebcamSensor extends SensorAdapter<IplImage> {

    private FrameGrabber grabber;
    private IplImage grabbedImage;

    public WebcamSensor() {
        super();
        try {
            grabber = FrameGrabber.createDefault(0);
        } catch (FrameGrabber.Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public IplImage getData() {
        try {
            grabbedImage = grabber.grab();
        } catch (FrameGrabber.Exception ex) {
            ex.printStackTrace(System.err);
        }
        return grabbedImage;
    }

    @Override
    public void start() {
        super.start();
        try {
            grabber.start();
        } catch (FrameGrabber.Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void stop() {
        super.stop();
        try {
            grabber.stop();
        } catch (FrameGrabber.Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
