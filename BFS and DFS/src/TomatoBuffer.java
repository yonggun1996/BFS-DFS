import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TomatoBuffer {
	
	static int[][] map;
	static int max = 0;
	static Queue<TomatoBufferNode> queue = new LinkedList<TomatoBufferNode>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					TomatoBufferNode tn = new TomatoBufferNode(i, j);
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
			bw.write(String.valueOf(max - 1));
		}else {
			bw.write(String.valueOf(-1));
		}
		
		bw.flush();
		bw.close();
		
	}
	
	public static void BFS() {
		while(!queue.isEmpty()) {
			TomatoBufferNode tn = queue.poll();
			
			int x = tn.x;
			int y = tn.y;
			int sum = map[x][y];
			
			max = Math.max(max, sum);
			
			if(x > 0 && map[x - 1][y] == 0) {
				map[x - 1][y] = sum + 1;
				queue.offer(new TomatoBufferNode(x - 1, y));
			}
			
			if(x < map.length - 1 && map[x + 1][y] == 0) {
				map[x + 1][y] = sum + 1;
				queue.offer(new TomatoBufferNode(x + 1, y));
			}
			
			if(y > 0 && map[x][y - 1] == 0) {
				map[x][y - 1] = sum + 1;
				queue.offer(new TomatoBufferNode(x, y - 1));
			}
			
			if(y < map[0].length - 1 && map[x][y + 1] == 0) {
				map[x][y + 1] = sum + 1;
				queue.offer(new TomatoBufferNode(x, y + 1));
			}
		}
	}

}

class TomatoBufferNode {
	int x;
	int y;
	
	public TomatoBufferNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}