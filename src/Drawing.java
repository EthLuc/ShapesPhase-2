//Ethan Lucas
//9-20-2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Drawing extends JFrame {

    private DrawingPanel drawingPanel;

    public Drawing()
    {
        
        setTitle("Simple Drawing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter()
        {

            public void keyPressed(KeyEvent e)
            {
                
                drawingPanel.processKey(e.getKeyChar()); 
            }
        });
    
        JLabel instructions = new JLabel("(E)rase (T)rails (L)ine (B)ox (O)val (C)olor (S)ave (R)estore");
        add(instructions, BorderLayout.SOUTH);
    }



    public static void main(String[] args) 
    {
            Drawing drawing = new Drawing();
            drawing.setVisible(true);
    }
}
