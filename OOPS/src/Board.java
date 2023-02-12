import java.util.ArrayList;

public class Board {

    Snake snake1 = new Snake();
    Snake snake2 = new Snake();
    Snake snake3 = new Snake();
    Snake snake4 = new Snake();
    Snake snake5 = new Snake();
    Ladder ladder1 = new Ladder();
    Ladder ladder2 = new Ladder();
    Ladder ladder3 = new Ladder();
    Ladder ladder4 = new Ladder();
    Ladder ladder5 = new Ladder();

    public ArrayList<Integer> ladderStartPositions = new ArrayList<Integer>();
    public ArrayList<Integer> snakeStartPositions = new ArrayList<Integer>();
    public int droppedBySnake(int currentPosition)
    {
        if(snake1.getStartPosition() == currentPosition)
            return snake1.getEndPosition();
        else if(snake2.getStartPosition() == currentPosition)
            return snake2.getEndPosition();
        else if(snake3.getStartPosition() == currentPosition)
            return snake3.getEndPosition();
        else if(snake4.getStartPosition() == currentPosition)
            return snake4.getEndPosition();
        else if(snake5.getStartPosition() == currentPosition)
            return snake5.getEndPosition();
        else
            return currentPosition;
    }

    public int risenByLadder(int currentPosition)
    {
        if(ladder1.getStartPosition() == currentPosition)
            return ladder1.getEndPosition();
        else if(ladder2.getStartPosition() == currentPosition)
            return ladder2.getEndPosition();
        else if(ladder3.getStartPosition() == currentPosition)
            return ladder3.getEndPosition();
        else if(ladder4.getStartPosition() == currentPosition)
            return ladder4.getEndPosition();
        else if(ladder5.getStartPosition() == currentPosition)
            return ladder5.getEndPosition();
        else
            return currentPosition;
    }

    public void setBoard() {

        snake1.setStartPosition(34);
        snake1.setEndPosition(2);

        snake2.setStartPosition(45);
        snake2.setEndPosition(9);

        snake3.setStartPosition(74);
        snake3.setEndPosition(24);

        snake4.setStartPosition(93);
        snake4.setEndPosition(64);

        snake5.setStartPosition(97);
        snake5.setEndPosition(7);

        ladder1.setStartPosition(4);
        ladder1.setEndPosition(25);

        ladder2.setStartPosition(27);
        ladder2.setEndPosition(85);

        ladder3.setStartPosition(19);
        ladder3.setEndPosition(65);

        ladder4.setStartPosition(34);
        ladder4.setEndPosition(99);

        ladder5.setStartPosition(67);
        ladder5.setEndPosition(78);

        ladderStartPositions.add(ladder1.getStartPosition());
        ladderStartPositions.add(ladder2.getStartPosition());
        ladderStartPositions.add(ladder3.getStartPosition());
        ladderStartPositions.add(ladder4.getStartPosition());
        ladderStartPositions.add(ladder5.getStartPosition());

        snakeStartPositions.add(snake1.getStartPosition());
        snakeStartPositions.add(snake2.getStartPosition());
        snakeStartPositions.add(snake3.getStartPosition());
        snakeStartPositions.add(snake4.getStartPosition());
        snakeStartPositions.add(snake5.getStartPosition());
    }
    }

