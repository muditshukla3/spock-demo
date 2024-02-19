package com.ms;

public class Renderer {

    private final Palette palette;

    public Renderer(Palette palette) {
        this.palette = palette;
    }
    public Color getForegroundColour() {
        return palette.getPrimaryColour();
    }
    public void drawLine() {
    }
}
