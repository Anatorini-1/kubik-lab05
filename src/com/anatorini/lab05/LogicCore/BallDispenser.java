package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.Entities.Ball;

import java.awt.*;

public class BallDispenser extends Thread{
    private Board board;
    public BallDispenser(Board board){
        this.board = board;
    }
    @Override
    public void run(){
        while (true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(board.getBalls().size()<5){
                    int pos = board.getFreeRows().get((int)(Math.random()*board.getFreeRows().size()));
                    Ball b = new Ball((int)(board.getSize().getWidth()/2),pos,board,(Math.random() < 0.5 ? 1 : -1), Color.RED);
                    try {
                        board.set(b);
                        b.start();
                    } catch (SpaceAlreadyOccupiedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}
