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
public class CompositeOrderedStimulus extends StimulusAdapter<Object>{
    
    private List<StimulusAdapter> stimulusList;
    protected Object info;
    private ArrayList<Boolean> satisfied;
    private int i;
    public CompositeOrderedStimulus(List<StimulusAdapter> stimulusList) {
        super();
        i=0;
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

        for (ISensor<Object> curSensor : lSensors) {

            info = curSensor.getData();
            
            
                if (stimulusList.get(i).shouldReact(info)) {
                    satisfied.set(i, true);   
                    i++;
                }
                else{
                    Collections.fill(satisfied, false);
                    i=0;
                }
            
            if (checkAllTrue(satisfied)) {
                callReactors();
                Collections.fill(satisfied, false);
            i=0;
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
