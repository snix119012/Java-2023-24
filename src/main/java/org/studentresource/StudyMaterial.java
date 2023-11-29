package org.studentresource;

public class StudyMaterial implements StudentResource {
    private String bookId, bookName;

    public StudyMaterial(String bookId, String bookName, String courseName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Override
    public String getId() {
        return bookId;
    }

    @Override
    public String getName() {
        return bookName;
    }

    @Override
    public void setId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public void setName(String bookName) {
        this.bookName = bookName;
    }

}
