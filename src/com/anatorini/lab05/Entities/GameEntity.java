package com.anatorini.lab05.Entities;


import com.anatorini.lab05.LogicCore.Board;
import com.anatorini.lab05.LogicCore.SpaceAlreadyOccupiedException;
import com.anatorini.lab05.Main;

import java.awt.*;

public abstract class GameEntity extends Thread{
    protected int  positionX;
    public boolean disposable  =false;
    protected  int positionY;
    public int prevPositionX;
    protected Color color;
    public int prevPositionY;
    protected final Board board;
    public int velocityX;
    protected int velocityY;
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    public Color getColor(){
        return color;
    }
    protected GameEntity(int initPosX,int initPosY,int initVx,int initVy,Board board,Color color){
        this.velocityX = initVx;
        this.velocityY = initVy;
        this.positionX = initPosX;
        this.positionY = initPosY;
        this.prevPositionX = initPosX;
        this.prevPositionY = initPosY;
        this.board = board;
        this.color = color;
    }
    public final void move() throws SpaceAlreadyOccupiedException {
       if( positionX+velocityX<0 || positionX+velocityX>=board.getSize().getWidth() || positionY+velocityY<0 || positionY+velocityY>=board.getSize().getHeight()){
           velocityX = -velocityX;
           velocityY = -velocityY;
       }
       if(board.tryToMove(positionX+velocityX,positionY+velocityY,this)){
           prevPositionX = positionX;
           prevPositionY = positionY;
           positionX += velocityX;
           positionY += velocityY;
       }
       else{
              velocityX = -velocityX;
              velocityY = -velocityY;
       }
        //System.out.println(this instanceof Ball ? "Ball" : "Player "+ "Moved to "+positionX+" "+positionY);

    }
    public abstract void action();
    @Override
    public final void run(){
        while (!disposable){
            try {
                if(Main.isRunning) {
                    move();
                    action();
                }
                else Thread.yield();

                if(this instanceof Ball) sleep(Main.ballSpeed+(int)(Math.random()*Main.ballSpeed));
                else sleep(Main.playerSpeed+(int)(Math.random()*Main.playerSpeed));
            } catch (InterruptedException | SpaceAlreadyOccupiedException e) {
                e.printStackTrace();
            }
        }
    }
}
