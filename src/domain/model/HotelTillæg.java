package domain.model;

public class HotelTillæg {
    private String navn;
    private double pris;

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
