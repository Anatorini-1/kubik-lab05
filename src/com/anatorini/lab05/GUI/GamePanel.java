package com.anatorini.lab05.GUI;

import com.anatorini.lab05.LogicCore.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GamePanel extends JPanel {
    private Board board;
    public GamePanel(Board board){
        this.board = board;
        setBackground(Color.blue);
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2d);
        drawEntities(g2d);
    }

    private void drawGrid(Graphics2D g2d){
        double dx = g2d.getClipBounds().getWidth() / board.getSize().getWidth();
        double dy = g2d.getClipBounds().getHeight() / board.getSize().getHeight();
        for(int i=0;i<board.getSize().getWidth();i++){
            g2d.drawLine((int)(i*dx),0,(int)(i*dx),(int)g2d.getClipBounds().getHeight());
        }
        for(int i=0;i<board.getSize().getHeight();i++){
            g2d.drawLine(0,(int)(i*dy),(int)g2d.getClipBounds().getWidth(),(int)(i*dy));
        }
    }
    private void drawEntities(Graphics2D g2d){
        double dx = g2d.getClipBounds().getWidth() / board.getSize().getWidth();
        double dy = g2d.getClipBounds().getHeight() / board.getSize().getHeight();
        //System.out.println(this.getParent().getWidth());
       // System.out.println(g2d.getClipBounds().getWidth());
        g2d.setColor(Color.RED);
        for(int i=0;i<board.getSize().getWidth();i++){
            for(int j=0;j<board.getSize().getHeight();j++){
                if(board.get(i,j)!=null){
                        g2d.setColor(board.get(i,j).getColor());
                        g2d.fillOval((int)(i*dx),(int)(j*dy),(int)dx,(int)dy);
                }
            }
        }
    }


}
