import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
            }
            repaint();
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

