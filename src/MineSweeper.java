import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

// 0-9 arası input alabiliyoruz bunu 1den başlatsak iyi olur
// yapılacaklar: Güzelleştir, kod tekrarından kurtul metod yapabilidklerini metod yap

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




                fillTheMap();
        }

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
                // Map'i bastırıyor bunu oyun bitince sil
                for (int p = 0; p < map.length; p++){
                        for (int a = 0; a < map[p].length; a++){
                                System.out.print(map[p][a] + " ");
                        }
                        System.out.println();
                }
        }


        void run(){
                do {
                        System.out.printf("Please give a row number between 0 and %s : ",row-1);
                        pseudoRow = scan.nextInt();
                        System.out.printf("Please give a column number between 0 and %s : ",col-1);
                        pseudoCol = scan.nextInt();

                        if ((pseudoRow >= 0 && pseudoRow < row) && (pseudoCol >= 0 && pseudoCol < col)){
                                if (Objects.equals(map[pseudoRow][pseudoCol], " * ")){
                                        System.out.println("You landed on a mine...");
                                        System.out.println();

                                        for (int p = 0; p < map.length; p++){
                                                for (int a = 0; a < map[p].length; a++){
                                                        System.out.print(map[p][a] + " ");
                                                }
                                                System.out.println();
                                        }
                                        notFinished = false;
                                        System.exit(0);
                                }else{
                                        int counter = 0;
                                        for (int i = pseudoRow - 1; i <= pseudoRow + 1; i++){
                                                try {
                                                        for (int j = pseudoCol - 1; j <= pseudoCol+1; j++ ){
                                                                if (Objects.equals(map[i][j], " * ")){
                                                                        counter++;
                                                                }
                                                        }
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
                                                                // sağ üst
                                                                counter = isThereMine(0,1,-1,0);

                                                        } else if (pseudoRow == 0) {
                                                                // Üst orta
                                                                counter = 0;
                                                                counter = isThereMine(0,0,-1,1);
                                                                counter /= 2;

                                                        } else if (pseudoRow+1 == row) {
                                                                counter = isThereMine(-1,0,-1,1);

                                                        }else if (pseudoCol+1 == col){
                                                                counter = isThereMine(-1,1,-1,0);

                                                        }
                                                }
                                        }

                                        board[pseudoRow][pseudoCol] = String.format(" %s ",counter);

                                        for (int p = 0; p < map.length; p++){
                                                for (int a = 0; a < map[p].length; a++){
                                                        System.out.print(board[p][a] + " ");
                                                }
                                                System.out.println();
                                        }
                                }

                                if (hasFinished(board)){
                                        System.out.println("You Won! You found all the clear areas!");
                                        System.out.println("Here is the map of the mines");
                                        for (int p = 0; p < map.length; p++){
                                                for (int a = 0; a < map[p].length; a++){
                                                        System.out.print(map[p][a] + " ");
                                                }
                                                System.out.println();
                                        }

                                        System.exit(0);
                                }
                        }else {
                                // Row veya column için yanlış sayı verilmiş
                                System.out.println("Please give numbers within the limits given to you!");
                                continue;
                        }

                }while (notFinished);
        }

        boolean hasFinished(String[][] arr){
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
}
