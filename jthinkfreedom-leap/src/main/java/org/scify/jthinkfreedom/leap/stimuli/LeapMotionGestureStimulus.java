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
package org.scify.jthinkfreedom.leap.stimuli;

import com.leapmotion.leap.Frame;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author peustr
 */
public abstract class LeapMotionGestureStimulus extends StimulusAdapter {

    protected Frame frame;

    protected LeapMotionGestureStimulus() {
        super();
    }

    @Override
    public void onDataReceived() {

        for (Sensor<Frame> sensor : sensors) {
            frame = sensor.getData();
            if (frame != null) {
                if (shouldReact()) {
                    callReactors();
                }
            }
        }

    }

}
