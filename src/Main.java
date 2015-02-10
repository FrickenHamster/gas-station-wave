import java.util.*;

public class Main
{
	
	
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int moves = scanner.nextInt();
		
		int[] queens = new int[n * 2];

		for (int i = 0; i < n; i++)
		{
			int y = scanner.nextInt();
			int x = scanner.nextInt();
			queens[i * 2] = x - 1;
			queens[i * 2 + 1] = y - 1;
		}/*
		int n = 4;
		int moves = 10;
		int[] queens = new int[]{1, 3, 2, 3, 3, 1, 4, 1};
		for (int i = 0; i < queens.length; i++)
		{
			queens[i] --;
		}*/
		Solver solver = new Solver(n, moves, queens);
	}
}
