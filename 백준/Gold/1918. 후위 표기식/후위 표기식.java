import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String form = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		for (int i=0; i<form.length(); i++) {
			char c = form.charAt(i);
			if (0 <= c-'A' && c-'A' <= 26) {
				System.out.print(c);
			} else {
				if (c == '(') {
					stack.push(c);
				} else if (c == ')') {
					while (stack.peek() != '(') {
						System.out.print(stack.pop());
					}
					stack.pop();
				} else {
					while (!stack.isEmpty() && haveToPop(stack.peek(), c)) {
						System.out.print(stack.pop());
					}
					stack.push(c);
				}
			}
		}
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}
	
	static boolean haveToPop(char a, char b) {
		int aNum, bNum;
		switch (a) {
			case '*':
			case '/':
				aNum = 2;
				break;
			case '+':
			case '-':
				aNum = 1;
				break;
			default:
				aNum = 0;
		}
		
		switch (b) {
			case '*':
			case '/':
				bNum = 2;
				break;
			case '+':
			case '-':
				bNum = 1;
				break;
			default:
				bNum = 0;
		}
		
		return (aNum >= bNum) ? true : false; 
	}
}