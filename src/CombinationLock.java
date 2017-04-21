//package combinationLock;


/**
 * @version 1.1
 * @since 10/17/16
 * @author George Nguyen
 */
public class CombinationLock {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // setting up first lock and checking current position
        Server combo1 = new Server();
        combo1.pos0();
        combo1.getPos();
        
        // entering combination and then checking current position
        //combo1.firstTry("clockwise", 64);
        combo1.firstTry();
        //combo1.secondTry("counter", 59);
        combo1.secondTry();
        combo1.thirdTry("clockwise", 27);
        combo1.pos0();
        combo1.getPos();
        
        // checking if lock is open, then opening, closing, and resetting lock
        combo1.isOpen();
        combo1.openLock();
        combo1.closeLock();
        combo1.resetLock();
        System.out.println();
        
        // trying to open lock after it has been reset, then getting currentPos
        combo1.openLock();
        combo1.pos0();
        combo1.getPos();
        
        // testing if lock will reopen after it has already been opened 
        // and closed
        combo1.firstTry("clockwise", 64);
        combo1.secondTry("counter", 59);
        combo1.thirdTry("clockwise", 27);
        combo1.openLock();
        
        // testing boundary values (where currentPos may be negative)
        Server combo2 = new Server(20, 39, 0);
        combo2.firstTry("clockwise", 60);
        combo2.secondTry("counter", 61);
        combo2.thirdTry("clockwise", 1);
        combo2.openLock();
        
        // testing boundary values (most number of dial turns possible that 
        // would open the lock
        Server combo3 = new Server();
        combo3.firstTry("clockwise", 80);
        combo3.secondTry("counter", 80);
        combo3.thirdTry("clockwise", 40);
        combo3.openLock();
        
        // testing boundary values ( values that exceed two revolutions, 
        // thus would not open the lock, 
        // and may result in currentPos > 39 or currentPos < 0
        Server combo4 = new Server();
        combo4.firstTry("clockwise", 120);
        combo4.secondTry("counter", 120);
        combo4.thirdTry("clockwise", 80);
        combo4.openLock();
    }
    
}