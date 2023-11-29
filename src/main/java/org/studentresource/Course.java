package org.studentresource;

import java.util.List;

public class Course implements StudentResource {
    private String id;
    private String name;
    private List<StudyMaterial> booksList;

    public Course(String sc101, String introductionToComputerScience) {
        this.id = sc101;
        this.name = introductionToComputerScience;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<StudyMaterial> getBooks() {
        return booksList;
    }

    // Constructor, getters, setters
    // Implement all necessary methods from StudentResource
}