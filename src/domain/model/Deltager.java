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

    public Deltager(String fuldeNavn, String telefonnummer, Addresse addresse,  Firma firma){
        this.id = generateID();
        this.fuldeNavn = fuldeNavn;
        this.telefonnummer = telefonnummer;
        this.addresse = addresse;
        this.firma = firma;
    }

    public void setTilmeldingsList (Tilmelding tilmelding){
        if(!tilmeldingsList.contains(tilmelding)){
            tilmeldingsList.add(tilmelding);
            System.out.println("Tilmelding tilføjet til deltagers tilmeldingslist");
        }
    }

    public double getUdgifter(){
        return 0;
    }

    private int generateID(){
        // If we store data on AttendanceID - we could make each unique to the attendance
        return new Random().nextInt(111_111, 999_999);
    }
}