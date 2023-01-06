package com.anatorini.lab05;

import com.anatorini.lab05.GUI.GameFrame;
import com.anatorini.lab05.LogicCore.*;

import java.util.ArrayList;

public class Main {
    public static  BallDispenser bd;
    public static  Board board;
    public static GameFrame gameFrame;
    public static int boardWidth= 21;
    public static int boardHeight = 21;
    public static int ballCount = 5;
    public static int leftTeamPlayerCount = 3;
    public static int rightTeamPlayerCount = 2;
    public static Boolean isRunning = false;
    public static int scoreLeft = 0;
    public static int scoreRight = 0;

    public static ArrayList<Integer> leftGoals = new ArrayList<>();
    public static ArrayList<Integer> rightGoals = new ArrayList<>();

    public static int ballSpeed = 100;
    public static int playerSpeed = 100;

    public static void main(String[] args) throws SpaceAlreadyOccupiedException {
        board = new Board(boardWidth,boardHeight);
        GameFrame gf = new GameFrame();
        bd = new BallDispenser();
        bd.start();
        new Painter(gf).start();
    }
}