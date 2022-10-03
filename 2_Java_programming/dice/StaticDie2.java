import java.util.Random;

public class StaticDie2 {
    
    private static Random RAND = new Random(); // random instans
    
    public static void main(String args[]) {
        // kasta 5 gånger
        for(int i=0; i<5; ++i) {
            // slumpa tärningsstorlek
            int size = RAND.nextInt(94)+7; // 6-100
            System.out.println("Throwing die size " + size + " "+ 5 +" times.");
            
            // gör kasten
            for(int j=0; j<5; ++j) {
                System.out.println("  Throw "+ (j+1) +": " + throwDice(size));
            }
        }
    }
    
    public static int throwDice(int sides) {
        
        return RAND.nextInt(sides)+1;
    }
}
