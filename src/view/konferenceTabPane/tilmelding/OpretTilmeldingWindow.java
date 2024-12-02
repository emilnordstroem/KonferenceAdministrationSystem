package view.konferenceTabPane.tilmelding;

import domain.controller.Controller;
import domain.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.Storage;
import view.deltagerTabPane.SearchAlgorithm;
import java.time.LocalDate;
import java.util.ArrayList;
import static view.errorHandling.Error.conflictingDates;

public class OpretTilmeldingWindow extends Stage {
    private Konference konferenceTilTilmelding;

    private final ComboBox<Deltager> deltagerComboBox = new ComboBox<>();
    private final Label firmaNavnLabel = new Label();
    private final ToggleButton foredragsholderButton = new ToggleButton("X");
    private final DatePicker ankomstDato = new DatePicker();
    private final DatePicker afrejseDato = new DatePicker();

    private final TextField ledsagerNavnTextField = new TextField();
    private final ListView<Udflugt> udflugtListView = new ListView<>();

    private final ListView<Hotel> hotelListView = new ListView<>();
    private final ListView<HotelTillæg> hotelTillægListView = new ListView<>();

    private Label errorLabel = new Label();

    private Button opretButton = new Button("Opret");
    private Button cancelButton = new Button("Cancel");


    public OpretTilmeldingWindow(Konference konference){
        this.setTitle("Opret tilmelding");
        GridPane pane = new GridPane();
        this.konferenceTilTilmelding = konference;
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        elementLayout(pane);
        elementContent();


        opretButton.setOnAction(event -> {
            Deltager deltager = deltagerComboBox.getSelectionModel().getSelectedItem();
            LocalDate fraDato = ankomstDato.getValue();
            LocalDate tilDato = afrejseDato.getValue();

            // Error preventions
            if(conflictingDates(fraDato, tilDato)){
                 System.out.println("Fejl i datepicker - fra dato efter til dato");
                 errorLabel.setText("Den valgte fra dato kommer efter den valgte til dato");
            } else if (deltager == null) {
                System.out.println("Ingen deltager valgt");
                errorLabel.setText("Vælg deltager til tilmelding");
            } else {
                opretTilmeldingAction(deltager, fraDato, tilDato);
                hide();
            }
        });

        cancelButton.setOnAction(event -> hide());
    }

    private void elementLayout(GridPane pane){
        Label konferenceInfoLabel = new Label(konferenceTilTilmelding.toString());
        Label deltagerInfoLabel = new Label("Deltagerinformation");
        pane.add(konferenceInfoLabel, 0,0,2,1);
        pane.add(deltagerInfoLabel, 0,1);
        pane.add(deltagerComboBox,0,2);

        Label tilhørendeFirmaLabel = new Label("Tilhørende firma:");
        HBox firmaHBox = new HBox(tilhørendeFirmaLabel, firmaNavnLabel);
        pane.add(firmaHBox, 0,3);

        Label foredragsholderLabel = new Label("Foredragsholder?");
        HBox foredragsholderHBox = new HBox(foredragsholderLabel, foredragsholderButton);
        pane.add(foredragsholderHBox,0,4);

        Label ankomstDatoLabel = new Label("Ankomstdato:");
        VBox ankomstdatoVBox = new VBox(ankomstDatoLabel, ankomstDato);
        Label afrejseDatoLabel = new Label("Afrejsedato");
        VBox afrejseDatoVBox = new VBox(afrejseDatoLabel, afrejseDato);
        HBox datoHBox = new HBox(ankomstdatoVBox, afrejseDatoVBox);
        pane.add(datoHBox, 0,5);

        Label programForLedsagereLabel = new Label("Program for ledsagere");
        Label ledsagerNavnLabel = new Label("Ledsager:");
        HBox ledsagerHBox = new HBox(ledsagerNavnLabel, ledsagerNavnTextField);
        pane.add(programForLedsagereLabel, 0,7);
        pane.add(ledsagerHBox, 0,8);
        pane.add(udflugtListView,0,9);
        udflugtListView.setPrefHeight(100);
        udflugtListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        udflugtListView.setDisable(true);
        ledsagerNavnTextField.setOnMouseClicked(event -> udflugtListView.setDisable(false));

        Label hotelmulighederLabel = new Label("Overnatningsmuligheder");
        HBox hotelTilvalgHBox = new HBox(hotelListView, hotelTillægListView);
        hotelTillægListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hotelListView.setPrefHeight(100);
        hotelTillægListView.setPrefHeight(100);
        pane.add(hotelmulighederLabel, 0,10);
        pane.add(hotelTilvalgHBox, 0,11);

        HBox buttonHBox = new HBox(opretButton, cancelButton);
        pane.add(errorLabel, 0,12);
        pane.add(buttonHBox, 0,13);
    }

