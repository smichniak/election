import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {
//        District district1 = new District(1, 10);
//        District district2 = new District(2, 15);
//        int[] vector1 = {1, 2, -3};
//        int[] vector2 = {-4, 5, -3};
//
//        Campaign campaign1 = new Campaign(vector1, district1);
//        Campaign campaign2 = new Campaign(vector2, district2);
//
//
//        List<Campaign> campaigns = new ArrayList<>(List.of(campaign1, campaign2));
//        Collections.sort(campaigns);
//        for (Campaign campaign : campaigns) {
//            System.out.println(campaign.getCost());
//        }
//
//
//        Party partyA = new CheapParty("A", 100);
//        Party partyB = new CheapParty("B", 100);
//        Party partyC = new CheapParty("C", 100);
//
//
//        Map<Party, Integer> votes = new HashMap<>(Map.of(partyA, 720, partyB, 300, partyC, 480));
//        MandateDistribution mandate1 = new DHondt();
//        MandateDistribution mandate2 = new SainteLague();
//        MandateDistribution mandate3 = new HareNiemeyer();
//        System.out.println(mandate1.mandates(votes, 8));
//        System.out.println(mandate2.mandates(votes, 8));
//        System.out.println(mandate3.mandates(votes, 8));
        try {
            Parser parser = new Parser(args[0]);
            parser.parseInput();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}

