package UI.GUI.controller;

import core.Tache;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewElementController {
    public TextField tfTitre;
    public DatePicker dpDate;
    public TextArea taDescription;
    public Button btAnnuler;
    public Button btEnregistrer;

    private Stage dialogStage;
    private Tache tache;
    private boolean okClicked;

    public void annulerClicked(ActionEvent actionEvent) {
        okClicked = false;
        dialogStage.close();
    }

    public void enregistrerClicked(ActionEvent actionEvent) {
        this.tache.setTitre(tfTitre.getText());
        this.tache.setDescription(new StringBuffer(taDescription.getText()));
        this.tache.setBeforeTime(dpDate.getValue().atStartOfDay());
        this.tache.setStatus(1);
        okClicked =  true;
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public boolean getOkClicked() {
        return okClicked;
    }
}
