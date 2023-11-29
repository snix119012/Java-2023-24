package org.studentresource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertNull;

class StudentResourceManagerTest {
    private StudentResourceManager<Course> manager;

    @BeforeEach
    void setUp() {
        manager = new StudentResourceManager<>();
    }

    @Test
    void addAndRetrieveResourceTest() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);

        Course retrieved = manager.getResource("CS101");
        assertNotNull(retrieved, "Retrieved course should not be null.");
        assertEquals("Introduction to Computer Science", retrieved.getName(), "Course name should match.");
    }

    @Test
    void removeResourceTest() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);
        assertNotNull(manager.getResource("CS101"), "Course should be present.");


        manager.remove(course);
        assertNull(manager.getResource("CS101"), "Course should be removed.");
    }

    @Test
    void findResourceTest() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);
        Course foundCourse = manager.find("CS101", "Introduction to Computer Science");

        assertNotNull(foundCourse, "Found course should be not null.");
        assertEquals("Introduction to Computer Science" ,foundCourse.getName(),"course name should be the same");
    }

    @Test
    void addBookToResourceTest() {
        StudentResourceManager<Course> manager = new StudentResourceManager<>();
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);
        StudyMaterial book = new StudyMaterial("B1", "Java Book", "CS101");
        manager.addBookToResource(course, book);
        List<StudyMaterial> booksForResource = manager.getBooksForResource(course);
        assertNotNull(booksForResource, "Books for resource should not be null.");
        assertEquals(1, booksForResource.size(), "There should be one book for the resource.");
        assertEquals("Java Book", booksForResource.get(0).getName(), "Book name should .");
    }
    // Add more tests to cover all functionalities
}