import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato {
	
	static int[][] map;
	static int max = 0;
	static Queue<TomatoNode> queue = new LinkedList<TomatoNode>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int m = in.nextInt();
		int n = in.nextInt();
		
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = in.nextInt();
				
				if(map[i][j] == 1) {
					TomatoNode tn = new TomatoNode(i, j);
					queue.offer(tn);
				}
			}
		}
		
		BFS();
		
		boolean nothing_zero = true;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					nothing_zero = false;
				}
			}
		}
		
		if(nothing_zero) {
			System.out.println(max - 1);
		}else {
			System.out.println(-1);
		}
		
	}
	
	public static void BFS() {
		while(!queue.isEmpty()) {
			TomatoNode tn = queue.poll();
			
			int x = tn.x;
			int y = tn.y;
			int sum = map[x][y];
			
			max = Math.max(max, sum);
			
			if(x > 0 && map[x - 1][y] == 0) {
				map[x - 1][y] = sum + 1;
				queue.offer(new TomatoNode(x - 1, y));
			}
			
			if(x < map.length - 1 && map[x + 1][y] == 0) {
				map[x + 1][y] = sum + 1;
				queue.offer(new TomatoNode(x + 1, y));
			}
			
			if(y > 0 && map[x][y - 1] == 0) {
				map[x][y - 1] = sum + 1;
				queue.offer(new TomatoNode(x, y - 1));
			}
			
			if(y < map[0].length - 1 && map[x][y + 1] == 0) {
				map[x][y + 1] = sum + 1;
				queue.offer(new TomatoNode(x, y + 1));
			}
		}
	}

}

class TomatoNode {
	int x;
	int y;
	
	public TomatoNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}