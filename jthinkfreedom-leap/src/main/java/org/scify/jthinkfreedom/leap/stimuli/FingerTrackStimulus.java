package org.scify.jthinkfreedom.leap.stimuli;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Vector;
import java.awt.Toolkit;
import org.scify.jthinkfreedom.skeleton.sensors.Sensor;
import org.scify.jthinkfreedom.skeleton.stimuli.Coordinates;
import org.scify.jthinkfreedom.skeleton.stimuli.StimulusAdapter;

/**
 * Class that uses the leap motion sensor in order to track a finger and
 * translate it to real 2D coordinates.
 *
 * @author peustr
 */
public class FingerTrackStimulus extends StimulusAdapter implements Coordinates {

    private static final int SCR_WIDTH
            = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCR_HEIGHT
            = Toolkit.getDefaultToolkit().getScreenSize().height;

    // The coordinates
    private int posX, posY;

    private Frame frame;

    public FingerTrackStimulus() {
        super();
        posX = 0;
        posY = 0;
    }

    @Override
    public void onDataReceived() {

        for (Sensor<Frame> sensor : sensors) {
            frame = sensor.getData();
            if (frame != null) {
                Vector fingerPos = frame.fingers().get(0).stabilizedTipPosition();
                Vector boxFingerPos = frame.interactionBox().normalizePoint(fingerPos);
                posX = (int) (SCR_WIDTH * boxFingerPos.getX());
                posY = (int) (SCR_HEIGHT - boxFingerPos.getY() * SCR_HEIGHT);
                if (shouldReact()) {
                    callReactors();
                }
            }
        }

    }

    @Override
    public boolean shouldReact() {
        return posX > 0 && posY > 0;
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

}
