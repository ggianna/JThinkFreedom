/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.stimuli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.scify.jthinkfreedom.sensors.ISensor;

/**
 *
 * @author alexisz
 */
public class CompositeStimulus extends StimulusAdapter<Object> {

    private List<StimulusAdapter> stimulusList;
    protected Object info;
    private ArrayList<Boolean> satisfied;

    public CompositeStimulus(List<StimulusAdapter> stimulusList) {
        super();
        this.stimulusList = stimulusList;
        satisfied = new ArrayList(stimulusList.size());
        for (StimulusAdapter sa : stimulusList) {
            satisfied.add(false);
        }
    }

    public List<StimulusAdapter> getStimulusList() { 
       return stimulusList;
    }

    public void setStimulusList(List<StimulusAdapter> stimulusList) {
        this.stimulusList = stimulusList;
    }

    @Override
    public void onDataReceived() {
        if (lSensors.isEmpty()) {
            return;
        }
        //boolean tmp = true;

        for (ISensor<Object> curSensor : lSensors) {

            info = curSensor.getData();

            for (int i = 0; i < stimulusList.size(); i++) {
                if (stimulusList.get(i).shouldReact(info)) {
                    satisfied.set(i, true);   
                    
                }
            }
            if (checkAllTrue(satisfied)) {
                callReactors();
                Collections.fill(satisfied, false);
            }
            
        }
    }

    public boolean checkAllTrue(List<Boolean> bList) {
        for (Boolean b : bList) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}