/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.stroke;

/**
 *
 * @author xrousakis
 */
public class TimeStampedStroke {

    private Character c;
    private long timestamp;

    public TimeStampedStroke(Character c, long timestamp) {
        this.c = c;
        this.timestamp = timestamp;
    }

    /*public TimeStampedStroke() {
        
    }*/
    
    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
