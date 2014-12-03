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
