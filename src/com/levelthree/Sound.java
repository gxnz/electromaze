package com.levelthree;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;

public class Sound extends Level3 {

    public static void PlaySound(File Sound){
        try{

            Clip clip = AudioSystem.getClip();

            clip.stop();
            clip.open(AudioSystem.getAudioInputStream(Sound));
                clip.start();
        }catch(Exception e){
        }
    }
}

