package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
두 개의 구역을 나눌 때 인구 차이가 제일 적은 경우를 구하는 문제
2개의 구역으로 나누는 방법은 조합을 통해서 했다.
단 2개로 나눠져야 하기 때문에 최대 n-1개의 조합을 구했다.
서로 연결이 된다면 두 구역의 인구를 합친 후 차이를 구해 갱신한다
 */

public class No17471 {

    static int n;//입력받는 구역의 수
    static int[] arr;//각 구역의 인구
    static boolean[] visit;//해당 구역은 선택했는지 확인하는 배열
    static int answer = Integer.MAX_VALUE;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//연결된 그래프를 표현하는 HashMap

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visit = new boolean[n + 1];

        //각 도시의 인구수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //그래프를 구현하기 위한 for문
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();

            for(int j = 0; j < a; j++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            map.put(i, list);
        }

        //조합을 추려내기 위한 for문
        for(int i = 1; i < n; i++){//i가지 경우의 수를 뽑기 위한 for문
            for(int j = 1; j <= n; j++){//1 부터 n까지 수를 선택
                visit[j] = true;
                set_Combination(1, i, j);
                visit[j] = false;
            }
        }

        if(answer == Integer.MAX_VALUE) answer = -1;//두개의 선거구로 나눌 수 없는 경우 -1로 출력
        System.out.println(answer);
    }

    static void set_Combination(int now, int limit, int nd){
        if(now == limit){//모든 경우의 수 대로 조합했을 경우
            ArrayList<Integer> list1 = new ArrayList<>();//방문한 구역
            ArrayList<Integer> list2 = new ArrayList<>();//방문하지 않은 구역

            for(int i = 1; i <= n; i++){
                if(visit[i]){
                    list1.add(i);
                }else{
                    list2.add(i);
                }
            }

            boolean check1 = BFS(new ArrayList<>(list1));//방문한 구역이 이어지는지
            boolean check2 = BFS(new ArrayList<>(list2));//방문하지 않은 구역끼리 이어지는지

            if(check1 && check2){//두 개의 구역 다 이어질 경우
                int t = 0;
                int f = 0;

                //두 구역의 차이를 구한다
                for(int i = 1; i <= n; i++){
                    if(visit[i]){
                        t += arr[i];
                    }else{
                        f += arr[i];
                    }
                }

                answer = Math.min(answer, Math.abs(t - f));
            }
        }else{//아직 경우의 수 대로 조합이 안된 경우
            for(int i = nd + 1; i <= n; i++){
                visit[i] = true;
                set_Combination(now + 1, limit, i);
                visit[i] = false;
            }
        }
    }

    //BFS로 방문한 구역들이 이어지는지 확인
    //리스트에 구역이 들어있으며 HashMap에 저장된 리스트를 확인
    //리스트에 들어있고, 큐에 아직 들어오지 않은 데이터만 큐에 담아 BFS를 한다
    static boolean BFS(ArrayList<Integer> list){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(list.get(0));

        while(!queue.isEmpty()){
            int nd = queue.poll();
            int idx = list.indexOf(nd);
            list.remove(idx);//해당 노드의 인덱스는 삭제
            ArrayList<Integer> graph_List = map.get(nd);

            for(int i = 0; i < graph_List.size(); i++){
                int next = graph_List.get(i);
                if(list.contains(next) && !queue.contains(next)){//다음 노드가 큐에 들어온 적 없고 리스트에 있다면
                    queue.offer(next);
                }
            }
        }

        //해당 구역이 모두 이어지면 리스트가 빈다.
        if(list.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
