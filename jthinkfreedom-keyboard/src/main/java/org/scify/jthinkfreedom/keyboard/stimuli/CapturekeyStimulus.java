/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.stimuli;

import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author xrousakis
 */
public class CapturekeyStimulus  extends StimulusAdapter{
    
    Boolean key_pressed;
    @Override
    public void onDataReceived() {
         for(Sensor<Boolean> sensor:sensors){
            key_pressed=sensor.getData();
            if(key_pressed=true)
                callReactors();
        }
    }

    @Override
    public boolean shouldReact() {
       return true;
    }

    @Override
    public String getCanonicalString() {
        return "Sensoring for preseed Keys";
    }

    @Override
    public String getDescription() {
        return "Tracking  k for being pressed";
    }
    
}
