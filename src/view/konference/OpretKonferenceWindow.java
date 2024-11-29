package view.konference;

import domain.model.Hotel;
import domain.model.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import storage.Storage;

import java.time.LocalDate;

import static view.Error.blankTextField;
import static view.Error.conflictingDates;

public class OpretKonferenceWindow extends Stage {
    private final TextField navnTextField = new TextField();
    private final TextField prisPrDagTextField = new TextField();

    private final DatePicker fraDatoPicker = new DatePicker();;
    private final DatePicker tilDatoPicker = new DatePicker();;

    private final ComboBox<Hotel> hotelComboBox = new ComboBox<>();

    private final Button opretUdflugtButton = new Button("Opret");
    private final ComboBox<Udflugt> udflugtComboBox = new ComboBox<>();

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

        opretButton.setOnAction(event -> {
            String navn = navnTextField.getText();
            String afgift = prisPrDagTextField.getText();
            LocalDate fraDato = fraDatoPicker.getValue();
            LocalDate tilDato = tilDatoPicker.getValue();

            if(!blankTextField(navn, afgift)){
                System.out.println("Ikke alle konference felter er udfyldt (navn og afgift)");
                errorLabel.setText("Ikke alle felter er udfyldt");
            } else if (conflictingDates(fraDato, tilDato)) {
                System.out.println("Fejl i datepicker - fra dato efter til dato");
                errorLabel.setText("Den valgte fra dato kommer efter den valgte til dato");
            } else {
                opretKonferenceAction(navn, afgift, fraDato, tilDato);
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
        pane.add(hotelComboBox,0,8,2,1);

        Label udflugtLabel = new Label("Opret udflugter");
        pane.add(udflugtLabel, 0,9, 2,1);
        pane.add(opretUdflugtButton, 1,9,2,1);
        pane.add(udflugtComboBox, 0,10,2,1);

        pane.add(opretButton,0,12);
        pane.add(cancelButton,1,12);
        pane.add(errorLabel, 0, 11);
        errorLabel.setStyle("-fx-text-fill: red");
    }

    private void setHotelComboBox(){
        hotelComboBox.getItems().addAll(Storage.getHoteller());
    }

    private void opretUdflugt(){
        System.out.println("opretUdflugt metode kører");
        new OpretUdflugtWindow().showAndWait();
        udflugtComboBox.getItems().setAll(Storage.getUdflugter());
    }

    private void opretKonferenceAction(String navn, String pris, LocalDate fraDato, LocalDate tilDato){


    }
}
