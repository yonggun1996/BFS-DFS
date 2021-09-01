package com.company;

import sun.corba.Bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//도움이 된 블로그 : https://hidelookit.tistory.com/200
/*
시간초과의 우려를 대비해 1부터 최대 중량까지 이분탐색으로 탐색
해당 무게로 목적지까지 갈 수 있다면 true를 반환
 */
public class WeightLimit {

    //각 섬별로 연결되있는 구조를 담은 맵
    static HashMap<Integer, ArrayList<BridgeData>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= n; i++){
            ArrayList<BridgeData> list = new ArrayList<>();
            map.put(i, list);
        }

        int max = 1;
        int min = 1;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<BridgeData> list1 = map.get(a);
            list1.add(new BridgeData(b, c));
            map.put(a, list1);

            ArrayList<BridgeData> list2 = map.get(b);
            list2.add(new BridgeData(a, c));
            map.put(b, list2);

            max = Math.max(max, c);
        }

        //중량을 하나하나 살피는데 이분 탐색으로 확인
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        while(max >= min){
            int mid = (max + min) / 2;

            if(bfs(mid, start, end)){//해당 중량으로 건널 수 있다면 더 무거운 중량으로 바꿔본다
                min = mid + 1;
            }else{//해당 중량으로 건널 수 없는 경우 가벼운 무게로 바꿔본다
                max = mid - 1;
            }
        }

        System.out.println(max);
    }

    public static boolean bfs(int weight, int start, int end){
        Queue<Integer> queue = new LinkedList<>();//시작 위치에서 시작되는 섬번호를 담는다
        queue.offer(start);
        boolean[] visit = new boolean[map.size() + 1];//방문 여부 배열
        visit[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            if(now == end){//목적지에 도착했으면 true 반환
                return true;
            }

            ArrayList<BridgeData> bridgelist = map.get(now);//해당 섬의 데이터를 담은 리스트를 가져온다

            //그 리스트들을 토대로 다음 갈 노드 확인
            //다음으로 가는 길이 주어진 중량보다 많은 양을 가진다면 큐에 담는다
            for(int i = 0; i < bridgelist.size(); i++){
                int next_weight = bridgelist.get(i).min_weight;
                int next = bridgelist.get(i).next;

                if(next_weight >= weight && !visit[next]){
                    queue.offer(bridgelist.get(i).next);
                    visit[next] = true;
                }
            }
        }

        return false;//목적지에 도착하지 못한 경우
    }

}

class BridgeData{
    int next;
    int min_weight;

    public BridgeData(int next, int min_weight){
        this.next = next;
        this.min_weight = min_weight;
    }
}
