/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.examples;

import java.awt.PointerInfo;

/**
 *
 * @author eustratiadis-hua
 */
public class MouseLeftStimulus extends MouseStimulus {
    
    public MouseLeftStimulus() {
        super();
    }
    
    @Override
    protected boolean shouldReact(PointerInfo piOld, 
        PointerInfo piNew) {
        return piOld.getLocation().getX() >
                piNew.getLocation().getX();
    }
    
}