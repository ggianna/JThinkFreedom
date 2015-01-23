/* 
 * Copyright 2014 SciFY.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    @Override
    public String getCanonicalString() {
        return "Webcam";
    }
}
