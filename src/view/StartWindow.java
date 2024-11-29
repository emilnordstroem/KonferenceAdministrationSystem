package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.Deltager.DeltagerPane;
import view.Hotel.HotelPane;
import view.konference.KonferencePane;

public class StartWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Konference Administration System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tabKonferencer = new Tab("Konferencer");
        tabPane.getTabs().add(tabKonferencer);

        KonferencePane konferencePane = new KonferencePane();
        tabKonferencer.setContent(konferencePane);

        Tab tabHoteller = new Tab("Hoteller");
        tabPane.getTabs().add(tabHoteller);

        HotelPane hotelPane = new HotelPane();
        tabHoteller.setContent(hotelPane);

        Tab tabDeltagere = new Tab("Deltagere");
        tabPane.getTabs().add(tabDeltagere);

        DeltagerPane deltagerPane = new DeltagerPane();
        tabDeltagere.setContent(deltagerPane);

    }
}
