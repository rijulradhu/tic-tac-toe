# Tic-Tac-Toe with AI

This is a command-line app of the game 'Tic-Tac-Toe' with three levels of difficulty (easy,medium,hard). This game can be played between two users or between a user and the computer with any one out of the three levels of difficulty.

To play, download the jar file from the repo and type the given command in terminal
```
java -jar path/to/the/jar/file/TicTacToe.jar
```
NOTE: THE PROGRAM IS CASE-SENSITIVE

To start the game, you have two options: 
## **start** / **exit**

To play the game, you can write **start user easy**

**user** at first position signifies that user will play with **X** and computer with **O**. You can type in any combination of **user, easy, medium & hard**.

Wrong input will ask you to give your preference again.

```
Input command: start user diff
Bad parameters
Input command: start user eas
Bad parameters
```

The board is marked as 9 points with coordinates starting from (1,1) to (3,3). (1,1) is the top left square and (3,3) is the bottom right square. (2,2) is the centre. To play your move, type **coord1 coord2**. Just like (2,2) is mentioned in the example below.

```
Input command: start user medium
---------
|       |
|       |
|       |
---------
Enter the coordinates: 2 2
---------
|       |
|   X   |
|       |
---------
Making move level "medium"
---------
|       |
|   X   |
|     O |
---------
```

Trying to enter already occupied squares or entering wrong values will print the mistake. It'll ask you to enter correct coordinates.

```
Input command: start user medium
---------
|       |
|       |
|       |
---------
Enter the coordinates: 1 1
---------
| X     |
|       |
|       |
---------
Making move level "medium"
---------
| X     |
|       |
| O     |
---------
Enter the coordinates: 3 1
This cell is occupied! Choose another one!
Enter the coordinates: 3 4
Coordinates should be from 1 to 3!
Enter the coordinates: 3 a
You should enter numbers!
Enter the coordinates:
```

Type **exit** to close the game

## Levels of difficulty
1. **Easy** - It moves on random available squares without considering if it'll win or lose.
2. **Medium** - It moves on random available squares when neither itself or the player against it is one step far from winning. It checks if it can win in one move or lose in one move and plays accordingly to win or draw.
3. **Hard** - It moves on squares by using minimax algorithm. Using this algorithm, it backtracks all the possible outcomes of the board and chooses the one with the probability to win. 
