package storage;

import domain.model.*;

import java.util.ArrayList;


public class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugter = new ArrayList<>();

    // Konference Methods -------------------------------------------------------------------
    public static void addKonference(Konference konference) {
        if (!konferencer.contains(konference)) {
            konferencer.add(konference);
            System.out.println("Konference tilføjet i Storage");
        }
    }

    public static ArrayList<Konference> getKonferencer() {
        System.out.println("Konferencer hentet fra Storage");
        return new ArrayList<>(konferencer);
    }

    public static void removeKonference(Konference konference) {
        konferencer.remove(konference);
        System.out.println("Konference fjernet fra Storage");
    }

    // Hotel Methods -------------------------------------------------------------------------------
    public static void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
            System.out.println("Hotel tilføjet i Storage");
        }
    }

    public static ArrayList<Hotel> getHoteller() {
        System.out.println("Hoteller hentet fra Storage");
        return new ArrayList<>(hoteller);
    }

    public static Hotel getHotelByNavn(String navn) {
        for (Hotel hotel : hoteller) {
            if(hotel.getNavn().equals(navn)) {
                System.out.println("Hotel hentet ved navn i Storage");
                return hotel;
            }
        }
        return null;
    }

    public static void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
        System.out.println("Hotel fjernet fra Storage");
    }

    // Deltager Methods ----------------------------------------------------------------------------
    public static void addDeltager(Deltager deltager) {
        if (!deltagere.contains(deltager)) {
            deltagere.add(deltager);
            System.out.println("Deltager tilføjet i Storage");
        }
    }

    public static ArrayList<Deltager> getDeltagere() {
        System.out.println("Deltagere hentet fra Storage");
        return new ArrayList<>(deltagere);
    }

    public static void removeDeltager(Deltager deltager) {
        deltagere.remove(deltager);
        System.out.println("Deltager fjernet fra storage");
    }

    public static void addFirma(Firma firma){
        if(!firmaer.contains(firma)){
            firmaer.add(firma);
            System.out.println("Firma tilføjet i Storage");
        }
    }

    public static ArrayList<Firma> getFirmaer() {
        System.out.println("Firmaer hentet i Storage");
        return new ArrayList<>(firmaer);
    }

    // Udflugter Methods ----------------------------------------------------------------------------
    public static ArrayList<Udflugt> getUdflugter() {
        System.out.println("Udflugter hentet i storage");
        return new ArrayList<>(udflugter);
    }

    // Remove, Add, Clear udflugt er midlertidlig lagring af data til konferencen
    public static void removeUdflugt(Udflugt udflugt){
        udflugter.remove(udflugt);
        System.out.println("Udflugt fjernet fra Storage");
    }

    public static void addUdflugt(Udflugt udflugt){
        if (!udflugter.contains(udflugt)){
            udflugter.add(udflugt);
            System.out.println("Udflugt er tilføjet i storage");
        }
    }

    public static void clearUdflugter(){
        udflugter.clear();
        System.out.println("Udflugter er cleared i storage");
    }
}