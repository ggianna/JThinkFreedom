/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.stimuli;

import com.leapmotion.leap.Frame;
import org.scify.jthinkfreedom.sensors.ISensor;

/**
 *
 * @author alexisz
 */
public abstract class LeapMotionStimulus extends StimulusAdapter<Frame> {

    protected Frame info = null;

    protected LeapMotionStimulus() {
        super();
    }

    @Override
    public void onDataReceived() {
        if (lSensors.isEmpty()) {
            return;
        }
        for (ISensor<Frame> isCurSensor : lSensors) {
            info = isCurSensor.getData();
            if (shouldReact(info)) {
                System.out.println("Caught gesture!");
                callReactors();
            }
        }
    }
}
