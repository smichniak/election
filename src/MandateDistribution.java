import java.util.Map;

public abstract class MandateDistribution {

    public abstract Map<Party, Integer> mandates(Map<Party, Integer> votes, int mandatesCount);


}
