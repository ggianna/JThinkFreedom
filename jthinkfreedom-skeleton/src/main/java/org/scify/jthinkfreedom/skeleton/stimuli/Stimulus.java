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

}
