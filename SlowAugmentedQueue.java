import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

public class SlowAugmentedQueue implements IAugmentedQueue {
	private Queue<Integer> q = new ArrayDeque<Integer>();
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
		for (int element: q) {
			average = (average * count + element) / (count+1);
			count++;
		}
		return average;
	}

	@Override
	public void enqueue(int x) {
		q.add(x);
		map.put(x, map.getOrDefault(x, 0) + 1);
	}

	@Override
	public int dequeue() {
		int x = q.remove();
		map.put(x, map.get(x) - 1);
		if (map.get(x) == 0) map.remove(x);
		return x;
	}

	@Override
	public int front() {
		return q.peek();
	}
}