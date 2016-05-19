
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RepresentAsGraph {
	// Algorithm extracts numbers from jobs and creates and adjacency matrix of  jobs via using their number.
	// So please, while testing it, name your jobs from 1 to n for example--> job1, job2, job3... OR 1,2,3... OR iş1, iş2, iş3... etc

	// This program creates doList and checks adjacency matrix.It checks
	// dependency via using isDependent method. IF job is not dependent, it
	// removes it from DoList
	// and adds job to result list. This process continues until mDoList is
	// empty

	private ArrayList<String> mJobs, mResult;// mJobs is list of jobs which is  taken from JSON file AND  mResult is result list of  sorted jobs 
	private List<Integer> mDoList;// mDoList is list of integers, after jobs are extracted from
	private boolean[][] mAdjacencyMatrix;// mAdjacencyMatrix stores connections between jobs.

	// Constructor starts here
	public RepresentAsGraph(ArrayList<String> jobs,
			ArrayList<ArrayList<Integer>> edges) {
		mJobs = jobs;
		mAdjacencyMatrix = new boolean[mJobs.size()][mJobs.size()];

		for (ArrayList<Integer> edge : edges) {
			mAdjacencyMatrix[edge.get(0)][edge.get(1)] = true;
		}
	}

	// This getter is written for test case
	public boolean[][] getAdjacency() {
		return mAdjacencyMatrix;
	}

	// The main sorting method
	public ArrayList<String> sortJobs() {
		mResult = new ArrayList<>();
		mDoList = new LinkedList<>();

		for (int i = 0; i < mJobs.size(); i++) {
			mDoList.add(i);
		}

		outer: while (!mDoList.isEmpty()) {
			for (Integer r : mDoList) {
				if (!isDependent(r, mDoList)) {
					mResult.add(mJobs.get(r));
					mDoList.remove(r);
					continue outer;
				}
			}

			try {
				throw new Exception("What the hell, they depend on each other!");
			} catch (Exception e) {
				System.err.println(e);
				mResult = new ArrayList<String>();
				mResult.add("What the hell, they depend on each other!");
				return mResult;
			}
		}

		Collections.reverse(mResult);
		return mResult;
	}

	// Checks dependency via using mAdjacencyMatrix
	boolean isDependent(int index, List<Integer> doList) {
		for (int todoItem : doList) {
			if (mAdjacencyMatrix[index][todoItem])
				return true;
		}
		return false;
	}
}