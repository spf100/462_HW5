import java.util.ArrayList;
import java.util.TreeMap;

public class SlowAugmentedStack implements IAugmentedStack {
	private ArrayList<Integer> stack = new ArrayList<>();
	private TreeMap<Integer,Integer> map = new TreeMap<>();
	
	@Override
	public int min() {
		return map.firstKey();
	}

	@Override
	public int max() {
		return map.lastKey();
	}

	@Override
	public double average() {
		double average = 0;
		int count = 0;
		for (int element: stack) {
			average = (average * count + element) / (count+1);
			count++;
		}
		return average;
	}

	@Override
	public void push(int x) {
		stack.add(x);
		map.put(x, map.getOrDefault(x, 0) + 1);
	}

	@Override
	public int pop() {
		int x = stack.remove(stack.size()-1);
		map.put(x, map.get(x) - 1);
		if (map.get(x) == 0) map.remove(x);
		return x;
	}

	@Override
	public int top() {
		return stack.get(stack.size()-1);
	}
}