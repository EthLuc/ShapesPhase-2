import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.awt.Graphics;

public abstract class Shape implements Serializable
{
    protected Point start;
    protected Point end;
    protected Color color;


    /**
     * Default constructor.
     */
    public Shape() 
    {
    
    }

    /**
     * Constructor for Shape object. Accepts data for start, end, and color.
     * 
     * @param start - point value for starting position.
     * @param end - point value for ending position.
     * @param color - color value for color of the object.
     */
    public Shape(Point start, Point end, Color color)
    {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Returns starting position's point value.
     * 
     * @return point value for starting position.
     */
    public Point getStart()
    {
        return start;
    }

    /**
     * Returns ending position's point value.
     * 
     * @return point value for ending position.
     */
    public Point getEnd()
    {
        return end;
    }

    /**
     * Returns color for the color of the object.
     * 
     * @return color value for the color of the object.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets the value for the starting position.
     * 
     * @param start - point value for the starting position,
     */
    public void setStart()
    {
        this.start = start;
    }

    /**
     * Sets the value for the ending position.
     * 
     * @param end - point value for the ending position,
     */
    public void setEnd()
    {
        this.end = end;
    }

    /**
     * Sets the value for the color of the object.
     * 
     * @param color - color value for the color of the object.
     */
    public void setColor()
    {
        this.color = color;
    }

    /*
     * For later when we learn to draw the actual shapes.
     */
    public abstract void draw(Graphics g);
    
}