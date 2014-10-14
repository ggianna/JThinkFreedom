package org.scify.jthinkfreedom.skeleton.sensors;

import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;

/**
 *
 * @author peustr
 * @param <T>
 */
public interface Sensor<T> {

    /**
     * Returns the last read data from the sensor. If data were not previously
     * available, new data should be acquired.
     *
     * @return The last available data.
     */
    public T getData();

    /**
     * Calls the onDataReceived of each stimulus.
     */
    public void updateStimuli();

    /**
     * Adds a stimulus to the list of stimuli to inform when new data is made
     * available.
     *
     * @param sToAdd The stimulus.
     */
    public void addStimulus(Stimulus sToAdd);

    /**
     * Removes a stimulus from the list of stimuli to inform when new data are
     * made available.
     *
     * @param sToRemove The stimulus.
     */
    public void removeStimulus(Stimulus sToRemove);

    /**
     * Clears the stimuli list, i.e. no stimulus will be made aware when new
     * data are available.
     */
    public void clearStimuli();

    /**
     * Start gathering data. Possible call of the sensors start method implied.
     */
    public void start();

    /**
     * Stop gathering data. Possible call of the sensors stop method implied.
     */
    public void stop();

    /**
     * Indicates whether the sensor is running. If it is not, data returned from
     * calls to getData should be null.
     *
     * @return True if the sensor is running, false otherwise.
     */
    public boolean isRunning();

}
