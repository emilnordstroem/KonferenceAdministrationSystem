package view.konferenceTabPane.konference;

import domain.controller.Controller;
import domain.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConfirmDeleteKonferenceWindow extends Stage {
    private final Konference konferenceTilFjernelse;
    private final Button bekræftButton = new Button("OK");
    private final Button afbrydButton = new Button("Afbryd");

    public ConfirmDeleteKonferenceWindow(Konference konference) {
        this.setTitle("Bekræft fjernelse af udflugt");
        GridPane pane = new GridPane();
        this.konferenceTilFjernelse = konference;
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        HBox infoBox = new HBox(1);
        Label information = new Label("Bekræft fjernelse af: ");
        Label deltagerNavn = new Label(konferenceTilFjernelse.getNavn());
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

    private void bekræftFjernelseAction(){
        Controller.fjernKonference(konferenceTilFjernelse);
        System.out.printf("%s er nu fjernet fra Storage%n", konferenceTilFjernelse.getNavn());
    }
}

