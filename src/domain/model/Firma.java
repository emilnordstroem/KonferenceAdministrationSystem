package domain.model;

public class Firma {
    private final String navn;
    private final String telefonnummer;

    public Firma(String navn, String telefonnummer) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
    }

    @Override
    public String toString(){
        return String.format("%s (%s)", navn, telefonnummer);
    }
}
