import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallMoveBuffer {
	
	static String[] map;
	static int[][] visit;
	static Queue<BWMBufferNode> queue = new LinkedList<BWMBufferNode>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BreakWallMoveBuffer b = new BreakWallMoveBuffer();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		map = new String[height];
		visit = new int[height][width];//벽을 뚫은 횟수를 담는다
		for(int i = 0; i < height; i++) {
			map[i] = br.readLine();
			for(int j = 0; j < width; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		
		BWMBufferNode node = new BWMBufferNode(0, 0, 0, 1);
		queue.offer(node);
		bw.write(String.valueOf(b.BFS()));
		bw.flush();
		bw.close();
		
	}
	
	public int BFS() {
		while(!queue.isEmpty()) {
			BWMBufferNode node = queue.poll();
			
			int y = node.y;
			int x = node.x;
			int wall = node.wall;//벽을 뚫은 횟수
			int load = node.load;//여태 기록한 거리
			
			if(y == map.length - 1 && x == map[0].length() - 1) {//목표지점까지 왔다면 기록한 거리를 반환
				return load;
			}
			
			//위
			if(y > 0) {
				if(visit[y - 1][x] > wall) {//만약 벽을 안부순 경우가 있다면 다시 기록을 한다
					if(map[y - 1].charAt(x) == '1') {//벽을 만났다면
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMBufferNode(y - 1, x, wall + 1, load + 1));//벽을 부신 횟수를 추가
							visit[y - 1][x] = wall + 1;
						}
					}else {//벽을 만나지 않았다면
						queue.offer(new BWMBufferNode(y - 1, x, wall, load + 1));
						visit[y - 1][x] = wall;
					}
				}
				
			}
			
			//아래
			if(y < map.length - 1) {
				if(visit[y + 1][x] > wall) {//만약 벽을 안부순 경우가 있다면 다시 기록을 한다
					if(map[y + 1].charAt(x) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMBufferNode(y + 1, x, wall + 1, load + 1));//벽을 부신 횟수를 추가
							visit[y + 1][x] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y + 1, x, wall, load + 1));
						visit[y + 1][x] = wall;
					}
				}
			}
			
			//왼쪽
			if(x > 0) {
				if(visit[y][x - 1] > wall) {//만약 벽을 안부순 경우가 있다면 다시 기록을 한다
					if(map[y].charAt(x - 1) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMBufferNode(y, x - 1, wall + 1, load + 1));//벽을 부신 횟수를 추가
							visit[y][x - 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y, x - 1, wall, load + 1));
						visit[y][x - 1] = wall;
					}
				}
			}
			
			//오른쪽
			if(x < map[0].length() - 1) {
				if(visit[y][x + 1] > wall) {//만약 벽을 안부순 경우가 있다면 다시 기록을 한다
					if(map[y].charAt(x + 1) == '1') {
						if(wall == 0) {//벽을 부신적이 없다면
							queue.offer(new BWMBufferNode(y, x + 1, wall + 1, load + 1));//벽을 부신 횟수를 추가
							visit[y][x + 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y, x + 1, wall, load + 1));
						visit[y][x + 1] = wall;
					}
				}
			}
			
		}
		return -1;//목표지점까지 못온 경우
	}

}

class BWMBufferNode{
	int x;
	int y;
	int wall;//벽을 뚫은 횟수
	int load;//거리
	
	public BWMBufferNode(int y, int x, int wall, int load) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.load = load;
	}
}
