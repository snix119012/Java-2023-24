package org.starmap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.starmap.controller.StarMapController;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.view.FileManagementView;
import org.starmap.view.StarMapToEdit;
import java.io.IOException;
import java.util.List;


public class MainApp extends Application {
    //To be able to edit the star, press the left mouse button twice and press shift. Changing the position of the star is possible by closing the star editing window and holding down the left mouse button and dropping it in the place of your choice.
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        StarMapController controller = new StarMapController("src/main/resources/stars.json");
        Star star1 = new Star("Star 1", 100, 200, 2.0);
        Star star2 = new Star("Star 2", 300, 400, 1.5);
        controller.addStar(star1);
        controller.addStar(star2);
        Constellation constellation = new Constellation("Constellation A", List.of(star1, star2));
        controller.addConstellation(constellation);
        try {
            controller.saveToFile("src/main/resources/test-output.json");
            System.out.println("Saved in file: " + controller.getCurrentFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            controller.loadFromFile("src/main/resources/test-output.json");
            System.out.println("Loaded from file: " + controller.getCurrentFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded stars:");
        for (Star star : controller.getStars()) {
            System.out.println(star.getName() + ": (" + star.getXPosition() + ", " + star.getYPosition() + ")");
        }
        System.out.println("Loaded constellations:");
        for (Constellation loadedConstellation : controller.getConstellations()) {
            System.out.println(loadedConstellation.getName());
            for (Star star : loadedConstellation.getStars()) {
                System.out.println("- " + star.getName());
            }
        }
        StarMapToEdit view = new StarMapToEdit(controller);
        FileManagementView fileManagementView = new FileManagementView(controller, primaryStage);
        Slider brightnessSlider = new Slider(0, 1, 0.5);
        VBox controls = new VBox(brightnessSlider);
        controls.setSpacing(10);
        VBox root = new VBox();
        root.getChildren().addAll(fileManagementView, view, controls);
        controls.setSpacing(10);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Star Map - Press Ctrl + V to toggle ARylas visibility");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.drawMap();
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getText().equalsIgnoreCase("v")) {
                view.toggleAxesVisibility();
            }
        });
    }
}