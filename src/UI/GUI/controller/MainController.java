package UI.GUI.controller;

import core.Tache;
import core.TacheList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;


public class MainController {
    @FXML
    private AnchorPane apMainScreen;
    @FXML
    private Button btAdd;
    @FXML
    private ScrollPane spTacheList;
    @FXML
    private VBox vbTacheList;

    TacheList tacheList;

    int infoPaneIndex;
    Tache tacheInInfoPane;
    Stage primaryStage;
    public void setup(TacheList tacheList,Stage primaryStage) {
        this.tacheList = tacheList;
        this.primaryStage = primaryStage;

        spTacheList.setFitToWidth(true);
        infoPaneIndex = -1;
        tacheInInfoPane = null;
        //creation d'element
        for(Tache tache: tacheList.getTacheList()){
            addTache(tache);
        }

    }

    public void addTache(Tache tache){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UI/GUI/view/TacheElement.fxml"));
            AnchorPane element = (AnchorPane) loader.load();
            ElementController elementController = loader.getController();
            elementController.setup(tache,this);
            vbTacheList.getChildren().add(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTache(Tache tache){
        int index = tacheList.getTacheList().indexOf(tache);
        showInfo(tacheInInfoPane); // to close the info pane
        Node node = vbTacheList.getChildren().remove(index);
        node = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UI/GUI/view/TacheElement.fxml"));
            AnchorPane element = (AnchorPane) loader.load();
            ElementController elementController = loader.getController();
            elementController.setup(tache,this);
            vbTacheList.getChildren().add(index,element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteElement(Tache tache) {
        int index = tacheList.getTacheList().indexOf(tache);
        if(infoPaneIndex!=-1)showInfo(tacheInInfoPane); //close info pane if open
        Node node = vbTacheList.getChildren().remove(index);
        node = null;
        tacheList.supprimerTache(tache);
    }

    public void showInfo(Tache tache) {
        int index = tacheList.getTacheList().indexOf(tache);
        if(infoPaneIndex == -1) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/UI/GUI/view/InfoElement.fxml"));
                AnchorPane element = (AnchorPane) loader.load();
                InfoElementController controller = loader.getController();
                controller.setup(tache,this);
                infoPaneIndex = index;
                tacheInInfoPane = tache;
                vbTacheList.getChildren().add(++infoPaneIndex,element);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if(infoPaneIndex == (index+1)){
            Node node = vbTacheList.getChildren().remove(infoPaneIndex);
            node = null;
            infoPaneIndex = -1;
            tacheInInfoPane = null;
        }else{
            try {
                Node node = vbTacheList.getChildren().remove(infoPaneIndex);
                node = null;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/UI/GUI/view/InfoElement.fxml"));
                AnchorPane element = (AnchorPane) loader.load();
                InfoElementController controller = loader.getController();
                controller.setup(tache,this);
                infoPaneIndex = index;
                tacheInInfoPane = tache;
                vbTacheList.getChildren().add(++infoPaneIndex,element);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void addButtonClicked(){
        if(tacheInInfoPane != null) showInfo(tacheInInfoPane);
        Tache tache = new Tache();
        boolean isOkClicked = false;
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UI/GUI/view/NewElement.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cree Tache");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewElementController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTache(tache);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            isOkClicked = controller.getOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isOkClicked){
            addTache(tache);
            tacheList.ajouterTache(tache);
        }else tache = null;
    }
}
