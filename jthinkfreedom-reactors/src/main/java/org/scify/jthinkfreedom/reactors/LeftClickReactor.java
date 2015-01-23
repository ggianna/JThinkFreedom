/* 
 * Copyright 2014 SciFY.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    @Override
    public String getCanonicalString() {
        return "Left Click";
    }

}
