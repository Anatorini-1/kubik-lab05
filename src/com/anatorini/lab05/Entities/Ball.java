package com.anatorini.lab05.Entities;

import com.anatorini.lab05.LogicCore.BallDispenser;
import com.anatorini.lab05.LogicCore.Board;
import com.anatorini.lab05.LogicCore.SpaceAlreadyOccupiedException;

import java.awt.*;

public class Ball extends GameEntity{
    public void action(){
        if(positionX == board.getSize().getWidth()-1 || positionX == 0){
            try {
                board.set(this.positionX,this.positionY,null);
                this.disposable=true;

            } catch (SpaceAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Ball(int x, int y, Board board, int initVx, Color color) {
        super(x,y,initVx,0,board,color);
    }
}
