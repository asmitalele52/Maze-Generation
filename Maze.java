import java.util.Random;
import java.util.Scanner;

public class Maze {

	public static void main(String[] args) {
		//Reading user input
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the rows and column");
		int row = 0;
		do {
		    while (!reader.hasNextInt()) {
		        System.out.println("That's not a number!");
		        reader.next();
		    }
		    row = reader.nextInt();
		    if(row<=0)
		    	System.out.println("Enter a positive number");
		} while (row <= 0);
		
		int column = 0;
		do {
		    while (!reader.hasNextInt()) {
		        System.out.println("That's not a number!");
		        reader.next();
		    }
		    column = reader.nextInt();
		    if(column<=0)
		    	System.out.println("Enter a positive number");
		} while (column <= 0);
		reader.close();
		//end of reading input
		
		//creating maze using Disjoint Sets
		DisjSets set = new DisjSets(row*column);
		int count = 0;
		boolean horizontalWalls [][] = new boolean [row][column];
		boolean verticalWalls [][] = new boolean [row][column];
		Random rd = new Random();
		while(count != row*column-1)
		{
			int randomElement = rd.nextInt(row*column);
			int direction = rd.nextInt(4);
			int adjacent = -1;
			//north
			if(direction == 0){
				adjacent = randomElement -column;
				if(adjacent <0)
					continue;
			}
			//east
			else if( direction == 1){
				adjacent = randomElement +1;
				if(adjacent%column == 0)
					continue;
			}
			//south
			else if( direction == 2){
				adjacent = randomElement+column;
				if(adjacent >= row*column)
					continue;
			}
			//west
			else{
				adjacent = randomElement -1;
				if(randomElement%column ==  0)
					continue;
			}
			//Union if they are not already in the same set
			if(set.find(randomElement) != set.find(adjacent)){
				set.union(set.find(randomElement), set.find(adjacent));
				if(direction == 1 || direction == 3)
					verticalWalls[Math.min(adjacent,randomElement)/column][Math.min(adjacent,randomElement)%column] = true;
				else
					horizontalWalls[Math.min(adjacent,randomElement)/column][Math.min(adjacent,randomElement)%column] = true;
				count++;
			}
		}
		
		//printing maze
		System.out.println("Creating a maze of "+row+" rows and "+column+" columns");
		for(int i = 0; i < column; i++) {
			if(i == 0) {
				System.out.print("   ");
			}else {
				System.out.print("_ ");
			}				
		}
		System.out.println(" ");
		for(int i = 0;i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				if( j == 0 && i!= 0)
					System.out.print("|");
				else if( j == 0 && i== 0)
					System.out.print(" ");
				if(horizontalWalls[i][j])
					System.out.print(" ");
				else
					System.out.print("_");
				
				if(verticalWalls[i][j] || (i == row-1 && j== column - 1))
					System.out.print(" ");
				else
					System.out.print("|");
			}
		System.out.println("");
		}
	}
}
