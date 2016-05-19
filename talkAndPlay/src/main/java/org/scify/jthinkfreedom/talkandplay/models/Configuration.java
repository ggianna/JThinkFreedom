package org.scify.jthinkfreedom.talkandplay.models;

import java.util.ArrayList;
import java.util.List;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author peustr
 */
public class Configuration {

    private SensorAdapter sensor;
    private StimulusAdapter stimulus;
    private ReactorAdapter reactor;
    private List<Category> categories;

    public Configuration() {
        categories = new ArrayList<>();
    }

    public Configuration(SensorAdapter sensor, StimulusAdapter stimulus, ReactorAdapter reactor) {
        this.sensor = sensor;
        this.stimulus = stimulus;
        this.reactor = reactor;
    }

    public SensorAdapter getSensor() {
        return sensor;
    }

    public void setSensor(SensorAdapter sensor) {
        this.sensor = sensor;
    }

    public StimulusAdapter getStimulus() {
        return stimulus;
    }

    public void setStimulus(StimulusAdapter stimulus) {
        this.stimulus = stimulus;
    }

    public ReactorAdapter getReactor() {
        return reactor;
    }

    public void setReactor(ReactorAdapter reactor) {
        this.reactor = reactor;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Configuration{" + "sensor=" + sensor + ", stimulus=" + stimulus + ", reactor=" + reactor + ", categories=" + categories + '}';
    }

    
}
