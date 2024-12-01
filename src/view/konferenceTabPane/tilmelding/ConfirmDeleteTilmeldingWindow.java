package view.konferenceTabPane.tilmelding;

import domain.controller.Controller;
import domain.model.Konference;
import domain.model.Tilmelding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import storage.Storage;

import java.util.ArrayList;

public class ConfirmDeleteTilmeldingWindow extends Stage {
    private final Konference konference;
    private final Tilmelding tilmeldingTilFjernelse;
    private final Button bekræftButton = new Button("OK");
    private final Button afbrydButton = new Button("Afbryd");

    public ConfirmDeleteTilmeldingWindow(Tilmelding tilmelding, Konference konference) {
        this.setTitle("Bekræft fjernelse af udflugt");
        GridPane pane = new GridPane();
        this.konference = konference;
        this.tilmeldingTilFjernelse = tilmelding;
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        HBox infoBox = new HBox(1);
        Label information = new Label("Bekræft tilmeldingsfjernelse af: ");
        Label deltagerNavn = new Label(tilmeldingTilFjernelse.getDeltager().getFuldeNavn());
        infoBox.getChildren().addAll(information, deltagerNavn);
        pane.add(infoBox, 1,0);

        pane.add(bekræftButton, 1, 1);
        bekræftButton.setOnAction(event -> {
            bekræftFjernelseAction();
            hide();
        });

        pane.add(afbrydButton, 2, 1);
        afbrydButton.setOnAction(event -> hide());
    }

    // Ikke færdigudviklet
    private void bekræftFjernelseAction(){
        if(tilmeldingTilFjernelse != null){
            Controller.fjernTilmelding(tilmeldingTilFjernelse, konference);
            System.out.println("Fjern tilmelding bekræftet");
        }
    }
}
