import java.util.Random;

public class StaticDie {
    public static void main(String args[]) {
      System.out.println("Dice: " + throwDice(8));
    }
    
    public static int throwDice(int sides) {
        Random rand = new Random();
        return rand.nextInt(sides)+1;
    }
}
