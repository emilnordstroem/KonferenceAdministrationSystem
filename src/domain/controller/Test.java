package domain.controller;

import domain.model.*;
import storage.*;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate startDato = LocalDate.of(2025, 5, 1);
        LocalDate slutDato = LocalDate.of(2025, 5, 7);
        Konference miljøKonference = new Konference("Miljø 2.0", startDato, slutDato, 75);
        System.out.println(miljøKonference.toString());


    }
}
