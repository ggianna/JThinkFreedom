package org.scify.jthinkfreedom.reactors;

import java.awt.AWTException;
import java.awt.Robot;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;
import org.scify.jthinkfreedom.skeleton.stimuli.Coordinates;
import org.scify.jthinkfreedom.skeleton.stimuli.Stimulus;

/**
 * A reactor that moves the mouse cursor to the specified destination. The
 * stimulus associated with that reactor must implement the ICoordinates
 * interface (that means getX and getY public methods will be provided).
 *
 * @author peustr
 * @param <T>
 */
public class MouseMoveReactor<T extends Stimulus & Coordinates> extends ReactorAdapter {

    public T stimulus;

    public MouseMoveReactor(T stimulus) {
        this.stimulus = stimulus;
    }

    @Override
    public void react() {
        try {
            Robot rMouseMove = new Robot();
            rMouseMove.mouseMove(stimulus.getX(), stimulus.getY());
        } catch (AWTException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
