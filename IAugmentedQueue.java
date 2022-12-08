public interface IAugmentedQueue {
	/**
	 * @return minimum value in queue
	 */
	int min();

	/**
	 * @return maximum value in queue
	 */
	int max();

	/**
	 * @return average value in queue
	 */
	double average();

	/**
	 * Add value to queue
	 * @param x value to queue
	 */
	void enqueue(int x);

	/** 
	 * Remove value from queue
	 * @return value that was dequeued
	 */
	int dequeue();

	/** 
	 * return front of queue
	 * @return value at front of the queue
	 */
	int front();
}