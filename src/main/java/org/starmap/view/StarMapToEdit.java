package org.starmap.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.starmap.controller.StarMapController;
import org.starmap.model.Star;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;


public class StarMapToEdit extends StarMapView {

    private Star selectedStar = null;
    private boolean showAxes = true;
    public Slider brightnessSlider;
    private ToggleButton hideStarsButton;

    public StarMapToEdit(StarMapController controller) {
        super(controller);
        addMouseClickListener();
        addMouseDragListener();
        addBrightnessControls();
        addStarsVisibilityToggle();
    }

    private void addMouseClickListener() {
        this.setOnMouseClicked(event -> {
            if (event.isShiftDown()) {
                handleEditOperation(event.getX(), event.getY());
            }
        });
    }

    private void addMouseDragListener() {
        this.setOnMouseDragged(event -> {
            if (selectedStar != null) {
                double newX = event.getX();
                double newY = event.getY();
                handleStarDrag(selectedStar, newX, newY);
                redrawMap();
            }
        });
    }

    private void handleEditOperation(double x, double y) {
        Optional<Star> starOptional = findStarAtPosition(x, y);
        if (starOptional.isPresent()) {
            selectedStar = starOptional.get();
            showStarEditDialog(selectedStar);
        }
    }

    private Optional<Star> findStarAtPosition(double x, double y) {
        List<Star> stars = getController().getStars();
        return stars.stream()
                .filter(star -> isPointInStar(x, y, star))
                .findFirst();
    }

    private boolean isPointInStar(double x, double y, Star star) {
        double distance = Math.sqrt(Math.pow(x - star.getXPosition(), 2) + Math.pow(y - star.getYPosition(), 2));
        return distance < 10;
    }

    private void handleStarDrag(Star star, double newX, double newY) {
        star.setXPosition(newX);
        star.setYPosition(newY);

    }

    private void showStarEditDialog(Star star) {
        Dialog<Pair<String, Double>> dialog = new Dialog<>();
        dialog.setTitle("Edit Star");
        dialog.setHeaderText("Editing Star: " + star.getName());
        TextField starNameField = new TextField(star.getName());
        Slider brightnessSlider = new Slider(0, 1, star.getBrightness());
        brightnessSlider.setShowTickMarks(true);
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setMajorTickUnit(0.2);
        GridPane grid = new GridPane();
        grid.add(new Label("New Star Name:"), 0, 0);
        grid.add(starNameField, 1, 0);
        grid.add(new Label("Brightness:"), 0, 1);
        grid.add(brightnessSlider, 1, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new Pair<>(starNameField.getText(), brightnessSlider.getValue());
            }
            return null;
        });
        Optional<Pair<String, Double>> result = dialog.showAndWait();
        result.ifPresent(newData -> {
            if (!newData.getKey().isEmpty()) {
                star.setName(newData.getKey());
                star.setBrightness(newData.getValue());
                redrawMap();
            } else {
                System.out.println("Star name can not be empty.");
            }
        });
    }

    @Override
    protected void drawStar(GraphicsContext gc, double x, double y, double size, Color color) {
        super.drawStar(gc, x, y, size, color);
        if (selectedStar != null && selectedStar.getXPosition() == x && selectedStar.getYPosition() == y) {
            gc.setFill(Color.RED);
            gc.fillOval(x - 5, y - 5, 10, 10);
        }
    }

    @Override
    public void drawMap() {
        super.drawMap();
        redrawSelectedStar();
        if (showAxes) {
            drawAxes();
        }


    }
    private void drawAxes() {
        GraphicsContext gc = getGraphicsContext2D();
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        gc.setStroke(Color.BLUE);
        double axisLength = 1000;
        gc.strokeLine(centerX - axisLength / 2, centerY, centerX + axisLength / 2, centerY);
        gc.setStroke(Color.RED);
        gc.strokeLine(centerX, centerY - axisLength / 2, centerX, centerY + axisLength / 2);
    }
    private void redrawSelectedStar() {
        if (selectedStar != null) {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.RED);
            gc.fillOval(selectedStar.getXPosition() - 5, selectedStar.getYPosition() - 5, 10, 10);
        }
    }

    public void toggleAxesVisibility() {
        showAxes = !showAxes;
        redrawMap();
    }
    private void addBrightnessControls() {
        brightnessSlider = new Slider(0, 1, 0.5);
        brightnessSlider.setShowTickMarks(true);
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setMajorTickUnit(0.2);

        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (selectedStar != null) {
                selectedStar.setBrightness(newValue.doubleValue());
                redrawMap();
            }
        });

        VBox controls = new VBox(brightnessSlider);
        controls.setSpacing(10);
        getController().addUIControls(controls);
    }
    private void addStarsVisibilityToggle() {
        hideStarsButton = new ToggleButton("Hide Stars");
        hideStarsButton.setOnAction(event -> setStarsVisibility(!hideStarsButton.isSelected()));
        VBox controls = new VBox(hideStarsButton);
        controls.setSpacing(10);
    }



    public void setStarsVisibility(boolean visible) {
        getController().getStars().forEach(star -> star.setVisible(visible));
        redrawMap();
    }
    @Override
    public void redrawMap() {
        super.redrawMap();
        redrawSelectedStar();
        if (showAxes) {
            drawAxes();
        }
    }

}