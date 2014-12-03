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
package org.scify.jthinkfreedom.leap.sensors;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;

/**
 *
 * @author peustr
 */
public class LeapMotionSensor extends SensorAdapter<Frame> {

    private Controller controller;
    private Listener l;
    private Frame frame;

    public LeapMotionSensor() {
        super();
        controller = new Controller();
        l = new Listener() {

            @Override
            public void onConnect(Controller controller) {
                super.onConnect(controller);
                controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
                controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
                controller.enableGesture(Gesture.Type.TYPE_SWIPE);
                controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
            }

            @Override
            public void onFrame(Controller controller) {
                super.onFrame(controller);
                frame = controller.frame();
            }

        };
        controller.addListener(l);
    }

    @Override
    public Frame getData() {
        return frame;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

}
