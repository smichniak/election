import java.util.Comparator;

public class CostSorter implements Comparator<Campaign> {
    @Override
    public int compare(Campaign campaign1, Campaign campaign2) {
        return  campaign1.getCost() - campaign2.getCost();
    }
}
