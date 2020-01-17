package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;

public class GameBoard extends JFrame{

	private GridBagConstraints c;
	private JPanel p;
	private JPanel p2;
	private JButton solve;
	
	//n is the nxn size of the desired game board.
	public GameBoard(int n) {
		
		//Initialize the window
		super("Sudoku");

		//Set up JButton
		solve = new JButton("Solve!");
		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//SudokuSolver.solveRequest(); TODO - Fix this
			}
		});
		
		//Set up JPanel for the gameboard
		p = new JPanel(new GridLayout(n, n));
		p.setPreferredSize(new Dimension(600, 600));
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				p.add(new Cell(600/n));					
			}
		}
		
		//Set up JPanel for the buttons/options
		p2 = new JPanel();
		p2.setPreferredSize(new Dimension(600, 50));
		p2.add(solve, new GridLayout(1, 1));
		
		//Set up the frame
		setSize(new Dimension(600, 651));
		setPreferredSize(new Dimension(600, 650));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(p);
		add(p2);
		setVisible(true);
	}
	
	public void updateBoard(int[][] ar){
		for(int i=0; i<ar.length; i++) {
			for(int j=0; j<ar.length; j++) {
			((Cell) p.getComponent((i*9) + j)).setText("\n" + ((Integer)ar[i][j]).toString());
			}
		}
	}
	
	public void highlightCell(int x, int y)throws Exception {
		((Cell) p.getComponent((x*9) + y)).setBackground(new Color(150, 150, 200));
		Thread.sleep(10);
	}
	
	public void unhighlightCell(int x, int y)throws Exception {
		Thread.sleep(70);
		((Cell) p.getComponent((x*9) + y)).setBackground(new Color(200, 200, 200));
	}
}
