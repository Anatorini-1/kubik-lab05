package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.GUI.GameFrame;

public class Painter extends Thread{
    private GameFrame gf;
    public Painter(GameFrame gf){
        this.gf=  gf;
    }
    @Override
    public void run(){
        while (true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gf.revalidate();
            gf.repaint();
        }


    }
}
