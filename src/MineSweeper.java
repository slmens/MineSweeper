import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

// yapılacaklar: Güzelleştir, kod tekrarından kurtul metod yapabilidklerini metod yap/ puan kontrolü

public class MineSweeper {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        int row,col,mineCount,pseudoRow,pseudoCol;
        String[][] board,map;
        boolean notFinished = true;

        MineSweeper(int row,int col){
                this.row = row;
                this.col = col;
                mineCount = (row * col) / 4;
                board = new String[row][col];
                map = new String[row][col];

                // We call it in the constructor method so that as soon as our object is created, it creates our arrays and makes them ready for the game.
                fillTheMap();
        }

        /*
               This method has 3 for loops. In the first loop, map and board arrays are filled with the "-" character. In the second loop, the map array uses random numbers
               within the boundaries as rows and columns and fills these places with "*" characters as many as the mine-count variable calculated in the constructor method.
               The third for loop prints the board array so that the player can see where he can move.
         */
        void fillTheMap(){

                for (int i = 0; i < map.length; i++){
                        for (int j = 0; j < map[i].length; j++){
                                map[i][j] = " - ";
                                board[i][j] = " - ";
                        }
                }

                for (int t = 0; t < this.mineCount; t++ ){
                        int rand1,rand2;
                        rand1 = random.nextInt(row);
                        rand2 = random.nextInt(col);

                        map[rand1][rand2] = " * ";
                }

                System.out.println("Your game is ready!");
                System.out.println();
                printMethod(board);
        }


        void run(){
                do {
                        // First we get the coordinates from the user. User can enter a number between 1 and row/column number that user entered in the Main class.
                        System.out.println();
                        System.out.printf("Please give a row number between 1 and %s : ",row);
                        pseudoRow = scan.nextInt();
                        System.out.printf("Please give a column number between 1 and %s : ",col);
                        pseudoCol = scan.nextInt();
                        System.out.println();

                        // Then we subtract 1 from these numbers because even if we ask the user to start from 1 so that the user does not get confused and does not start from 0, our array starts from 0.
                        // That is, in a 4 by 4 array, if the user enters 1, it becomes array[0], and if the user enters 4, it becomes array[3].
                        pseudoRow -= 1;
                        pseudoCol -= 1;

                        // We control the numbers that user gave, if they are in the range of 1 and top limit (row/column).
                        if ((pseudoRow >= 0 && pseudoRow < row) && (pseudoCol >= 0 && pseudoCol < col)){
                                if (hasLandedOnMine(pseudoRow,pseudoCol)){
                                        // We are checking the coordinates given by the user in the map array because the location of the mines is recorded there.
                                        // If there is a " * " character in the coordinates given by the user, it means that he stepped on a mine, so hasLandedOnMine
                                        // method return true,and we finish the game.
                                        System.out.println("You landed on a mine...");
                                        System.out.println();

                                        // Then we print out the map array to show the user where are the mines are.
                                        printMethod(map);

                                        // Also program breaks the while loop that we are currently in by setting the notFinished variable to false. On top of that program also exit the program with System.exit(0)
                                        notFinished = false;
                                        System.exit(0);
                                }else{
                                        // If everything is okay, the inputs are in the limits and there are no mines in that coordinate, then program starts to calculate mines around that coordinate
                                        int counter = 0;

                                        // Program starts a for loop in the range of row -1 to row +1 and column -1 to column +1
                                        for (int i = pseudoRow - 1; i <= pseudoRow + 1; i++){
                                                try {
                                                        // If the coordinates that we get from the user is not on the edges, then that coordinate has 9 blocks around itself including itself.
                                                        // So program can look each row and column without mistake and count mines
                                                        for (int j = pseudoCol - 1; j <= pseudoCol+1; j++ ){
                                                                if (Objects.equals(map[i][j], " * ")){
                                                                        counter++;
                                                                }
                                                        }

                                                        // But if the coordinate is on the edges then we need to calculate mines around that coordinate in another way
                                                        // Please read comment lines above the isThereMine method then you will understand this method
                                                }catch (IndexOutOfBoundsException e){
                                                        counter = 0;
                                                        if (pseudoRow == 0 && pseudoCol == 0){
                                                                counter = isThereMine(0,1,0,1);

                                                        } else if (pseudoRow+1 == row && pseudoCol+1 == col) {
                                                                counter = isThereMine(-1,0,-1,0);

                                                        } else if (pseudoRow+1 == row && pseudoCol == 0) {
                                                                counter = isThereMine(-1,0,0,1);

                                                        } else if (pseudoCol == 0){
                                                                counter = isThereMine(-1,1,0,1);

                                                        }else if (pseudoRow == 0 && pseudoCol+1 == col) {
                                                                counter = isThereMine(0,1,-1,0);

                                                        } else if (pseudoRow == 0) {
                                                                counter = isThereMine(0,0,-1,1);
                                                                counter /= 2;

                                                        } else if (pseudoRow+1 == row) {
                                                                counter = isThereMine(-1,0,-1,1);

                                                        }else if (pseudoCol+1 == col){
                                                                counter = isThereMine(-1,1,-1,0);

                                                        }
                                                }
                                        }

                                        // Finally, we enter the coordinates given to us into our board array and set the region in that coordinate equal to the counter,
                                        // that is, the number of mines around that region.
                                        board[pseudoRow][pseudoCol] = String.format(" %s ",counter);

                                        printMethod(board);
                                }
                                // We are checking to see if the game is over by hasFinished method (read the comments above the hasFinished method) before the while loop ends.
                                // If it returns true, the game is over; if it returns false, we continue the while loop.
                                if (hasFinished(board,map)){
                                        System.out.println("You Won! You found all the clear areas!");
                                        System.out.println("Here is the map of the mines");

                                        printMethod(map);

                                        notFinished = false;
                                        System.exit(0);
                                }
                        }else {
                                // If the input that I get from the user is beyond the limits, then I warn the user
                                System.out.println("Please give numbers within the limits given to you!");
                        }

                }while (notFinished);
        }

