import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] water = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		for (int i=0; i<n; i++) {
			water[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(water);
		
		int i = 0;
		int j = n-1;
		int ai = 0;
		int aj = n-1;
		
		int ans = water[i] + water[j];
		while (i < j) {
			int newSum = water[i] + water[j];
			if (Math.abs(newSum) < Math.abs(ans)) {
				ans = newSum;
				ai = i;
				aj = j;
			}
			
			if (newSum < 0) {
				i++;
			} else if (newSum > 0) {
				j--;
			} else {
				break;
			}
		}
		
		System.out.println(water[ai] + " " + water[aj]);
	}
}