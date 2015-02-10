import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 2/5/2015
 * Time: 6:29 PM
 */
public class State
{
	private String key;
	private int conflicts;
	private int[] queens;
	private int queenNum;
	
	private ArrayList<State> successors;
	
	
	public State(int[] queens, int queenNum)
	{
		this.queenNum = queenNum;
		this.queens = queens;
		this.key = "";
		conflicts = 0;
		for (int i = 0; i < queenNum; i++)
		{
			this.key += queens[i * 2];
			this.key += queens[i * 2 + 1];
			for (int j = i + 1; j < queenNum; j++)
			{
				if (queens[i * 2 + 1] == queens[j * 2 + 1] || Math.abs(queens[i * 2] - queens[j * 2]) == Math.abs(queens[i * 2 + 1] - queens[j * 2 + 1]))
				{
					conflicts++;
				}
			}
		}
		//System.out.println(conflicts);
	}
	
	
	public ArrayList<State> getSuccessors()
	{
		ArrayList<State> suc = new ArrayList<State>();
		for (int i = 0; i < queenNum; i++)
		{
			
			int qy = queens[i * 2 + 1];
			//System.out.println(i + "," + qy);
			if (qy > 0)
			{
				int[] newQueens = new int[queenNum * 2];
				for (int j = 0; j < queenNum; j++)
				{
					newQueens[j * 2] = queens[j * 2];
					if (j == i)
					{
						newQueens[j * 2 + 1] = qy - 1;
						key += (qy - 1);
					} else
					{
						newQueens[j * 2 + 1] = queens[j * 2 + 1];
						key += queens[j * 2 + 1];
					}
				}
				State newState  = new State(newQueens, queenNum);
				suc.add(newState);
				//System.out.println("added down");
			}

			if (qy < queenNum - 1)
			{
				int[] newQueens = new int[queenNum * 2];
				for (int j = 0; j < queenNum; j++)
				{
					newQueens[j * 2] = queens[j * 2];
					if (j == i)
					{
						newQueens[j * 2 + 1] = qy + 1;
						key += (qy - 1);
					} else
					{
						newQueens[j * 2 + 1] = queens[j * 2 + 1];
						key += queens[j * 2 + 1];
					}
				}
				State newState = new State(newQueens, queenNum);
				suc.add(newState);
				//System.out.println("added up");
			}
			
		}
		//System.out.println(suc.size());
		//successors = suc;
		return suc;
	}


	@Override
	public String toString()
	{
		String ss = "";
		ss += "Conf:" + conflicts + " []";
		for (int i = 0; i < queenNum; i++)
		{
			ss += queens[i * 2] + 1;
			ss += ",";
			ss += queens[i * 2 + 1] + 1;
			ss += " : ";
		}
		return ss;
	}

	public int getSteps(int[] def)
	{
		int ss = 0;
		for (int i = 0; i < queenNum; i++)
		{
			ss += Math.abs(queens[i * 2 + 1] - def[i * 2 + 1]);
		}
		return ss;
	}
	
	public int getConflicts()
	{
		return conflicts;
	}

	public String getKey()
	{
		return key;
	}
}
