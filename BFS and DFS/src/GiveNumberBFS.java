import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GiveNumberBFS {
	
	static String[] arr;
	static boolean[][] visit;
	static Queue<Node> queue = new LinkedList<Node>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = Integer.parseInt(in.next());
		arr = new String[n];
		visit = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.next();
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			String s = arr[i];
			
			for(int j = 0; j < n; j++) {
				if(s.charAt(j) == '1' && !visit[i][j]) {
					visit[i][j] = true;
					Node nd = new Node(i, j);
					queue.offer(nd);
					int count = search();
					list.add(count);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public static int search() {
		int count = 0;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			count++;
			
			if(x > 0 && arr[x - 1].charAt(y) == '1' && !visit[x - 1][y]) {
				visit[x - 1][y] = true;
				queue.offer(new Node(x - 1, y));
			}
			
			if(x < arr.length - 1 && arr[x + 1].charAt(y) == '1' && !visit[x + 1][y]) {
				visit[x + 1][y] = true;
				queue.offer(new Node(x + 1, y));
			}
			
			if(y > 0 && arr[x].charAt(y - 1) == '1' && !visit[x][y - 1]) {
				visit[x][y - 1] = true;
				queue.offer(new Node(x, y - 1));
			}
			
			if(y < arr.length - 1 && arr[x].charAt(y + 1) == '1' && !visit[x][y + 1]) {
				visit[x][y + 1] = true;
				queue.offer(new Node(x, y + 1));
			}
		}
		
		return count;
	}

}

class Node{
	int x;
	int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
