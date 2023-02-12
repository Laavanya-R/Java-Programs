import java.util.Scanner;

public class PlaySnakeAndLadder {

    static boolean gameOver;
    public static void main(String[] arguments)
    {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.setBoard();


        System.out.println("Welcome to - Snake and Ladder");

        System.out.print("Enter name of 1st player : ");
        Player player1 = new Player();
        player1.setPlayerName(sc.next());
        player1.setCurrentPosition(0);

        System.out.print("Enter name of 2nd player : ");
        Player player2 = new Player();
        player2.setPlayerName(sc.next());
        player2.setCurrentPosition(0);

        player1.setMyTurn(true);
        player2.setMyTurn(false);
        Player currentPlayer = player1;

        Dice diceObject = new Dice();

        while(!gameOver) {
           if(player1.getMyTurn())
                currentPlayer = player1;
            else if (player2.getMyTurn())
                currentPlayer = player2;

            System.out.println(currentPlayer.getPlayerName() + "!! Ready to Roll Dice? Enter Y to confirm");
            String confirmRollDice = sc.next().toUpperCase();

            if (confirmRollDice.equals("Y")) {
                diceObject.rollDice();
                System.out.println("Dice rolled to '" + diceObject.getDiceFaceValue() + "'");

                //player moves to the new position - players current position gets updated
                currentPlayer.movePlayerToNewPosition(diceObject.getDiceFaceValue());

                //if there is a ladder start in new position - players current position gets updated
                if(board.ladderStartPositions.contains(currentPlayer.getCurrentPosition())) {
                    currentPlayer.setCurrentPosition(board.risenByLadder(currentPlayer.getCurrentPosition()));
                    System.out.println("Risen by a ladder - position risen");
                }

                //if there is a snake start in new position - players current position gets updated
                if(board.snakeStartPositions.contains(currentPlayer.getCurrentPosition())) {
                    currentPlayer.setCurrentPosition(board.droppedBySnake(currentPlayer.getCurrentPosition()));
                    System.out.println("Bitten by a snake - position dropped");
                }
                if(currentPlayer.getCurrentPosition() > 100)
                    currentPlayer.setCurrentPosition(100);

                System.out.println("Current position of " + currentPlayer.getPlayerName() + " is " + currentPlayer.getCurrentPosition());

                //if(board.ladderRise(currentPlayer.getCurrentPosition()))

                player1.setMyTurn(!player1.getMyTurn());
                player2.setMyTurn(!player2.getMyTurn());

                if(currentPlayer.getCurrentPosition() >= 100) {
                    currentPlayer.setCurrentPosition(100);
                    gameOver = true;
                    System.out.println("Congrats " + currentPlayer.getPlayerName() + "!!! You Won!!!");
                }
           } else System.out.println(currentPlayer.getPlayerName() + "! enter Y to confirm");
        }






    }
}
