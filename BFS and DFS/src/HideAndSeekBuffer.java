import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HideAndSeekBuffer {
	
	static int[] visit = new int[100001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(n);
		visit[n] = 0;
		
		if(n == k) {
			bw.write(String.valueOf(0));
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
			
			bw.write(String.valueOf(visit[k]));
		}
		
		bw.flush();
		bw.close();
		
	}

}
