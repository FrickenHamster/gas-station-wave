import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 1/25/2015
 * Time: 10:13 PM
 */
public class Solver
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
	private StateTree tree;
	
	private HashMap<String, State> discoverMap;

	public Solver(int n, int k, int[] start)
	{
		this.n = n;
		this.k = k;
		
		pieces = new Piece[n];
		
		
		discoverMap = new HashMap<String, State>();
		
		State test = new State(start, n);

		tree = new StateTree(test);
		
		//ArrayList<State> ss = test.getSuccessors();

		tree.search();
		
		/*for (State s : ss)
		{
			System.out.println(s.toString());
		}*/
	}
}
