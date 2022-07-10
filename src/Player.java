import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    List<Integer> position = new ArrayList<>();
    char[][] board = Board.board;
    public Player(){
        for(int i = 0; i<9;i++){
            position.add(i+1);
        }
    }
    public void moveUser(int pos){
        while(true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter the coordinates: ");
                int x = in.nextInt();
                int y = in.nextInt();
                if(x>3 || x<1 || y>3 || y<1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if(board[x-1][y-1] == ' '){
                    board[x-1][y-1] = pos == 1 ? 'X' : 'O';
                    break;
                }else{
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }catch (Exception e){
                System.out.println("You should enter numbers!");
            }
        }
    }

    public int getRandom(){
        Random random = new Random();
        int number = random.nextInt(9);
        while(board[number/3][number%3] != ' '){
            number = random.nextInt(9);
        }
        return number;
    }

    public void moveEasy(int pos){
        System.out.println("Making move level \"easy\"");
        int number = getRandom();
        board[number/3][number%3] = pos == 1 ? 'X' : 'O';
    }

    public void moveMedium(int pos){
        System.out.println("Making move level \"medium\"");
        int[] move = winPossible(pos == 1? 'X':'O');
        if(move[0] == -1){
            move = winPossible(pos == 1? 'O':'X');
            if(move[0] == -1){
                int number = getRandom();
                board[number/3][number%3] = pos == 1? 'X':'O';
            }else{
                board[move[0]][move[1]] = pos == 1? 'X':'O';
            }
        }else{
            board[move[0]][move[1]] = pos == 1? 'X':'O';
        }
    }

    public void moveHard(int pos){
        System.out.println("Making move level \"hard\"");
        Move best = minimax(board,pos,pos);
        board[best.index[0]][best.index[1]] = pos == 1? 'X':'O';
    }

    public Move minimax(char[][] board, int realPlayer, int currentPlayer){
        List<int[]> availSpots = emptyIndices(board);
        int realEnemy;
        if(realPlayer == 1){
            realEnemy = 2;
        }else{
            realEnemy = 1;
        }

        int enemyPlayer;
        if(currentPlayer == 1){
            enemyPlayer = 2;
        }else{
            enemyPlayer = 1;
        }

        if(Board.winnerCheck(realPlayer == 1?'X':'O')){
            return new Move(10);
        }else if(Board.winnerCheck(realEnemy == 1?'X':'O')){
            return new Move(-10);
        }else if(availSpots.size() == 0){
            return new Move(0);
        }

        List<Move> moves = new ArrayList<>();
        for(int[] spot: availSpots){
            Move move = new Move();
            move.index = spot;
            board[spot[0]][spot[1]] = currentPlayer == 1?'X':'O';
            Move result = minimax(board,realPlayer,enemyPlayer);
            move.score = result.score;
            board[spot[0]][spot[1]] = ' ';
            moves.add(move);
        }

        Move best = null;
        if(realPlayer == currentPlayer){
            int min = -10000;
            for(Move move: moves){
                if(move.score > min){
                    min = move.score;
                    best = move;
                }
            }
        }else{
            int max = 10000;
            for(Move move: moves){
                if(move.score < max){
                    max = move.score;
                    best = move;
                }
            }
        }

        return best;
    }

    public List<int[]> emptyIndices(char[][] board){
        List<int[]> ans = new ArrayList<>();
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(board[i][j] == ' '){
                    ans.add(new int[]{i,j});
                }
            }
        }
        return ans;
    }

    public int[] winPossible(char c){
        if((board[0][0] == c && board[2][2] == c && board[1][1] == ' ') || (board[0][2] == c && board[2][0] == c && board[1][1] == ' ')){
            return new int[]{1,1};
        }else if(board[1][1] == c){
            if(board[0][0] == c && board[2][2] == ' '){
                return new int[]{2,2};
            }else if(board[0][0] == ' ' && board[2][2] == c){
                return new int[]{0,0};
            }else if(board[0][2] == ' ' && board[2][0] == c){
                return new int[]{0,2};
            }else if(board[0][2] == c && board[2][0] == ' '){
                return new int[]{2,0};
            }
        }

        for(int i = 0;i<3;i++){
            if(board[i][0] == c && board[i][1] == c && board[i][2] == ' ')
                return new int[]{i,2};
            else if(board[i][0] == c && board[i][1] == ' ' && board[i][2] == c)
                return new int[]{i,1};
            else if(board[i][0] == ' ' && board[i][1] == c && board[i][2] == c)
                return new int[]{i,0};
            else if(board[0][i] == c && board[1][i] == c && board[2][i] == ' ')
                return new int[]{2,i};
            else if(board[0][i] == c && board[1][i] == ' ' && board[2][i] == c)
                return new int[]{1,i};
            else if(board[0][i] == ' ' && board[1][i] == c && board[2][i] == c)
                return new int[]{0,i};
        }
        return new int[]{-1,-1};
    }
}

class Move{
    int[] index;
    int score;

    Move(){}
    Move(int score){
        this.score = score;
    }
}
