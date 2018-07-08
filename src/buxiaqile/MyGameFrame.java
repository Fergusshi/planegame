package buxiaqile;




/*
 * game design 01
 * @author Bowen Shi
 * 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MyGameFrame extends Frame {
	
	private Image offScreenImage = null;
	
    Image plane = GameUtil.getImage("images/plane.png");
    Image background = GameUtil.getImage("images/bg.jpg");
    int planeX = 250;
    int planeY = 250;
    Plane planeimg = new Plane(plane,250,250 );
    Shell shell = new Shell();
    Shell[] shells = new Shell[50];
    
    
    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
        
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    } 

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		planeimg.drawItself(g); 
		shell.draw(g);
		
		for(int i = 0; i<shells.length; i++) {
			shells[i].draw(g);
		}

	}
	
	public void launchFrame() {
		this.setTitle("first try of the game");
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(50, 50);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		new paintThread().start();
		addKeyListener(new KeyMonitor());
		
		for(int i = 0; i<50; i++) {
			shells[i] = new Shell();
		}
		
	}

	class paintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.print(e.getKeyCode());
			planeimg.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			planeimg.minusDirection(e);
		} 
		
	}
	
	public static void main(String[] args) {
		MyGameFrame gameFrame = new MyGameFrame();
		gameFrame.launchFrame();
		
	}

}
