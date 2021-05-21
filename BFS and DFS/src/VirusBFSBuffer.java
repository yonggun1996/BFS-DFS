import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class VirusBFSBuffer {
	
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int computer = Integer.parseInt(br.readLine());
		int network = Integer.parseInt(br.readLine());
		visit = new boolean[computer + 1];
		
		for(int i = 1; i <= computer; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}
		
		StringTokenizer st2;
		for(int i = 0; i < network; i++) {
			st2 = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st2.nextToken());
			int num2 = Integer.parseInt(st2.nextToken());
			
			ArrayList<Integer> list1 = map.get(num1);
			list1.add(num2);
			map.put(num1, list1);
			
			ArrayList<Integer> list2 = map.get(num2);
			list2.add(num1);
			map.put(num2, list2);
		}
		
		queue.offer(1);
		ArrayList<Integer> answer_list = new ArrayList<Integer>();
		
		while(!queue.isEmpty()) {
			int key = queue.poll();
			if(!visit[key]) {
				answer_list.add(key);
				visit[key] = true;
				
				ArrayList<Integer> list = map.get(key);
				for(int i = 0; i < list.size(); i++) {
					int num = list.get(i);
					if(!visit[num]) {
						queue.offer(num);
					}
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer_list.size() - 1));
		bw.flush();
	}

}
