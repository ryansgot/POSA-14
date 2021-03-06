package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 * 
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject (which is accessed via a Condition). It must
 *        implement both "Fair" and "NonFair" semaphore semantics,
 *        just liked Java Semaphores.
 */
public class SimpleSemaphore {
    /**
     * Define a Lock to protect the critical section.
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	
    	// START SOLUTION
    	permitsAvailable = new SimpleAtomicLong(0 < permits ? permits : 1);
    	rl = new ReentrantLock(fair);
    	c = rl.newCondition();
    	// END SOLUTION
    }

    /**
     * Define a Condition that waits while the number of permits is 0.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	
    	// START SOLUTION
    	rl.lockInterruptibly();
    	try {
	    	if (0 == permitsAvailable.get()) {
	    		c.await();
	    	}
	    	permitsAvailable.decrementAndGet();
    	} finally {
    		rl.unlock();
    	}
    	// END SOLUTION
    }

    /**
     * Define a count of the number of available permits.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	
    	// START SOLUTION
    	rl.lock();
    	try {
	    	if (0 == permitsAvailable.get()) {
	    		c.awaitUninterruptibly();
	    	}
	    	permitsAvailable.decrementAndGet();
    	} finally {
    		rl.unlock();
    	}
    	// END SOLUTION
    }

    /**
     * Acquire one permit from the semaphore in a manner that can be
     * interrupted.
     */
    void release() {
        // TODO - you fill in here
    	
    	// START SOLUTION
    	rl.lock();
    	try {
    		permitsAvailable.incrementAndGet();
    	} finally {
    		c.signal();
    		rl.unlock();
    	}
    	// END SOLUTION
    }

    /**
     * Acquire one permit from the semaphore in a manner that cannot be
     * interrupted.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	
    	// START SOLUTION
    	return (int) permitsAvailable.get();
    	// END SOLUTION
    }
    
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    
    // START SOLUTION
    private final ReentrantLock rl;
    // END SOLUTION

    /**
     * Return one permit to the semaphore.
     */
    // TODO - you fill in here
    
    // START SOLUTION
    private final Condition c;
    // END SOLUTION

    /**
     * Return the number of permits available.
     */
    // TODO - you fill in here
    
    // START SOLUTION
    private volatile SimpleAtomicLong permitsAvailable;
    // END SOLUTION
}
