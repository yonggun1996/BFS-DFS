package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeLength {

    static int first_node = 0;//1번 노드에서 찾아낸 가장 먼 노드
    static int count = 0;//트리의 지름
    static HashSet<Integer> visit = new HashSet<>();//각 노드의 방문 여부
    static HashMap<Integer, ArrayList<ListData>> graph = new HashMap<>();//주어진 입력에 대한 트리를 구성

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 1; i <= n; i++){
            ArrayList<ListData> list = new ArrayList<>();
            graph.put(i, list);
        }

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while(true){
                int next = Integer.parseInt(st.nextToken());
                if(next == -1){
                    break;
                }

                int dist = Integer.parseInt(st.nextToken());
                ArrayList<ListData> list1 = graph.get(start);
                list1.add(new ListData(next, dist));
                graph.put(start, list1);
            }
        }

        //1번 노드서 부터 가장 먼 노드를 확인한다.
        ArrayList<ListData> list = graph.get(1);
        for(int j = 0; j < list.size(); j++){
            visit.add(1);
            visit.add(list.get(j).index);
            search(list.get(j).index, list.get(j).dist);
            visit.clear();
        }

        count = 0;
        list = graph.get(first_node);
        //구한 노드를 토대로 다시 먼 노드를 확인한다
        for(int j = 0; j < list.size(); j++){
            visit.add(first_node);
            visit.add(list.get(j).index);
            search(list.get(j).index, list.get(j).dist);
            visit.clear();
        }

        System.out.println(count);

    }

    //노드들을 DFS로 확인
    public static void search(int now, int dist){
        if(count < dist){//누적한 거리가 기존 길이보다 크면 갱신
            count = dist;
            first_node = now;
        }

        ArrayList<ListData> list = graph.get(now);

        for(int i = 0; i < list.size(); i++){
            ListData ld = list.get(i);
            int next = ld.index;
            int now_dist = ld.dist;

            if(!visit.contains(next)){
                visit.add(next);
                search(next, dist + now_dist);
            }
        }
    }

}

class ListData{
    int index;
    int dist;

    public ListData(int index, int dist){
        this.index = index;
        this.dist = dist;
    }
}
