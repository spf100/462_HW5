import java.util.Random;

public class Driver {
	private static int NUMBER_OF_TESTS_PER_ROUND = 10_000;
	private static int NUMBER_OF_ROUNDS = 100;

	private static int OP_ADD = 0;
	private static int OP_REMOVE = 1;
	private static int OP_POLL = 2;
	private static int OP_MIN = 3;
	private static int OP_MAX = 4;
	private static int OP_AVE = 5;
	private static String[] stackOperations = {"push","pop","top","min","max","ave"};
	private static String[] queueOperations = {"enqueue","dequeue","front","min","max","ave"};
	private static Random prng = new Random();

	private static double EPSILON = 1E-6;

	public static void main(String[] args) {
		testQueues();

		testStacks();

	}


	public static void testQueues() {
		SlowAugmentedQueue slowQ = new SlowAugmentedQueue();
		long[] slowTimes = new long[6];
		FastAugmentedQueue fastQ = new FastAugmentedQueue();
		long[] fastTimes = new long[6];
		double[] counts = new double[6];
		int[] fastErrors = new int[6];
		System.out.println("Testing Queues");

		// Add values
		for (int i = 0; i < NUMBER_OF_ROUNDS * NUMBER_OF_TESTS_PER_ROUND; i++) {
			int x = prng.nextInt() % 1000;
			long time = System.currentTimeMillis();
			slowQ.enqueue(x);
			slowTimes[OP_ADD] += System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			fastQ.enqueue(x);
			fastTimes[OP_ADD] += System.currentTimeMillis() - time;
			counts[OP_ADD]++;
		}


		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			// Calculate averages
			long time = System.currentTimeMillis();
			double slowAve = slowQ.average();
			slowTimes[OP_AVE] += System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			double fastAve = fastQ.average();
			fastTimes[OP_AVE] += System.currentTimeMillis() - time;
			if (Math.abs(slowAve - fastAve) >= EPSILON) {
				fastErrors[OP_AVE]++;
			}
			counts[OP_AVE]++;
			for (int j = 0; j < NUMBER_OF_TESTS_PER_ROUND; j++) {
				switch(prng.nextInt(6)) {
				case 0:
					time = System.currentTimeMillis();
					int x1 = slowQ.front();
					slowTimes[OP_POLL] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					int x2 = fastQ.front();
					fastTimes[OP_POLL] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_POLL]++;
					counts[OP_POLL]++;
					break;
				case 1:
					time = System.currentTimeMillis();
					x1 = slowQ.dequeue();
					slowTimes[OP_REMOVE] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastQ.dequeue();
					fastTimes[OP_REMOVE] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_REMOVE]++;
					counts[OP_REMOVE]++;
					break;
				case 2:
					time = System.currentTimeMillis();
					x1 = slowQ.min();
					slowTimes[OP_MIN] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastQ.min();
					fastTimes[OP_MIN] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_MIN]++;
					counts[OP_MIN]++;
					break;
				case 3:
					time = System.currentTimeMillis();
					x1 = slowQ.max();
					slowTimes[OP_MAX] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastQ.max();
					fastTimes[OP_MAX] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_MAX]++;
					counts[OP_MAX]++;
					break;
				default:
					int x = prng.nextInt() % 1000;
					time = System.currentTimeMillis();
					slowQ.enqueue(x);
					slowTimes[OP_ADD] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					fastQ.enqueue(x);
					fastTimes[OP_ADD] += System.currentTimeMillis() - time;
					counts[OP_ADD]++;
					break;
				}
			}
		}
		System.out.println("Time analysis (microseconds per operation)");
		System.out.println(String.format("%-10s%15s%15s", "Operation","Slow Q","Fast Q"));
		boolean error = false;
		for (int i = 0; i < slowTimes.length; i++) {
			if (fastErrors[i] > 0) error = true;
			System.out.println(String.format("%-10s%15d%15d", queueOperations[i], (long) (10E6 * slowTimes[i]/counts[i]), (long) (10E6 * fastTimes[i]/counts[i])));
		}
		if (error) {
			System.out.println("\nError counts");
			System.out.println(String.format("%-10s%15s%15s", "Operation","Error Count","Correct Count"));
			for (int i = 0; i < fastErrors.length; i++) {
				if (fastErrors[i] > 0) System.out.println(String.format("%-10s%15d%15d", queueOperations[i], fastErrors[i], ((long)counts[i] - fastErrors[i])));
			}
		}
		else {
			System.out.println("\nNo errors encountered during testing.");
		}
	}


	public static void testStacks() {
		SlowAugmentedStack slowStack = new SlowAugmentedStack();
		long[] slowTimes = new long[6];
		FastAugmentedStack fastStack = new FastAugmentedStack();
		long[] fastTimes = new long[6];
		int[] fastErrors = new int[6];
		double[] counts = new double[6];
		System.out.println("\nTesting Stacks");

		// Add values
		for (int i = 0; i < NUMBER_OF_ROUNDS * NUMBER_OF_TESTS_PER_ROUND; i++) {
			int x = prng.nextInt();
			long time = System.currentTimeMillis();
			slowStack.push(x);
			slowTimes[OP_ADD] += System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			fastStack.push(x);
			fastTimes[OP_ADD] += System.currentTimeMillis() - time;
			counts[OP_ADD]++;
		}

		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			// Calculate averages
			long time = System.currentTimeMillis();
			double slowAve = slowStack.average();
			slowTimes[OP_AVE] += System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			double fastAve = fastStack.average();
			fastTimes[OP_AVE] += System.currentTimeMillis() - time;
			if (Math.abs(slowAve - fastAve) >= EPSILON) {
				fastErrors[OP_AVE]++;
			}
			counts[OP_AVE]++;
			for (int j = 0; j < NUMBER_OF_TESTS_PER_ROUND; j++) {
				switch(prng.nextInt(6)) {
				case 0:
					time = System.currentTimeMillis();
					int x1 = slowStack.top();
					slowTimes[OP_POLL] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					int x2 = fastStack.top();
					fastTimes[OP_POLL] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_POLL]++;
					counts[OP_POLL]++;
					break;
				case 1:
					time = System.currentTimeMillis();
					x1 = slowStack.pop();
					slowTimes[OP_REMOVE] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastStack.pop();
					fastTimes[OP_REMOVE] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_REMOVE]++;
					counts[OP_REMOVE]++;
					break;
				case 2:
					time = System.currentTimeMillis();
					x1 = slowStack.min();
					slowTimes[OP_MIN] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastStack.min();
					fastTimes[OP_MIN] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_MIN]++;
					counts[OP_MIN]++;
					break;
				case 3:
					time = System.currentTimeMillis();
					x1 = slowStack.max();
					slowTimes[OP_MAX] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					x2 = fastStack.max();
					fastTimes[OP_MAX] += System.currentTimeMillis() - time;
					if (x1 != x2) fastErrors[OP_MAX]++;
					counts[OP_MAX]++;
					break;
				default:
					int x = prng.nextInt();
					time = System.currentTimeMillis();
					slowStack.push(x);
					slowTimes[OP_ADD] += System.currentTimeMillis() - time;
					time = System.currentTimeMillis();
					fastStack.push(x);
					fastTimes[OP_ADD] += System.currentTimeMillis() - time;
					counts[OP_ADD]++;
					break;
				}
			}
		}
		System.out.println("Time analysis (microseconds per operation)");
		System.out.println(String.format("%-10s%15s%15s", "Operation","Slow Stack","Fast Stack"));
		boolean error = false;
		for (int i = 0; i < slowTimes.length; i++) {
			if (fastErrors[i] > 0) error = true;
			System.out.println(String.format("%-10s%15d%15d", stackOperations[i], (long) (10E6 * slowTimes[i]/counts[i]), (long) (10E6 * fastTimes[i]/counts[i])));
		}
		if (error) {
			System.out.println("\nError counts");
			System.out.println(String.format("%-10s%15s%15s", "Operation","Error Count","Correct Count"));
			for (int i = 0; i < fastErrors.length; i++) {
				if (fastErrors[i] > 0) System.out.println(String.format("%-10s%15d%15d", stackOperations[i], fastErrors[i], ((long)counts[i] - fastErrors[i])));
			}
		}
		else {
			System.out.println("\nNo errors encountered during testing.");
		}
	}
}