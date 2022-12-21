package com.anatorini.lab05.Entities;

import com.anatorini.lab05.LogicCore.Board;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameEntity {
    public void action(){
        //TODO account for teammates with better positions
        LinkedList<Ball> balls = board.getBalls();
        Ball closestBall = null;
        for(Ball ball : balls){
            if((ball.getPositionX()-positionX) / ball.velocityX > 0 ) continue;
            if(closestBall == null || Math.abs(ball.getPositionX()-positionX) < Math.abs(closestBall.getPositionX()-positionX)){
                closestBall = ball;
            }
        }
        if(closestBall != null){
            if(closestBall.getPositionY() > positionY){
                velocityY = 1;
            }
            else if(closestBall.getPositionY() < positionY){
                velocityY = -1;
            }
            else{
                velocityY = 0;
            }
        }
        else{
            velocityY = 0;
        }
    }
    public Player(int x, int y, Board board, int initVy, Color color) {
        super(x,y,0,initVy,board,color);
    }
}
