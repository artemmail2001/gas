package astro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Molec {
	final Random random = new Random();
	double x;
	double y;
	double x1;
	double y1;
	float speedx;
	float speedy;
	public Molec(int t) {
		this.x = t * 100;
		this.y = random.nextInt(100) * t;
		this.x1 = 50;
		this.y1 = 50;
		this.speedx = 1.3f;
		this.speedy = 2.1f;
	}
	public int getWidth() { 
		return (int)x1;
	}

	public int getHeight() { 
		return (int)y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, (int)x1, (int)y1);
	}
	public void go() {
		this.x = this.x + this.speedx;
		this.y = this.y + this.speedy;
		if (this.x>= 1800 || this.x <= 0) {
			this.speedx *= -1;
		}
		if (this.y >= 800 || this.y <= 0) {
			this.speedy *= -1;
		}
	}
	
	}
