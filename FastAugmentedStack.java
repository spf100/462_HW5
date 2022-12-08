public class FastAugmentedStack implements IAugmentedStack {
	DynamicIntArray stackystack = new DynamicIntArray();
	DynamicIntArray min = new DynamicIntArray();
	DynamicIntArray max = new DynamicIntArray();
	double average;
	double avg;
	int temp = 0;
	@Override
	public int min() {
		// TODO Auto-generated method stub
		return min.getLast();
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return max.getLast();
	}

	@Override
	public double average() {
		// TODO Auto-generated method stub
		avg = average/stackystack.length();
		return avg;
	}

	@Override
	public void push(int x) {
		// TODO Auto-generated method stub
		stackystack.append(x);

		if(max.isEmpty()){
			max.append(x);;
		}
		if(min.isEmpty()) {
			min.append(x);
		}

		if(x > max.getLast()){
			max.append(x);
		}
		if(x < min.getLast()){
			min.append(x);
			}
		average += x;
		return;
	}

	@Override
	public int pop() {
		// TODO Auto-generated method stub\
		temp = stackystack.getLast();
		stackystack.removeLast();
		if(temp == max.getLast()){
			max.removeLast();
		}
		if(temp == min.getLast()){
			min.removeLast();
		}
		average -= temp;
		return temp;
	}

	@Override
	public int top() {
		// TODO Auto-generated method stub
		return stackystack.getLast();
	}
}
