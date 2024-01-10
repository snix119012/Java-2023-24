package org.starmap;

import org.junit.jupiter.api.Test;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DataWriterTest {

    @Test
    void saveToFile() throws IOException {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star("Star1", 100, 200, 2.0));
        stars.add(new Star("Star2", 300, 400, 1.0));
        List<Constellation> constellations = new ArrayList<>();
        Constellation constellation = new Constellation("ConstellationA", stars);
        constellations.add(constellation);
        Path tempFile = Files.createTempFile("test-starmap", ".json");
        DataWriter.saveToFile(tempFile.toString(), stars, constellations);
        assertTrue(Files.exists(tempFile));
        assertTrue(Files.size(tempFile) > 0);
    }
}
