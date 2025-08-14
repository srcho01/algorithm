import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
	
	static List<int[]> order = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		hanoi(n, 1, 3, 2);
		
		StringBuilder sb = new StringBuilder();
		sb.append((long)Math.pow(2, n)-1).append("\n");
		
		for (int[] content: order) {
			sb.append(content[0] + " ").append(content[1] + "\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void hanoi(int n, int from, int to, int sub) {
		if (n == 1) {
			order.add(new int[]{from, to});
			return;
		}
		
		hanoi(n-1, from, sub, to);
		hanoi(1, from, to, sub);
		hanoi(n-1, sub, to, from);
	}
	
}