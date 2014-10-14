package org.scify.jthinkfreedom.leap.stimuli;

import com.leapmotion.leap.Gesture;
import static com.leapmotion.leap.Gesture.State.STATE_STOP;

/**
 *
 * @author peustr
 */
public class SwipeGestureStimulus extends LeapMotionGestureStimulus {

    public SwipeGestureStimulus() {
        super();
    }

    @Override
    public boolean shouldReact() {
        for (Gesture gesture : frame.gestures()) {
            return gesture.type() == Gesture.Type.TYPE_SWIPE
                    && gesture.state() == STATE_STOP;
        }
        return false;
    }

}
