import java.util.Scanner;

//package combinationLock;

/** 
 * @version 1.10
 * @since 10/17/16
 * @author George Nguyen
 */


public class Server {
    
    private static final int TOTAL = 40;           // total dial numbers on lock
    private static final int TOTAL_MAX = 2*TOTAL;  // total revolutions possible, equal to 2 complete dial turns
    private int x, y, z;                           // pass code for lock
    private boolean xx, yy, zz;                    // track if inputted passcode matches x, y, and z 
    private int currentPos = 0;                    // current position of dial 
    private int status = 0;                        // checks lock's open/close status
    Scanner scan = new Scanner(System.in);
    
    
    
    // CONSTRUCTOR METHODS *****************************************************
    /**
     * creates a combination lock with passcode x, y, z
     * @param a is combination x value
     * @param b is combination y value
     * @param c is combination z value
     */
    public Server(int a, int b, int c){
        x = a;
        y = b;
        z = c;
        // colored output displaying lock combination, MAY NOT BE PORTABLE:
        //System.out.printf((char)27 + "[46mYour lock combination has been set to: %d, %d, %d\n" + (char)27 + "[0m", x, y, z);
        // non colored output displaying lock combination
        System.out.printf("Your lock combination has been set to: %d, %d, %d\n", x, y, z);
        
        // output hiding lock combination
        //System.out.print("Your lock combination has been set.\n");
        }
    
    /**
     * creates a default combination lock with passcode 0,0,0
     */
    public Server(){
    	System.out.println("Please set your Lock combination.  Enter 3 numbers, separated by spaces: ");
    	int [] lockCombination = new int[3];
    	for (int i = 0; i < 3; i++){
    		lockCombination[i] = scan.nextInt();
    	}
        x = lockCombination[0];
        y = lockCombination[1];
        z = lockCombination[2];
        // colored output displaying lock combination.  MAY NOT BE PORTABLE
        //System.out.printf((char)27 + "[46mLock combination set to: %d, %d, %d\n" + (char)27 + "[0m", x, y, z );
        // output displaying lock combination
         System.out.printf("Lock combination set to: %d, %d, %d\n", x, y, z);
        
        // output hiding lock combination
        //System.out.printf("Lock combination set to default.\n")
    }
    
    
// ACCESSOR METHODS ***********************************************************
    /**
     * Gets current position of dial
     * @return current position of dial
     */
    public int getPos(){
        System.out.println("The dial is currently at " + currentPos);
        return currentPos;
    }
        
    /**
     * checks if dial is at position 0
     * @param pos current position of dial
     * @return true if at 0, otherwise false
     */
    public boolean pos0(){
        System.out.println("Is the dial at 0...?");
        if (currentPos == 0){
            System.out.println("\tLooks like it.");
            return true;
        }
        else {
            System.out.println("\tDoesn't look like it.");
            return false;
        }
    }
    
    /**
     * Checks if lock is open or closed
     * @return true if open, otherwise false
     */
    public boolean isOpen(){
        System.out.println("You check the lock to see if it's already open, ");
        if (status == 1){
            System.out.println("\tand it is.\n");
            return true;
        } else {
            System.out.println("\tbut it isn't.\n");
            return false;
        }
    }
    
//  MUTATOR METHODS ***********************************************************
    
    /**
     * turns the dial clockwise
     * @param ticks amount of ticks to turn clockwise
     * @return currentPos   current position of dial
     */
    public int turnClockwise(int ticks) {
        currentPos = (currentPos + ticks) % TOTAL;
        System.out.println("You turn the dial " + ticks
                            + " ticks clockwise to " + currentPos);
        return currentPos;
    }
    
    
    /**
     * turns the dial counter clockwise
     * @param ticks amount of ticks to turn clockwise
     * @return currentPos   current position of dial
     */
    public int turnCounter(int ticks) {
        // if number of dials turned exceeds two revolutions
        if (ticks > TOTAL_MAX){ticks %= TOTAL_MAX;}
        // if currentPos would result in a negative
        if ((TOTAL - (ticks - currentPos)) < 0){
            currentPos = TOTAL + (TOTAL - (ticks - currentPos));
        }
        else {currentPos = TOTAL - (ticks - currentPos);}
        System.out.println("You turn the dial " + ticks
                            + " ticks counter clockwise to " + currentPos);
        return currentPos;
    }
    


