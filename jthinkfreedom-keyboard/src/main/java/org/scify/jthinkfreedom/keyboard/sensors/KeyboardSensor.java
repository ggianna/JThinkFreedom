/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.keyboard.sensors;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.MediaKey;
import com.tulskiy.keymaster.common.Provider;
import javax.swing.KeyStroke;
import org.scify.jthinkfreedom.skeleton.sensors.SensorAdapter;

/**
 *
 * @author xrousakis
 */
public class KeyboardSensor extends SensorAdapter<Character> {
    
    private Provider provider;
    private final Object lock = new Object();
    private Character pressedKey;
    HotKeyListener hl;

    public KeyboardSensor() {
        super();
        hl = new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotkey) {
                 
                String s = hotkey.toString();
                System.out.println("on hot key before: " + s);
                pressedKey = s.charAt(7);
                System.out.println("on hot key with key: " + pressedKey);
            }
        };
    }

    @Override
    public void start() {
        super.start();
        provider = Provider.getCurrentProvider(false);
        provider.register(KeyStroke.getKeyStroke(" A"), hl);
        provider.register(KeyStroke.getKeyStroke(" B"), hl);
        provider.register(KeyStroke.getKeyStroke(" C"), hl);
    }

    @Override
    public void stop() {
        super.stop();
        provider.stop();
    }

    @Override
    public String getCanonicalString() {
        return "Keyboard";
    }

    @Override
    public String getDescription() {
        return "Keyboard";
    }

    public static void main(String args[]) throws InterruptedException {
        KeyboardSensor ks = new KeyboardSensor();
        ks.start();
        Thread.sleep(5000);
        ks.stop();
    }
    
    public void restorePressedKey(){
        pressedKey=null;
    }
    
    
    @Override
    public Character getData() {
       // System.out.println("get data");
        //Character valueToReturn =  pressedKey ==null? null : new Character(pressedKey);
      //  pressedKey = null;
        
       // if (valueToReturn!=null)
      //  {
      //      System.out.println(valueToReturn);
     //  }
        return  pressedKey;
      //  return null;
    }

}
