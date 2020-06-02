import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        District district1 = new District(1, 10);
        District district2 = new District(2, 15);
        int[] vector1 = {1, 2, -3};
        int[] vector2 = {-4, 5, -3};

        Campaign campaign1 = new Campaign(vector1, district1);
        Campaign campaign2 = new Campaign(vector2, district2);

        List<Campaign> campaigns = new ArrayList<Campaign>(List.of(campaign1, campaign2));
        campaigns.sort(new CostSorter());
        for (Campaign campaign : campaigns) {
            System.out.println(campaign.getCost());
        }


    }
}
