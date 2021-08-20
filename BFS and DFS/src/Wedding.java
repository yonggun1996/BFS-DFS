package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Wedding {

    /*
    동기 중에서 친구의 친구까지 몇 명이 올 수 있는지 출력하는 문제
    2번에 걸쳐서 올 수 있는 친구는 몇 명인가?
    BFS를 통해서 문제 해결
     */

    static int[] friendship;//몇 다리 건너서 아는가?
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//각 사람들의 인맥 네트워크 구현
    static Queue<Friend> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());//동기의 수
        friendship = new int[n + 1];

        //우선 모든 사람에게 리스트를 담아야한다.
        for(int i = 1; i <= n; i++){
            ArrayList<Integer> list1 = new ArrayList<>();
            map.put(i, list1);
        }

        int m = Integer.parseInt(br.readLine());

        //인맥 네트워크 설정
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list1 = map.get(f1);

            list1.add(f2);
            map.put(f1, list1);

            ArrayList<Integer> list2 = map.get(f2);

            list2.add(f1);
            map.put(f2, list2);
        }

        ArrayList<Integer> list = map.get(1);
        //본인의 친구들은 한 다리 건너면 알기 때문에 1로 설정
        for(int i = 0; i < list.size(); i++){
            friendship[list.get(i)] = 1;
            queue.offer(new Friend(list.get(i), 1));
        }

        search();
        int answer = 0;
        for(int i = 2; i < friendship.length; i++){
            if(friendship[i] <= 2 && friendship[i] > 0){
                answer++;
            }
        }

        System.out.println(answer);
    }

    //BFS를 실행하는 코드
    public static void search(){
        while (!queue.isEmpty()){
            Friend f = queue.poll();
            int now = f.next;//현재 누구인지
            int dist = f.dist;//그동안 거친 다리

            ArrayList<Integer> list = map.get(now);
            for(int i = 0; i < list.size(); i++){
                int next = list.get(i);
                if(next != 1){//본인이 나오면 탐색하지 않는다
                    if(friendship[next] == 0 || friendship[next] > dist){//탐색을 하지 않았거나 기존에 탐색한 값이 거쳐온 값보다 크면 실행
                        friendship[next] = dist + 1;
                        queue.offer(new Friend(next, dist + 1));
                    }
                }
            }
        }
    }

}

//친구에 대한 정보를 담은 클래스
class Friend{
    int next;//해당 사람의 친구
    int dist;//몇 다리를 건넜는가

    public Friend(int next, int dist){
        this.next = next;
        this.dist = dist;
    }
}
