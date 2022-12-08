public interface IAugmentedStack {
	
	/**
	 * @return minimum value in stack
	 */
	int min();

	/**
	 * @return maximum value in stack
	 */
	int max();

	/**
	 * @return average value in stack
	 */
	double average();

	/**
	 * Add value to stack
	 * @param x value to add
	 */
	void push(int x);

	/** 
	 * Remove value from stack
	 * @return value that was removed
	 */
	int pop();

	/** 
	 * return top of stack
	 * @return value at top of the stack
	 */
	int top();
}