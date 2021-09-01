

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBirdge {
	
	static int count = 1;//섬 번호를 붙일 변수
	static int answer = Integer.MAX_VALUE;//정답이 될 변수
	static boolean[][] visit;//섬의 방문 여부
	static int[][] map;
	static Queue<NodeData> queue = new LinkedList<NodeData>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n][n];
		
		//섬을 입력
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각각의 떨어져 있는 섬 번호를 붙이는 과정
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1 && !visit[i][j]) {
					queue.offer(new NodeData(j, i, 0));
					visit[i][j] = true;//큐에 넣을때 방문여부를 체크해야 메모리 초과가 일어나지 않는다
					setting_island();
					count++;
				}
			}
		}
		
		//떨어져있는 섬을 연결하는 과정
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					visit = new boolean[n][n];
					visit[i][j] = true;//큐에 넣을때 방문여부를 체크해야 메모리 초과가 일어나지 않는다
					queue.offer(new NodeData(j, i, 0));
					searchLoad(map[i][j]);
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	//각자 다른 섬으로 세팅
	public static void setting_island() {
		while(!queue.isEmpty()) {
			NodeData nd = queue.poll();
			int width = nd.width;
			int height = nd.height;
			
			map[height][width] = count;
			
			//위로 갈 수 있으며, 윗 섬이 방문하지 않았고 내륙 지역이라면 탐색 범위를 넓히기 위해 큐에 넣는다
			if(height > 0 && !visit[height - 1][width] && map[height - 1][width] == 1) {
				queue.offer(new NodeData(width, height - 1 , 0));
				visit[height - 1][width] = true;
			}
			
			//아래로 갈 수 있으며, 아래 섬이 방문하지 않았고 내륙 지역이라면 탐색 범위를 넓히기 위해 큐에 넣는다
			if(height < map.length - 1 && !visit[height + 1][width] && map[height + 1][width] == 1) {
				queue.offer(new NodeData(width, height + 1, 0));
				visit[height + 1][width] = true;
			}
			
			//왼쪽으로 갈 수 있으며, 왼쪽 섬이 방문하지 않았고 내륙 지역이라면 탐색 범위를 넓히기 위해 큐에 넣는다
			if(width > 0 && !visit[height][width - 1] && map[height][width - 1] == 1) {
				queue.offer(new NodeData(width - 1, height, 0));
				visit[height][width - 1] = true;
			}
			
			//오른로 갈 수 있으며, 오른쪽 섬이 방문하지 않았고 내륙 지역이라면 탐색 범위를 넓히기 위해 큐에 넣는다
			if(width < map.length - 1 && !visit[height][width + 1] && map[height][width + 1] == 1) {
				queue.offer(new NodeData(width + 1, height, 0));
				visit[height][width + 1] = true;
			}
		}
	}
	
	//해당 위치에서 연결할 수 있는 다른 섬을 찾는 메서드
	public static void searchLoad(int start) {
		while(!queue.isEmpty()) {
			NodeData nd = queue.poll();
			int width = nd.width;
			int height = nd.height;
			int dist = nd.dist;
			
			//윗쪽 섬을 갈 수  있는 경우
			if(height > 0 && !visit[height - 1][width]) {
				if(map[height - 1][width] == 0) {//바다지역이라면 방문체크 후 큐에 넣는다
					visit[height - 1][width] = true;
					queue.offer(new NodeData(width, height - 1 , dist + 1));
				}else if(dist > 0 && map[height - 1][width] != start){//윗 쪽 섬이 다른 섬이라면 최솟값을 갱신한다.
					answer = Math.min(answer, dist);
				}
			}
			
			if(height < map.length - 1 && !visit[height + 1][width]) {
				if(map[height + 1][width] == 0) {//바다지역이라면 방문체크 후 큐에 넣는다
					visit[height + 1][width] = true;
					queue.offer(new NodeData(width, height + 1 , dist + 1));
				}else if(dist > 0 && map[height + 1][width] != start){//아래 쪽 섬이 다른 섬이라면 최솟값을 갱신한다.
					answer = Math.min(answer, dist);
				}
			}
			
			if(width > 0 && !visit[height][width - 1]) {
				if(map[height][width - 1] == 0) {//바다지역이라면 방문체크 후 큐에 넣는다
					visit[height][width - 1] = true;
					queue.offer(new NodeData(width - 1, height , dist + 1));
				}else if(dist > 0 && map[height][width - 1] != start){//왼쪽 섬이 다른 섬이라면 최솟값을 갱신한다.
					answer = Math.min(answer, dist);
				}
			}
			
			if(width < map.length - 1 && !visit[height][width + 1]) {
				if(map[height][width + 1] == 0) {//바다지역이라면 방문체크 후 큐에 넣는다
					visit[height][width + 1] = true;
					queue.offer(new NodeData(width + 1, height , dist + 1));
				}else if(dist > 0 && map[height][width + 1] != start){//왼쪽 섬이 다른 섬이라면 최솟값을 갱신한다.
					answer = Math.min(answer, dist);
				}
			}
		}
	}

}

class NodeData{
	int width;//가로
	int height;//세로
	int dist;//거리
	
	public NodeData(int width, int height, int dist) {
		this.width = width;
		this.height = height;
		this.dist = dist;
	}
}
