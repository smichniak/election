package parties;

import java.util.List;

public abstract class Party {
    private String name;
    protected int budget;
    private int mandates;

    public Party(String name, int budget) {
        this.name = name;
        this.budget = budget;
        this.mandates = 0;
    }

    public int getMandates() {
        return mandates;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addMandates(int mandates) {
        this.mandates += mandates;
    }

    // Zwraca false, jeśli partię nie stać już na żadne działanie,
    // w przecinym przypadku zwraca true i wykonuje to działanie
    public abstract boolean runCampaign(List<Campaign> campaigns);

}
