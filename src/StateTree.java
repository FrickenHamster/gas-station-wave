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
	
	
	
	public StateTree(State root)
	{
		this.root = root;
		
		
	}
	
	public State search()
	{
		pQueue = new PriorityQueue<Node>(100, new NodeComparator());
		HashMap<String, Boolean> openSet = new HashMap<String, Boolean>();
		HashMap<String, Node> closedSet = new HashMap<String, Node>();
		Node curNode = new Node(root, 0, root.getConflicts() * 2, null);
		pQueue.add(curNode);
		while(!pQueue.isEmpty())
		{
			
			curNode = pQueue.poll();
			//System.out.println("iter fscore:" + curNode.fScore + "step:" + curNode.step + "[]"  + curNode.state.toString());
			if (curNode.state.getConflicts() == 0)
			{
				System.out.println("found solution :" + curNode.state.toString());
			}
			closedSet.put(curNode.state.getKey(), curNode);
			ArrayList<State> suc = curNode.state.getSuccessors();
			for (State state : suc)
			{
				Node nn = closedSet.get(state.getKey());
				if (nn == null)
				{
					nn = new Node(state, curNode.step + 1, curNode.step + 1 + state.getConflicts() * 2, curNode);
					pQueue.add(nn);
				}
				else if (nn.step > curNode.step + 1)
				{
					pQueue.remove(nn);
					nn.step = curNode.step + 1;
					nn.fScore = nn.step + nn.state.getConflicts() * 2;
					pQueue.add(nn);
				}
			}
		}
		return curNode.state;
	}
	
	
	
}
