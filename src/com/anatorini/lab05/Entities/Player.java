package com.anatorini.lab05.Entities;

import com.anatorini.lab05.LogicCore.Board;
import com.anatorini.lab05.Main;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameEntity {
    public void action(){
        //TODO account for teammates with better positions
        LinkedList<Ball> balls = board.getBalls();
        Ball closestBall = null;
        for(Ball ball : balls){
            if((ball.getPositionX()-positionX) / ball.velocityX > 0 ) continue;
            if(closestBall == null || (Math.abs(ball.getPositionX()-positionX) < Math.abs(closestBall.getPositionX()-positionX))){
                if(Main.board.isPlayerClosestToBall(this,ball))
                    closestBall = ball;
            }
        }
        if(closestBall != null){
            velocityY = Integer.compare(closestBall.getPositionY(), positionY);
        }
        else{
            velocityY = 0;
        }
    }
    public Player(int x, int y, Board board, int initVy, Color color) {
        super(x,y,0,initVy,board,color);
        System.out.println("Player created");
    }
}
