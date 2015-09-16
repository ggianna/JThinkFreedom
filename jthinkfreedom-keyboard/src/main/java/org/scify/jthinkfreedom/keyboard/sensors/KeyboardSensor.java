/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.sensors;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import javax.swing.KeyStroke;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;

/**
 *
 * @author xrousakis
 */
public class KeyboardSensor extends SensorAdapter<Character> {
   //private Thread thread;
    //private boolean stop;
    private Provider provider;
    private final Object lock = new Object();
    private Character pressedKey;
    HotKeyListener hl;

    public KeyboardSensor() {
        super();
        //stop =false;
        
        hl = new HotKeyListener() {
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
            }
        };
    }

    @Override
    public void start() {
        super.start();
        registerKeys();
    }
    
    
     /*this thread will check if a sequence came and it will */
     /*public void run() {
       thread = new Thread(new Runnable() {

           @Override
           public void run() {
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       }); 
    }*/
    
    

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

    /*stop the provider from receiving the key events*/
    /*private void stopProvider() {
       
        provider.reset();
        provider.stop();
        
    }*/

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

}
