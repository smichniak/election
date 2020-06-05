import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        District district1 = new District(1, 10);
//        District district2 = new District(2, 15);
//        int[] vector1 = {1, 2, -3};
//        int[] vector2 = {-4, 5, -3};
//
//        Campaign campaign1 = new Campaign(vector1, district1);
//        Campaign campaign2 = new Campaign(vector2, district2);
//
//        List<Campaign> campaigns = new ArrayList<Campaign>(List.of(campaign1, campaign2));
//        campaigns.sort(new CostSorter());
//        for (Campaign campaign : campaigns) {
//            System.out.println(campaign.getCost());
//        }

        Map<String, Integer> votes = new HashMap<String, Integer>(Map.of("A", 720, "B", 300, "C", 480));
        MandateDistribution mandate1 = new DHondt();
        MandateDistribution mandate2 = new SainteLague();
        MandateDistribution mandate3 = new HareNiemeyer();
        System.out.println(mandate1.mandates(votes, 8));
        System.out.println(mandate2.mandates(votes, 8));
        System.out.println(mandate3.mandates(votes, 8));


    }
}
