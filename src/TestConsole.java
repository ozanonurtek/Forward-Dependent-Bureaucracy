
import java.util.ArrayList;

public class TestConsole {

	public static void main(String[] args) throws Exception {
		//Please dont forget to change PATH while parsing json file.
		String PATH = "/home/ozan/Desktop/input.json";
		JsonOp json = new JsonOp(PATH);
		RepresentAsGraph graph = new RepresentAsGraph(json.getJobsList(),
				json.getConnections());

		System.out.println("---!HERE ARE JOBS!---");
		System.out.println();
		System.out.println(json.getJobsList());

		System.out.println();
		System.out.println("---!HERE ARE CONNECTIONS!---");
		for (ArrayList<Integer> i : json.getConnections()) {
			for (int k =0; k<i.size(); k++) {
				if (k % 2 == 0)
				System.out.print("job" + (i.get(k) + 1)+ " is dependent on ");
				else
					System.out.println("job" + (i.get(k) + 1));
			}
		}

		System.out.println();
		System.out.println("---!HERE IS ADJACENCY MATRIX!---");
		System.out.println("     " + json.getJobsList());
		for (int i = 0; i < graph.getAdjacency().length; i++) {
			System.out.print("job" + (i + 1) + " " + "{");
			for (int j = 0; j < graph.getAdjacency()[i].length; j++) {
				System.out.print(graph.getAdjacency()[i][j]);
				if (j != graph.getAdjacency()[i].length - 1)
					System.out.print(",");
			}
			System.out.print("}");
			System.out.println();

		}
		System.out.println();

		System.out.println("---!JOBS ARE SORTED!---");
		System.out.println(graph.sortJobs());
	}
}
