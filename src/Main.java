import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int row,col,count = 0;

        System.out.println("---------------Welcome to minesweeper game---------------");
        System.out.println("Please give me the number of row number and column number that you desire between 2 and 100.  \nThe loop will continue to ask you row and column number until you enter a number between 2 and 100..." );

        // Değerlendirme formu : 3
        do {
            System.out.print("Row count: ");
            row = scan.nextInt();
            System.out.print("Column count: ");
            col = scan.nextInt();

            count++;
            if (count == 5){
                System.out.println("Too many attempts. System closing...");
                System.exit(0);
            }
            // I set a limit on the number of rows and columns, and if a number between 100 and 2 is not entered, it asks you to enter it again. And if you enter a number outside of limits
            // for 5 times, then system closes itself.
        }while ((row > 100 || row < 2) || (col > 100 || col < 2));

        System.out.println("Thank you, I'm preparing your game...");
        System.out.println();
        Thread.sleep(2000);

        // I created my object from MineSweeper class. And run the run method.
        MineSweeper mine = new MineSweeper(row,col);
        mine.run();
    }
}





// board basılıyor
// kenardaki kutucuk seçildiğinde mayın saymıyor


