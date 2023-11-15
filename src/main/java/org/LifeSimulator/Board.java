package org.LifeSimulator;
public class Board {

    private int width;
    private int height;
    private Organism[][] organisms;
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.organisms = new Organism[width][height];
    }
    public Organism getOrganism(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return organisms[x][y];
        }
        return null;
    }
    public void addOrganism(Organism organism, int x, int y) {
        if (organisms[x][y] == null) {
            organisms[x][y] = organism;
            organism.setPosition(new Position(x, y));
        } else {
            System.out.println("Position already occupied!");
        }
    }
  public void moveOrganism(Organism organism, int newX, int newY) {
      if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
          System.out.println("Invalid move!");
          return;
      }
      Organism existingOrganism = organisms[newX][newY];
      if (existingOrganism != null) {
          if (organism.getClass() == existingOrganism.getClass()) {
              //if the type of organism is the same connect two of them to one
              int totalEnergy = organism.getEnergy() + existingOrganism.getEnergy();
              organism.setEnergy(totalEnergy);
          } else {
              if (organism.getEnergy() > existingOrganism.getEnergy()) {
                  organism.setEnergy(organism.getEnergy() + existingOrganism.getEnergy());
              } else {
                  existingOrganism.setEnergy(existingOrganism.getEnergy() + organism.getEnergy());
              }
          }
      }
      organisms[organism.getPosition().getX()][organism.getPosition().getY()] = null;
      organisms[newX][newY] = organism;
      organism.setPosition(new Position(newX, newY));
  }
  public int getNumberOfOrganisms() {
        int numberOfOgranisms = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (organisms[x][y] != null) {
                    numberOfOgranisms++;
                }
            }
        }
        return numberOfOgranisms;
    }
    public void removeOrganism(int x, int y) {
            organisms[x][y] = null;
    }
}