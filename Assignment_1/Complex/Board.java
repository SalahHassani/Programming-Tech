package Assignment_1.Complex;


public class Board {
    
    public void printBoard(int numBoxesHorizontal, int numBoxesVertical, int posX, int posY){

        int boxHeight = 5;
        int boxWidth = 12;

        String[] person = {
            " \\ O / ",   // Head and arms
            "   |   ",   // Body
            "  / \\  "  // Legs
        };



        for (int row = 0; row < numBoxesVertical; row++) {


            for (int i = 0; i < boxHeight; i++) {
                System.out.print("\t\t\t\t ");

                for (int col = 0; col < numBoxesHorizontal; col++) {

                    if (i == 0 || i == boxHeight) {
                        System.out.print("+");
                        for (int j = 0; j < boxWidth - 2; j++) {
                            System.out.print("-");
                        }
                        if(col == numBoxesHorizontal - 1) System.out.print("+"); // removing the double +s...

                    } else {
                        System.out.print("|");
                        for (int j = 0; j < boxWidth - 2; j++) {
                            // System.out.print(" ");
                            if (row == posX && col == posY && i >= 2 && i <= 4 && j >= 2 && j <= 8) {
                                // Display the person
                                System.out.print(person[i - 2].charAt(j - 2));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        if(col == numBoxesHorizontal - 1) System.out.print("|"); // removing the double |s...
                    }

                }

                System.out.println();
            }

        }

        // Print the bottom border for the last row of boxes
        System.out.print("\t\t\t\t ");
        for (int i = 0; i < numBoxesHorizontal; i++) {
            System.out.print("+");
            for (int j = 0; j < boxWidth - 2; j++) {
                System.out.print("-");
            }
            if (i == numBoxesHorizontal - 1) {
                System.out.print("+");
            }
        }
    }
}
