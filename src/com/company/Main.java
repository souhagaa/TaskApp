package com.company;

import UI.GUI.GUI;
import core.Tache;
import core.TacheLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persistance.XMLSAX;

import java.io.IOException;
import java.time.LocalDateTime;

import static javafx.application.Application.launch;

public class Main extends Application {
    GUI gui;
    TacheLinkedList tacheLinkedList;
    Stage primaryStage;
    XMLSAX xmlsax;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        tacheLinkedList = new TacheLinkedList();
        this.primaryStage = stage;
        xmlsax = new XMLSAX();
        xmlsax.charger(tacheLinkedList);
        this.gui = new GUI(stage,tacheLinkedList);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        xmlsax.enregistrer(tacheLinkedList);
        super.stop();
    }
}
