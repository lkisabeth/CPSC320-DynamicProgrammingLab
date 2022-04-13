public class ConsoleView {
    void welcome() {
        System.out.println("Welcome to the Slope-Run Calculator.");
        System.out.println();
        System.out.println("Select a file containing slope information and this tool will tell you how many possible runs there are down the slope(s).");
        System.out.println("An appropriately formatted file will look something like this:");
        System.out.println();
        System.out.println("3 10");
        System.out.println("12121323242440403030");
        System.out.println("3 10");
        System.out.println("12121224402340233030");
        System.out.println("00");
        System.out.println();
    }

    public void printSlope(Slope slope) {
        System.out.println(slope.toString());
    }
}
