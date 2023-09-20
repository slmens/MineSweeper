# MineSweeper
This project is an assignment for Patika+ bootcamp. 

I will briefly explain how it works and teach you how to play mine sweeper if you don't know.

![Ekran Görüntüsü (11)](https://github.com/slmens/MineSweeper/assets/99343829/5bada01b-b7c6-4aed-8930-9d9d4e8dc5ae) 

In the first picture, the program asks us how many rows and columns we want our game to have. It also warns us and says only enter a number between 2 and 100. If you enter a number below 2 or above 100, it will not accept it and will ask again until you try 5 times, then the program will close itself.

![Ekran Görüntüsü (6)](https://github.com/slmens/MineSweeper/assets/99343829/bee02f19-83be-4630-b5b7-0d6161f3a56c)

Then program asks us the coordinates (row,column) that we want to look out if there are any mines. But again it does not accept any number below the row and column count that we gave to program in the previous picture. For example if the game is 4(row)x6(column) you can't enter a number above 4 for row coordinate and above 6 for column coordinate. Also you can't enter a number below 1.
 
![Ekran Görüntüsü (7)](https://github.com/slmens/MineSweeper/assets/99343829/8df76487-e442-4104-9958-5c6be7468db5)

![Ekran Görüntüsü (8)](https://github.com/slmens/MineSweeper/assets/99343829/a68505e4-9af9-4b2e-865c-da62d96b49a2)

In the pictures above there we see two different possibilities of the region whose coordinates we have given. 
Either there is a mine in the coordinates you gave or there is not. If there is, you have lost the game, but if not, the program looks at all the coordinates within 1 block of that coordinate and tells you how many mines there are. If there are 2 mines, it writes 2, otherwise it writes 0. For example there is 1 on the board in the first picture. This means that there is a mine somewhere either to the right, below or diagonally to the right of that square. Since that block has no top or left, we doesn't count them.

![Ekran Görüntüsü (10)](https://github.com/slmens/MineSweeper/assets/99343829/82f48757-c958-4203-8a30-ab96e8d89d9e)

If you find all the squares that doesn't have mines, then you win.

That's it.
