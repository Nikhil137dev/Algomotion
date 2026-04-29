

import java.util.*;

public class GraphAlgo {

    public static void bfs(ArrayList<ArrayList<Integer>> g){

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[g.size()];

        q.add(0);
        vis[0]=true;

        while(!q.isEmpty()){
            int node = q.poll();
            System.out.print(node+" ");

            for(int nei : g.get(node)){
                if(!vis[nei]){
                    vis[nei]=true;
                    q.add(nei);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(ArrayList<ArrayList<Integer>> g,int node,boolean[] vis){

        vis[node]=true;
        System.out.print(node+" ");

        for(int nei : g.get(node)){
            if(!vis[nei])
                dfs(g,nei,vis);
        }
    }
}