        // This is a simple method that check if the coordinates that we are taken from the user is == "*"
        // If so return true and finish the game
        boolean hasLandedOnMine(int row,int col){
            return Objects.equals(map[row][col], " * ");
        }

        /*
                This method takes two two-dimensional array parameters. One of them is the board, that is, the array where our game is played, and the other is the map, where the location of the mines is recorded.
                This method performs counting in both arrays. It counts the "-" character in the board array, and it counts the "*" character in the map array to see how many mines there are. If these are equal to each other
                This means that there is no place on the board that can be opened that is not a mine, and it returns true.
        */

        boolean hasFinished(String[][] arr,String[][] map){
                int pseudoMineCount = 0,newMineCount = 0;
                for (int j = 0; j < arr.length; j++){
                        for (int i = 0; i < arr[j].length ; i++){
                                if (Objects.equals(arr[j][i], " - ")){
                                        pseudoMineCount++;
                                }
                        }
                }

                for (int t = 0; t < arr.length; t++){
                        for (int k = 0; k < arr[t].length ; k++){
                                if (Objects.equals(map[t][k], " * ")){
                                        newMineCount++;
                                }
                        }
                }

                if (pseudoMineCount == newMineCount){
                        return true;
                }else {
                        return false;
                }
        }

        /*
                This method only works when the given row and column values coincide with the edges. When the row and column values come across a place with 9 blocks around it,
                there is no problem, it can count the surrounding mines. But let's say the point 0,0 has arrived. In this case, according to the logic I have established in the
                run method, it will start from a row and a column minus. That is, it will start from the point -1,-1. Since there is no such point, it will give an indexOutOfBounds
                error. To prevent this, the (x,y) values that do not have 9 blocks around it, including itself, fall into this method and the surrounding mines are calculated
                according to each point. So in a 4 by 4 array, point 0,0 and point 4,4 are not the same. For point 4.4, minus 1 row and minus 1 column works, so it can check point 3.3
                and then point 3.4, but there is no 3.5,since there is no column on the right side, we count only 2 columns in the for loop specifically for this point,
                and It does not pass to the third column. As a result, this is a method that counts and returns the count of mines around the area that is given.
         */
        int isThereMine(int for1n1, int for1n2, int for2n1, int for2n2){
                int counter = 0;

                for (int row = pseudoRow + for1n1; row <= pseudoRow + for1n2; row++){
                        for (int col = pseudoCol + for2n1; col <= pseudoCol + for2n2; col++ ){
                                if (Objects.equals(map[row][col], " * ")){
                                        counter++;
                                }
                        }
                }

                return counter;
        }

        // There was too much print in for loop used in the code, so I reduced it to 1 with the method.
        void printMethod(String[][] arr){
                for (int i = 0; i < arr.length; i++){
                        for (int j = 0; j < arr[i].length; j++){
                                System.out.print(arr[i][j] + " ");
                        }
                        System.out.println();
                }
        }
}
