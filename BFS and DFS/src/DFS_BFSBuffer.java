import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class DFS_BFSBuffer {
	
	static boolean[] visit;
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	static StringBuilder dfs = new StringBuilder();
	static Stack<Integer> stack = new Stack<Integer>();
	static StringBuilder bfs = new StringBuilder();
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());//정점의 개수
		int m = Integer.parseInt(st.nextToken());//간선의 개수
		int v = Integer.parseInt(st.nextToken());//탐색을 시작할 정점의 번호
		
		visit = new boolean[n + 1];
		
		for(int i = 1; i <= n; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st2.nextToken());
			int num2 = Integer.parseInt(st2.nextToken());
			
			ArrayList<Integer> list1 = map.get(num1);
			list1.add(num2);
			
			for(int j = 0; j < list1.size(); j++) {
				System.out.print("리스트 1 : " + list1.get(j) + " ");
			}
			System.out.println();
			
			Collections.sort(list1);
			map.put(num1, list1);
			
			ArrayList<Integer> list2 = map.get(num2);
			list2.add(num1);
			
			for(int j = 0; j < list2.size(); j++) {
				System.out.print("리스트 2 : " + list2.get(j) + " ");
			}
			System.out.println();
			
			Collections.sort(list2);
			map.put(num2, list2);
		}
		
		stack.push(v);
		DFS(v);
		System.out.println(dfs.toString());
		
		Arrays.fill(visit, false);
		queue.offer(v);
		BFS(v);
		System.out.println(bfs.toString());
	}
	
	public static void DFS(int v) {
		while(!stack.empty()) {
			int key = stack.pop();
			if(!visit[key]) {
				dfs.append(key);
				dfs.append(" ");
				visit[key] = true;
				
				ArrayList<Integer> list = map.get(key);
				for(int i = list.size() - 1; i >= 0; i--) {
					int n = list.get(i);
					
					if(!visit[n]) {
						stack.push(n);
					}
				}
			}
		}
	}
	
	public static void BFS(int v) {
		while(!queue.isEmpty()) {
			int key = queue.poll();
			if(!visit[key]) {
				bfs.append(key);
				bfs.append(" ");
				visit[key] = true;
				
				ArrayList<Integer> list = map.get(key);
				for(int i = 0; i < list.size(); i++) {
					int n = list.get(i);
					
					if(!visit[n]) {
						queue.offer(n);
					}
				}
			}
		}
	}

}
