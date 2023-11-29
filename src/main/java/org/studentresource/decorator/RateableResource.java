package org.studentresource.decorator;

import org.studentresource.StudentResource;

public class RateableResource extends ResourceDecorator {
    public RateableResource(StudentResource decoratedResource) {
        super(decoratedResource);
    }
    private  double rating = 0;

    public void setRating(double rating){
        this.rating = rating;
    }

    public double getRating(){
        return this.rating;
    }
}
