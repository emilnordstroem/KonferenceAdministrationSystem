package domain.controller;

import domain.model.Deltager;
import domain.model.Konference;
import domain.model.Tilmelding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Konference havOgHimmel = Controller.opretKonference("Hav og himmel", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);

        Deltager finn = Controller.opretDeltager("Finn Madsen", null, null);
        Controller.opretTilmelding(havOgHimmel, finn, false, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), null, new ArrayList<>(), null, new ArrayList<>());
        System.out.println(finn.getSamletUdgifter());
    }
}
