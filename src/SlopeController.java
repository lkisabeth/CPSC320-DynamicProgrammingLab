import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SlopeController {

    void go() {
        // Welcome the user and explain the program
        ConsoleView view = new ConsoleView();
        view.welcome();

        // Use JFileChooser to select a Slopes file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        /*
        The Slopes file will be in this format:
        Input for each test case starts with a line of the form n l indicating there are n junctions (in addition to the base and top) and l legs. (l = 0 indicates end of input and l < 30.)
        These junctions will be numbered 2, 3, . . . , n+1 (if n > 0) with 0 indicating the base and 1 indicating the top.
        On the following line there follows l pairs, a b, indicating a leg for a down to b. All junctions will be connected to at least one other junction.
        The data will be consistent (no loops up the mountain) and every leg will be on at least one run.

        Example:
        3 10
        12121323242440403030
        3 10
        12121224402340233030
        00
        */

        // Instantiate Slopes from the file.
        ArrayList<Slope> slopes = createSlopes(input);

        /*
        Each test case should output a line of the form:
        Slope m has r runs where you determine the value for r and m is the number of the test cases (starting at 1.)

        Example:
        Slope 1 has 14 runs.
        Slope 2 has 18 runs.
        */

        // Output the result
        for (Slope slope: slopes) {
            view.printSlope(slope);
        }
    }

    ArrayList<Slope> createSlopes(Scanner input) {
        ArrayList<Slope> slopes = new ArrayList<>();
        int id = 1;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("00")) {
                break;
            }
            String[] split = line.split(" ");
            int numJunctions = Integer.parseInt(split[0]);
            int numLegs = Integer.parseInt(split[1]);
            String legs = input.nextLine().replaceAll("\\s", "");
            Slope slope = new Slope(id, numJunctions, numLegs, legs);
            slopes.add(slope);
            id++;
        }
        return slopes;
    }
}
