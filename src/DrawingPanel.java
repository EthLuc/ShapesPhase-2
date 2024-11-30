import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DrawingPanel extends JComponent {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private ShapeType currentShape = ShapeType.LINE;
    private Color currentColor = Color.BLACK;
    private Point startPoint;
    private boolean trails = false;

    public DrawingPanel()
    {
        
        MouseAdapter mouseHandler = new MouseAdapter() 
        {
            
            public void mousePressed(MouseEvent e)
            {
                startPoint = e.getPoint();
                addShape(startPoint, startPoint);
            }

            public void mouseDragged(MouseEvent e)
            {
                if(!trails)
                {
                    shapes.remove(shapes.size() - 1);
                }
                addShape(startPoint, e.getPoint());
                repaint();
                }
            };

            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        public void addShape(Point start, Point end)
        {
            Shape newShape = null;
            switch (currentShape) {
            case LINE:
                newShape = new Line(start, end, currentColor);
            break;
            case BOX:
                newShape = new Box(start, end, currentColor);
            break;
            case OVAL:
                newShape = new Oval(start, end, currentColor);
            break;
            }
            if (newShape != null)
            {
                shapes.add(newShape);
                repaint();
            }
        }

        public void processKey(char key)
        {
            switch(Character.toLowerCase(key))
            {
                case 'e': shapes.clear();
                break;
                case 't': trails = !trails;
                break;
                case 'l': currentShape = ShapeType.LINE;
                break;
                case 'b': currentShape = ShapeType.BOX;
                break;
                case 'o': currentShape = ShapeType.OVAL;
                break;
                case 'c': currentColor = JColorChooser.showDialog(this, "Choose Color", currentColor);
                break;
                case 's': saveDrawing();
                break;
                case 'r': restoreDrawing();
                break;
            }
            repaint();
        }

        private void saveDrawing()
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) 
                {
                    out.writeObject(shapes);
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }



        private void restoreDrawing()
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                shapes = (ArrayList<Shape>) in.readObject();
                repaint();
                } catch (IOException | ClassNotFoundException e) 
                {
                    e.printStackTrace();
                }
            }
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            for(Shape shape : shapes)
            {
                shape.draw(g);
            }
        }
    }

