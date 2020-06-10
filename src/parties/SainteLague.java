package parties;

public class SainteLague extends DivisionMethod {
    @Override
    protected int step() {
        // W tej metodzie dzielimy przez kolejne liczby naturalne nieparzyste (od 1 co dwa)
        return 2;
    }

    @Override
    public String toString() {
        return "Metoda Sainte-Laguë";
    }
}
