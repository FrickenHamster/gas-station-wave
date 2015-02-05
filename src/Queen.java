/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 1/25/2015
 * Time: 10:13 PM
 */
public class Queen
{
	
	private class Piece
	{
		public int x;
		public int y;
		public int moves;
		
		public Piece(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
	}
	
	private int n;
	private int k;
	
	private Piece[] pieces;

	public Queen(int n, int k, int[] start)
	{
		this.n = n;
		this.k = k;
		
		pieces = new Piece[n];

		for (int i = 0; i < n; i++)
		{
			pieces[i] = new Piece(start[i * 2], start[i * 2 + 1]);
			
		}
		
	}
}
