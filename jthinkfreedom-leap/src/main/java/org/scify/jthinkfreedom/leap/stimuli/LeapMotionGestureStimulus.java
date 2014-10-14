package org.scify.jthinkfreedom.leap.stimuli;

import com.leapmotion.leap.Frame;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 *
 * @author peustr
 */
public abstract class LeapMotionGestureStimulus extends StimulusAdapter {

    protected Frame frame;

    protected LeapMotionGestureStimulus() {
        super();
    }

    @Override
    public void onDataReceived() {

        for (Sensor<Frame> sensor : sensors) {
            frame = sensor.getData();
            if (frame != null) {
                if (shouldReact()) {
                    callReactors();
                }
            }
        }

    }

}
