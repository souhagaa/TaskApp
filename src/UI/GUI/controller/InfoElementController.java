package UI.GUI.controller;

import core.Tache;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class InfoElementController {
    @FXML
    private TextField tfTitre;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea taDescription;
    private Tache tache;

    MainController mainController;

    public void setup(Tache tache,MainController mainController){
        this.tache = tache;
        this.mainController = mainController;
        tfTitre.setText(tache.getTitre());
        taDescription.setText(tache.getDescription().toString());
        dpDate.setValue(tache.getBeforeTime().toLocalDate());
    }
    @FXML
    private void enregistrerClicked(){
        this.tache.setTitre(tfTitre.getText());
        this.tache.setDescription(new StringBuffer(taDescription.getText()));
        this.tache.setBeforeTime(dpDate.getValue().atStartOfDay());
        mainController.editTache(tache);
    }

    @FXML
    private void annulerClicked(){
        tfTitre.setText("");
        taDescription.setText("");
        dpDate.setValue(null);

        mainController.showInfo(tache);
    }
}
