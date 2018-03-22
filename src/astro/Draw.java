package astro;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Draw extends Canvas implements Runnable{

        private static final long serialVersionUID = 1L;
        int n = 30;
        int a = 0;
        Molec[] gaz = new Molec[n];
        private boolean running;
        public static int WIDTH = 2000;
        public static int HEIGHT = 1000;
        public static String NAME = "טהואכüםûי דאח";
       
        public void start() {
                running = true;
                new Thread(this).start();
        }
       
        public void run() {
             
                init();
               
                while(running) {
                        render();
                        update();
                }
        }
       
        public void init() {
        	for (int i = 0; i < n; i++)
                gaz[i] = new Molec(i);
               
        }
        public void render() {
                BufferStrategy bs = getBufferStrategy();
                if (bs == null) {
                        createBufferStrategy(2);
                        requestFocus();
                        return;
                }
               
                Graphics g = bs.getDrawGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
                for (int i = 0; i < n; i++)
                    gaz[i].draw(g);
                g.dispose();
                bs.show(); 
        }
       
        public void update() {
        
        for (int i = 0; i < n; i++)
        	for (int j = i+1; j < n; j++){
        			if ((gaz[i].x - gaz[j].x)*(gaz[i].x - gaz[j].x)+(gaz[i].y - gaz[j].y)*(gaz[i].y - gaz[j].y) <= gaz[i].getWidth()*gaz[i].getHeight()){
        		System.out.println("colliz");
        		Draw.colliz(gaz[i], gaz[j]);
        		System.out.println(gaz[i].speedx);
        		System.out.println(gaz[i].speedy);
        		System.out.println(gaz[j].speedx);
        		System.out.println(gaz[j].speedy);
        			}
        	}
	for (int i = 0; i < n; i++){
                
               gaz[i].go();
	}
        			
	
        }
 
        public static void colliz(Molec a, Molec b) {
            double alphacos;
            double alphasin;
            double alpha;
        	double v1x = a.speedx;
    		double v1y = a.speedy;
    		double v2x = b.speedx;
    		double v2y = b.speedy;
    		if (a.x - b.x == 0) 
    		alpha = Math.PI/2; //!
    		else
    		alpha = Math.atan(Math.abs((-b.y + a.y)/(-b.x + a.x))); //!
    		 alphacos = Math.cos(alpha);
    		 alphasin = Math.sin(alpha);
    		a.speedy = (float) ((v2x*Math.cos(alpha) + v2y*Math.sin(alpha))*alphasin + (-v1x*Math.sin(alpha) + v1y*Math.cos(alpha))*alphacos);//!
    		a.speedx = (float) ((v2x*Math.cos(alpha) + v2y*Math.sin(alpha))*alphacos - (-v1x*Math.sin(alpha) + v1y*Math.cos(alpha))*alphasin);//!
    		b.speedy = (float) ((v1x*Math.cos(alpha) + v1y*Math.sin(alpha))*alphasin + (-v2x*Math.sin(alpha) + v2y*Math.cos(alpha))*alphacos);//!
    		b.speedx = (float) ((v1x*Math.cos(alpha) + v1y*Math.sin(alpha))*alphacos - (-v2x*Math.sin(alpha) + v2y*Math.cos(alpha))*alphasin);//!
    		
        }
        public static void col(Molec a, Molec b) {
        	float n = a.speedx;
        	float m = a.speedy;
        	a.speedx = b.speedx;
        	a.speedy = b.speedy;
        	b.speedx = m;
        	b.speedy = n;
        }
        public static void coll(Molec a, Molec b) {
        	double v1x = a.speedx;
    		double v1y = a.speedy;
    		double v2x = b.speedx;
    		double v2y = b.speedy;
    		double alpha;
        	alpha = -Math.PI/3;
        	double alphacos;
        	double alphasin;
   		 	alphacos = Math.cos(alpha);
   		 	alphasin = Math.sin(alpha);
   		 	a.speedy = (float) ((v2x*Math.cos(alpha) + v2y*Math.sin(alpha))*alphasin - (-v1x*Math.sin(alpha) + v1y*Math.cos(alpha))*alphacos);//!
   		 	a.speedx = (float) ((v2x*Math.cos(alpha) + v2y*Math.sin(alpha))*alphacos + (v1x*Math.sin(alpha) + v1y*Math.cos(alpha))*alphasin);//!
   		 	b.speedx = (float) ((v1x*Math.cos(alpha) + v1y*Math.sin(alpha))*alphacos + (v2x*Math.sin(alpha) + v2y*Math.cos(alpha))*alphasin);
   		 	b.speedy = (float) ((v1x*Math.cos(alpha) + v1y*Math.sin(alpha))*alphasin - (-v2x*Math.sin(alpha) + v2y*Math.cos(alpha))*alphacos);//!
        	
        	
        }
        public static void main(String[] args) {
                Draw game = new Draw();
                game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                JFrame frame = new JFrame(Draw.NAME);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(game, BorderLayout.CENTER);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
                game.start();
        }
}
	