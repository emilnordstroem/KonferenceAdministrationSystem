package view;

import domain.model.Konference;
import domain.model.Tilmelding;
import domain.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import storage.Storage;

import java.util.ArrayList;

public class KonferencePane extends GridPane {
    private final ListView<Konference> konferencerListView;
    private final ListView<Tilmelding> tilmeldingerListView;
    private final ListView<Udflugt> udflugterListView;


    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Columns constraints
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.NEVER); // Kolonner skalerer ikke unødvendigt
        colConstraints.setMinWidth(Control.USE_COMPUTED_SIZE); // Brug kun nødvendig plads
        this.getColumnConstraints().addAll(
                new ColumnConstraints(125), // Opret konference
                new ColumnConstraints(125), // Opdater konference
                new ColumnConstraints(125), // Slet konference
                new ColumnConstraints(125),  // Opret tilmelding
                new ColumnConstraints(125), // Opdater tilmelding
                new ColumnConstraints(125), // Slet tilmelding
                new ColumnConstraints(100),  // Opret udflugt
                new ColumnConstraints(105), // Opdater udflugt
                new ColumnConstraints(100) // Slet udflugt
        );


        // Listviews and respective labels
        this.add(new Label("Vælg en konference:"), 0, 0);
        konferencerListView = new ListView<>();
        this.add(konferencerListView, 0,1,3,1);
        konferencerListView.getItems().setAll(Storage.getKonferencer());
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged(newKonference);
        konferencerListView.getSelectionModel().selectedItemProperty().addListener(listener);

        Label deltagereLabel = new Label("Deltagere tilmeldt til den valgte konference:");
        deltagereLabel.setMinWidth(250);
        this.add(deltagereLabel, 3, 0);
        tilmeldingerListView = new ListView<>();
        this.add(tilmeldingerListView, 3, 1, 3, 1);

        Label udflugterLabel = new Label("Udflugter som den valgte konfernece tilbyder:");
        this.add(udflugterLabel, 6, 0);
        udflugterLabel.setMinWidth(250);
        udflugterListView = new ListView<>();
        this.add(udflugterListView, 6, 1, 3, 1);

        Button createKonferenceButton = new Button("Opret konference");
        this.add(createKonferenceButton, 0, 2);

        Button opdaterKonferenceButton = new Button("Opdater konference");
        GridPane.setHalignment(opdaterKonferenceButton, HPos.RIGHT);
        this.add(opdaterKonferenceButton, 1, 2);

        Button sletKonferenceButton = new Button("Slet konference");
        GridPane.setHalignment(sletKonferenceButton, HPos.RIGHT);
        this.add(sletKonferenceButton, 2, 2);

        Button opretTilmeldingButton = new Button("Opret tilmelding");
        this.add(opretTilmeldingButton, 3,2);

        Button opdaterTilmeldingButton = new Button("Opdater tilmelding");
        GridPane.setHalignment(opdaterTilmeldingButton, HPos.RIGHT);
        this.add(opdaterTilmeldingButton, 4, 2);

        Button sletTilmeldingButton = new Button("Slet tilmelding");
        GridPane.setHalignment(sletTilmeldingButton, HPos.RIGHT);
        this.add(sletTilmeldingButton, 5, 2);

        Button createUdflugtButton = new Button("Opret udflugt");
        this.add(createUdflugtButton, 6, 2);

        Button opdaterUdflugtButton = new Button("Opdater udflugt");
        GridPane.setHalignment(opdaterUdflugtButton, HPos.RIGHT);
        this.add(opdaterUdflugtButton, 7, 2);

        Button sletUdFlugtButton = new Button("Slet udflugt");
        GridPane.setHalignment(sletUdFlugtButton, HPos.RIGHT);
        this.add(sletUdFlugtButton, 8, 2);
    }

    // Funktionel kode
    private void selectedKonferenceChanged(Konference newKonference) {
        if(newKonference != null) {
            ArrayList<Tilmelding> sorteretTilmeldingArrayList = newKonference.getTilmeldinger();
            sorteretTilmeldingArrayList.sort((o1, o2) -> {
                if(o1.getDeltager().getEfterNavn().compareTo(o2.getDeltager().getEfterNavn()) == 0) {
                    return o1.getDeltager().getForNavn().compareTo(o2.getDeltager().getForNavn());
                }

                return o1.getDeltager().getEfterNavn().compareTo(o2.getDeltager().getEfterNavn());
            });
            tilmeldingerListView.getItems().setAll(sorteretTilmeldingArrayList);

            udflugterListView.getItems().setAll(newKonference.getUdflugter());
        }
    }
}
