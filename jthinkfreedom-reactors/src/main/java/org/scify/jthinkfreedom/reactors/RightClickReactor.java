package org.scify.jthinkfreedom.reactors;

import java.awt.AWTException;
import java.awt.Robot;
import static java.awt.event.InputEvent.BUTTON3_DOWN_MASK;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 * A simple reactor that performs a right mouse click.
 *
 * @author peustr
 */
public class RightClickReactor extends ReactorAdapter {

    @Override
    public void react() {
        try {
            Robot rRightClick = new Robot();
            rRightClick.mousePress(BUTTON3_DOWN_MASK);
            rRightClick.mouseRelease(BUTTON3_DOWN_MASK);
        } catch (AWTException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
