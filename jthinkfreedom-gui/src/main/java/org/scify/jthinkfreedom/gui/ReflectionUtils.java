package org.scify.jthinkfreedom.gui;

import java.util.ArrayList;
import java.util.List;
import org.reflections.Reflections;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author peustr
 */
public class ReflectionUtils {

    public static List<Class<? extends SensorAdapter>> getSensors() {
        Reflections reflections = new Reflections();
        List<Class<? extends SensorAdapter>> sensorsList = new ArrayList();
        sensorsList.addAll(reflections.getSubTypesOf(SensorAdapter.class));
        return sensorsList;
    }

    public static List<Class<? extends StimulusAdapter>> getStimuli() {
        Reflections reflections = new Reflections();
        List<Class<? extends StimulusAdapter>> stimuliList = new ArrayList();
        stimuliList.addAll(reflections.getSubTypesOf(StimulusAdapter.class));
        return stimuliList;
    }
}
