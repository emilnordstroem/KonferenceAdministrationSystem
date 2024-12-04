package view.errorHandling;

public class Alert extends javafx.scene.control.Alert {

    public Alert(AlertType alertType, String title, String beskrivelse){
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(beskrivelse);
        this.show();
    }
}