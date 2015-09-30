/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.sensors;

import org.scify.jthinkfreedom.keyboard.stroke.TimeStampedStroke;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import java.util.Date;
import java.util.PriorityQueue;
import javax.swing.KeyStroke;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;

/**
 *
 * @author xrousakis
 */
public class KeyboardSensor extends SensorAdapter<Character> {

    private Provider provider;
    private PriorityQueue q;
    private final Object lock = new Object();
    private Character pressedKey;
    HotKeyListener hl;

    public KeyboardSensor() {
        super();
        //q = new PriorityQueue();     
        hl = new HotKeyListener() {
            //TimeStampedStroke stroke;
            @Override
            public void onHotKey(HotKey hotkey) {
                String s = hotkey.toString();
                if (s.equalsIgnoreCase("HotKey{SPACE}")) {
                    pressedKey = ' ';
                } else if (s.equalsIgnoreCase("HotKey{RIGHT}")) {
                    pressedKey = 'x';
                } else {
                    pressedKey = s.charAt(7);
                }
                //stroke = new TimeStampedStroke(pressedKey, createTimestmap());
                //q.add(stroke);
            }

        };
    }

    @Override
    public void start() {
        super.start();
        registerKeys();
    }

    
    private long createTimestmap() {
        Date date = new Date();
        return date.getTime();
    }

    private void registerKeys() {
        provider = Provider.getCurrentProvider(false);
        provider.register(KeyStroke.getKeyStroke(" A"), hl);
        provider.register(KeyStroke.getKeyStroke(" SPACE"), hl);
        provider.register(KeyStroke.getKeyStroke(" B"), hl);
        provider.register(KeyStroke.getKeyStroke(" C"), hl);
        provider.register(KeyStroke.getKeyStroke(" LEFT"), hl);
        provider.register(KeyStroke.getKeyStroke(" RIGHT"), hl);
        provider.register(KeyStroke.getKeyStroke(" UP"), hl);
        provider.register(KeyStroke.getKeyStroke(" DOWN"), hl);
    }

    @Override
    public void stop() {
        super.stop();
        provider.stop();
    }

    public HotKeyListener returnListener() {
        return hl;
    }

    @Override
    public String getCanonicalString() {
        return "Keyboard";
    }

    @Override
    public String getDescription() {
        return "Keyboard";
    }

    public void restorePressedKey() {
        pressedKey = null;
    }

    @Override
    public Character getData() {
        return pressedKey;
    }

    /*@Override
    public Object getTimeStampedStroke() {
        return q.poll();
    }*/

}
