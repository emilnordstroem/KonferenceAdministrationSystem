package view.hotelTabPane;

import domain.controller.ControllerHotel;
import domain.model.Adresse;
import domain.model.Hotel;
import domain.model.Konference;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import storage.Storage;
import view.errorHandling.Error;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.stream.Stream;

public class HotelWindow extends Stage {
    private final TextField navnTextField = new TextField();
    private final TextField vejNavnTextField = new TextField();
    private final TextField bygningsNrTextField = new TextField();
    private final TextField byTextField = new TextField();
    private final TextField landTextField = new TextField();
    private final TextField enkeltværelsesPrisTextField = new TextField();
    private final TextField dobbeltværelsesPrisTextField = new TextField();
    private final ListView<Konference> konferencerListView = new ListView<>();
    private final Button okBtn;
    private final Button cancelBtn = new Button("Cancel");
    private final Label errorLabel = new Label();
    private final Hotel hotel;

    public HotelWindow(String title, Hotel hotel) {
        this.hotel = hotel;
        this.okBtn = new Button(title);
        this.setTitle(title + " hotel");
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public HotelWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        setNavnOgAdresse(pane);
        setPriser(pane);
        setKonferencerListView(pane);
        setButtons(pane);

        setTextFieldsAndListView();
    }

    private void setNavnOgAdresse(GridPane pane) {
        Label navnLbl = new Label("Navn");
        pane.add(navnLbl, 0,0);
        pane.add(navnTextField, 0,1,2,1);

        Label adresseLbl = new Label("Adresse");
        pane.add(adresseLbl, 0, 2);

        Label vejNavnLabel = new Label("Vej:");
        pane.add(vejNavnLabel, 0, 3);
        pane.add(vejNavnTextField, 1, 3);

        Label bygningsNrLabel = new Label("Bygningsnr.:");
        pane.add(bygningsNrLabel, 0, 4);
        pane.add(bygningsNrTextField, 1, 4);

        Label byLabel = new Label("By:");
        pane.add(byLabel, 0, 5);
        pane.add(byTextField, 1, 5);

        Label landLabel = new Label("Land:");
        pane.add(landLabel, 0, 6);
        pane.add(landTextField, 1, 6);
    }

    private void setPriser(GridPane pane) {
        Label priserLbl = new Label("Priser");
        pane.add(priserLbl, 0, 7);

        Label enkeltværelsesPrisLbl = new Label("Enkeltværelsespris:");
        pane.add(enkeltværelsesPrisLbl, 0, 8);
        pane.add(enkeltværelsesPrisTextField, 1, 8);

        Label dobbeltværelsesPrisLbl = new Label("Dobbeltværelsespris:");
        pane.add(dobbeltværelsesPrisLbl, 0, 9);
        pane.add(dobbeltværelsesPrisTextField, 1, 9);
    }

    private void setKonferencerListView(GridPane pane) {
        Label konferencerLbl = new Label("Konferencer");
        pane.add(konferencerLbl, 0, 10);
        pane.add(konferencerListView, 0, 11, 2, 1);
        konferencerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        konferencerListView.setPrefHeight(200);
        konferencerListView.getItems().setAll(Storage.getKonferencer());
    }

    private void setButtons(GridPane pane) {
        pane.add(cancelBtn, 0, 12);
        cancelBtn.setOnAction(event -> hide());
        pane.add(okBtn, 1, 12);
        okBtn.setOnAction(event -> okAction());
        GridPane.setHalignment(okBtn, HPos.RIGHT);
        pane.add(errorLabel, 0, 13);
        errorLabel.setStyle("-fx-text-fill: red; -fx-pref-width: 200px");
    }

    private void okAction() {
        String navnInput = navnTextField.getText().trim();
        String enkeltværelsesPrisInput = enkeltværelsesPrisTextField.getText().trim();
        String dobbeltværelsesPrisInput = dobbeltværelsesPrisTextField.getText().trim();
        String vejInput = vejNavnTextField.getText().trim();
        String bygningNrInput = bygningsNrTextField.getText().trim();
        String byInput = byTextField.getText().trim();
        String landInput = landTextField.getText().trim();
        ArrayList<Konference> konferencer = new ArrayList<>(konferencerListView.getSelectionModel().getSelectedItems());

        if(Error.blankTextField(navnInput, enkeltværelsesPrisInput, dobbeltværelsesPrisInput, vejInput, bygningNrInput, byInput, landInput)){
            errorLabel.setText("Ikke alle felter er udfyldt");
            return;
        }

        Adresse adresse = new Adresse(vejInput, bygningNrInput, byInput, landInput);

        try {
            double enkeltværelsesPris = Double.parseDouble(enkeltværelsesPrisInput);
            double dobbeltværelsesPris = Double.parseDouble(dobbeltværelsesPrisInput);

            if(hotel != null) {
                ControllerHotel.opdaterHotel(navnInput, adresse, enkeltværelsesPris, dobbeltværelsesPris, konferencer);
            }
            else {
                ControllerHotel.opretHotel(navnInput, adresse, enkeltværelsesPris, dobbeltværelsesPris, konferencer);
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

    private void setTextFieldsAndListView() {
        if(hotel != null) {
            navnTextField.setText(hotel.getNavn());
            vejNavnTextField.setText(hotel.getAddresse().getVejNavn());
            bygningsNrTextField.setText(hotel.getAddresse().getBygningsNr());
            byTextField.setText(hotel.getAddresse().getBy());
            landTextField.setText(hotel.getAddresse().getLand());
            enkeltværelsesPrisTextField.setText(String.valueOf(hotel.getEnkeltVærelsesPris()));
            dobbeltværelsesPrisTextField.setText(String.valueOf(hotel.getDobbeltVærelsesPris()));

            // Chooses konferences that has previously been selected as konferences
            // that the deltagers can stay at
            for(int index = 0; index < Storage.getKonferencer().size(); index++) {
                Konference storageKonference = Storage.getKonferencer().get(index);
                if(hotel.getKonferencer().contains(storageKonference)) {
                    konferencerListView.getSelectionModel().selectIndices(index);
                }
            }
        }
        else {
            Stream.of(navnTextField, vejNavnTextField, bygningsNrTextField, byTextField, landTextField, enkeltværelsesPrisTextField, dobbeltværelsesPrisTextField).forEach(TextInputControl::clear);
        }
    }
}
