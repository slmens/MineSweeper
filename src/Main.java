import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int row,col,count = 0;

        System.out.println("---------------Welcome to minesweeper game---------------");
        System.out.println("Please give me the number of row number and column number that you desire between 2 and 100. The loop will continue to ask you row and column number until you enter a number between 2 and 100..." );

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
        }while ((row > 100 || row < 2) || (col > 100 || col < 2));

        System.out.println("Thank you, I'm preparing your game...");
        System.out.println();
        Thread.sleep(2000);

        MineSweeper mine = new MineSweeper(row,col);
        mine.run();
    }
}





// board basılıyor
// kenardaki kutucuk seçildiğinde mayın saymıyor


