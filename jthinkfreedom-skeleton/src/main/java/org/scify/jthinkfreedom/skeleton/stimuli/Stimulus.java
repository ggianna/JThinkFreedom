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

import org.scify.jthinkfreedom.skeleton.reactors.Reactor;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;

/**
 *
 * @author peustr
 */
public interface Stimulus {

    
    
    /**
     * The behavior that this stimulus imitates is implemented in this method.
     * The criteria that define whether or not the reactors are called are also
     * defined here.
     */
    public void onDataReceived();

    /**
     * The criteria that define whether or not the reactors are called. This
     * method should be called in onDataReceived method.
     *
     * @return True if reactors should be called, false otherwise.
     */
    public boolean shouldReact();

    /**
     * Calls every reactor in the list of reactors associated with this
     * stimulus.
     */
    public void callReactors();

    /**
     * Adds a reactor in the list of reactors associated with this stimulus.
     *
     * @param rToAdd The reactor.
     */
    public void addReactor(Reactor rToAdd);

    /**
     * Removes a reactor from the list of reactors associated with this
     * stimulus.
     *
     * @param rToRemove The reactor.
     */
    public void removeReactor(Reactor rToRemove);

    /**
     * Clears the list of reactors.
     */
    public void clearReactors();

    /**
     * Adds a sensor to the list of sensor this stimulus works under.
     *
     * @param sToAdd The sensor.
     */
    public void addSensor(Sensor sToAdd);

    /**
     * Removes a sensor from the list of sensor this stimulus works under.
     *
     * @param sToRemove The sensor.
     */
    public void removeSensor(Sensor sToRemove);

    /**
     * Clears the list of sensors.
     */
    public void clearStimuli();

    /**
     * Returns a simple string representation of the stimulus.
     *
     * @return
     */
    public String getCanonicalString();

    /**
     * Returns a string that describes the stimulus.
     *
     * @return
     */
    public String getDescription();
}
