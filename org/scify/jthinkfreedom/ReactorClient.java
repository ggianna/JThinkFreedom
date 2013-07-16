/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import gr.demokritos.iit.jinsect.structs.Pair;
import gr.demokritos.iit.jinsect.utils;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.scify.jthinkfreedom.reactors.LeftClickReactor;
import org.scify.jthinkfreedom.reactors.RightClickReactor;
import org.scify.jthinkfreedom.reactors.TCPReactorClient;
import org.scify.jthinkfreedom.sensors.ISensor;
import org.scify.jthinkfreedom.sensors.NetworkGraphSensor;
import org.scify.jthinkfreedom.sensors.NetworkImageSensor;
import org.scify.jthinkfreedom.sensors.WebcamSensor;
import org.scify.jthinkfreedom.stimuli.HeadUpStimulus;
import org.scify.jthinkfreedom.stimuli.LeftEyeClosedStimulus;

/**
 *
 * @author ggianna
 */
public class ReactorClient {
    public static void main(String[] saArgs) {
        Hashtable hSwitches = utils.parseCommandLineSwitches(saArgs);
        int iCamNo = Integer.valueOf(utils.getSwitch(hSwitches, "camNo", "0"));
        String sCamURL = utils.getSwitch(hSwitches, "camURL", "");
        String sServerIP = utils.getSwitch(hSwitches, "serverIP", "localhost");
        int iServerPort = Integer.valueOf(utils.getSwitch(hSwitches, "serverPort", "25100"));
        
        ISensor<opencv_core.IplImage> sSensor;
        if (sCamURL.length() > 0)
            try {
                System.err.println("Trying for network capture..." + sCamURL);
                sSensor = new NetworkImageSensor(sCamURL);
                
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return;
            }
        else {
            try {
                sSensor = new WebcamSensor(iCamNo);
                System.err.println("Webcam " + iCamNo);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return;
            }
        }
        System.err.println("Camera:" + sSensor.toString());
        
        
        try {
            sSensor.start();
        } catch (Exception ex) {
            Logger.getLogger(NetworkGraphSensor.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Connect sensor to reactor and stimulus
//        HeadUpStimulus sHeadUp = new HeadUpStimulus();
//        sHeadUp.setTriggerOffset(10);
//        sSensor.addStimulus(sHeadUp);
//        sHeadUp.addSensor(sSensor);
        
        TCPReactorClient trc = new TCPReactorClient();
        // Add TCP reactor server data to TCP reactor client
        trc.add(new Pair<String, Integer>(sServerIP, iServerPort));
        LeftEyeClosedStimulus sLeftClosedStimulus = new LeftEyeClosedStimulus();
        sSensor.addStimulus(sLeftClosedStimulus);
        sLeftClosedStimulus.addSensor(sSensor);
        // Add TCP reactor client as reactor
        sLeftClosedStimulus.addReactor(trc);
        
        final CanvasFrame win = new CanvasFrame("Source");
        Date dStart = new Date();
        
        while (true) {
            opencv_core.IplImage iToRender = sSensor.getData();
            if (iToRender != null)
                win.showImage(iToRender);
//            else
//                System.err.println("Warning: no data returned (null)");
            
            // Break after 30 seconds
            if (new Date().getTime() - dStart.getTime() > 30000)
                break;
        }
        
        sSensor.stop();
        // Finalize
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        win.dispose();
        
    }
        
}
