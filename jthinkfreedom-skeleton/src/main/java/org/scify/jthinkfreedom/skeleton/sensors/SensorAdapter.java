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
