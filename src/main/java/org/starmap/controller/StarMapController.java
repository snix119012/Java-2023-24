package org.starmap.controller;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataLoader;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class StarMapController {
    private List<Star> stars;
    private final List<Constellation> constellations;
    private String currentFilePath;

    public StarMapController(String dataFilePath) {
        this.stars = DataLoader.loadStars(dataFilePath);
        this.constellations = DataLoader.loadConstellations(dataFilePath, stars);
        this.currentFilePath = dataFilePath;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }
    public void addStar(Star star) {
        stars.add(star);
    }
    public void addConstellation(Constellation constellation) {
        constellations.add(constellation);
    }

    public void saveToFile(String filePath) throws IOException {
        JSONObject starMapJson = new JSONObject();
        JSONArray starsJson = new JSONArray();
        for (Star star : stars) {
            JSONObject starJson = new JSONObject();
            starJson.put("name", star.getName());
            starJson.put("xPosition", star.getXPosition());
            starJson.put("yPosition", star.getYPosition());
            starJson.put("brightness", star.getBrightness());
            starsJson.put(starJson);
        }
        starMapJson.put("stars", starsJson);
        JSONArray constellationsJson = new JSONArray();
        for (Constellation constellation : constellations) {
            JSONObject constellationJson = new JSONObject();
            constellationJson.put("name", constellation.getName());
            JSONArray starsInConstellationJson = new JSONArray();
            for (Star star : constellation.getStars()) {
                starsInConstellationJson.put(star.getName());
            }
            constellationJson.put("stars", starsInConstellationJson);

            constellationsJson.put(constellationJson);
        }
        starMapJson.put("constellations", constellationsJson);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(starMapJson.toString());
        }
        this.currentFilePath = filePath;
    }
    public void addUIControls(Node controls) {
        Object root = null;
        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(controls);
        }
    }
    public String getCurrentFilePath() {
        return currentFilePath;
    }
    public void loadFromFile(String filePath) throws IOException {
        List<Star> loadedStars = DataLoader.loadStars(filePath);
        List<Constellation> loadedConstellations = DataLoader.loadConstellations(filePath, loadedStars);
        this.stars.clear();
        this.stars.addAll(loadedStars);
        this.constellations.clear();
        this.constellations.addAll(loadedConstellations);
        this.currentFilePath = filePath;
    }
}
