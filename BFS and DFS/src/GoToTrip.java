package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GoToTrip {

    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//여행지 그래프를 표현한 map

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++){
            ArrayList<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num == 1){
                    ArrayList<Integer> list = map.get(j);
                    list.add(i);
                    map.put(j, list);
                }
            }
        }

        String answer = "YES";
        String[] plan = br.readLine().split(" ");//입력받은 여행계획을 배열로 설정

        int start = 0;
        for(int i = start; i < plan.length - 1; i++){
            int s = Integer.parseInt(plan[i]);
            int e = Integer.parseInt(plan[i + 1]);

            if(!search(s, e)){//시작지점에서 끝 지점까지 갈 수 있는지 확인
                //갈 수 없으면 answer를 NO로 변경 후 탐색 중단
                answer = "NO";
                break;
            }
        }

        System.out.println(answer);
    }

    public static boolean search(int start, int end){
        if(start == end) return true;//같은 지역을 계속 있는 경우는 true로 반환
        boolean[] visit = new boolean[map.size() + 1];//지역 방문 여부 배열
        visit[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = map.get(start);

        for(int i = 0; i < list.size(); i++){
            queue.offer(list.get(i));
        }

        //BFS로 계속해서 탐색
        while(!queue.isEmpty()){
            int now = queue.poll();

            if(now == end){//원하는 목적지가 나오면 true로 반환
                return true;
            }

            //현재 지역에서 연결된 지역을 모두 확인
            ArrayList<Integer> now_list = map.get(now);
            for(int i = 0; i < now_list.size(); i++){
                int idx = now_list.get(i);
                if(!visit[idx]){//방문하지 않은 경우만 확인
                    queue.offer(idx);
                    visit[idx] = true;
                }
            }
        }

        return false;//끝까지 봐도 목적지가 나오지 않은 경우 false 반환
    }

}
