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
package org.scify.jthinkfreedom.skeleton.stimuli;

import java.util.ArrayList;
import java.util.List;
import org.scify.jthinkfreedom.skeleton.reactors.Reactor;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;

/**
 *
 * @author peustr
 */
public abstract class StimulusAdapter implements Stimulus {

    protected List<Sensor> sensors;
    protected List<Reactor> reactors;

    protected StimulusAdapter() {
        sensors = new ArrayList();
        reactors = new ArrayList();
    }

    @Override
    public abstract void onDataReceived();

    @Override
    public abstract boolean shouldReact();

    @Override
    public void callReactors() {
        for (Reactor reactor : reactors) {
            reactor.react();
        }
    }

    @Override
    public void addReactor(Reactor rToAdd) {
        reactors.add(rToAdd);
    }

    @Override
    public void removeReactor(Reactor rToRemove) {
        reactors.remove(rToRemove);
    }

    @Override
    public void clearReactors() {
        reactors.clear();
    }

    @Override
    public void addSensor(Sensor sToAdd) {
        sensors.add(sToAdd);
    }

    @Override
    public void removeSensor(Sensor sToRemove) {
        sensors.remove(sToRemove);
    }

    @Override
    public void clearStimuli() {
        sensors.clear();
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Reactor> getReactors() {
        return reactors;
    }

    public void setReactors(List<Reactor> reactors) {
        this.reactors = reactors;
    }

}
