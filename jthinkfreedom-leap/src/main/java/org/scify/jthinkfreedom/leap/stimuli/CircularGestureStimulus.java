package org.scify.jthinkfreedom.leap.stimuli;

import com.leapmotion.leap.Gesture;
import static com.leapmotion.leap.Gesture.State.STATE_STOP;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAnnotation;

/**
 *
 * @author peustr
 */
@StimulusAnnotation(sensorClass = "org.scify.jthinkfreedom.leap.sensors.LeapMotionSensor")
public class CircularGestureStimulus extends LeapMotionGestureStimulus {

    public CircularGestureStimulus() {
        super();
    }

    @Override
    public boolean shouldReact() {
        for (Gesture gesture : frame.gestures()) {
            return gesture.type() == Gesture.Type.TYPE_CIRCLE
                    && gesture.state() == STATE_STOP;
        }
        return false;
    }

}
