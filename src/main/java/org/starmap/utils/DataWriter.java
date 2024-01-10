package org.starmap.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    public static void saveToFile(String filePath, List<Star> stars, List<Constellation> constellations) throws IOException {
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
    }
}