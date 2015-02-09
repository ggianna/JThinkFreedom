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
            if (soundFile != null) {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(soundFile)));
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void react() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.start();
            while (clip.getFramePosition() < clip.getFrameLength()) {
                // nop
            }
        }

    }

    @Override
    public String getCanonicalString() {
        return "Play Sound";
    }

    @Override
    public String getDescription() {
        return "Play a sound";
    }

}
