package view.hotelTabPane;

import domain.controller.ControllerHotelTillæg;
import domain.model.Hotel;
import domain.model.HotelTillæg;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.errorHandling.Alert;
import view.errorHandling.Error;

import java.util.InputMismatchException;

public class HotelTillægWindow extends Stage {
    private final ComboBox<HotelTillæg> hotelTillægComboBox = new ComboBox<>();
    private final TextField navnTextField = new TextField();
    private final TextField prisTextField = new TextField();
    private final Button okButton;
    private final Button cancelButton = new Button("Cancel");
    private final String title;
    private final Hotel hotel;

    public HotelTillægWindow(String title, Hotel hotel) {
        this.title = title;
        this.hotel = hotel;
        this.okButton = new Button(title);
        this.setTitle(title + " hoteltillæg ved " + hotel);
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        setComboBox(pane);
        setNavnOgPris(pane);
        setButtons(pane);
    }

    private void setComboBox(GridPane pane) {
        pane.add(new Label("Vælg et hoteltillæg:"), 0, 0);
        pane.add(hotelTillægComboBox, 1, 0);
        hotelTillægComboBox.getItems().setAll(hotel.getHotelTillæger());
        if (title.equals("Opret")) {
            hotelTillægComboBox.setDisable(true);
        }
        hotelTillægComboBox.setOnAction(event -> setTextFields());
    }

    private void setNavnOgPris(GridPane pane) {
        pane.add(new Label("Navn:"), 0, 1);
        pane.add(navnTextField, 1, 1);

        pane.add(new Label("Pris"), 0, 2);
        pane.add(prisTextField, 1, 2);

        if (title.equals("Slet")) {
            navnTextField.setDisable(true);
            prisTextField.setDisable(true);
        }
    }

    private void setTextFields() {
        HotelTillæg hotelTillæg = hotelTillægComboBox.getSelectionModel().getSelectedItem();
        if (hotelTillæg != null) {
            navnTextField.setText(hotelTillæg.getNavn());
            prisTextField.setText(String.format("%.2f", hotelTillæg.getPris()));
        }
    }

    private void setButtons(GridPane pane) {
        pane.add(cancelButton, 0, 3);
        cancelButton.setOnAction(event -> hide());

        pane.add(okButton, 1, 3);
        GridPane.setHalignment(okButton, HPos.RIGHT);
        okButton.setOnAction(event -> okAction());
    }

    private void okAction() {
        String navnInput = navnTextField.getText().trim();
        String prisInput = prisTextField.getText().trim();
        HotelTillæg hotelTillæg = hotelTillægComboBox.getValue();

        if (title.equals("Slet")) {
            if (hotelTillæg == null) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Slet hoteltillæg", "Du skal vælge et hoteltillæg at slette.");
                return;
            }
            ControllerHotelTillæg.fjernHotelTillæg(hotelTillæg, hotel);
            hide();
            return;
        }

        // Validation for "Opret" and "Opdater"
        if (navnInput.isEmpty()) {
            new Alert(javafx.scene.control.Alert.AlertType.ERROR, "Fejl", "Navn skal udfyldes.");
            return;
        }
        if (prisInput.isEmpty()) {
            new Alert(javafx.scene.control.Alert.AlertType.ERROR, "Fejl", "Pris skal udfyldes.");
            return;
        }

        try {
            double pris = Double.parseDouble(prisInput);

            // Check if the price is negative
            if (pris < 0) {
                new Alert(javafx.scene.control.Alert.AlertType.ERROR, "Fejl", "Prisen må ikke være negativ.");
                return;
            }

            if (title.equals("Opdater") && hotelTillæg != null) {
                ControllerHotelTillæg.opdaterHotelTillæg(hotelTillæg, navnInput, pris);
            } else {
                ControllerHotelTillæg.opretHotelTillæg(navnInput, pris, hotel);
            }
        } catch (NumberFormatException ex) {
            new Alert(javafx.scene.control.Alert.AlertType.ERROR, "Fejl", "Prisen skal være et positivt tal.");
            return;
        }

        hide();
    }
}

