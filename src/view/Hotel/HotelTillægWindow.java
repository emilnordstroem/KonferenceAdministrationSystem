package view.Hotel;

import domain.controller.Controller;
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

import java.util.InputMismatchException;

public class HotelTillægWindow extends Stage {
    private final ComboBox<HotelTillæg> hotelTillægComboBox = new ComboBox<>();
    private final TextField navnTextField = new TextField();
    private final TextField prisTextField = new TextField();
    private final Button okButton;
    private final Button cancelButton = new Button("Cancel");
    private final Label errorLabel = new Label();
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
        if(title.equals("Opret")) {
            hotelTillægComboBox.setDisable(true);
        }
        hotelTillægComboBox.setOnAction(event -> setTextFields());
    }

    private void setNavnOgPris(GridPane pane) {
        pane.add(new Label("Navn:"), 0, 1);
        pane.add(navnTextField, 1, 1);

        pane.add(new Label("Pris"), 0, 2);
        pane.add(prisTextField, 1, 2);

        if(title.equals("Slet")) {
            navnTextField.setDisable(true);
            prisTextField.setDisable(true);
        }
    }

    private void setTextFields() {
        HotelTillæg hotelTillæg = hotelTillægComboBox.getSelectionModel().getSelectedItem();
        if(hotelTillæg != null) {
            navnTextField.setText(hotelTillæg.getNavn());
            prisTextField.setText(String.valueOf(hotelTillæg.getPris()));
        }
    }

    private void setButtons(GridPane pane) {
        pane.add(cancelButton, 0,3);
        cancelButton.setOnAction(event -> hide());

        pane.add(okButton, 1,3);
        GridPane.setHalignment(okButton, HPos.RIGHT);
        okButton.setOnAction(event -> okAction());

        pane.add(errorLabel, 0, 4);
        errorLabel.setStyle("-fx-text-fill: red; -fx-pref-width: 200px");
    }
    

    private void okAction() {
        String navnInput = navnTextField.getText().trim();
        String prisInput = prisTextField.getText().trim();
        HotelTillæg hotelTillæg = hotelTillægComboBox.getValue();

        if(Utility.blankTextField(navnInput, prisInput)) {
            errorLabel.setText("Ikke alle felter er udfyldt");
            return;
        }

        try {
            double pris = Double.parseDouble(prisInput);
            if(hotelTillæg != null && title.equals("Opdater")) {
                hotelTillæg.setNavn(navnInput);
                hotelTillæg.setPris(pris);
            }
            else if(hotelTillæg != null && title.equals("Slet")) {
                hotel.removeHotelTillæg(hotelTillæg);
            }
            else {
                Controller.opretHotelTillæg(navnInput, pris, hotel);
            }
        }
        catch (NumberFormatException ex) {
            errorLabel.setText("Prisen skal være et positivt tal.");
            return;
        }
        catch (InputMismatchException ex) {
            errorLabel.setText(ex.getMessage());
            return;
        }
        hide();
    }
}
