package domain.model;

public class Addresse {
    private final String vejNavn;
    private final String bygningsNr;
    private final String by;
    private final String land;

    public Addresse(String vejNavn, String bygningsNr, String by, String land) {
        this.vejNavn = vejNavn;
        this.bygningsNr = bygningsNr;
        this.by = by;
        this.land = land;
    }
}
