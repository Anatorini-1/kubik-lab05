package com.anatorini.lab05.LogicCore;

import com.anatorini.lab05.Entities.Player;
import com.anatorini.lab05.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GameController {
    private static ArrayList<Player> players;
    public static void init() throws SpaceAlreadyOccupiedException {
       players = new ArrayList<>();
       Main.isRunning = true;
       Main.scoreLeft = 0;
       Main.scoreRight = 0;
       IntStream.range(0,Main.teamLeftPlayerCount).forEach((i) -> {
            players.add(new Player(1,(int)(Main.board.getSize().getHeight()*((float)i/(float)Main.teamLeftPlayerCount)),Main.board,1, Color.GREEN));
        });
       IntStream.range(0,Main.teamRightPlayerCount).forEach((i) -> {
            players.add(new Player((int)(Main.board.getSize().getWidth()-2),(int)(Main.board.getSize().getHeight()*((float)i/(float)Main.teamRightPlayerCount)),Main.board,-1, Color.YELLOW));
        });
       players.forEach((p) -> {
           try {
               Main.board.set(p);
           } catch (SpaceAlreadyOccupiedException e) {
               throw new RuntimeException(e);
           }
       });
       players.forEach(Thread::start);
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
}

