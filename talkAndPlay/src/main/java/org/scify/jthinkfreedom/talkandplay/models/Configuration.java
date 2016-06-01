package org.scify.jthinkfreedom.talkandplay.models;

import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

public class Configuration {

    private int rotationSpeed;
    private int defaultGridRow;
    private int defaultGridColumn;
    private SensorAdapter sensor;
    private StimulusAdapter stimulus;
    private ReactorAdapter reactor;

    public Configuration() {
    }

    public Configuration(SensorAdapter sensor, StimulusAdapter stimulus, ReactorAdapter reactor) {
        this.sensor = sensor;
        this.stimulus = stimulus;
        this.reactor = reactor;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public int getDefaultGridRow() {
        return defaultGridRow;
    }

    public void setDefaultGridRow(int defaultGridRow) {
        this.defaultGridRow = defaultGridRow;
    }

    public int getDefaultGridColumn() {
        return defaultGridColumn;
    }

    public void setDefaultGridColumn(int defaultGridColumn) {
        this.defaultGridColumn = defaultGridColumn;
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

    @Override
    public String toString() {
        return "Configuration{" + "sensor=" + sensor + ", stimulus=" + stimulus + ", reactor=" + reactor + '}';
    }

}
