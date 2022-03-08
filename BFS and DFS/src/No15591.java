package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No15591 {
    static HashMap<Integer, ArrayList<PlayerData>> map = new HashMap<>();//그래프롤 표현한 해시맵
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++){
            map.put(i, new ArrayList<>());
        }

        //경로 설정하는 for문
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            ArrayList<PlayerData> list1 = map.get(p);
            list1.add(new PlayerData(q, r));
            map.put(p, list1);

            ArrayList<PlayerData> list2 = map.get(q);
            list2.add(new PlayerData(p, r));
            map.put(q, list2);
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());//최소 조건의 USADO
            int v = Integer.parseInt(st.nextToken());//탐색을 시작할 노드
            System.out.println(BFS(k,v));
        }
    }

    //BFS를 통해서 볼 수 있는 동영상의 개수를 확인하는 메서드
    static int BFS(int min, int node){
        Queue<Integer> queue = new LinkedList<>();
        int result = 0;//탐색할 노드의 개수이자 반환할 개수
        boolean[] visit = new boolean[map.size() + 1];//방문을 표시하는 배열

        queue.offer(node);
        visit[node] = true;
        while (!queue.isEmpty()){
            int nd = queue.poll();//노드를 큐에 꺼낸다
            ArrayList<PlayerData> list = map.get(nd);//해당 노드에 연결된 노드 리스트를 확인한다

            for(int i = 0; i < list.size(); i++){
                int next = list.get(i).node;//다음 노드
                int dist = list.get(i).dist;//다음 노드의 거리

                if(!visit[next] && dist >= min){//방문도 안하고 USADO 거리가 최소 거리보다 길면 큐에 담아서 탐색
                    queue.offer(next);
                    result++;
                    visit[next] = true;
                }
            }
        }

        return result;
    }
}

//노드의 거리와 번호를 담은 객체
class PlayerData{
    int node;
    int dist;

    public PlayerData(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}
