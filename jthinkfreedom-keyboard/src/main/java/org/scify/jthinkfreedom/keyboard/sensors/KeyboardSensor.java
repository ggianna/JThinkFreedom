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
public class KeyboardSensor extends SensorAdapter<Boolean> {

    private Provider provider;
    //private Character keyPressed;
    private boolean pressed;

    public KeyboardSensor() {
        super();
        pressed=false;
        HotKeyListener hl = new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotkey) {
                System.out.println("HEREEEEEEEEEEEEEEEE");
                pressed=true;
            }
        };
        provider = Provider.getCurrentProvider(true);
        provider.register(KeyStroke.getKeyStroke(" A"), hl);
    }
    
    

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

   

    @Override
    public String getCanonicalString() {
        return "Keyboard";
    }

    @Override
    public String getDescription() {
        return "Keyboard";
    }
    
    public static void main(String args[]){
        KeyboardSensor ks=new KeyboardSensor();
    }

 
    @Override
    public Boolean getData() {
        return pressed;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
