import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Slope {
    int id;
    int junctions;
    int numLegs;
    String legs;
    List<List<Integer>> graph = new ArrayList<>();
    int numRuns;

    public Slope(int i, int n, int l, String s) {
        id = i;
        junctions = n;
        numLegs = l;
        legs = s;
        // print out current state of the slope (mainly for debugging purposes)
        System.out.println("ID: " + id + " Junctions: " + junctions + " numLegs: " + numLegs + " Legs: " + legs);
        // use helper method to construct graph needed to calculate number of runs
        constructGraph();
        // calculate number of runs
        calculateNumRuns();
    }

    // utility method to construct a Graph from the given legs string
    void constructGraph() {
        for (int i = 0; i < junctions + 2; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numLegs; i++) {
            graph.get(Character.getNumericValue(legs.charAt(i*2))).add(Character.getNumericValue(legs.charAt(i*2 + 1)));
        }
    }

    // writing the calculateNumRuns method is the main problem I need to solve for this lab
    void calculateNumRuns() {
        // use a Queue to store runs
        Queue<List<Integer>> runs = new LinkedList<>();

        // Use a List to track an individual run
        List<Integer> run = new ArrayList<>();
        // add the 1 junction (top of slope)
        run.add(1);
        runs.offer(run);

        while (!runs.isEmpty()) {
            // remove a run from the queue
            run = runs.poll();
            // get last junction in the run
            int last = run.get(run.size() - 1);
            // if that junction is 0, increment numRuns by 1
            if (last == 0) numRuns++;

            List<Integer> lastNode = graph.get(last);
            for (int i = 0; i < lastNode.size(); i++) {
                if (isNotVisited(lastNode.get(i), run)) {
                    List<Integer> newRun = new ArrayList<>(run);
                    newRun.add(lastNode.get(i));
                    runs.offer(newRun);
                }
            }
        }
    }

    private boolean isNotVisited(int x, List<Integer> run) {
        int size = run.size();
        for (int i = 0; i < size; i++) {
            if (run.get(i) == x) return false;
        }

        return true;
    }

    public String toString() {
        return "Slope " + id + " has " + numRuns + " runs.";
    }
}
