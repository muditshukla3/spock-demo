package com.ms;

public class Polygon {

    private int sides;
    public Renderer renderer;

    public Polygon(int sides) {
        this(sides, null);
    }

    public Polygon(int sides, Renderer renderer) {
        if(sides <= 2){
            throw new TooFewSidesException("You can't have fewer than 3 sides for a polygon", sides);
        }
        this.sides = sides;
        this.renderer = renderer;
    }

    public int getSides() {
        return sides;
    }

    public void draw() {
        for (int i = 0; i < sides; i++) {
            renderer.drawLine();
        }
    }
}
