package UI.GUI.controller;

import core.Tache;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class ElementController {
    @FXML
    private Label lTacheTitre;
    @FXML
    private CheckBox cbStatus;
    @FXML
    private Button btDelete;
    @FXML
    private AnchorPane apTacheElement;

    public void setup(Tache tache,MainController mainController) {
        lTacheTitre.setText(tache.getTitre()+" avant le "+tache.getBeforeTime().toLocalDate().toString());
        if(tache.getStatus()==0) cbStatus.setSelected(true);
        else cbStatus.setSelected(false);
        btDelete.setOnMouseClicked(mouseEvent -> mainController.deleteElement(tache));
        cbStatus.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(t1) tache.setStatus(0);
            else tache.setStatus(1);
        });
        apTacheElement.setOnMouseClicked(mouseEvent -> mainController.showInfo(tache));
    }


}
