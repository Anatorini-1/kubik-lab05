package com.anatorini.lab05.GUI;

import com.anatorini.lab05.Main;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    ParamField boardWidthField;
    ParamField boardHeightField;
    ParamField leftTeamPlayerCountField;
    ParamField rightTeamPlayerCountField;
    ParamField ballCountField;
    public ControlPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(200,800);
        add(new JLabel("Board width"),c);
        setBackground(Color.red);
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.BOTH;
        JButton button = new JButton("Start");
        boardHeightField = new ParamField("Board height",() -> Main.boardHeight, i -> Main.boardHeight = i,5,100,1);
        boardWidthField = new ParamField("Board width",() -> Main.boardWidth, i -> Main.boardWidth = i,5,100,1);
        leftTeamPlayerCountField = new ParamField("Left team player count",() -> Main.teamLeftPlayerCount, i -> Main.teamLeftPlayerCount = i,1,10,1);
        rightTeamPlayerCountField = new ParamField("Right team player count",() -> Main.teamRightPlayerCount, i -> Main.teamRightPlayerCount = i,1,10,1);
        ballCountField = new ParamField("Ball count",() -> Main.ballCount, i -> Main.ballCount = i,1,10,1);
       /* add(boardHeightField,c);
        c.gridy++;
        add(boardWidthField,c);
        c.gridy++;
        add(leftTeamPlayerCountField,c);
        c.gridy++;
        add(rightTeamPlayerCountField,c);
        c.gridy++;
        add(ballCountField,c);
        c.gridy++;*/


    }
}
