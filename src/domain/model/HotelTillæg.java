package domain.model;

public class HotelTillæg {
    private final String navn;
    private final double pris;

    public HotelTillæg(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }
}
