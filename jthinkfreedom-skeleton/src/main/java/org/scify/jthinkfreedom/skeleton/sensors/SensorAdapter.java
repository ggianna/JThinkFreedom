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
package org.scify.jthinkfreedom.skeleton.sensors;

import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a basic sensor.
 *
 * @author peustr
 * @param <T>
 */
public abstract class SensorAdapter<T> implements Sensor<T> {

    protected List<Stimulus> stimuli;
    protected boolean bRunning;

    protected SensorAdapter() {
        stimuli = new ArrayList();
    }

    @Override
    public abstract T getData();

    @Override
    public void updateStimuli() {
        while (bRunning) {
            for (Stimulus stimulus : stimuli) {
                stimulus.onDataReceived();
            }
        }
    }

    @Override
    public void addStimulus(Stimulus sToAdd) {
        stimuli.add(sToAdd);
    }

    @Override
    public void removeStimulus(Stimulus sToRemove) {
        stimuli.remove(sToRemove);
    }

    @Override
    public void clearStimuli() {
        stimuli.clear();
    }

    @Override
    public void start() {
        bRunning = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                updateStimuli();
            }
        });
        t.start();
    }

    @Override
    public void stop() {
        bRunning = false;
    }

    @Override
    public boolean isRunning() {
        return bRunning;
    }

    public List<Stimulus> getStimuli() {
        return stimuli;
    }

    public void setStimuli(List<Stimulus> stimuli) {
        this.stimuli = stimuli;
    }

}
