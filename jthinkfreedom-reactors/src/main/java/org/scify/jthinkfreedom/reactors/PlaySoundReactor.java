package org.scify.jthinkfreedom.reactors;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 * A reactor that can be parameterized with the name of a sound file (a .wav for
 * example) that plays said sound.
 *
 * @author peustr
 */
public class PlaySoundReactor extends ReactorAdapter {

    private Clip clip;
    private String soundFile;

    public PlaySoundReactor(String soundFile) {
        this.soundFile = soundFile;
        initComponents();
    }

    private void initComponents() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundFile)));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void react() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.start();
        while (clip.getFramePosition() < clip.getFrameLength()) {
            // nop
        }

    }

}
