package view.hotelTabPane;

import domain.model.Hotel;
import domain.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    Button opretHotelTillægButton = new Button("Opret hoteltillæg");
    Button opdaterHotelTillægButton = new Button("Opdater hoteltillæg");
    Button sletHotelTillægButton = new Button("Slet hoteltillæg");

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        setHotellerListView();
        setTilmeldingerListView();
        setButtons();
    }

    private void setHotellerListView() {
        this.add(new Label("Vælg et hotel:"), 0, 0);
        this.add(hotellerListView, 0, 1, 6, 1);
        hotellerListView.getItems().setAll(Storage.getHoteller());
        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        hotellerListView.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void setTilmeldingerListView() {
        this.add(new Label("Deltagere reserveret på det valgte hotel:"), 6, 0);
        tilmeldingerListView.setPrefWidth(540);
        this.add(tilmeldingerListView, 6, 1, 3, 1);
    }

    private void setButtons() {
        this.add(opretHotelButton, 0, 2);
        opretHotelButton.setOnAction(event -> opretHotelAction());

        this.add(opdaterHotelButton, 1, 2);
        opdaterHotelButton.setOnAction(event -> opdaterHotelAction());

        this.add(sletHotelButton, 2, 2);
        sletHotelButton.setOnAction(event -> sletHotelAction());

        this.add(opretHotelTillægButton, 3, 2);
        opretHotelTillægButton.setOnAction(event -> opretHotelTillægAction());

        this.add(opdaterHotelTillægButton, 4, 2);
        opdaterHotelTillægButton.setOnAction(event -> opdaterHotelTillæg());

        this.add(sletHotelTillægButton, 5, 2);
        sletHotelTillægButton.setOnAction(event -> sletHotelTillægAction());
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
        new HotelWindow("Opret").showAndWait();
        hotellerListView.getItems().setAll(Storage.getHoteller());
    }

    private void opdaterHotelAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            new HotelWindow("Opdater", hotel).showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opdater hotel");
            alert.setHeaderText("Du har ikke valgt et hotel i listen.");
            // wait for the modal dialog to close
            alert.show();
        }
        hotellerListView.refresh();
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

    private void opretHotelTillægAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            new HotelTillægWindow("Opret", hotel).showAndWait();
            hotellerListView.refresh();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opret hotel");
            alert.setHeaderText("Du har ikke valgt et hotel i listen.");
            // wait for the modal dialog to close
            alert.show();
        }
    }

    private void opdaterHotelTillæg() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            new HotelTillægWindow("Opdater", hotel).showAndWait();
            hotellerListView.refresh();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opdater hotel");
            alert.setHeaderText("Du har ikke valgt et hotel i listen.");
            // wait for the modal dialog to close
            alert.show();
        }
    }

    private void sletHotelTillægAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            new HotelTillægWindow("Slet", hotel).showAndWait();
            hotellerListView.refresh();
            tilmeldingerListView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Slet hotel");
            alert.setHeaderText("Du har ikke valgt et hotel i listen.");
            // wait for the modal dialog to close
            alert.show();
        }
    }
}

