package Trouble;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TroubleGUI extends JFrame{
	
	private JPanel mainJP;
	private TroublePanel TPanel;
	private PlayerLabel PLabel;
	private final int ROWS = 9;
	private final int COLS = 9;
	private Trouble_Player currPlayer;
	private Trouble_Player[] playerArr;
	
	public TroubleGUI() {
		
		String[] players = {"2", "3", "4"};
		int n = JOptionPane.showOptionDialog(null, "How many players?", "Number of Players?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, players, players[0]);
		
		if(n < 0) {
			System.exit(0);
		}
		
		mainJP = new JPanel(new BorderLayout());
		playerArr = new Trouble_Player[n+2];
		for(int i = 0; i < playerArr.length; i++) {
			switch(i) {
			case 0:
				playerArr[i] = new Trouble_Player(Color.BLUE, 'B', "Blue", new ImageIcon(TroubleGUI.class.getResource("/Images/Blue Piece.png")));
				break;
			case 1:
				playerArr[i] = new Trouble_Player(Color.YELLOW, 'Y',"Yellow", new ImageIcon(TroubleGUI.class.getResource("/Images/Yellow Piece.png")));
				break;
			case 2:
				playerArr[i] = new Trouble_Player(Color.RED, 'R',"Red", new ImageIcon(TroubleGUI.class.getResource("/Images/Red Piece.png")));
				break;
			case 3:
				playerArr[i] = new Trouble_Player(Color.GREEN, 'G',"Green", new ImageIcon(TroubleGUI.class.getResource("/Images/Green Piece.png")));
				break;
			}
		}
		currPlayer = playerArr[0];
		TPanel = new TroublePanel();
		PLabel = new PlayerLabel();
		
		mainJP.add(TPanel, BorderLayout.CENTER);
		mainJP.add(PLabel, BorderLayout.SOUTH);
		
		add(mainJP);
		setVisible(true);
		setSize(900, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private class PlayerLabel extends JLabel{
		
		public String currP;
		public Font bigFont;
		
		public PlayerLabel() {
			bigFont = new Font(Font.SANS_SERIF, Font.PLAIN, 70);
			currP = currPlayer.getColorFull();
			
			setText(currP+"'s turn");
			setFont(bigFont);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
			setOpaque(true);
			setBackground(currPlayer.getColor());
			setForeground(Color.WHITE);
			
			setSize(900, 200);
		}
		
		public void refresh() {
			currP = currPlayer.getColorFull();
			setText(currP+"'s turn");
			setBackground(currPlayer.getColor());
		}
		
		public void winner() {
			currP = currPlayer.getColorFull();
			setText(currP.toUpperCase()+" WINS!!!");
		}
		
	}
	
	private class TroublePanel extends JPanel implements ActionListener{
		
		private char[][] cBoard;
		private JButton[][] board;
		private int dice;
		private int[][][] spaces;
		private ImageIcon empty;
		
		public TroublePanel() {
			spaces = ThreeDInit();
			setLayout(new GridLayout(ROWS, COLS));
			cBoard = new char[ROWS][COLS];
			board = new JButton[ROWS][COLS];
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					board[i][j] = new JButton();
					board[i][j].setBackground(Color.WHITE);
					board[i][j].setEnabled(false);
					board[i][j].addActionListener(this);
					add(board[i][j]);
				}
			}
			dice = 0;
			empty = new ImageIcon(TroubleGUI.class.getResource("/Images/Empty Piece.png"));
			Image emptyImage = empty.getImage().getScaledInstance(90, 90, 0);
			empty = new ImageIcon(emptyImage);
			displayBoard();
			setVisible(true);
		}
		
		public void displayBoard() {
			Font bigFont = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
			
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					board[i][j].setBackground(Color.BLACK);
				}
			}
			
			for(int i = 0; i < playerArr.length; i++) {
				char c = playerArr[i].getInitial();
				switch(c) {
				case 'B':
					for(int j = 0; j < 4; j++) {
						cBoard[0][j] = playerArr[i].getInitial();
					}
					for(int j = 24; j < spaces[i].length; j++) {
						board[spaces[i][j][0]][spaces[i][j][1]].setBackground(playerArr[i].getLightColor());
						board[spaces[i][j][0]][spaces[i][j][1]].setIcon(empty);
						board[spaces[i][j][0]][spaces[i][j][1]].setDisabledIcon(empty);
					}
					break;
				case 'Y':
					for(int j = 0; j < 4; j++) {
						cBoard[8][8-j] = playerArr[i].getInitial();
					}
					for(int j = 24; j < spaces[i].length; j++) {
						board[spaces[i][j][0]][spaces[i][j][1]].setBackground(playerArr[i].getLightColor());
						board[spaces[i][j][0]][spaces[i][j][1]].setIcon(empty);
						board[spaces[i][j][0]][spaces[i][j][1]].setDisabledIcon(empty);
					}
					break;
				case 'R':
					for(int j = 0; j < 4; j++) {
						cBoard[j][8] = playerArr[i].getInitial();
					}
					for(int j = 24; j < spaces[i].length; j++) {
						board[spaces[i][j][0]][spaces[i][j][1]].setBackground(playerArr[i].getLightColor());
						board[spaces[i][j][0]][spaces[i][j][1]].setIcon(empty);
						board[spaces[i][j][0]][spaces[i][j][1]].setDisabledIcon(empty);
					}
					break;
				case 'G':
					for(int j = 0; j < 4; j++) {
						cBoard[8-j][0] = playerArr[i].getInitial();
					}
					for(int j = 24; j < spaces[i].length; j++) {
						board[spaces[i][j][0]][spaces[i][j][1]].setBackground(playerArr[i].getLightColor());
						board[spaces[i][j][0]][spaces[i][j][1]].setIcon(empty);
						board[spaces[i][j][0]][spaces[i][j][1]].setDisabledIcon(empty);
					}
					break;
				}
			}
			
			for(int i = 1; i <= 6; i++) {
				board[1][i].setBackground(Trouble_Player.getLightColor(Color.BLUE));
				board[1][i].setIcon(empty);
				board[1][i].setDisabledIcon(empty);
				board[i][7].setBackground(Trouble_Player.getLightColor(Color.RED));
				board[i][7].setIcon(empty);
				board[i][7].setDisabledIcon(empty);
				board[7][8-i].setBackground(Trouble_Player.getLightColor(Color.YELLOW));
				board[7][8-i].setIcon(empty);
				board[7][8-i].setDisabledIcon(empty);
				board[8-i][1].setBackground(Trouble_Player.getLightColor(Color.GREEN));
				board[8-i][1].setIcon(empty);
				board[8-i][1].setDisabledIcon(empty);
			}
			
			for(int i = 0; i < cBoard.length; i++) {
				for(int j = 0; j < cBoard[i].length; j++) {
					if(i == 4 && j == 4) {
						board[i][j].setBackground(Color.WHITE);
						board[i][j].setEnabled(true);
						board[i][j].setFont(bigFont);
						board[i][j].setText("0");
					}
					for(int p = 0; p < playerArr.length; p++) {
						if(cBoard[i][j] == playerArr[p].getInitial()) {
							board[i][j].setBackground(playerArr[p].getLightColor());
							board[i][j].setIcon(playerArr[p].getPlayerIcon());
							board[i][j].setDisabledIcon(playerArr[p].getPlayerIcon());
						}
					}
				}
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnPressed = (JButton) e.getSource();
			
			int btnRow = -1;
			int btnCol = -1;
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if(board[i][j].equals(btnPressed)) {
						btnRow = i;
						btnCol = j;
					}
				}
			}
			
			if(btnRow == 4 && btnCol == 4) {
				dice = diceRoll();
				board[4][4].setText(String.valueOf(dice));
				board[4][4].setEnabled(false);
				choosePiece(dice);
			}else {
				movePiece(btnRow, btnCol);
			}
			
			PLabel.refresh();
			
			

		}
		
		public int diceRoll() {
			int n = (int)(Math.random()*6) + 1;
			return n;
		}
	
		public void takeTurn() {
			Trouble_Player temp = currPlayer;
			for(int i = 0; i < playerArr.length; i++) {
				if(playerArr[i].equals(temp)) {
					if(i == playerArr.length - 1) {
						currPlayer = playerArr[0];
					}else {
						currPlayer = playerArr[i+1];
					}
				}
			}
		}
		
		public void choosePiece(int dice) {
			int ind = -1;
			for(int i = 0; i < playerArr.length; i++) {
				if(playerArr[i].equals(currPlayer)) {
					ind = i;
				}
			}
			
			if(dice == 6) {
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board[i].length; j++) {
						if(i == spaces[ind][0][0] && j == spaces[ind][0][1] && cBoard[i][j] != currPlayer.getInitial()) {	//If first space is empty, enable home pieces.
							for(int k = 0; k < board.length; k++) {
								for(int l = 0; l < board[k].length; l++) {
									if((k == 0 || l == 0 || k == 8 || l == 8) && cBoard[k][l] == currPlayer.getInitial()) {
										board[k][l].setEnabled(true);
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < spaces[ind].length; i++) {
					if(i + 6 < spaces[ind].length && cBoard[spaces[ind][i][0]][spaces[ind][i][1]] == currPlayer.getInitial() && cBoard[spaces[ind][i+6][0]][spaces[ind][i+6][1]] != currPlayer.getInitial()) {	//If six spaces in front of piece is not after the last space or another piece of the same player enable piece.
						board[spaces[ind][i][0]][spaces[ind][i][1]].setEnabled(true);
					}
				}
			}else {
				for(int i = 0; i < spaces[ind].length; i++) {
					if(i+dice < spaces[ind].length && cBoard[spaces[ind][i][0]][spaces[ind][i][1]] == currPlayer.getInitial() && cBoard[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]] != currPlayer.getInitial()) {	//If dice spaces in front of piece is not after the last space or another piece of the same player enable piece.
						board[spaces[ind][i][0]][spaces[ind][i][1]].setEnabled(true);
					}
				}
			}
			if(!checkEnabled()) {
				if(dice != 6) {
					takeTurn();
				}
				board[4][4].setEnabled(true);
			}
		}
		
		public void movePiece(int r, int c) {
			int ind = -1;
			for(int i = 0; i < playerArr.length; i++) {
				if(playerArr[i].equals(currPlayer)) {
					ind = i;
				}
			}
				
			
			if(dice == 6) {
				for(int i = 0; i < spaces[ind].length; i++) {
					if(spaces[ind][i][0] == r && spaces[ind][i][1] == c) {
						for(int j = 0; j < playerArr.length; j++) {
							if(cBoard[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]] == playerArr[j].getInitial()) {
								eaten(playerArr[j].getInitial());
							}
						}
						if(i > 23) {
							currPlayer.subGoalCount();
						}
						if(i+dice > 23) {
							currPlayer.addGoalCount();
						}
						cBoard[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]] = currPlayer.getInitial();
						board[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]].setIcon(currPlayer.getPlayerIcon());
						board[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]].setDisabledIcon(currPlayer.getPlayerIcon());
						cBoard[r][c] = '\u0000';
						board[r][c].setDisabledIcon(empty);
					}
				}
				if(r == 0 || c == 0 || r == 8 || c == 8){
					for(int j = 0; j < playerArr.length; j++) {
						if(cBoard[spaces[ind][0][0]][spaces[ind][0][1]] == playerArr[j].getInitial()) {
							eaten(playerArr[j].getInitial());
						}
					}
					cBoard[spaces[ind][0][0]][spaces[ind][0][1]] = currPlayer.getInitial();
					board[spaces[ind][0][0]][spaces[ind][0][1]].setIcon(currPlayer.getPlayerIcon());
					board[spaces[ind][0][0]][spaces[ind][0][1]].setDisabledIcon(currPlayer.getPlayerIcon());
					cBoard[r][c] = '\u0000';
					board[r][c].setDisabledIcon(empty);
					currPlayer.subHomeCount();
					if(currPlayer.getGoalCount() == 4) {
						PLabel.winner();
						JOptionPane.showMessageDialog(getComponent(0), currPlayer.getColorFull()+" is the Winner!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					takeTurn();
				}
			}else {
				for(int i = 0; i < spaces[ind].length; i++) {
					if(spaces[ind][i][0] == r && spaces[ind][i][1] == c) {
						for(int j = 0; j < playerArr.length; j++) {
							if(cBoard[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]] == playerArr[j].getInitial()) {
								eaten(playerArr[j].getInitial());
							}
						}
						if(i > 23) {
							currPlayer.subGoalCount();
						}
						if(i+dice > 23) {
							currPlayer.addGoalCount();
						}
						cBoard[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]] = currPlayer.getInitial();
						board[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]].setIcon(currPlayer.getPlayerIcon());
						board[spaces[ind][i+dice][0]][spaces[ind][i+dice][1]].setDisabledIcon(currPlayer.getPlayerIcon());
						cBoard[r][c] = '\u0000';
						board[r][c].setDisabledIcon(empty);
					}
				}
				if(currPlayer.getGoalCount() == 4) {
					PLabel.winner();
					JOptionPane.showMessageDialog(getComponent(0), currPlayer.getColorFull()+" is the Winner!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				takeTurn();
			}
			disableAll();
			board[4][4].setEnabled(true);
		}
		
		public int[][][] ThreeDInit(){
			int[][][] n = {
				{
					{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {7, 1}, {6, 1}, {5, 1}, {4, 1}, {3, 1}, {2, 1}, {2, 2}, {2, 3}, {3, 3}, {4, 3}
				},
				{
					{7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {7, 1}, {6, 1}, {5, 1}, {4, 1}, {3, 1}, {2, 1}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {6, 6}, {6, 5}, {5, 5}, {4, 5}
				},
				{
					{1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {7, 1}, {6, 1}, {5, 1}, {4, 1}, {3, 1}, {2, 1}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 6}, {3, 6}, {3, 5}, {3, 4}
				},
				{
					{7, 1}, {6, 1}, {5, 1}, {4, 1}, {3, 1}, {2, 1}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {6, 2}, {5, 2}, {5, 3}, {5, 4}
				}
			};
			return n;
			
		}
		
		public void disableAll() {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					board[i][j].setEnabled(false);
				}
			}
		}
		
		public boolean checkEnabled() {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if(board[i][j].isEnabled()) {
						return true;
					}
				}
			}
			return false;
		}
		
		public void eaten(char c) {
			switch(c) {
			case 'B':
				for(int i = 0; i < 4; i++) {
					if(cBoard[0][i] != c) {
						cBoard[0][i] = c;
						board[0][i].setIcon(playerArr[0].getPlayerIcon());
						board[0][i].setDisabledIcon(playerArr[0].getPlayerIcon());
						playerArr[0].addHomeCount();
						break;
					}
				}
				break;
			case 'Y':
				for(int i = 0; i < 4; i++) {
					if(cBoard[8][8-i] != c) {
						cBoard[8][8-i] = c;
						board[8][8-i].setIcon(playerArr[1].getPlayerIcon());
						board[8][8-i].setDisabledIcon(playerArr[1].getPlayerIcon());
						playerArr[1].addHomeCount();
						break;
					}
				}
				break;
			case 'R':
				for(int i = 0; i < 4; i++) {
					if(cBoard[i][8] != c) {
						cBoard[i][8] = c;
						board[i][8].setIcon(playerArr[2].getPlayerIcon());
						board[i][8].setDisabledIcon(playerArr[2].getPlayerIcon());
						playerArr[2].addHomeCount();
						break;
					}
				}
				break;
			case 'G':
				for(int i = 0; i < 4; i++) {
					if(cBoard[8-i][0] != c) {
						cBoard[8-i][0] = c;
						board[8-i][0].setIcon(playerArr[3].getPlayerIcon());
						board[8-i][0].setDisabledIcon(playerArr[3].getPlayerIcon());
						playerArr[3].addHomeCount();
						break;
					}
				}
				break;
			}
		}
		
	}

}