package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
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
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
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
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
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
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	
    	// START SOLUTION
    	rl.lock();
    	try {
    		permitsAvailable.incrementAndGet();
    	} finally {
    		c.notify();
    		rl.unlock();
    	}
    	// END SOLUTION
    }
    
    /**
     * Return the number of permits available.
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
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    
    // START SOLUTION
    private final Condition c;
    // END SOLUTION

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    
    // START SOLUTION
    private volatile SimpleAtomicLong permitsAvailable;
    // END SOLUTION
}

