package org.scify.jthinkfreedom.reactors;

import java.awt.AWTException;
import java.awt.Robot;
import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 * A simple reactor that performs a left mouse click.
 *
 * @author peustr
 */
public class LeftClickReactor extends ReactorAdapter {

    @Override
    public void react() {
        try {
            Robot rLeftClick = new Robot();
            rLeftClick.mousePress(BUTTON1_DOWN_MASK);
            rLeftClick.mouseRelease(BUTTON1_DOWN_MASK);
        } catch (AWTException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
