package me.aneta;

import java.util.Random;

public class Jumping extends Organism {

    private Random rand = new Random();

    public Jumping(int energy) {
        super(energy);
    }

    public void move(Board board) {
        int newX, newY;



            do {
                int direction = rand.nextInt(4);
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
                    default:
                        System.out.println("RAND ERROR");
                        newX = getPosition().getX();
                        newY = getPosition().getY();
                }
            } while (newX < 0 || newX >= 10 || newY < 0 || newY >= 10);

            board.moveOrganism(this, newX, newY);



    }
}