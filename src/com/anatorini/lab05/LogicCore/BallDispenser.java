package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.Entities.Ball;
import com.anatorini.lab05.Main;

import java.awt.*;
import java.util.ArrayList;

public class BallDispenser extends Thread{
    public BallDispenser(){
    }
    @Override
    public void run(){
        while (true){
            try {
                sleep(Main.ballSpeed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!Main.isRunning) continue;
            if(Main.board.getBalls().size()< Main.ballCount){
                ArrayList<Integer> rows = Main.board.getFreeRows();
                //Yes, "rows"" is unnecessary, but it doesn't work without it (for some reason)
                    int posY = rows.get((int)(Math.random()*rows.size()-1));
                    Ball b = new Ball((int)(Main.board.getSize().getWidth()/2),posY,Main.board,(Math.random() < 0.5 ? 1 : -1), Color.RED);
                    try {
                        Main.board.set(b);
                        b.start();
                    } catch (SpaceAlreadyOccupiedException e) {
                        throw new RuntimeException(e);
                    }
            }
            else Thread.yield();
        }
    }
}
