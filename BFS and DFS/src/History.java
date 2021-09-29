package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class History {

    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//역사적 순서를 연결한 그래프
    static int[][] incident;//각 위치간의 상관관계

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        incident = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++){
            ArrayList<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        //그래프 생성 코드
        for(int i = 0; i < k; i++){
            String[] data = br.readLine().split(" ");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);

            ArrayList<Integer> list = map.get(start);
            list.add(end);
            map.put(start, list);
        }

        //각 사건을 기점으로 BFS로 탐색을 해 각각 연결되는 부분을 확인해 상관관계를 설정한다
        for(int i = 1; i <= n; i++){
            search(i);
        }

        //알아보고싶은 관계를 확인
        int s = Integer.parseInt(br.readLine());
        for(int i = 0; i < s; i++){
            st = new StringTokenizer(br.readLine());
            int ind1 = Integer.parseInt(st.nextToken());
            int ind2 = Integer.parseInt(st.nextToken());
            System.out.println(incident[ind1][ind2]);
        }
    }

    //시작지점부터 연결되는 부분을 확인해 연결이 되면 상관관계를 수정
    public static void search(int start){
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = map.get(start);
        boolean[] visit = new boolean[map.size() + 1];
        visit[start] = true;

        for(int i = 0; i < list.size(); i++){
            queue.offer(list.get(i));
            visit[list.get(i)] = true;
        }

        while(!queue.isEmpty()){
            int end = queue.poll();
            incident[start][end] = -1;//시작점부터 먼저일어난 사건 설정
            incident[end][start] = 1;//나중에 일어난 사건 설정

            ArrayList<Integer> now_list = map.get(end);
            for(int i = 0; i < now_list.size(); i++){
                int next = now_list.get(i);

                if(!visit[next]){
                    queue.offer(next);
                    visit[next] = true;
                }
            }
        }
    }

}
