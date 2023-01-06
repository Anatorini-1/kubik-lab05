package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.Entities.Ball;
import com.anatorini.lab05.Entities.GameEntity;
import com.anatorini.lab05.Entities.Player;
import com.anatorini.lab05.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
    private final GameEntity[][] board;
    public Board(int sizeX, int sizeY){
        if(sizeX%2!=1) throw new RuntimeException();
        board = new GameEntity[sizeX][sizeY];
    }
    public GameEntity get(int x,int y){
        return board[x][y];
    }
    synchronized public void set(int x,int y, GameEntity val) throws SpaceAlreadyOccupiedException {
        if(board[x][y]!=null && val!=null) throw new SpaceAlreadyOccupiedException("Space "+x+","+y+" is already occupied");
        this.board[x][y] = val;

    }
    synchronized public void set(GameEntity val) throws SpaceAlreadyOccupiedException {
        if(val != null && this.board[val.getPositionX()][val.getPositionY()] !=null) throw new SpaceAlreadyOccupiedException("Space "+val.getPositionX()+","+val.getPositionY()+" is already occupied");
        this.board[val.getPositionX()][val.getPositionY()] = val;
    }
    synchronized public boolean tryToMove(int x,int y, GameEntity val){
        if(board[x][y]!=null && val!=null) return false;
        this.board[x][y] = val;
        if(val != null) {
            this.board[val.getPositionX()][val.getPositionY()] = null;
        }
        return true;
    }

    synchronized public void score(Ball b){
        if(b.getPositionX() == 0){
            Main.scoreLeft++;
            Main.leftGoals.set(b.getPositionY(),Main.leftGoals.get(b.getPositionY())+1);
        }else{
            Main.scoreRight++;
            Main.rightGoals.set(b.getPositionY(),Main.rightGoals.get(b.getPositionY())+1);
        }
    }

    synchronized public ArrayList<Integer> getFreeRows(){
        var toReturn = new ArrayList<Integer>();
        boolean b = true;
        for(int i = 0;i<Main.boardHeight;i++){
            b = true;
            for(int j = 0;j<Main.boardWidth;j++){
                if(board[j][i] != null && board[j][i] instanceof Ball){
                    b = false;
                    break;
                }
            }
            if(b) toReturn.add(i);
        }
        return toReturn;
    }


    synchronized public boolean isPlayerClosestToBall(Player p,Ball b){
       LinkedList<Player> players = getPlayers();
       Player closest = p;
       for(Player player : players){
           if(player.getPositionX() != p.getPositionX()) continue;
           if(Math.sqrt(Math.pow(player.getPositionX()-b.getPositionX(),2)+Math.pow(player.getPositionY()-b.getPositionY(),2)) < Math.sqrt(Math.pow(closest.getPositionX()-b.getPositionX(),2)+Math.pow(closest.getPositionY()-b.getPositionY(),2))){
               closest = player;
           }
       }
       return closest == p;
    }

    synchronized public LinkedList<Ball> getBalls(){
        LinkedList<Ball> balls = new LinkedList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] instanceof Ball){
                    balls.add((Ball) board[i][j]);
                }
            }
        }
        return balls;
    }

    synchronized public LinkedList<Player> getPlayers(){
        LinkedList<Player> players = new LinkedList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] != null && board[i][j] instanceof Player){
                    players.add((Player) board[i][j]);
                }
            }
        }
        return players;
    }
    public Dimension getSize(){
        return new Dimension(board.length,board[0].length);
    }

    public void dumpBoardState(){
        for(int i=0;i< getSize().getHeight();i++){
            for(int j=0;j<getSize().getWidth();j++){
                if(board[i][j] instanceof Ball){
                    System.out.print("B");
                }else if(board[i][j] instanceof Player){
                    System.out.print("P");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public synchronized void holdDispenser(){
        if(Main.bd.getState().equals(Thread.State.RUNNABLE)){
            System.out.println("Le hold");
            try {
                Main.bd.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public synchronized void releaseDispenser(){
        if(Main.bd.getState().equals(Thread.State.WAITING)){
            System.out.println("Le release");
            Main.bd.notify();
        }
    }

}
