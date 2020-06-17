package coolboys.net;

import java.util.Arrays;
import java.util.Random;

public class Test {
	public static String test() {
		int score = 240;
		String token = "12345-";
		boolean done = false;
		int[] nums = new int[5];
		int total = 100;
		Random rand = new Random();
		for (int i = 0; i < nums.length-1; i++) {
		    nums[i] = rand.nextInt(total);
		    total -= nums[i];
		}
		nums[nums.length-1] = total;
		Arrays.sort(nums);
		System.out.println("Hallo");
		return "hi";
	}
}
