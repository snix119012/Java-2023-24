package org.LifeSimulator;
public class LifeSimulator {
    public static void printBoard(Board board) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Organism currentOrganism = board.getOrganism(x, y);
                if (currentOrganism != null)
                {
                    char symbol;
                    if (currentOrganism instanceof Jumping) {
                        symbol = 'J';
                    } else if (currentOrganism instanceof Sighting) {
                        symbol = 'S';
                    } else {
                        symbol = 'O';
                    }
                    System.out.print(symbol + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        Organism organism1 = new Organism(100);
        board.addOrganism(organism1, 5, 5);
        Organism jumpingOrganism = new Jumping(100);
        board.addOrganism(jumpingOrganism, 2, 2);
        Organism sightingOrganism = new Sighting(100, 3);
        board.addOrganism(sightingOrganism, 4, 4);
        Organism sightingOrganism3 = new Sighting(200, 3);
        board.addOrganism(sightingOrganism3, 7, 4);
        Organism sightingOrganism4 = new Sighting(100, 1);
        board.addOrganism(sightingOrganism4, 9, 9);
        int numOrganisms = board.getNumberOfOrganisms();
        printBoard(board);
        while (numOrganisms > 1) {
            //empty board to update positions
            Board posBoard = new Board(10, 10);
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    Organism currentOrganism = board.getOrganism(x, y);
                    if (currentOrganism != null) {
                        if(currentOrganism instanceof Jumping)
                        {
                            currentOrganism.move(posBoard);
                            currentOrganism.move(posBoard);
                        }
                        else
                            currentOrganism.move(posBoard);
                    }
                }
            }
            printBoard(posBoard);

            // slowing down the game
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // positions board to original board
            board = posBoard;
            //removing organisms without energy
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    Organism currentOrganism = board.getOrganism(x, y);
                    if (currentOrganism != null && currentOrganism instanceof Sighting) {
                        Sighting sighting = (Sighting) currentOrganism;
                        sighting.observation(board);
                    }

                    if (currentOrganism != null && currentOrganism.getEnergy() <= 0) {
                        board.removeOrganism(x, y);
                    }
                }
            }
            numOrganisms = board.getNumberOfOrganisms();
            if(numOrganisms>1)
            {
                System.out.println("Still living: " + numOrganisms + " organisms");
            }

        }
        System.out.println("Simulation completed - One organism left on board");
    }
}

