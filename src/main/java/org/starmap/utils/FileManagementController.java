package org.starmap.utils;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.starmap.controller.StarMapController;
import java.io.File;
import java.io.IOException;

public class FileManagementController {
    private final StarMapController starMapController;
    private final Stage primaryStage;

    public FileManagementController(StarMapController starMapController, Stage primaryStage) {
        this.starMapController = starMapController;
        this.primaryStage = primaryStage;
    }
    public void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Star Map As");
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try {
                DataWriter.saveToFile(file.getAbsolutePath(), starMapController.getStars(), starMapController.getConstellations());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleSave() {
        String currentFilePath = starMapController.getCurrentFilePath();

        if (currentFilePath != null) {
            try {
                starMapController.saveToFile(currentFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            handleSaveAs();
        }
    }

    public void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Star Map");
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                starMapController.loadFromFile(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}