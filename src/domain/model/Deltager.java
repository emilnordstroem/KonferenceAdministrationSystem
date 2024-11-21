package domain.model;

import java.util.ArrayList;
import java.util.Random;

public class Deltager {
    private int id;
    private final String fuldeNavn;
    private final String telefonnummer;
    private Addresse addresse;
    private final ArrayList<Tilmelding> tilmeldingsList = new ArrayList<>();
    private Firma firma;

    public Deltager(String fuldeNavn, String telefonnummer, Addresse addresse){
        this.id = generateID();
        this.fuldeNavn = fuldeNavn;
        this.telefonnummer = telefonnummer;
        this.addresse = addresse;
    }

    public void setTilmeldingsList (Tilmelding tilmelding){
        if(!tilmeldingsList.contains(tilmelding)){
            tilmeldingsList.add(tilmelding);
            System.out.println("Tilmelding tilføjet til deltagers tilmeldingslist");
        }
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public double getSamletUdgifter(){
        ArrayList<Tilmelding> tilmeldinger = new ArrayList<>(tilmeldingsList);
        double samletUdgifter = 0;
        if(!tilmeldinger.isEmpty()){
            for(Tilmelding tilmelding : tilmeldinger){
                samletUdgifter += tilmelding.getSamletTilmeldingsUdgift();
            }
        }
        return samletUdgifter;
    }

    private int generateID(){
        // If we store data on AttendanceID - we could make each unique to the attendance
        return new Random().nextInt(111_111, 999_999) + 1;
    }
}