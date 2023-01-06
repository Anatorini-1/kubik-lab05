package com.anatorini.lab05.GUI;

import com.anatorini.lab05.Entities.Ball;
import com.anatorini.lab05.Entities.GameEntity;
import com.anatorini.lab05.Entities.Player;
import com.anatorini.lab05.LogicCore.Board;
import com.anatorini.lab05.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GamePanel extends JPanel {

    public GamePanel(){
        //setBackground(Color.blue);
       addComponentListener(new ComponentListener() {
           @Override
           public void componentResized(ComponentEvent e) {
               setSize(Math.min(getWidth(),getHeight()),Math.min(getWidth(),getHeight()));
               int parentWidth = getParent().getWidth();
                int parentHeight = getParent().getHeight();
                int width = getWidth();
                int height = getHeight();
                int x = (parentWidth - width) / 2;
                int y = (parentHeight - height) / 2;
                setLocation(x, y);
                //TODO account for uneven sizes !!!
           }

           @Override
           public void componentMoved(ComponentEvent e) {

           }

           @Override
           public void componentShown(ComponentEvent e) {

           }

           @Override
           public void componentHidden(ComponentEvent e) {

           }
       });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Score: "+ Main.scoreLeft+" : "+Main.scoreRight,getWidth()/2,40);
        g2d.translate(0,50);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2d);
        drawEntities(g2d);
        drawScore(g2d);
    }

    private void drawGrid(Graphics2D g2d){
        //if(Main.boardWidth > 30 || Main.boardHeight > 30) return;
        double dx = g2d.getClipBounds().getWidth() / Main.board.getSize().getWidth();
        double dy = g2d.getClipBounds().getHeight() / Main.board.getSize().getHeight();
        for(int i=0;i<Main.board.getSize().getWidth();i++){
            g2d.drawLine((int)(i*dx),0,(int)(i*dx),(int)g2d.getClipBounds().getHeight());
        }
        for(int i=0;i<Main.board.getSize().getHeight();i++){
            g2d.drawLine(0,(int)(i*dy),(int)g2d.getClipBounds().getWidth(),(int)(i*dy));
        }
    }
    private void drawEntities(Graphics2D g2d){
        double dx = g2d.getClipBounds().getWidth() / Main.board.getSize().getWidth();
        double dy = g2d.getClipBounds().getHeight() / Main.board.getSize().getHeight();
        //System.out.println(this.getParent().getWidth());
       // System.out.println(g2d.getClipBounds().getWidth());
        g2d.setColor(Color.RED);
        /*System.out.println("-----------------------");
        board.dumpBoardState();
        System.out.println("-----------------------");*/
        for(int i=0;i<Main.board.getSize().getWidth();i++){
            for(int j=0;j<Main.board.getSize().getHeight();j++){
                GameEntity e = Main.board.get(i,j);
                if(e!=null){
                        g2d.setColor(e.getColor());
                        g2d.fillOval((int)(e.getPositionX()*dx),(int)(e.getPositionY()*dy),(int)dx,(int)dy);
                   // System.out.println("Drawing"+(e instanceof Ball ? "Ball" : "Player")+" at "+e.getPositionX()+" "+e.getPositionY());
                }
            }
        }
    }

    private void drawScore(Graphics2D g2d){
        double dx = g2d.getClipBounds().getWidth() / Main.board.getSize().getWidth();
        double dy = g2d.getClipBounds().getHeight() / Main.board.getSize().getHeight();
        g2d.setColor(Color.BLACK);
        for(int i=0;i<Main.leftGoals.size();i++){
            g2d.drawString(Main.leftGoals.get(i).toString(),(int)(0.5*dx),(int)(i*dy + 0.5*dx));
            g2d.drawString(Main.rightGoals.get(i).toString(),(int)(g2d.getClipBounds().getWidth()-(0.5*dx)),(int)(i*dy + 0.5*dy));

        }
    }


}
