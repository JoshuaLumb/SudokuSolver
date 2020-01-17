package sudoku;

public class SudokuSolver {
	
	static GameBoard gb;
	private static boolean displayActive = true; //Enabling this will slow down the algorithm and enable visualization.
	
	//Pass this function the matrix, row number, and the number to be tested in that row.
	private static boolean checkRow(int[][] ar, int y, int n) {
		for (int i = 0; i<ar.length; i++) {
			if (ar[y][i] == n) {
				return false;
			}
		}
		return true;
	}
	
	//Pass this function the matrix, column number, and the number to be tested in that row.
	private static boolean checkCol(int[][] ar, int x, int n) {
		for (int i=0; i<9; i++) {
			if (n == ar[i][x]) {
				return false;
			}
		}
		return true;
	}
	
	//Pass the function the matrix, the row number, the column number, and the number to be tested
	private static boolean check3x3(int[][] ar, int x, int y, int n) {
		
		//Normalize x, y for the following algorithm
		if (x<3) {
			x=0;
		}
		else if (x<6) {
			x=3;
		}
		else {
			x=6;
		}
		if (y<3) {
			y=0;
		}
		else if (y<6) {
			y=3;
		}
		else {
			y=6;
		}
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (n==ar[x+j][y+i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Pass this function the array to print it to the console
	private static void printAr(int[][] ar) {
		for(int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				System.out.print(ar[i][j]);
				if (j!=8) {
					System.out.print("   ");
				}
				else {
					System.out.print("\n\n");
				}
			}
		}
	}
	
	//Pass this fn the array, x+y coords, and the int to be tested to determine if it can fit there under the rules of sudoku
	private static boolean canFit(int[][] ar, int x, int y, int n) {
		if (!checkRow(ar, x, n) || !checkCol(ar, y, n) || !check3x3(ar, x, y, n)) {
			return false;
		}
		return true;
	}
	
	private static boolean solvePuzzle(int[][] ar, int n) {
		int x = -1;
		int y = -1;
		boolean isEmpty = true;
		printAr(ar); //TEST
		if (displayActive)
			gb.updateBoard(ar);
		
		for (int i = 0; i < n; i++) 
	    { 
	        for (int j = 0; j < n; j++)  
	        { 
	            if (ar[i][j] == 0)  
	            { 
	                x = i; 
	                y = j; 
	                  
	                //If there are still missing values, continue to solve
	                isEmpty = false;  
	                break; 
	            } 
	        } 
	        if (!isEmpty) 
	        { 
	            break; 
	        } 
	    } 
	  
	    //When no empty space is left on the board, terminate 
	    if (isEmpty)  
	    { 
	        return true; 
	    } 
	  
	    //If this didn't work out, backtrack 
	    for (int num = 1; num <= n; num++) 
	    { 
	        if (canFit(ar, x, y, num)) 
	        {	
	        	if (displayActive) {
		        	try {
		        		gb.highlightCell(x, y);
		        	}
		        	catch(Exception e) {
		        		System.out.println(e);
		        	}
	        	}
	            ar[x][y] = num;
	            if (displayActive) {
		            try {
		            	gb.unhighlightCell(x, y);
		            }
		            catch(Exception e) {
		        		System.out.println(e);
		            }
	            }
	            if (solvePuzzle(ar, n))  
	            { 
	                return true; 
	            }  
	            else
	            { 
	                ar[x][y] = 0; // replace it 
	            } 
	        } 
	    } 
	    return false; 
	}
	
	/*
	public static void solveRequest() {
		if (solvePuzzle(ar, n)) {
			System.out.println("\nSolved Puzzle:");
			printAr(ar);
		}
		else {
			System.out.println("Cannot solve puzzle.");
		}
	}
	*/
	
	public static void main(String[] args) {
		
		int [][] ar =
				{
						{3, 0, 6, 5, 0, 8, 4, 0, 0}, 
			            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
			            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
			            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
			            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
			            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
			            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
			            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
			            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
			     };
		int n = ar.length; //To represent size of the array

		//Create the visual game board
		if (displayActive) {
			gb = new GameBoard(n);
			gb.updateBoard(ar);
		}

		System.out.println("Unsolved Puzzle:");
		printAr(ar);
		
		if (solvePuzzle(ar, n)) {
			System.out.println("\nSolved Puzzle:");
			printAr(ar);
		}
		else {
			System.out.println("Cannot solve puzzle.");
		}
	}
}