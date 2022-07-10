import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.print("Input command: ");
            String input = in.nextLine();
            String[] initialState = input.split("\\s");
            if(initialState[0].equals("start")){
                if(initialState.length < 3){
                    System.out.println("Bad parameters!");
                }else if(initialState.length == 3){
                    switch (initialState[1]){
                        case "user" :
                        case "easy" :
                        case "medium" :
                        case "hard" :
                            break;
                        default: {
                            System.out.println("Bad parameters");
                            continue;
                        }
                    }
                    switch (initialState[2]){
                        case "user" :
                        case "easy" :
                        case "medium" :
                        case "hard" :
                            break;
                        default: {
                            System.out.println("Bad parameters");
                            continue;
                        }
                    }
                    String boardState = "_________";
                    Board board = new Board(boardState);
                    board.start(initialState[1],initialState[2]);
                }else{
                    System.out.println("Bad parameters!");
                }
            }else if(initialState[0].equals("exit")){
                break;
            }else{
                System.out.println("Bad parameters!");
            }
        }
    }
}