    private void elementContent(){
        ArrayList<Deltager> ikkeTilmeldteDeltagere = ikkeTilmeldteDeltagere();
        ArrayList<Deltager> sorteretDeltagerArrayList = SearchAlgorithm.sortedStringArray(ikkeTilmeldteDeltagere);

        deltagerComboBox.getItems().setAll(sorteretDeltagerArrayList);
        ChangeListener<Deltager> deltagerListener = (ov, oldKonference, valgtDeltager) -> this.selectedDeltagerChanged(valgtDeltager);
        deltagerComboBox.getSelectionModel().selectedItemProperty().addListener(deltagerListener);

        udflugtListView.getItems().setAll(konferenceTilTilmelding.getUdflugter());
        hotelListView.getItems().setAll(konferenceTilTilmelding.getHoteller());

        ChangeListener<Hotel> hotelListener = (ov, oldKonference, valgtHotel) -> this.selectedHotelChanged(valgtHotel);
        hotelListView.getSelectionModel().selectedItemProperty().addListener(hotelListener);
    }

    private void selectedDeltagerChanged(Deltager deltager){
        if(deltager.getFirma() != null){
            firmaNavnLabel.setText(deltager.getFirma().toString());
        }
    }

    private void selectedHotelChanged(Hotel hotel){
        if(hotel != null){
            hotelTillægListView.getItems().setAll(hotel.getHotelTillæger());
        }
    }

    private ArrayList<Deltager> ikkeTilmeldteDeltagere(){
        ArrayList<Deltager> alleDeltagere = Storage.getDeltagere();
        ArrayList<Deltager> ikkeTilmeldteDeltagere = new ArrayList<>();

        // Sortere deltagere som allerade er tilmeldt fra
        for(Deltager deltager : alleDeltagere){
            if(!deltager.getTilmeldinger().isEmpty()){
                for(Tilmelding tilmelding : deltager.getTilmeldinger()){
                    if(tilmelding.getKonference() != konferenceTilTilmelding){
                        ikkeTilmeldteDeltagere.add(deltager);
                    }
                }
            } else {
                ikkeTilmeldteDeltagere.add(deltager);
            }
        }

        return ikkeTilmeldteDeltagere;
    }

    private void opretTilmeldingAction(Deltager deltager, LocalDate fraDato, LocalDate tilDato){
        Boolean erForedragsholder = foredragsholderButton.isSelected();
        String ledsager = ledsagerNavnTextField.getText();
        ArrayList<Udflugt> valgteUdflugterList = new ArrayList<>(udflugtListView.getSelectionModel().getSelectedItems());
        Hotel hotel = hotelListView.getSelectionModel().getSelectedItem();
        ArrayList<HotelTillæg> valgteHotelTillægs = new ArrayList<>(hotelTillægListView.getSelectionModel().getSelectedItems());

        Tilmelding tilmelding = Controller.opretTilmelding(konferenceTilTilmelding, deltager, erForedragsholder, ledsager,
                fraDato, tilDato, valgteUdflugterList, hotel, valgteHotelTillægs);
        System.out.printf("Tilmeldling oprettet: " + tilmelding);
    }
}
