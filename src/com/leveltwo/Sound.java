package com.leveltwo;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;

public class Sound extends Level2 {

    //public static void main(String[] args){
   // File Resistor = new File("src/com/leveltwo/sound/resistor.wav");

        //PlaySound(resistor);
    //}

    //public static void Stop(){



    public static void PlaySound(File Sound){
        try{

            Clip clip = AudioSystem.getClip();

            clip.stop();
            clip.open(AudioSystem.getAudioInputStream(Sound));
                clip.start();

           //System.out.println(clip.isRunning());
            //System.out.println("playing");
            //Thread.sleep(clip.getMicrosecondLength()/1000);

        }catch(Exception e){


        }
    }




}

