package com.shreeji.tech;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Queue<Integer> q = new CustomQueueImpl<>();
		for (int i = 0; i < n; i++) {
			int cmd = in.nextInt();
			switch (cmd) {
			case 1:
				int a = in.nextInt();
				q = q.enQueue(a);
				break;
			case 2:
				q = q.deQueue();
				break;
			case 3:
				System.out.println(q.head());
				break;
			default:
				break;
			}
		}
		in.close();
	}
}
 	