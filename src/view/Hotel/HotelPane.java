package view.Hotel;

import domain.model.Hotel;
import domain.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import storage.Storage;

import java.util.Optional;

public class HotelPane extends GridPane {
    // Listviews
    private final ListView<Hotel> hotellerListView = new ListView<>();
    private final ListView<String> tilmeldingerListView = new ListView<>();

    // Buttons
    Button opretHotelButton = new Button("Opret hotel");
    Button opdaterHotelButton = new Button("Opdater hotel");
    Button sletHotelButton = new Button("Slet hotel");

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.NEVER); // Kolonner skalerer ikke unødvendigt
        colConstraints.setMinWidth(Control.USE_COMPUTED_SIZE); // Brug kun nødvendig plads
        this.getColumnConstraints().addAll(
                new ColumnConstraints(186), // Opret hotel
                new ColumnConstraints(186), // Opdater hotel
                new ColumnConstraints(186) // Slet hotel
        );
        setHotellerListView();
        setTilmeldingerListView();
        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        hotellerListView.getSelectionModel().selectedItemProperty().addListener(listener);

        this.add(opretHotelButton, 0, 2);
        opretHotelButton.setOnAction(event -> opretHotelAction());

        GridPane.setHalignment(opdaterHotelButton, HPos.CENTER);
        this.add(opdaterHotelButton, 1, 2);

        GridPane.setHalignment(sletHotelButton, HPos.RIGHT);
        this.add(sletHotelButton, 2, 2);
        sletHotelButton.setOnAction(event -> sletHotelAction());

    }

    private void setHotellerListView() {
        this.add(new Label("Vælg et hotel:"), 0, 0);
        this.add(hotellerListView, 0, 1, 3, 1);
        hotellerListView.getItems().setAll(Storage.getHoteller());
    }

    private void setTilmeldingerListView() {
        this.add(new Label("Deltagere reserveret på det valgte hotel:"), 3, 0);
        tilmeldingerListView.setPrefWidth(598);
        this.add(tilmeldingerListView, 3, 1, 3, 1);
    }

    private void selectedHotelChanged(Hotel newHotel) {
        if (newHotel != null) {
            for (Tilmelding tilmelding : newHotel.getTilmeldinger()) {
                String s = Utility.buildInfoOnHotelTilmelding(tilmelding);
                tilmeldingerListView.getItems().add(s);
            }
        }
    }

    private void opretHotelAction() {
        new HotelWindow("Opret hotel").showAndWait();
        hotellerListView.getItems().setAll(Storage.getHoteller());
    }

    private void sletHotelAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet hotel");
            alert.setContentText("Er du sikker på du vil slette " + hotel.getNavn() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Storage.removeHotel(hotel);
                hotellerListView.getItems().setAll(Storage.getHoteller());
                tilmeldingerListView.getItems().setAll();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Slet hotel");
            alert.setHeaderText("Du har ikke valgt et hotel i listen.");
            // wait for the modal dialog to close
            alert.show();
        }
    }
}

