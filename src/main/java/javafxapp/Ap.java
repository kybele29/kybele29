package javafxapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ap  extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		TextField pathAField = new TextField();
        pathAField.setPromptText("Path A");
        Button pathAButton = new Button("Select a folder");

        TextField pathBField = new TextField();
        pathBField.setPromptText("Path B");
        Button pathBButton = new Button("Select a folder");

        pathAButton.setOnAction(e -> selectFile(primaryStage, pathAField));
        pathBButton.setOnAction(e -> selectFile(primaryStage, pathBField));

        HBox pathABox = new HBox(10, pathAField, pathAButton);
        HBox pathBBox = new HBox(10, pathBField, pathBButton);

        Button deleteButton = new Button("Delete");
        Button okButton = new Button("OK");

        deleteButton.setOnAction(e -> {
            pathAField.clear();
            pathBField.clear();
        });

        okButton.setOnAction(e -> {
            savePaths(pathAField.getText(), pathBField.getText());
            primaryStage.close();
        });

        HBox actionBox = new HBox(10, deleteButton, okButton);
        
        VBox root = new VBox(10, pathABox, pathBBox, actionBox);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("JavaFX Dosya Seçici");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectFile(Stage stage, TextField textField) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dosya Seç");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            textField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void savePaths(String pathA, String pathB) {
        try (FileWriter writer = new FileWriter("paths.txt")) {
            writer.write("Path A: " + pathA + "\n");
            writer.write("Path B: " + pathB + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
	}


