/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.stimuli;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import static com.leapmotion.leap.Gesture.State.STATE_STOP;

/**
 *
 * @author alexisz
 */
public class ScreenTapGestureStimulus extends LeapMotionStimulus {

    public ScreenTapGestureStimulus() {
        super();
    }

    @Override
    public boolean shouldReact(Frame info) {
        for (Gesture g : info.gestures()) {
         if (g.type() == Gesture.Type.TYPE_SCREEN_TAP && g.state()==STATE_STOP){
                System.out.println("screen tap gesture!");
                return true;
            }
        }
        return false;
    }
}
