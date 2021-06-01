import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato2Buffer {
	
	static int max = 0;
	static int[][][] arr;
	static Queue<TomatoBuffNode2> queue = new LinkedList<TomatoBuffNode2>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());//5
		int y = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());
		
		//높이 : z, 세로 : y, 가로 : x
		arr = new int[z][y][x];
		
		for(int i = 0; i < z; i++) {
			for(int j = 0; j < y; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < x; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					
					if(arr[i][j][k] == 1) {
						max = Math.max(max, arr[i][j][k]);
						TomatoBuffNode2 t = new TomatoBuffNode2(k, j, i);
						queue.offer(t);
					}
				}
			}
		}
		
		BFS();
		
		boolean flag = true;
		for(int i = 0; i < z; i++) {
			for(int j = 0; j < y; j++) {
				for(int k = 0; k < x; k++) {
					if(arr[i][j][k] == 0) {
						flag = false;
					}
				}
			}
		}
		
		if(flag) {
			bw.write(String.valueOf(max - 1));
			//System.out.println(max - 1);
		}else {
			//System.out.println(-1);
			bw.write(String.valueOf(-1));
		}
		bw.flush();
		bw.close();
		
	}
	
	public static void BFS() {
		while(!queue.isEmpty()) {
			TomatoBuffNode2 tn2 = queue.poll();
			
			int x = tn2.x;//가로
			int y = tn2.y;//세로
			int z = tn2.z;//높이
			
			int num = arr[z][y][x];
			max = Math.max(max, num);
			
			if(x > 0 && arr[z][y][x - 1] == 0) {
				queue.offer(new TomatoBuffNode2(x - 1, y, z));
				arr[z][y][x - 1] = num + 1;
			}
			
			if(x < arr[0][0].length - 1 && arr[z][y][x + 1] == 0) {
				queue.offer(new TomatoBuffNode2(x + 1, y, z));
				arr[z][y][x + 1] = num + 1;
			}
			
			if(y > 0 && arr[z][y - 1][x] == 0) {
				queue.offer(new TomatoBuffNode2(x, y - 1, z));
				arr[z][y - 1][x] = num + 1;
			}
			
			if(y < arr[0].length - 1 && arr[z][y + 1][x] == 0) {
				queue.offer(new TomatoBuffNode2(x, y + 1, z));
				arr[z][y + 1][x] = num + 1;
			}
			
			if(z > 0 && arr[z - 1][y][x] == 0) {
				queue.offer(new TomatoBuffNode2(x, y, z - 1));
				arr[z - 1][y][x] = num + 1;
			}
			
			if(z < arr.length - 1 && arr[z + 1][y][x] == 0) {
				queue.offer(new TomatoBuffNode2(x, y, z + 1));
				arr[z + 1][y][x] = num + 1;
			}
		}
	}

}


class TomatoBuffNode2{
	int x;
	int y;
	int z;
	
	public TomatoBuffNode2(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
