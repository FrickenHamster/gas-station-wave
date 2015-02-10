import com.sun.org.apache.xpath.internal.operations.*;

import java.lang.*;
import java.lang.String;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 2/5/2015
 * Time: 6:42 PM
 */
public class StateTree
{
	
	PriorityQueue<Node> pQueue;
	
	private class Node
	{
		public State state;
		public int step;
		public int fScore;
		public Node parent;
		public ArrayList<Node> children;

		public Node(State state, int step, int fScore, Node parent)
		{
			this.state = state;
			this.step = step;
			this.fScore = fScore;
			this.parent = parent;
			children = new ArrayList<Node>();
		}
	}
	
	private class NodeComparator implements Comparator<Node>
	{
		@Override
		public int
		compare(Node o1, Node o2)
		{
			if (o1.fScore < o2.fScore)
			{
				return -1;
			}
			if (o1.fScore > o2.fScore)
			{
				return 1;
			}
			return 0;
		}
	}
	
	private State root;
	
	private int[] def;
	private int queenNum;
	
	public StateTree(int[] start, int queenNum)
	{
		this.root = new State(start, queenNum);
		this.def = start;
		this.queenNum = queenNum;
	}
	
	public State search(int stepLimit, long startTime)
	{
		long endTime = startTime + 30 * 60000;
		
		int gcn = 10;
		
		int lowConf = queenNum;
		
		pQueue = new PriorityQueue<Node>(100, new NodeComparator());
		HashMap<String, Boolean> openSet = new HashMap<String, Boolean>();
		HashMap<String, Node> closedSet = new HashMap<String, Node>();
		Node curNode = new Node(root, 0, root.getConflicts() * 2, null);
		State lowState = root;
		pQueue.add(curNode);
		while(!pQueue.isEmpty())
		{
			if (curNode.state.getConflicts() == 0)
				return curNode.state;
			else if (curNode.state.getConflicts() < lowConf)
			{
				lowConf = curNode.state.getConflicts();
				lowState = curNode.state;
			}
			
			if (System.currentTimeMillis() > endTime)
			{
				System.out.println("timeout");
				return lowState;
			}
			gcn--;
			if (gcn < 0)
			{
				gcn = 10;
				System.gc();
			}
			
			curNode = pQueue.poll();
			//System.out.println("iter fscore:" + curNode.fScore + "step:" + curNode.step + "[]"  + curNode.state.toString() + curNode.state.getHamHeur() + "f:" + curNode.fScore);
			closedSet.put(curNode.state.getKey(), curNode);
			ArrayList<State> suc = curNode.state.getSuccessors();
			for (State state : suc)
			{
				Node nn = closedSet.get(state.getKey());
				int ss = -1;
				//System.out.println(nn);
				if (nn == null)
				{
					ss = state.getSteps(def);
					if (ss > stepLimit)
					{
						//System.out.println("step over" + state.toString() + ss + ":" + state.getKey());
						continue;
					}
					//nn = new Node(state, ss, ss + state.getConflicts() * 2, curNode);
					nn = new Node(state, ss, ss * 2 + state.getHamHeur(), curNode);
					closedSet.put(state.getKey(), nn);
					pQueue.add(nn);
				}
			}
		}
		return lowState;
	}
	
	
	
}
