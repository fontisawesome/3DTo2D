/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dto2d;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author HECAI
 */
public class Main extends Canvas {

    static int cx = 0;
    static int cy = 0;
    static int cz = 0;
    static double camerax = 0;
    static int cameray = 0;
    static int cameraz = 0;
    static int screenz = 100;
    ArrayList<int[]> lines = new ArrayList<int[]>();
    static int[] xvals = {50, 50, -50, -50};
    static int[] yvals = {-50, 50, 50, -50};
    static int[] zvals = {100, 100, 100, 100};

    /**
     * @param args the command line arguments
     */
    public static double[] project(double x, double y, double z) {
        x -= cx;
        y -= cy;
        z -= cz;
        double sinx = Math.sin(camerax * 2 * Math.PI / 360);
        double cosx = Math.cos(camerax * 2 * Math.PI / 360);
        double dx = cosx * x;
        double dy = y;
        double dz = cosx * z + sinx * x;
        double twoD[] = {screenz * dx / dz + 100, screenz * dy / dz + 100};
        return twoD;

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Main();
        canvas.setSize(384, 400);
        canvas.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'a'){
                    camerax++;
                    canvas.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (e.getKeyChar() == 'd'){
                    camerax--;
                    canvas.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        });
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        
        for (int i = 0; i < 4; i++) {
            double point1x = xvals[i];
            double point1y = yvals[i];
            double point1z = zvals[i];
            int point2x = xvals[(i + 1) % 4];
            int point2y = yvals[(i + 1) % 4];
            int point2z = zvals[(i + 1) % 4];
            int tpoint1x = (int) project(point1x, point1y, point1z)[0];
            int tpoint1y = (int) project(point1x, point1y, point1z)[1];
            int tpoint2x = (int) project(point2x, point2y, point2z)[0];
            int tpoint2y = (int) project(point2x, point2y, point2z)[1];
            g.drawLine(tpoint1x, tpoint1y, tpoint2x, tpoint2y);
        }
    }
}
