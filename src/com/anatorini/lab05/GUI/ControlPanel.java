package com.anatorini.lab05.GUI;

import com.anatorini.lab05.LogicCore.GameController;
import com.anatorini.lab05.LogicCore.SpaceAlreadyOccupiedException;
import com.anatorini.lab05.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        //setBackground(Color.red);
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.BOTH;
        JButton button = new JButton("Start");
        boardHeightField = new ParamField("Board height",() -> Main.boardHeight, i -> Main.boardHeight = i,5,() -> 100,1);
        boardWidthField = new ParamField("Board width",() -> Main.boardWidth, i -> Main.boardWidth = i,5,() -> 100,2);
        leftTeamPlayerCountField = new ParamField("Left team player count",() -> Main.leftTeamPlayerCount, i -> Main.leftTeamPlayerCount = i,1,() -> Main.boardHeight,1);
        rightTeamPlayerCountField = new ParamField("Right team player count",() -> Main.rightTeamPlayerCount, i -> Main.rightTeamPlayerCount = i,1,() -> Main.boardHeight,1);
        ballCountField = new ParamField("Ball count",() -> Main.ballCount, i -> Main.ballCount = i,1,() -> Main.boardHeight,1);
       add(boardHeightField,c);
        c.gridy++;
        add(boardWidthField,c);
        c.gridy++;
        add(leftTeamPlayerCountField,c);
        c.gridy++;
        add(rightTeamPlayerCountField,c);
        c.gridy++;
        add(ballCountField,c);
        c.gridy++;
        add(button,c);
        button.addActionListener(e -> {
            try {
                GameController.cleanUp();
                Main.rightTeamPlayerCount = rightTeamPlayerCountField.getValue();
                Main.leftTeamPlayerCount = leftTeamPlayerCountField.getValue();
                Main.boardHeight = boardHeightField.getValue();
                Main.boardWidth = boardWidthField.getValue();
                Main.ballCount = ballCountField.getValue();
                Main.leftGoals = new ArrayList<>();
                Main.rightGoals = new ArrayList<>();
                for(int i=0;i<Main.boardHeight;i++){
                    Main.leftGoals.add(0);
                    Main.rightGoals.add(0);
                }
                GameController.init();
            } catch (SpaceAlreadyOccupiedException ex) {
                throw new RuntimeException(ex);
            }
        });
        c.gridy++;
        add(new SpeedField("Ball Delay",() ->  Main.ballSpeed, i -> Main.ballSpeed = i),c);

        c.gridy++;
        add(new SpeedField("Player Delay",() ->  Main.playerSpeed, i -> Main.playerSpeed = i),c);

    }
}
