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

import com.leapmotion.leap.Gesture;
import static com.leapmotion.leap.Gesture.State.STATE_STOP;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAnnotation;

/**
 *
 * @author peustr
 */
@StimulusAnnotation(sensorClass = "org.scify.jthinkfreedom.leap.sensors.LeapMotionSensor")
public class KeyTapGestureStimulus extends LeapMotionGestureStimulus {

    public KeyTapGestureStimulus() {
        super();
    }

    @Override
    public boolean shouldReact() {
        for (Gesture gesture : frame.gestures()) {
            return gesture.type() == Gesture.Type.TYPE_KEY_TAP
                    && gesture.state() == STATE_STOP;
        }
        return false;
    }

    @Override
    public String getCanonicalString() {
        return "Key Tap";
    }

    @Override
    public String getDescription() {
        return "Detecting a key tap gesture";
    }

}
