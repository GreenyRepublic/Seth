package standard;

/**
 * Created by Ben Clark on 15/09/2015.
 * Class defining the properties held by a single card in a game of Sets, and functions used to access those attributes.
 */

import java.util.*;
public class Card

{
    private int number;
    private String shape;
    private String colour;
    private String shade;

    private ArrayList<String> ShapeList = new ArrayList<String>();
    private ArrayList<String> ColourList = new ArrayList<String>();
    private ArrayList<String> ShadeList = new ArrayList<String>();

    public Card (int number, String shape, String colour, String shade)
    {
        this.number = number;
        this.shape = shape;
        this.colour = colour;
        this.shade = shade;


        ShapeList.add("Diamond");
        ShapeList.add("Capsule");
        ShapeList.add("Squiggle");

        ColourList.add("Red");
        ColourList.add("Green");
        ColourList.add("Purple");

        ShadeList.add("Filled");
        ShadeList.add("Striped");
        ShadeList.add("Empty");
    }

    //These functions set the values of a card's attribute based on the input integer.
    public void shapeGen(int seed)
    {
        shape = ShapeList.get(seed);
    }

    public void colourGen(int seed)
    {
        colour = ColourList.get(seed);
    }

    public void shadeGen(int seed)
    {
        shade = ShadeList.get(seed);
    }

    //These functions return the requested card attribute.
    public int getNum()
    {
        return number;
    }

    public String getShape()
    {
        return shape;
    }

    public String getColour()
    {
        return colour;
    }

    public String getShade()
    {
        return shade;
    }
}