    /**
     * enter first passcode for lock
     * user turns dial either clockwise, or counter clockwise
     * @param direction specifies the direction to turn dial
     * @param ticks number of ticks to turn dial
     */
    public void firstTry(String direction, int ticks){
        if (direction == "clockwise") { turnClockwise(ticks); }
        else { turnCounter(ticks); }
        if (fullRevolution(ticks) && currentPos == x){
            xx = true;
        } 
        else xx = false;
    }
    
    /**
     * firstTry method prompts user for input
     */
    public void firstTry(){
    	System.out.println("Please enter the direction you would like to turn the dial.\n" 
    					+ "(clockwise/counterclockwise) => (c/cc)");
    	String direction = scan.nextLine();
    	while (!direction.equals("c") || !direction.equals("c")){
    		System.out.println("Invalid entry.  Which direction would you like to turn the dial?\n" 
    					+ "(clockwise/counterclockwise) => (c/cc)");
    		direction = scan.nextLine();
    	}
    	System.out.println("Please enter the number of ticks you want to spin the dial.");
    	int ticks = scan.nextInt();
    	if (direction == "c") {turnClockwise(ticks);}
    	else if (direction == "cc") {turnCounter(ticks);}
    	else ;
    }
    
    
    /**
     * enter second passcode for lock
     * user turns dial either clockwise, or counter clockwise
     * @param direction direction to turn dial
     * @param ticks number of ticks to turn dial
     */
    public void secondTry(String direction, int ticks){
        if (direction == "clockwise") { turnClockwise(ticks); }
        else { turnCounter(ticks); }
        if (fullRevolution(ticks) && currentPos == y){
            yy = true;
        } 
        else yy = false;
    }
    
    
     /**
     * enter third passcode for lock
     * user turns dial either clockwise, or counter clockwise
     * @param direction direction to turn dial
     * @param ticks number of ticks to turn dial
     */
    public void thirdTry(String direction, int ticks){
        if (direction == "clockwise") { turnClockwise(ticks); }
        else { turnCounter(ticks); }
        //if ((!fullRevolution(ticks)) && currentPos == z)
        if (currentPos == z)
        {
            zz = true;
        } 
        else zz = false;
        System.out.println();
    }
    
    
    /**
     * opens the lock
     * if all pass codes are input correctly and lock is closed, lock opens
     */
    public void openLock(){
        System.out.println("You try to open the lock.");
        if (xx && yy && zz) {
            status = 1;
            System.out.println((char)27 + "[34m\tClick.  The lock opens.\n" + (char)27 + "[0m");
        }
        else System.out.println((char)27 + "[34m\tThe lock doesn't budge.\n" + (char)27 + "[0m");
    }
    
    /**
     * closes lock, if lock is open
     */
    public void closeLock(){
        if (status == 1){status = 0;}
        System.out.println("You close the lock.");
    }
    
    
    /**
     * resets the clock dial after lock is closed
     */
    public void resetLock(){
        xx = false;
        yy = false;
        zz = false;
        currentPos = 0;
        System.out.println("You turn the dial back and forth for a whole 23 " 
                        + "\nminutes and reset the lock, returning the dial to " 
                        + "0. Weirdo...");
    }
    
// OTHER METHODS ********************************
    
     /**
     * checks if dial has been spun a full revolution
     * @param totalTicks amount of ticks dial is spun 
     * @return true if spun full revolution, otherwise false
     */
    public boolean fullRevolution(int totalTicks){
        // code to make this true
        if (totalTicks >= TOTAL && totalTicks <= TOTAL_MAX){
            return true;
        }
        else return false;
    }
    
    
}