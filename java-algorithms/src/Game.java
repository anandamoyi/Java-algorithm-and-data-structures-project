import java.util.*;
import java.lang.*;
import java.io.*;


public class Game {
	
	Board sudoku;
	
	public class Cell{
		private int row = 0;
		private int column = 0;
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}
		public int getRow() {
			return row;
		}
		public int getColumn() {
			return column;
		}
	}
	
	public class Region{
		private Cell[] matrix;
		private int num_cells;
		public Region(int num_cells) {
			this.matrix = new Cell[num_cells];
			this.num_cells = num_cells;
		}
		public Cell[] getCells() {
			return matrix;
		}
		public void setCell(int pos, Cell element){
			matrix[pos] = element;
		}
		
	}
	
	public class Board{
		private int[][] board_values;
		private Region[] board_regions;
		private int num_rows;
		private int num_columns;
		private int num_regions;
		
		public Board(int num_rows,int num_columns, int num_regions){
			this.board_values = new int[num_rows][num_columns];
			this.board_regions = new Region[num_regions];
			this.num_rows = num_rows;
			this.num_columns = num_columns;
			this.num_regions = num_regions;
		}
		
		public int[][] getValues(){
			return board_values;
		}
		public int getValue(int row, int column) {
			return board_values[row][column];
		}
		public Region getRegion(int index) {
			return board_regions[index];
		}
		public Region[] getRegions(){
			return board_regions;
		}
		public void setValue(int row, int column, int value){
			board_values[row][column] = value;
		}
		public void setRegion(int index, Region initial_region) {
			board_regions[index] = initial_region;
		}	
		public void setValues(int[][] values) {
			board_values = values;
		}

	}
	
	public int[][] solver() {
		//To Do => Please start coding your solution here
		
		solve(sudoku);
		

		return sudoku.getValues();
	}
	
	private static boolean solve(Board sudoku) {
		
		boolean valid = false;
		boolean a = true;
		boolean isFull = true;
		int region = 0;
		int cell = 0;
		for(int i = 0; i < sudoku.getRegions().length; i++) {
			for(int j = 0; j < sudoku.getRegions()[i].getCells().length; j++) {
				if(sudoku.getValue(sudoku.getRegions()[i].getCells()[j].getRow(),sudoku.getRegions()[i].getCells()[j].getColumn()) == -1) {
					region = i;
					cell = j;
					isFull = false;
					break;
					
				}
			}
		}
		
		if(isFull == true) {
			return true;
		}
		
		for(int k = 1; k <= sudoku.getRegions()[region].num_cells; k++) {
			
			for(int n = 0; n < sudoku.getRegions()[region].getCells().length; n++) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[n].getRow(),sudoku.getRegions()[region].getCells()[n].getColumn()) != k) {
					valid = true;
				} else {
					valid = false;
					break;
				}
				
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != 0) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() - 1,sudoku.getRegions()[region].getCells()[cell].getColumn()) == k) {
					valid = false;
				}
				
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != sudoku.num_rows - 1) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() + 1,sudoku.getRegions()[region].getCells()[cell].getColumn()) == k) {
					valid = false;
				}
				
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getColumn() != 0) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow(),sudoku.getRegions()[region].getCells()[cell].getColumn() - 1) == k) {
					
					valid = false;
					
				}
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getColumn() != sudoku.num_columns - 1) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow(),sudoku.getRegions()[region].getCells()[cell].getColumn() + 1) == k) {
					valid = false;
				}
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != 0 && sudoku.getRegions()[region].getCells()[cell].getColumn() != 0) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() - 1,sudoku.getRegions()[region].getCells()[cell].getColumn() - 1) == k) {
					valid = false;
				}
				
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != 0 && sudoku.getRegions()[region].getCells()[cell].getColumn() != sudoku.num_columns - 1) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() - 1,sudoku.getRegions()[region].getCells()[cell].getColumn() + 1) == k) {
					valid = false;
				}
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != sudoku.num_rows -1 && sudoku.getRegions()[region].getCells()[cell].getColumn() != 0) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() + 1,sudoku.getRegions()[region].getCells()[cell].getColumn() - 1) == k) {
					valid = false;
				}
			}
			
			if(sudoku.getRegions()[region].getCells()[cell].getRow() != sudoku.num_rows -1 && sudoku.getRegions()[region].getCells()[cell].getColumn() != sudoku.num_columns -1) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow() + 1,sudoku.getRegions()[region].getCells()[cell].getColumn() + 1) == k) {
					valid = false;
				}
				
			}
			
			
			
			if(valid == true) {
				sudoku.setValue(sudoku.getRegions()[region].getCells()[cell].getRow(), sudoku.getRegions()[region].getCells()[cell].getColumn(), k);
				 a= solve(sudoku);
				if(a == true) {
					break;
				} else {
					sudoku.setValue(sudoku.getRegions()[region].getCells()[cell].getRow(), sudoku.getRegions()[region].getCells()[cell].getColumn(), -1);
				}
				
			}
		   
			if(k == sudoku.getRegions()[region].num_cells) {
				if(sudoku.getValue(sudoku.getRegions()[region].getCells()[cell].getRow(), sudoku.getRegions()[region].getCells()[cell].getColumn()) == -1) {
					
					return false;
					
				}	
			
		}
	}
		
		return true;
		

		
}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] board = new int[rows][columns];
		//Reading the board
		for (int i=0; i<rows; i++){
			for (int j=0; j<columns; j++){
				String value = sc.next();
				if (value.equals("-")) {
					board[i][j] = -1;
				}else {
					try {
						board[i][j] = Integer.valueOf(value);
					}catch(Exception e) {
						System.out.println("Ups, something went wrong");
					}
				}	
			}
		}
		int regions = sc.nextInt();
		Game game = new Game();
	    game.sudoku = game.new Board(rows, columns, regions);
		game.sudoku.setValues(board);
		for (int i=0; i< regions;i++) {
			int num_cells = sc.nextInt();
			Game.Region new_region = game.new Region(num_cells);
			for (int j=0; j< num_cells; j++) {
				String cell = sc.next();
				String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
				String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
				Game.Cell new_cell = game.new Cell(Integer.valueOf(value1)-1,Integer.valueOf(value2)-1);
				new_region.setCell(j, new_cell);
			}
			game.sudoku.setRegion(i, new_region);
		}
		int[][] answer = game.solver();
		for (int i=0; i<answer.length;i++) {
			for (int j=0; j<answer[0].length; j++) {
				System.out.print(answer[i][j]);
				if (j<answer[0].length -1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	


}


