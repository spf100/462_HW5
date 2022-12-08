public class FastAugmentedQueue implements IAugmentedQueue {
	double average= 0;int temp = 0; double avg;
	DynamicIntArray queque = new DynamicIntArray();
	DynamicIntArray min = new DynamicIntArray();
	DynamicIntArray max = new DynamicIntArray();
	@Override
	public int min() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double average() {
		// TODO Auto-generated method stub
		avg = average / queque.length();
		return avg;
	}

	@Override
	public void enqueue(int x) {
		// TODO Auto-generated method stub
		queque.append(x);
		average += x;
	}

	@Override
	public int dequeue() {
		// TODO Auto-generated method stub
		if(queque.length()== 0) {
			System.out.printf("queue is empty cannot dequeue");
			return 0;
		}
		temp = queque.getFirst();
		queque.removeFirst();
		average -= temp;
		return temp;
	}

	@Override
	public int front() {
		// TODO Auto-generated method stub
		return queque.getFirst();
	}	
}