import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HideAndSeek {
	
	static int[] visit = new int[100001];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(n);
		visit[n] = 0;
		
		if(n == k) {
			System.out.println(0);
		}else {
			while(visit[k] == 0) {
				int num = queue.poll();
				int depth = visit[num];
				
				if(num + 1 < 100001 && visit[num + 1] == 0 && num + 1 != n) {
					visit[num + 1] = depth + 1;
					queue.offer(num + 1);
				}
				
				if(num - 1 >= 0 && visit[num - 1] == 0 && num - 1 != n) {
					visit[num - 1] = depth + 1;
					queue.offer(num - 1);
				}
				
				if(num * 2 < 100001 && visit[num * 2] == 0 && num * 2 != n) {
					visit[num * 2] = depth + 1;
					queue.offer(num * 2);
				}
				
			}
			
			System.out.println(visit[k]);
		}
		
	}

}
