package view;

import domain.controller.Controller;
import domain.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class App {
    private static final ArrayList<Konference> konferenceArrayList = new ArrayList<>();
    private static final ArrayList<Hotel> hotelArrayList = new ArrayList<>();

    private static final ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
    private static final ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

    public static void main(String[] args) {
        initStorage();
        Application.launch(StartWindow.class);
    }

    private static void initStorage() {
        //======================================================================
        // Hoteller
        Hotel denHvideSvane = Controller.opretHotel("Den hvide svane", new Adresse("Sønderhøj", "31", "Aarhus", "Denmark"), 1050, 1250, new ArrayList<>());
        HotelTillæg wifi = new HotelTillæg("WIFI", 50, denHvideSvane);


        // Opretter hoteller
        String[] hotelNavne = {
                "Hotel d'Angleterre",
                "Nimb Hotel",
                "Comwell Aarhus",
                "Hotel Koldingfjord",
                "Kurhotel Skodsborg",
                "Falsled Kro",
                "Hotel Skanderborghus",
                "Marienlyst Strandhotel",
                "Sinatur Hotel Storebælt",
                "Kokkedal Slot Copenhagen"
        };

        String[] hotelTillægsNavne = {
                "Bad", "WIFI", "Morgenmad"
        };

        for(int number = 0; number < hotelNavne.length; number++){
            Hotel hotel = Controller.opretHotel(hotelNavne[number], null, generatePricePerDay(), generatePricePerDay(), new ArrayList<>());

            int antalTillæg = new Random().nextInt(0,2) + 1;
            for(int tillæg = 0; tillæg < antalTillæg; tillæg++){
                Controller.opretHotelTillæg(hotelTillægsNavne[tillæg], generateUdflugtPrice(), hotel);
            }
            hotelArrayList.add(hotel);
        }

        //======================================================================
        // Konferencer
        Konference havOgHimmel = Controller.opretKonference("Hav og himmel", LocalDate.of(2024,12,16), LocalDate.of(2024,12,18), 1500, null);
        Udflugt byRundtur = havOgHimmel.createUdflugt("Byrundtur, Odense", null, LocalDate.of(2024, 12, 18), "Kr. 125 inkl. Frokost", 125);
        Udflugt egeskov = havOgHimmel.createUdflugt("Egeskov", null, LocalDate.of(2024, 12, 19), "Kr. 75", 75);
        Udflugt trapholtMuseum = havOgHimmel.createUdflugt("Trapholt Museum, Kolding", null, LocalDate.of(2024, 12, 20), "Kr. 200 inkl. Frokost", 200);


        // Opretter konferencer
        String[] konferenceNavne = {
                "Digital Innovation Summit", "Sundhedsteknologi Konference", "Fremtidens Byggeri",
                "Finans og Økonomi Forum", "Energi og Klima Symposium", "Dansk IT Forum",
                "Ledelse og HR Konference", "Kunstig Intelligens i Praksis", "Bæredygtighed og Grøn Omstilling",
                "Nordisk Forskningsdag", "Offentlig Digitalisering", "E-handelsdagen",
                "Sikkerhed og Beredskab"
        };

        String[] udflugtNavne = {
                "Guidede historiske byvandringer",
                "Vandretur i naturreservat",
                "Lokalt madlavningskursus",
                "Udforskning af arkæologiske steder",
                "Besøg på bæredygtig landbrugsfarm",
                "Klassisk håndværksskole",
                "Tur til flodens økosystemrestaurering",
                "Gåtur om urban innovation",
                "Oplevelse på lokal vingård eller bryggeri",
                "Besøg på videnskabs- og teknologimuseum",
                "Tur til kulturarvsmuseum",
                "Udforskning af botaniske haver",
                "Besøg på miljøbeskyttelsesprojektsted",
                "Traditionel musik- og danseforestilling",
                "Tur til lokal kunstgalleri og atelier",
                "Gåtur ved arkitektoniske vartegn",
                "Besøg på bæredygtigt energiforskningscenter",
                "Workshop om indfødte kulturer og traditioner",
                "Tur til marin økosystemforskningsstation",
                "Oplevelse af regionale mad- og markedstraditioner"
        };
        int tæller = 0;
        // Resetter tæller hvis outofbounce
        if(tæller < udflugtNavne.length){
            tæller = 0;
        }

        for(int number = 0; number < 13; number++){
            LocalDate fraDato = generateRandomDate();
            LocalDate tiLDato = fraDato.plusDays(new Random().nextInt(5,20) + 1);

            Konference konference = Controller.opretKonference(konferenceNavne[number], fraDato, tiLDato, generatePricePerDay(), new ArrayList<>());

            int antalHoteller = new Random().nextInt(2,5);
            for (int hotel = 0; hotel < antalHoteller; hotel++){
                int indexHotel = new Random().nextInt(0, hotelArrayList.size() - 1) + 1;
                konference.addHotel(hotelArrayList.get(indexHotel));
            }

            int antalUdflugter = new Random().nextInt(1,4);
            for(int udflugt = 0; udflugt < antalUdflugter; udflugt++){
                if(tæller < udflugtNavne.length){
                    konference.createUdflugt(udflugtNavne[tæller], null, generateRandomDate(), "", generateUdflugtPrice());
                    tæller++;
                } else {
                    tæller = 0;
                }
            }
            konferenceArrayList.add(konference);
        }

        //======================================================================
        // Deltagere
        Adresse finnMadsenAdresse = new Adresse("Tekandevej", "4","Horsens","Danmark");
        Deltager finnMadsen = Controller.opretDeltager("Finn", "Madsen", generatePhoneNumber(), finnMadsenAdresse, null);
        Controller.opretTilmelding(havOgHimmel, finnMadsen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, null, valgteHotelTillæg);


        Adresse nielsPetersenAdresse = new Adresse("Kælke Boulevarden", "94","Aarhus C","Danmark");
        Deltager nielsPetersen = Controller.opretDeltager("Niels", "Petersen", generatePhoneNumber(), nielsPetersenAdresse, null);
        Controller.opretTilmelding(havOgHimmel, nielsPetersen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse peterSommerAdresse = new Adresse("Thor-kristiansen vej", "10","Aalborg C","Danmark");
        Deltager peterSommer = Controller.opretDeltager("Peter", "Sommer", generatePhoneNumber(), peterSommerAdresse, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(trapholtMuseum);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, peterSommer, false,
                "Mie Sommer", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse loneJensenAdresse = new Adresse("Kane allé", "27","København K","Danmark");
        Deltager loneJensen = Controller.opretDeltager("Lone", "Jensen", generatePhoneNumber(), loneJensenAdresse, null);
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, loneJensen, true,
                "Jan Madsen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse emilStoeveAdresse = new Adresse("Bernhardt Jensens Boulevard", "95","Aarhus C","Danmark");
        Deltager emilStoeve = Controller.opretDeltager("Emil", "Støve", "25472030", emilStoeveAdresse, null);
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, emilStoeve, true,
                "Maria Thor-Kristiansen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        // Opretter deltagere
        String[] vejnavne = {
                "Nørrebrogade", "Vesterbrogade", "Østerbrogade", "Amagerbrogade",
                "Frederiksborggade", "Strøget", "Jagtvej", "Gammel Kongevej",
                "Valby Langgade", "H.C. Andersens Boulevard", "Lyngbyvej",
                "Tagensvej", "Falkoner Allé", "Peter Bangs Vej", "Finsensvej",
                "Købmagergade", "Frederikssundsvej", "Istedgade", "Nytorv",
                "Kongens Nytorv", "Christians Brygge", "Langelinie", "Holmens Kanal",
                "Vimmelskaftet", "Rådhuspladsen", "Nordre Fasanvej", "Sydhavnsgade",
                "Enghavevej", "Vester Voldgade", "Østbanegade"
        };

        String[] byer = {
                "København", "Aarhus", "Odense", "Aalborg", "Esbjerg", "Randers",
                "Kolding", "Horsens", "Vejle", "Roskilde", "Herning", "Hørsholm",
                "Helsingør", "Næstved", "Fredericia", "Ballerup", "Rødovre",
                "Sønderborg", "Holstebro", "Taastrup", "Slagelse", "Hillerød",
                "Svendborg", "Hjørring", "Silkeborg", "Nyborg", "Viborg", "Kalundborg",
                "Thisted", "Frederikshavn"
        };

        String[] fornavne = {
                "Emma", "William", "Ida", "Oliver", "Sofia",
                "Noah", "Freja", "Victor", "Alma", "Lucas",
                "Ella", "Oscar", "Anna", "Elias", "Clara",
                "Alfred", "Nora", "Carl", "Asta", "Mikkel",
                "Laura", "Malthe", "Lærke", "Magnus", "Liva",
                "Christian", "Mathilde", "Sebastian", "Maja", "Jonathan"
        };

        String[] efternavne = {
                "Jensen", "Nielsen", "Hansen", "Pedersen", "Andersen",
                "Christensen", "Larsen", "Sørensen", "Rasmussen", "Jørgensen",
                "Petersen", "Madsen", "Kristensen", "Olsen", "Thomsen",
                "Christiansen", "Poulsen", "Johansen", "Møller", "Mortensen",
                "Knudsen", "Jacobsen", "Andreasen", "Jeppesen", "Laursen",
                "Henriksen", "Holm", "Schmidt", "Eriksen", "Simonsen"
        };

        String[] companies = {
                "Novo Nordisk", "Maersk", "Carlsberg", "Danske Bank", "Lego",
                "Vestas", "Ørsted", "Arla Foods", "ISS", "Danfoss",
                "Coloplast", "Tryg", "Pandora", "Jysk", "Netcompany",
                "GN Store Nord", "Nykredit", "FLSmidth", "TDC", "Demant",
                "Novo Nordisk", "Maersk", "Carlsberg", "Danske Bank", "Lego",
                "Vestas", "Ørsted", "Arla Foods", "ISS", "Danfoss"
        };

        for(int number = 0; number < 30; number++){

            Adresse adresse = new Adresse(vejnavne[number], generateBuildingNumber(), byer[number], "Danmark");
            Firma firma = new Firma(companies[number], generatePhoneNumber());
            Deltager deltager = Controller.opretDeltager(fornavne[number], efternavne[number], generatePhoneNumber(), adresse, firma);

            if(number % 2 == 0){
                Konference konference = konferenceArrayList.get(new Random().nextInt(0,12) + 1);
                boolean isForedragsholder = new Random().nextBoolean();
                LocalDate fraDato = generateRandomDate();
                LocalDate tiLDato = fraDato.plusDays(new Random().nextInt(1,7) + 1);
                Hotel hotel = hotelArrayList.get(new Random().nextInt(0, hotelArrayList.size() - 1) + 1);

                Controller.opretTilmelding(konference, deltager, isForedragsholder, null,
                        fraDato, tiLDato, new ArrayList<>(), hotel, new ArrayList<>());
            }
        }
    }

    //======================================================================
    // Hjælpemetoder
    private static String generateBuildingNumber(){
        return String.valueOf(new Random().nextInt(0, 150) + 1);
    }

    private static LocalDate generateRandomDate(){
        int day = new Random().nextInt(1,30) + 1;
        int month = new Random().nextInt(1,12) + 1;
        int year = new Random().nextInt(2025, 2027) + 1;
        if(month == 2 && day > 28){
            return LocalDate.of(year, month, 28);
        }
        else if(Arrays.asList(2, 4, 6, 9, 11).contains(month) && day > 30) {
            return LocalDate.of(year, month, 30);
        }
        else {
            return LocalDate.of(year, month, day);
        }
    }

    private static double generatePricePerDay(){
        return new Random().nextDouble(250,2500) + 1;
    }

    private static double generateUdflugtPrice(){
        return new Random().nextDouble(50,300) + 1;

    }

    private static String generatePhoneNumber(){
        return String.valueOf(new Random().nextInt(10_00_00_00, 99_99_99_99) + 1);
    }
}
