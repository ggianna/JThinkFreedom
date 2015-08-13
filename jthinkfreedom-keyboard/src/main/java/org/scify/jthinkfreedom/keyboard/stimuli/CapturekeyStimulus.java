/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.stimuli;

import org.scify.jthinkfreedom.keyboard.sensors.KeyboardSensor;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author xrousakis
 */
public class CapturekeyStimulus extends StimulusAdapter {

    Character pressedKey;

    @Override
    public void onDataReceived() {
        for (Sensor<Character> sensor : sensors) {
            //System.out.println("onDataReceived");
            pressedKey = sensor.getData();
            if (pressedKey != null) {
                System.out.println(pressedKey);
                if (shouldReact()) {
                    System.out.println("Reactor called");
                    callReactors();
                    if(sensor instanceof KeyboardSensor)
                        ((KeyboardSensor)sensor).restorePressedKey();
                }
            }
        }
    }
    /* */

    @Override
    public boolean shouldReact() {
        //System.out.println(pressedKey);
        if (pressedKey.equals('A')) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getCanonicalString() {
        return "Sensoring pressed keys";
    }

    @Override
    public String getDescription() {
        return "Capturing pressed keys";
    }

}
