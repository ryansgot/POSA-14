// Import the necessary Java synchronization and scheduling classes.

package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @class SimpleAtomicLong
 *
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;
    
    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */

    // TODO -- you fill in here by replacing the null with an
    // initialization of ReentrantReadWriteLock.
    
    // START SOLUTION
    private ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();
    // END SOLUTION

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO -- you fill in here
    	
    	// START SOLUTION
    	mValue = initialValue;
    	// END SOLUTION
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get()
    {
    	long value;

        // TODO -- you fill in here
        
        // START SOLUTION
        mRWLock.readLock().lock();
        value = mValue;
        mRWLock.readLock().unlock();
        // END SOLUTION

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
        long value = 0;

        // TODO -- you fill in here
        
        // START SOLUTION
        mRWLock.writeLock().lock();
        value = --mValue;
        mRWLock.writeLock().unlock();
        // END SOLUTION

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
        long value = 0;

        // TODO -- you fill in here
        
        // START SOLUTION
        mRWLock.writeLock().lock();
        value = mValue++;
        mRWLock.writeLock().unlock();
        // END SOLUTION

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
        long value = 0;

        // TODO -- you fill in here
        
        // START SOLUTION
        mRWLock.writeLock().lock();
        value = mValue--;
        mRWLock.writeLock().unlock();
        // END SOLUTION

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
        long value = 0;

        // TODO -- you fill in here
        
        // START SOLUTION
        mRWLock.writeLock().lock();
        value = ++mValue;
        mRWLock.writeLock().unlock();
        // END SOLUTION

        return value;
    }
}