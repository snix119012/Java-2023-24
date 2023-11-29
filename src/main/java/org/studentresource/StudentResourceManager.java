package org.studentresource;

import java.util.ArrayList;
import java.util.List;

public class StudentResourceManager<T extends StudentResource> {
    private final List<T> resources;
    private final List<List<StudyMaterial>> resBooks;

    public StudentResourceManager() {
        this.resources = new ArrayList<>();
        this.resBooks = new ArrayList<>();
    }

    public void addResource(T resource) {
        if (resource != null) {
            resources.add(resource);
            resBooks.add(new ArrayList<>());
        }
    }

    public T getResource(String resourceId) {
        for (T resource : resources) {
            if (resource.getId().equals(resourceId)) {
                return resource;
            }
        }
        return null;
    }

    public void remove(T resource) {
        int index = resources.indexOf(resource);
        if (index != -1) {
            resources.remove(index);
            resBooks.remove(index);
        }
    }

    public T find(String resourceId, String resourceName) {
        for (T resource : resources) {
            if (resource.getId().contains(resourceId) || resource.getName().contains(resourceName)) {
                return resource;
            }
        }
        return null;
    }

    public List<StudyMaterial> getBooksForResource(T resource) {
        int index = resources.indexOf(resource);
        if (index != -1) {
            return resBooks.get(index);
        } else {
            return new ArrayList<>();
        }
    }

    public void addBookToResource(T resource, StudyMaterial book) {


        int index = resources.indexOf(resource);
        if (index != -1 && book != null) {
            List<StudyMaterial> books = resBooks.get(index);
            books.add(book);
        }
    }

}