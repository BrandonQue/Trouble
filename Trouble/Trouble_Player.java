package Trouble;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Trouble_Player {
	
	private Color color;
	private char initial;
	private String colorFull;
	private ImageIcon playerIcon;
	private int numPieces;
	private int goalCount;
	private int homeCount;
	private static int numPlayers = 0;
	public final int NUM_OF_PIECES = 4;
	
	public Trouble_Player() {
		color = Color.WHITE;
		initial = 'p';
		colorFull = "Player";
		playerIcon = new ImageIcon();
		numPieces = NUM_OF_PIECES;
		goalCount = 0;
		homeCount = NUM_OF_PIECES;
		numPlayers++;
	}
	
	public Trouble_Player(Color c, char i, String cF, ImageIcon iI) {
		color = c;
		initial = i;
		colorFull = cF;
		Image iconImage = iI.getImage().getScaledInstance(90, 90, 0);
		playerIcon = new ImageIcon(iconImage);
		numPieces = NUM_OF_PIECES;
		goalCount = 0;
		homeCount = NUM_OF_PIECES;
		numPlayers++;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Color getLightColor() {
		double f = 255*0.75;
		
		int red = (int)Math.round(Math.min(255, color.getRed()+f));
		int green = (int)Math.round(Math.min(255, color.getGreen()+f));
		int blue = (int)Math.round(Math.min(255, color.getBlue()+f));
		
		int alpha = color.getAlpha();
		
		return new Color(red, green, blue, alpha);
	}
	
	public static Color getLightColor(Color color) {
		double f = 255*0.50;
		
		int red = (int)Math.round(Math.min(255, color.getRed()+f));
		int green = (int)Math.round(Math.min(255, color.getGreen()+f));
		int blue = (int)Math.round(Math.min(255, color.getBlue()+f));
		
		int alpha = color.getAlpha();
		
		return new Color(red, green, blue, alpha);
	}
	
	public char getInitial() {
		return initial;
	}
	
	public String getColorFull() {
		return colorFull;
	}
	
	public ImageIcon getPlayerIcon() {
		return playerIcon;
	}
	
	public int getNumPieces() {
		return numPieces;
	}
	
	public int getGoalCount() {
		return goalCount;
	}
	
	public int getHomeCount() {
		return homeCount;
	}
	
	public static int getNumPlayers() {
		return numPlayers;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void setInitial(char i) {
		initial = i;
	}
	
	public void setColorFull(String cF) {
		colorFull = cF;
	}
	
	public void setPlayerIcon(ImageIcon iI) {
		playerIcon = iI;
	}
	
	public void addGoalCount() {
		goalCount++;
	}
	
	public void subGoalCount() {
		goalCount--;
	}
	
	public void addHomeCount() {
		homeCount++;
	}
	
	public void subHomeCount() {
		homeCount--;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Trouble_Player) {
			Trouble_Player other = (Trouble_Player)o;
			if(this.color.equals(other.color)) {
				if(this.initial == other.initial) {
					if(this.playerIcon.equals(other.playerIcon)) {
						if(this.goalCount == other.goalCount) {
							if(this.homeCount == other.homeCount) {
								return true;								
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public String toString() {
		String s = ("Trouble_Player:"
				+"\nColor = "+color
				+"\nInitial = "+initial
				+"\nNumber of pieces = 4"
				+"\nGoal count = "+goalCount
				+"\nHome count = "+homeCount);
		return s;
	}

}
