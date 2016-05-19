
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonOp {
	private String mPATH = " ";// File path for JSON file
	/*
	 * mConnection is List of lists, stores connection as integer pairs i.e =>
	 * (job1,job2)-->(0,1)
	 * Since we work on boolean adjacency matrix index starts
	 * from 0.
	 */
	private ArrayList<ArrayList<Integer>> mConnections = new ArrayList<ArrayList<Integer>>();
	private ArrayList<String> mJobs = new ArrayList<String>();//List of jobs.

	//getter for connections
	public ArrayList<ArrayList<Integer>> getConnections() {
		return mConnections;
	}
	//getter for joblist
	public ArrayList<String> getJobsList() {
		return mJobs;
	}
	//This constructor reads json file via using json-simple-1.1.1.jar
	public JsonOp(String PATH) throws FileNotFoundException, IOException,
			ParseException {
		mPATH = PATH;
		JSONParser parser = new JSONParser();
		JSONArray jArray;
		jArray = (JSONArray) parser.parse(new FileReader(mPATH));
		String name = "";
		for (Object jOBJ : jArray) {
			JSONObject job = (JSONObject) jOBJ;
			name = (String) job.get("name");
			int x = Integer.parseInt(name.replaceAll("[^0-9?!\\.]", ""));
			mJobs.add(name);
			JSONArray dependentList = (JSONArray) job.get("isDependedBy");
			for (Object dependent : dependentList) {
				ArrayList<Integer> inner = new ArrayList<Integer>();
				inner.add(x - 1);
				String dep = (String) dependent;
				inner.add(Integer.parseInt(dep.replaceAll("[^0-9?!\\.]", "")) - 1);
				mConnections.add(inner);
			}
		}

	}
}
