public class Board {
    public static char[][] board = new char[3][3];
    final private Player player = new Player();

    public Board(String boardState){
        char[] boardArray = boardState.toCharArray();
        int k = 0;
        int l = 0;
        for(char c: boardArray){
            if(c == '_')
                board[k][l] = ' ';
            else
                board[k][l] = c;
            if(l<2)
                l++;
            else if(l == 2){
                l = 0;
                k++;
            }
        }
    }

    public void printBoard(){
        System.out.println("---------");
        for (int i = 0;i<3;i++){
            System.out.print("| ");
            for (int j = 0; j<3;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }



    public void start(String player1, String player2){
        printBoard();
        while (true){
            switch (player1) {
                case "user" -> player.moveUser(1);
                case "easy" -> player.moveEasy(1);
                case "medium" -> player.moveMedium(1);
                case "hard" -> player.moveHard(1);
            }
            printBoard();
            char check = winner();
            if(check == 'X'){
                System.out.println("X wins");
                break;
            }else if(check == 'D'){
                System.out.println("Draw");
                break;
            }
            switch (player2) {
                case "user" -> player.moveUser(2);
                case "easy" -> player.moveEasy(2);
                case "medium" -> player.moveMedium(2);
                case "hard" -> player.moveHard(2);
            }
            printBoard();
            check = winner();
            if(check == 'O'){
                System.out.println("O wins");
                break;
            }else if(check == 'D'){
                System.out.println("Draw");
                break;
            }
        }
    }

    private boolean winnerX(){
        return winnerCheck('X');
    }

    private boolean winnerO(){
        return winnerCheck('O');
    }

    static boolean winnerCheck(char xo){
        if((Board.board[0][0] == Board.board[1][1] && Board.board[1][1] == Board.board[2][2]) || (Board.board[0][2] == Board.board[1][1] && Board.board[1][1] == Board.board[2][0])){
            if(Board.board[1][1] == xo)
                return true;
        }
        for(int i = 0; i<3;i++){
            if(Board.board[i][0]==Board.board[i][1] && Board.board[i][1] == Board.board[i][2]){
                if(Board.board[i][0] == xo)
                    return true;
            }
        }
        for(int i = 0; i<3;i++){
            if(Board.board[0][i]==Board.board[1][i] && Board.board[1][i] == Board.board[2][i]){
                if(Board.board[0][i] == xo)
                    return true;
            }
        }
        return false;
    }

    public char winner(){
        if(winnerX()){
            return 'X';
        }else if(winnerO()){
            return 'O';
        }else{
            int count = 0;
            for(int i = 0;i<3;i++){
                for(int j = 0; j<3;j++){
                    if(Board.board[i][j] == ' ')
                        count++;
                }
            }
            if(count == 0){
                return 'D';
            }else{
                return 'C';
            }
        }
    }
}
