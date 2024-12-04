package view.deltagerTabPane;

import domain.model.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UdgiftdetaljeWindow extends Stage {
    private final Deltager deltager;
    private final Label deltagerInfoLabel = new Label();

    private final TextArea tilmeldtKonferenceTextArea = new TextArea();
    private final TextField samletUdgifterTextField = new TextField();

    private final Button lukButton = new Button("Luk");

    public UdgiftdetaljeWindow(Deltager valgteDeltager) {
        this.setTitle("Oversigt over udgifter");
        setResizable(false);
        GridPane pane = new GridPane();
        this.deltager = valgteDeltager;
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

        lukButton.setOnAction(event -> hide());
    }

    private void elementLayout(GridPane pane){
        pane.add(deltagerInfoLabel, 0,0,2,1);
        pane.add(tilmeldtKonferenceTextArea, 0,1,2,1);
        pane.add(samletUdgifterTextField, 0,2,2,1);
        tilmeldtKonferenceTextArea.setEditable(false);
        samletUdgifterTextField.setEditable(false);
        pane.add(lukButton, 0,3,2,1);
    }

    private void elementContent() {
        deltagerInfoLabel.setText(deltager.getFuldeNavn());
        StringBuilder udgiftListeStringBuilder = new StringBuilder();

        for (Tilmelding tilmelding : deltager.getTilmeldinger()) {
            udgiftListeStringBuilder.append(formatKonferenceInfo(tilmelding))
                    .append(formatHotelInfo(tilmelding))
                    .append(formatUdflugtInfo(tilmelding))
                    .append(formatTotalUdgift(tilmelding))
                    .append(formatFakturaInfo(tilmelding))
                    .append("================================================");
        }

        tilmeldtKonferenceTextArea.appendText(udgiftListeStringBuilder.toString());
        samletUdgifterTextField.setText(String.format("Samlet udgifter for alle tilmeldinger: %.2f DKK", deltager.getSamletUdgifter()));
    }

    private String formatKonferenceInfo(Tilmelding tilmelding) {
        return String.format("Konference: %s (%d konferencedage)%n - Foredragsholder: %b%nSamlet konferenceafgift: %.2f%n%n",
                tilmelding.getKonference().getNavn(),
                tilmelding.getAntalDageKonference(),
                tilmelding.isErForedragsholder(),
                tilmelding.udregnKonferenceAfgift(tilmelding.getAntalDageKonference()));
    }

    private String formatHotelInfo(Tilmelding tilmelding) {
        if (tilmelding.getHotel() == null) {
            return "";
        }

        StringBuilder hotelStringBuilder = new StringBuilder(String.format(" - Hotel: %s (%d overnatninger)%n",
                tilmelding.getHotel().getNavn(),
                tilmelding.getAntalDageOphold())
        );

        boolean isDobbeltVærelse = tilmelding.getLedsagerNavn() != null && !tilmelding.getLedsagerNavn().isEmpty();
        if (isDobbeltVærelse) {
            hotelStringBuilder.append(String.format("     - Dobbeltværelsepris: %.2f DKK (pr. nat)%n", tilmelding.getHotel().getDobbeltVærelsesPris()));
        } else {
            hotelStringBuilder.append(String.format("     - Enkeltværelsepris: %.2f DKK (pr. nat)%n", tilmelding.getHotel().getEnkeltVærelsesPris()));
        }

        for (HotelTillæg tillæg : tilmelding.getHotelTillæg()) {
            hotelStringBuilder.append(String.format("     - %s: %.2f DKK%n", tillæg.getNavn(), tillæg.getPris()));
        }

        hotelStringBuilder.append(String.format("Samlet hoteludgift: %.2f DKK%n%n", tilmelding.udregnHotelUdgift(tilmelding.getAntalDageOphold())));
        return hotelStringBuilder.toString();
    }

    private String formatUdflugtInfo(Tilmelding tilmelding) {
        if (tilmelding.getValgteUdflugter().isEmpty()) {
            return "";
        }

        StringBuilder udflugtStringBuilder = new StringBuilder(" - Tilmeldte udflugter:\n");
        for (Udflugt udflugt : tilmelding.getValgteUdflugter()) {
            udflugtStringBuilder.append(String.format("   - %s: %.2f DKK%n", udflugt.getNavn(), udflugt.getPris()));
        }
        udflugtStringBuilder.append(String.format("Samlet udflugtudgift: %.2f DKK%n%n", tilmelding.udregnUdflugtUdgift()));
        return udflugtStringBuilder.toString();
    }

    private String formatFakturaInfo(Tilmelding tilmelding) {
        String fakturaModtager;
        if (tilmelding.getDeltager().getFirma() != null) {
            fakturaModtager = tilmelding.getDeltager().getFirma().toString();
        } else {
            fakturaModtager = tilmelding.getDeltager().getFuldeNavn();
        }
        return String.format("Faktureres til %s%n", fakturaModtager);
    }

    private String formatTotalUdgift(Tilmelding tilmelding) {
        return String.format("Samlet konferenceudgift: %.2f DKK%n", tilmelding.getSamletTilmeldingsUdgift());
    }
}
