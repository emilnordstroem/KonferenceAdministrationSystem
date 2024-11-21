package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private ArrayList<Udflugt> udflugter;
    private ArrayList<Hotel> hoteller;
    private ArrayList<Tilmelding> tilmeldinger;
    private double prisPrDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPrDag = prisPrDag;
    }
}
