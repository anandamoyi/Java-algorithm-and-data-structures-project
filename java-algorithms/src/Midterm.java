import java.util.*;
import java.lang.*;
import java.io.*;

public class Midterm {
	private static int[][] dp_table;
	private static int[] penalization;
	

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int chairs;
		try {
			chairs = Integer.valueOf(reader.readLine());
			penalization = new int[chairs];
			for (int i=0; i< chairs; i++) {
				penalization[i] = Integer.valueOf(reader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int answer = lost_marks(penalization);
		System.out.println(answer);
	}
	
	public static int lost_marks(int[] penalization) {
		//To Do => Please start coding your solution here
		
		dp_table = new int [penalization.length][penalization.length];
		int marks = penalization[1];
		
		if(penalization.length == 2) {
			return marks;
		} else {
			return marks = find_minimum(marks,1,1,penalization);
		}

		
	}
	
	private static int find_minimum(int marks, int index, int jump, int[] penalization) {
		
		int minimum_marks = 0;
		int minimum_marks_one = 0;
		int minimum_marks_two = 0;
		int a = 0;
		int b = 0;
		
		
		 if(index + jump + 1 == penalization.length - 1){
			minimum_marks = marks + penalization[penalization.length - 1];
			dp_table[index][jump] = penalization[penalization.length - 1];
			
		} else if(index - jump < 0) {
			a = marks + penalization[index+jump+1];
			if(dp_table[index+jump+1][jump+1] != 0) {
				minimum_marks = a + dp_table[index+jump+1][jump+1];	
			} else {
			    minimum_marks = find_minimum(a,index+jump+1,jump+1,penalization);
			    dp_table[index+jump+1][jump+1] = minimum_marks - a;
			}
			
		} else if(index + jump + 1 > penalization.length - 1) {
			a = marks + penalization[index - jump];
			if(dp_table[index - jump][jump] != 0) {
				minimum_marks = a + dp_table[index - jump][jump];
			} else {
			    minimum_marks = find_minimum(a,index - jump, jump,penalization);
			    dp_table[index - jump][jump] = minimum_marks - a;
			}
		} else {
			a = marks + penalization[index - jump];
			if(dp_table[index - jump][jump] != 0) {
				minimum_marks_one = a + dp_table[index - jump][jump];
			} else {
				minimum_marks_one = find_minimum(a, index - jump,jump,penalization);
				dp_table[index-jump][jump] = minimum_marks_one - a;
			}
			b = marks + penalization[index + jump + 1];
			
			if(dp_table[index+jump+1][jump+1] != 0) {
				minimum_marks_two = b + dp_table[index+jump+1][jump+1];
			} else {
				minimum_marks_two = find_minimum(b, index + jump + 1, jump+1,penalization);
				dp_table[index+jump+1][jump+1] = minimum_marks_two - b;
			}
			
			
			if(minimum_marks_one < minimum_marks_two) {
				minimum_marks = minimum_marks_one;
			} else {
				minimum_marks = minimum_marks_two;
			}
		}
		return minimum_marks;
	}

}

