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
import com.leapmotion.leap.Vector;
import java.awt.Toolkit;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.Coordinates;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAnnotation;

/**
 * Class that uses the leap motion sensor in order to track a finger and
 * translate it to real 2D coordinates.
 *
 * @author peustr
 */
@StimulusAnnotation(sensorClass = "org.scify.jthinkfreedom.leap.sensors.LeapMotionSensor")
public class FingerTrackStimulus extends StimulusAdapter implements Coordinates {

    private static final int SCR_WIDTH
            = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCR_HEIGHT
            = Toolkit.getDefaultToolkit().getScreenSize().height;

    // The coordinates
    private int posX, posY;

    private Frame frame;

    public FingerTrackStimulus() {
        super();
        posX = 0;
        posY = 0;
    }

    @Override
    public void onDataReceived() {

        for (Sensor<Frame> sensor : sensors) {
            frame = sensor.getData();
            if (frame != null) {
                Vector fingerPos = frame.fingers().get(0).stabilizedTipPosition();
                Vector boxFingerPos = frame.interactionBox().normalizePoint(fingerPos);
                posX = (int) (SCR_WIDTH * boxFingerPos.getX());
                posY = (int) (SCR_HEIGHT - boxFingerPos.getY() * SCR_HEIGHT);
                if (shouldReact()) {
                    callReactors();
                }
            }
        }

    }

    @Override
    public boolean shouldReact() {
        return posX > 0 && posY > 0;
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

}
