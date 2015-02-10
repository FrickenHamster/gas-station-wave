import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 1/25/2015
 * Time: 10:13 PM
 */
public class Solver
{
	
	
	private int n;
	private int k;
	
	private Timer timer;
	
	private StateTree tree;
	
	private HashMap<String, State> discoverMap;

	public Solver(int n, int k, int[] start)
	{
		this.n = n;
		this.k = k;
		
		
		/*timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				System.out.println("Timeout");
				System.exit(0);
			}
		}, 5 * 60 * 1000);*/
		
		discoverMap = new HashMap<String, State>();
		
		long startTime = System.currentTimeMillis();

		tree = new StateTree(start, n);
		
		State result = tree.search(k, startTime);

		result.printResult();
		System.out.println((System.currentTimeMillis() - startTime) + " seconds");
		
		System.exit(0);
		
	}
}
