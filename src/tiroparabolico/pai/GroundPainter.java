package tiroparabolico.pai;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GroundPainter {
	private Color color1 = new Color(71, 4, 4); 
	private Color color2 = new Color(24,139,15);   
	private int width;
	private int height;
	
	public GroundPainter(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	protected void paint(Graphics g){ 
	    Graphics2D g2 = (Graphics2D) g.create();
	     
	    Rectangle clip = g2.getClipBounds(); 
        g2.setPaint(new GradientPaint( 0, 0.0f, getColor2(), 0 ,getHeight() , getColor1())); 
       // g2.fillRect(0,0, getWidth(), getHeight());
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
	}
	private Color getColor1() {
		return color1;
	}
	private void setColor1(Color color1) {
		this.color1 = color1;
	}
	private Color getColor2() {
		return color2;
	}
	private void setColor2(Color color2) {
		this.color2 = color2;
	}
	private int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	private int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
