package com.anatorini.lab05.GUI;

import com.anatorini.lab05.LogicCore.Board;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Board board;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    public GameFrame(Board board){
        setSize(850,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.board = board;
        this.gamePanel=new GamePanel(board);
        this.controlPanel=new ControlPanel();
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.weighty=1;
        c.fill=GridBagConstraints.BOTH;
        JPanel gamePanelWrapper = new JPanel();
        gamePanelWrapper.setLayout(new GridBagLayout());
        gamePanelWrapper.add(gamePanel,c);
        add(gamePanelWrapper,c);
        c.gridx=1;
        c.gridy=0;
        c.weightx=0.2;
        c.weighty=0;
        c.fill=GridBagConstraints.HORIZONTAL;
        add(controlPanel,c);
        setVisible(true);
    }

}
