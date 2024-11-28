package domain.model;

public class Adresse {
    private String vejNavn;
    private String bygningsNr;
    private String by;
    private String land;

    public Adresse(String vejNavn, String bygningsNr, String by, String land) {
        this.vejNavn = vejNavn;
        this.bygningsNr = bygningsNr;
        this.by = by;
        this.land = land;
    }

    public Adresse(String vejNavn, String bygningsNr, String land) {
        this.vejNavn = vejNavn;
        this.bygningsNr = bygningsNr;
        this.land = land;
    }

    @Override
    public String toString(){
        return String.format("%s %s, %s, %s", vejNavn, bygningsNr, by, land);
    }
}
