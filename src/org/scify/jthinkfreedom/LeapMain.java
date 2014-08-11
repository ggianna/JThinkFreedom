package org.scify.jthinkfreedom;

import java.util.Date;
import org.scify.jthinkfreedom.reactors.LeftClickReactor;
import org.scify.jthinkfreedom.reactors.RightClickReactor;
import org.scify.jthinkfreedom.sensors.ISensor;
import org.scify.jthinkfreedom.sensors.LeapMotionSensor;
import org.scify.jthinkfreedom.stimuli.CirclularGestureStimulus;
import org.scify.jthinkfreedom.stimuli.IStimulus;
import org.scify.jthinkfreedom.stimuli.KeyTapGestureStimulus;

/**
 *
 * @author peustr
 */
public class LeapMain {

    public static final int MINUTE_IN_MILLIS = 60000;

    public static void main(String[] args) {
        ISensor leapSensor = new LeapMotionSensor();

        // Define stimuli
        IStimulus circle = new CirclularGestureStimulus();
        IStimulus tap = new KeyTapGestureStimulus();

        leapSensor.addStimulus(circle);
        leapSensor.addStimulus(tap);

        circle.addSensor(leapSensor);
        tap.addSensor(leapSensor);

        circle.addReactor(new LeftClickReactor());
        tap.addReactor(new RightClickReactor());

        leapSensor.start();

        // Count 1 minute
        Date dStart = new Date();
        while (new Date().getTime() - dStart.getTime() < MINUTE_IN_MILLIS) {
            leapSensor.getData();
        }

        leapSensor.stop();
    }

}
