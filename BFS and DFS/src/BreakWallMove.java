import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreakWallMove {
	
	static boolean breakwall = false;//벽을 만난적이 있는가?
	static String[] map;
	static int[][] visit;
	static int answer = -1;
	static Queue<BWMNode> queue = new LinkedList<BWMNode>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BreakWallMove b = new BreakWallMove();
		
		int height = in.nextInt();
		int width = in.nextInt();
		
		map = new String[height];
		visit = new int[height][width];
		for(int i = 0; i < height; i++) {
			map[i] = in.next();
			for(int j = 0; j < width; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		
		BWMNode node = new BWMNode(0, 0, 0, 1);
		queue.offer(node);
		System.out.println(b.BFS());
		
	}
	
	public int BFS() {
		while(!queue.isEmpty()) {
			BWMNode node = queue.poll();
			
			int y = node.y;
			int x = node.x;
			int wall = node.wall;
			int load = node.load;
			
			if(y == map.length - 1 && x == map[0].length() - 1) {
				return load;
			}
			
			//위
			if(y > 0) {
				/*if(map[y - 1].charAt(x) == '1') {
					queue.offer(new BWMNode(y - 1, x, true));
				}else {
					queue.offer(new BWMNode(y - 1, x, flag));
				}
				
				if(load[y - 1][x] == 0 || !flag) {
					load[y - 1][x] = m + 1;
				}*/
				if(visit[y - 1][x] > wall) {
					if(map[y - 1].charAt(x) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMNode(y - 1, x, wall + 1, load + 1));
							visit[y - 1][x] = wall + 1;
						}
					}else {
						queue.offer(new BWMNode(y - 1, x, wall, load + 1));
						visit[y - 1][x] = wall;
					}
				}
				
			}
			
			//아래
			if(y < map.length - 1) {
				/*if(map[y + 1].charAt(x) == '1') {
					queue.offer(new BWMNode(y + 1, x, true));
				}else {
					queue.offer(new BWMNode(y + 1, x, flag));
				}
				
				if(load[y + 1][x] == 0 || !flag) {
					load[y + 1][x] = m + 1;
				}*/
				
				if(visit[y + 1][x] > wall) {
					if(map[y + 1].charAt(x) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMNode(y + 1, x, wall + 1, load + 1));
							visit[y + 1][x] = wall + 1;
						}
					}else {
						queue.offer(new BWMNode(y + 1, x, wall, load + 1));
						visit[y + 1][x] = wall;
					}
				}
			}
			
			//왼쪽
			if(x > 0) {
				/*if(map[y].charAt(x - 1) == '1') {
					queue.offer(new BWMNode(y, x - 1, true));
				}else {
					queue.offer(new BWMNode(y, x - 1, flag));
				}
				
				if(load[y][x - 1] == 0 || !flag) {
					load[y][x - 1] = m + 1;
				}*/
				
				if(visit[y][x - 1] > wall) {
					if(map[y].charAt(x - 1) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMNode(y, x - 1, wall + 1, load + 1));
							visit[y][x - 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMNode(y, x - 1, wall, load + 1));
						visit[y][x - 1] = wall;
					}
				}
			}
			
			//오른쪽
			if(x < map[0].length() - 1) {
				/*if(map[y].charAt(x + 1) == '1') {
					queue.offer(new BWMNode(y, x + 1, true));
				}else {
					queue.offer(new BWMNode(y, x + 1, flag));
				}
				
				if(load[y][x + 1] == 0 || !flag) {
					load[y][x + 1] = m + 1;
				}*/
				if(visit[y][x + 1] > wall) {
					if(map[y].charAt(x + 1) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMNode(y, x + 1, wall + 1, load + 1));
							visit[y][x + 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMNode(y, x + 1, wall, load + 1));
						visit[y][x + 1] = wall;
					}
				}
			}
			
		}
		return -1;
	}

}

class BWMNode{
	int x;
	int y;
	int wall;//벽인가?
	int load;
	
	public BWMNode(int y, int x, int wall, int load) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.load = load;
	}
}
