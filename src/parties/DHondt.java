package parties;

public class DHondt extends DivisionMethod {
    @Override
    protected int step() {
        // W tej metodzie dzielimy przez kolejne liczby naturalne
        return 1;
    }

    @Override
    public String toString() {
        return "Metoda D’Hondta";
    }
}
