package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private final ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private double afgiftPerDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double afgiftPerDag) {
        this.navn = navn;
        if(startDato.isBefore(slutDato)){
            this.startDato = startDato;
            this.slutDato = slutDato;
        }
        this.afgiftPerDag = afgiftPerDag;
    }

    public void opretUdflugt(String navn, Addresse addresse, LocalDate dato, String beskrivelse, double pris){
        setUdflugter(new Udflugt(navn, addresse, dato, beskrivelse, pris));
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public double getAfgiftPerDag() {
        return afgiftPerDag;
    }

    public void setUdflugter(Udflugt udflugt) {
        if(!udflugter.contains(udflugt)){
            udflugter.add(udflugt);
            System.out.println("Udflugt tilføjet til konference udflugtsliste");
        }
    }

    public void setHoteller(Hotel hotel) {
        if(!hoteller.contains(hotel)){
            hoteller.add(hotel);
            System.out.println("Hotel tilføjet til konference hotelListe");
        }
    }

    public void setTilmeldinger(Tilmelding tilmelding) {
        if(!tilmeldinger.contains(tilmelding)){
            tilmeldinger.add(tilmelding);
            System.out.println("Tilmelding tilføjet til konference tilmeldingslist");
        }
    }

    @Override
    public String toString(){
        return String.format("%s: %s - %s%nPris per dag: %f", navn, startDato, slutDato, afgiftPerDag);
    }

}
