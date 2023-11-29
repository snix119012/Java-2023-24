package org.studentresource.decorator;

import org.studentresource.Course;

public class CommentableResource extends  ResourceDecorator{
    private String comment;

    public CommentableResource(Course decoratedResource) {
        super(decoratedResource);
    }

    public void addComment(String comment) {
       this.comment= comment;
    }

    public String getComment() {
        return comment;
    }

}
