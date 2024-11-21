package domain.model;

public class Addresse {
    private String vejNavn;
    private String bygningsNr;
    private String by;
    private String land;

    public Addresse(String vejNavn, String bygningsNr, String by, String land) {
        this.vejNavn = vejNavn;
        this.bygningsNr = bygningsNr;
        this.by = by;
        this.land = land;
    }
}
