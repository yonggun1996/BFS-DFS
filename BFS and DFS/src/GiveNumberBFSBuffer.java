import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GiveNumberBFSBuffer {
	
	static String[] arr;
	static boolean[][] visit;
	static Queue<Location2> queue = new LinkedList<Location2>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		arr = new String[n];
		visit = new boolean[n][n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken();
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			String s = arr[i];
			
			for(int j = 0; j < n; j++) {
				if(s.charAt(j) == '1' && !visit[i][j]) {
					visit[i][j] = true;
					Location2 nd = new Location2(i, j);
					queue.offer(nd);
					int count = search();
					list.add(count);
				}
			}
		}
		
		Collections.sort(list);
		//System.out.println(list.size());
		bw.write(String.valueOf(list.size()));
		bw.newLine();
		for(int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			bw.write(String.valueOf(list.get(i)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	public static int search() {
		int count = 0;
		
		while(!queue.isEmpty()) {
			Location2 n = queue.poll();
			int x = n.x;
			int y = n.y;
			count++;
			
			if(x > 0 && arr[x - 1].charAt(y) == '1' && !visit[x - 1][y]) {
				visit[x - 1][y] = true;
				queue.offer(new Location2(x - 1, y));
			}
			
			if(x < arr.length - 1 && arr[x + 1].charAt(y) == '1' && !visit[x + 1][y]) {
				visit[x + 1][y] = true;
				queue.offer(new Location2(x + 1, y));
			}
			
			if(y > 0 && arr[x].charAt(y - 1) == '1' && !visit[x][y - 1]) {
				visit[x][y - 1] = true;
				queue.offer(new Location2(x, y - 1));
			}
			
			if(y < arr.length - 1 && arr[x].charAt(y + 1) == '1' && !visit[x][y + 1]) {
				visit[x][y + 1] = true;
				queue.offer(new Location2(x, y + 1));
			}
		}
		
		return count;
	}

}

class Location2{
	int x;
	int y;
	
	public Location2(int x, int y) {
		this.x = x;
		this.y = y;
	}
}