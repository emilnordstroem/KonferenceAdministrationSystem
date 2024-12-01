package view.konferenceTabPane.konference;

import domain.controller.Controller;
import domain.model.Hotel;
import domain.model.Udflugt;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import storage.Storage;
import view.konferenceTabPane.konference.udflugt.ConfirmDeleteUdflugtWindow;
import view.konferenceTabPane.konference.udflugt.OpretUdflugtWindow;

import java.time.LocalDate;
import java.util.ArrayList;

import static view.Error.blankTextField;
import static view.Error.conflictingDates;

public class OpretKonferenceWindow extends Stage {
    private final TextField navnTextField = new TextField();
    private final TextField prisPrDagTextField = new TextField();

    private final DatePicker fraDatoPicker = new DatePicker();;
    private final DatePicker tilDatoPicker = new DatePicker();;

    private final ListView<Hotel> hotelListView = new ListView<>();

    private final Button opretUdflugtButton = new Button("Opret");
    private final Button sletUdflugtButton = new Button("Fjern");
    private final ListView<Udflugt> udflugtListView = new ListView<>();

    private Label errorLabel = new Label();

    private Button opretButton = new Button("Opret");
    private Button cancelButton = new Button("Cancel");

    public OpretKonferenceWindow(){
    this.setTitle("Opret konference");
//        initStyle(StageStyle.UTILITY);
//        initModality(Modality.APPLICATION_MODAL);
//        setResizable(false);
    GridPane pane = new GridPane();
    initContent(pane);
    this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        setElementLayout(pane);
        setHotelComboBox();

        opretUdflugtButton.setOnAction(event -> {
            System.out.println("opretUdflugtButton clicked");
            opretUdflugt();
        });

        sletUdflugtButton.setOnAction(event -> {
            System.out.println("sletUdflugtButton clicked");
            sletUdflugt();
        });

        opretButton.setOnAction(event -> {
            String navn = navnTextField.getText();
            String afgift = prisPrDagTextField.getText();
            LocalDate fraDato = fraDatoPicker.getValue();
            LocalDate tilDato = tilDatoPicker.getValue();

            // Error preventions
            if(!blankTextField(navn, afgift)){
                System.out.println("Ikke alle konference felter er udfyldt (navn og afgift)");
                errorLabel.setText("Ikke alle felter er udfyldt");
            } else if (conflictingDates(fraDato, tilDato)) {
                System.out.println("Fejl i datepicker - fra dato efter til dato");
                errorLabel.setText("Den valgte fra dato kommer efter den valgte til dato");
            } else {
                opretKonferenceAction(navn, Double.parseDouble(afgift), fraDato, tilDato);
                hide();
            }
        });
        cancelButton.setOnAction(event -> hide());
    }

    private void setElementLayout(GridPane pane){
        Label konferenceNavnLabel = new Label("Konferencens navn");
        pane.add(konferenceNavnLabel, 0, 0, 2, 1);
        pane.add(navnTextField, 0, 1, 2, 1);

        Label prisPrDagLabel = new Label("Afgift pr. dag");
        pane.add(prisPrDagLabel, 0, 2, 2, 1);
        pane.add(prisPrDagTextField, 0, 3, 2, 1);

        Label datoLabel = new Label("Vælg start og slut dato");
        pane.add(datoLabel,0,4, 2, 1);
        pane.add(fraDatoPicker,0,5, 2, 1);
        pane.add(tilDatoPicker,0,6, 2, 1);

        Label hotelLabel = new Label("Marker tilgængeligt hotel");
        pane.add(hotelLabel, 0,7,2,1);
        pane.add(hotelListView,0,8,2,1);
        hotelListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hotelListView.setPrefHeight(100);

        HBox buttonHBox= new HBox(1);
        Label udflugtLabel = new Label("Opret udflugter");
        buttonHBox.getChildren().addAll(udflugtLabel, opretUdflugtButton, sletUdflugtButton);
        pane.add(buttonHBox,0,9);
        pane.add(udflugtListView, 0,10,2,1);
        udflugtListView.setPrefHeight(100);

        pane.add(opretButton,0,12);
        pane.add(cancelButton,1,12);
        pane.add(errorLabel, 0, 11);
        errorLabel.setStyle("-fx-text-fill: red");
    }

    private void setHotelComboBox(){
        hotelListView.getItems().addAll(Storage.getHoteller());
    }

    private void opretUdflugt(){
        System.out.println("opretUdflugt metode kører");
        new OpretUdflugtWindow().showAndWait();
        udflugtListView.getItems().setAll(Storage.getUdflugter());
    }

    private void sletUdflugt(){
        new ConfirmDeleteUdflugtWindow(udflugtListView.getSelectionModel().getSelectedItem()).showAndWait();
        udflugtListView.getItems().setAll(Storage.getUdflugter());
    }

    private void opretKonferenceAction(String navn, double pris, LocalDate fraDato, LocalDate tilDato){
        ObservableList<Hotel> valgteHotellerListView = hotelListView.getSelectionModel().getSelectedItems();
        ArrayList<Hotel> valgteHotellerArrayList = new ArrayList<>(valgteHotellerListView);
        if(valgteHotellerArrayList.isEmpty()) {
            System.out.println("Ingen hoteller valgt i oprettelse af konference");
            errorLabel.setText("Vælg tilgængelige hoteller");
        } else {
            Controller.opretKonference(navn, fraDato, tilDato, pris, valgteHotellerArrayList);
        }
    }
}
