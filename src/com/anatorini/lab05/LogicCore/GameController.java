package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.Entities.Player;
import com.anatorini.lab05.GUI.GameFrame;
import com.anatorini.lab05.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GameController {
    private static ArrayList<Player> players;
    public static void init() throws SpaceAlreadyOccupiedException {
        Main.board = new Board(Main.boardWidth,Main.boardHeight);
       players = new ArrayList<>();
       Main.scoreLeft = 0;
       Main.scoreRight = 0;
       IntStream.range(0,Main.leftTeamPlayerCount).forEach((i) -> {
            players.add(new Player(1,(int)(Main.board.getSize().getHeight()*((float)i/(float)Main.leftTeamPlayerCount)),Main.board,1, Color.GREEN));
        });
       IntStream.range(0,Main.rightTeamPlayerCount).forEach((i) -> {
            players.add(new Player((int)(Main.board.getSize().getWidth()-2),(int)(Main.board.getSize().getHeight()*((float)i/(float)Main.rightTeamPlayerCount)),Main.board,-1, Color.YELLOW));
        });
       players.forEach((p) -> {
           try {
               Main.board.set(p);
               System.out.println(p);
           } catch (SpaceAlreadyOccupiedException e) {
               throw new RuntimeException(e);
           }
       });
       players.forEach(Thread::start);
        System.out.println("Started players"+players.size());
       Main.isRunning = true;
    }

    public static void end() throws SpaceAlreadyOccupiedException {
        Main.isRunning = false;
        players.forEach((p) -> {
            try {
                Main.board.set(p.getPositionX(), p.getPositionY(),null);
                p.disposable = true;
            } catch (SpaceAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void cleanUp() {
        if(players == null) return;
        players.forEach((p) -> {
            try {
                Main.board.set(p.getPositionX(), p.getPositionY(),null);
                p.disposable = true;
            } catch (SpaceAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}

