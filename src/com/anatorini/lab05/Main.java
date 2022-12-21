package com.anatorini.lab05;

import com.anatorini.lab05.GUI.GameFrame;
import com.anatorini.lab05.LogicCore.*;
import com.anatorini.lab05.Entities.Player;

import java.awt.*;

public class Main {
    public static  BallDispenser bd;
    public static int boardWidth= 21;
    public static int boardHeight = 21;
    public static int ballCount = 5;
    public static int teamLeftPlayerCount = 3;
    public static int teamRightPlayerCount = 2;
    public static boolean isRunning = false;
    public static int scoreLeft = 0;
    public static int scoreRight = 0;
    public static  Board board;

    public static void main(String[] args) throws SpaceAlreadyOccupiedException {
        board = new Board(boardWidth,boardHeight);
        GameFrame gf = new GameFrame(board);
        bd = new BallDispenser(board);
        bd.start();
        GameController.init();
        new Painter(gf).start();
    }
}