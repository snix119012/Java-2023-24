package org.studentresource.decorator;

import org.studentresource.StudentResource;

public abstract class ResourceDecorator implements StudentResource {
    protected StudentResource decoratedResource;

    public ResourceDecorator(StudentResource decoratedResource) {
        this.decoratedResource = decoratedResource;
    }

    @Override
    public String getId() {
        return decoratedResource.getId();
    }


    @Override
    public String getName() {
       return decoratedResource.getName();
    }

    @Override
    public void setId(String id) {
         decoratedResource.setId(id);

    }

    @Override
    public void setName(String name) {
       decoratedResource.setName(name);

    }
     public StudentResource getDecoratedResource(){
         return this.decoratedResource;
     }

    // Implement all necessary methods from StudentResource
    // Override methods to add additional behaviors
}