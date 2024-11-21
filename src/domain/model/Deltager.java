package domain.model;

import java.util.ArrayList;
import java.util.Random;

public class Deltager {
    private int id;
    private String fuldeNavn;
    private String telefonnummer;
    private Addresse addresse;
    private ArrayList<Tilmelding> tilmeldingsList = new ArrayList<>();
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

    private int generateID(){
        return new Random().nextInt(111_111, 999_999);
    }
}
