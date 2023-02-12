public class Player{
    private String playerName;
    private Boolean myTurn;
    private int currentPosition;
    public Dice dice;

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }


    public void setMyTurn(Boolean myTurn) {
        this.myTurn = myTurn;
    }

    public Boolean getMyTurn() {
        return myTurn;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void movePlayerToNewPosition(int diceFaceValue)
    {
        this.currentPosition = this.currentPosition + diceFaceValue;
    }
}
