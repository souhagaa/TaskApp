package UI.GUI;

import UI.GUI.controller.ElementController;
import UI.GUI.controller.MainController;
import core.Tache;
import core.TacheList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI{
    private TacheList tacheList;
    private Stage primaryStage;

    public GUI(Stage stage,TacheList tacheList){
        this.tacheList = tacheList;
        primaryStage = stage;
        setupStage();
    }

    private void setupStage(){
        this.primaryStage.setTitle("TacheApp");
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UI/GUI/view/MainScreen.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            MainController mainController = (MainController) loader.getController();
            mainController.setup(tacheList,primaryStage);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
