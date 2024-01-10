package org.starmap.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.starmap.controller.StarMapController;
import org.starmap.utils.FileManagementController;

public class FileManagementView extends MenuBar {
    public FileManagementView(StarMapController starMapController, Stage primaryStage) {
        FileManagementController fileManagementController = new FileManagementController(starMapController, primaryStage);

        Menu fileMenu = new Menu("File");

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(event -> fileManagementController.handleSave());

        MenuItem saveAsItem = new MenuItem("Save As");
        saveAsItem.setOnAction(event -> fileManagementController.handleSaveAs());

        MenuItem loadItem = new MenuItem("Load");
        loadItem.setOnAction(event -> fileManagementController.handleLoad());

        fileMenu.getItems().addAll(saveItem, saveAsItem, loadItem);
        this.getMenus().add(fileMenu);
    }
}