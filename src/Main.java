import java.util.*;

public class Main {
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        char [][] board = { {' ', '|', ' ','|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ','|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ','|', ' '}};
        printBoardGame(board);

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter any number from (1 - 9): ");
            int playerturn = scanner.nextInt();
            while(playerPosition.contains(playerturn) || cpuPosition.contains(playerturn)){
                System.out.println("Position have been taken! Chose again");
                playerturn = scanner.nextInt();
            }
            placeHolder(board, playerturn, "Player");
            boolean isTurnOver = false;
            Random rand = new Random();
            int cpuTurn = rand.nextInt(9) + 1;
            while(playerPosition.contains(cpuTurn) || cpuPosition.contains(cpuTurn)) {

                if (playerPosition.size() + cpuPosition.size() == 9) {
                    isTurnOver = true;
                    break;
                }

                System.out.println("Position have been taken! Chose again");
                cpuTurn = rand.nextInt(9) + 1;
            }

            if (!isTurnOver) {
                placeHolder(board, cpuTurn, "CPU");
                printBoardGame(board);
            }
            String result = checkWin();
            if (result.length() != 0) {
                System.out.println(result);
                break;
            }
        }
    }
    private static void printBoardGame(char[][] board){
        for(char[] row : board){
            for(char chars : row){
                System.out.print(chars);
            }
            System.out.println();
        }
    }
    private static void placeHolder(char[][] board, int position, String user){
        char symbol = ' ';
        if(user.equals("Player")){
            symbol = 'X';
            playerPosition.add(position);
        }else if(user.equals("CPU")){
            symbol = 'O';
            cpuPosition.add(position);
        }

        switch (position){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    private static String checkWin(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List diagonal1 = Arrays.asList(1,5,9);
        List diagonal2 = Arrays.asList(3,5,7);
        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diagonal1);
        winning.add(diagonal2);
        for (List list : winning) {
            if(playerPosition.containsAll(list)){
                return "Congratulation Player has won the game!";
            }else if(cpuPosition.containsAll(list)){
                return "CPU has won the game!";
            }else if(playerPosition.size() + cpuPosition.size() == 9){
                return "Its a tie!";
            }
        }
        return "";
    }
}
