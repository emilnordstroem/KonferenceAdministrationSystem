package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private final String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private final ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private final double prisPrDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        this.navn = navn;
        if(startDato.isBefore(slutDato)){
            this.startDato = startDato;
            this.slutDato = slutDato;
        }
        this.prisPrDag = prisPrDag;
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    @Override
    public String toString(){
        return String.format("%s: %s - %s", navn, startDato, slutDato);
    }

}
