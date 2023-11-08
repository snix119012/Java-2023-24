package me.aneta;

import java.util.Random;

public class Sighting extends Organism {

    private Random rand = new Random();
    private int sightRadius;

    public Sighting(int energy, int sightRadius) {
        super(energy);
        this.sightRadius = sightRadius;
    }
    public void observation(Board board)
    {
        int xNow = getPosition().getX();
        int yNow = getPosition().getY();
        for (int x = xNow - sightRadius; x <= xNow + sightRadius; x++) {
            for (int y = yNow - sightRadius; y <= yNow + sightRadius; y++) {
                if (x >= 0 && x < 10 && y >= 0 && y < 10 && (x != xNow || y != yNow)) {
                    Organism target = board.getOrganism(x, y);
                    if (target != null) {
                        System.out.println("Organism S located on [" + (xNow+1) + "," + (yNow+1) + "] can see organism on [" + (x+1) + "," + (y+1) + "]");
                    }
                }
            }
        }
    }
    public void move(Board board) {
        int newX, newY;
        do {
            int direction = rand.nextInt(8);
            switch (direction) {
                case 0:
                    newX = getPosition().getX() - 1;
                    newY = getPosition().getY();
                    break;
                case 1:
                    newX = getPosition().getX() + 1;
                    newY = getPosition().getY();
                    break;
                case 2:
                    newX = getPosition().getX();
                    newY = getPosition().getY() - 1;
                    break;
                case 3:
                    newX = getPosition().getX();
                    newY = getPosition().getY() + 1;
                    break;
                case 4:
                    newX = getPosition().getX() - 1;
                    newY = getPosition().getY() - 1;
                    break;
                case 5:
                    newX = getPosition().getX() + 1;
                    newY = getPosition().getY() + 1;
                    break;
                case 6:
                    newX = getPosition().getX() + 1;
                    newY = getPosition().getY() - 1;
                    break;
                case 7:
                    newX = getPosition().getX() - 1;
                    newY = getPosition().getY() + 1;
                    break;
                default:
                    System.out.println("RAND ERROR");
                    newX = getPosition().getX();
                    newY = getPosition().getY();
            }
        } while (newX < 0 || newX >= 10 || newY < 0 || newY >= 10);

        board.moveOrganism(this, newX, newY);
    }
}
