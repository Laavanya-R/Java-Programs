public class Dice {
    private int diceFaceValue;

    public void rollDice() {
        int randomNumber = (int)(Math.random()*6);
        this.diceFaceValue = randomNumber+1; //to get numbers from 1 to 6
    }
    public int getDiceFaceValue() {
        return diceFaceValue;
    }
 }
