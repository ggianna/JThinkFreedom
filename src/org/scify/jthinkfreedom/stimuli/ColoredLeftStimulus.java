package org.scify.jthinkfreedom.stimuli;

/**
 * Class that implements colored object tracking using a webcam sensor and the
 * opencv technology.
 *
 * @author alexisz
 */
public class ColoredLeftStimulus extends ColoredTrackStimulus {

    public ColoredLeftStimulus() {
        super();
    }

    @Override
    protected boolean shouldReact(int piOld, int piNew) {
        return piOld > piNew;
    }

}
