import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private Scanner scanner;
    private Party[] parties;
    private District[] districts;

    public Parser(String directory) throws FileNotFoundException {
        File inputFile = new File(directory);
        scanner = new Scanner(inputFile);
    }

    private void generateParties(int numberOfParties) {
        parties = new Party[numberOfParties];
        String[] names = new String[numberOfParties];
        int[] budgets = new int[numberOfParties];
        String[] strategies = new String[numberOfParties];

        for (int i = 0; i < numberOfParties; ++i) {
            names[i] = scanner.next();
        }
        for (int i = 0; i < numberOfParties; ++i) {
            budgets[i] = scanner.nextInt();
        }
        for (int i = 0; i < numberOfParties; ++i) {
            strategies[i] = scanner.next();
        }

        for (int i = 0; i < numberOfParties; ++i) {
            if (strategies[i].equals("R")) {
                parties[i] = new ExpensiveParty(names[i], budgets[i]);
            } else if (strategies[i].equals("S")) {
                parties[i] = new CheapParty(names[i], budgets[i]);
            } else if (strategies[i].equals("W")) {
                parties[i] = new RandomParty(names[i], budgets[i]);
            } else {
                parties[i] = new GreedyParty(names[i], budgets[i]);
            }
        }
    }

    private void generateDistricts(int numberOfDistricts) {
        districts = new District[numberOfDistricts];
        for (int i = 0; i < numberOfDistricts; ++i) {
            districts[i] = new District(i+1, scanner.nextInt());
        }
    }


    public void parseInput() {
        int numberOfDistricts = scanner.nextInt();
        int numberOfParties = scanner.nextInt();
        int numberOfCampaigns = scanner.nextInt();
        int numberOfTraits = scanner.nextInt();
        int numberOfPairs = scanner.nextInt();
        
        String toMerge = scanner.nextLine();
//        toMerge = toMerge.replace(',', ' ').replace('(', ' ').replace(')', ' ');

        generateParties(numberOfParties);
        generateDistricts(numberOfDistricts);



    }
}
