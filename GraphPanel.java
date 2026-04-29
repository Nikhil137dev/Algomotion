
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GraphPanel extends JPanel {

    ArrayList<Point> nodes = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    boolean[] visited;
    int selectedNode = -1;
    int currentNode = -1;

    ArrayList<Integer> order = new ArrayList<>();

    public GraphPanel(){

        setBackground(new Color(18,18,28));
        setToolTipText("Click empty space: add node | Click 2 nodes: connect edge");

        //  MOUSE CLICK HANDLING
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){

                int idx = getNodeAt(e.getPoint());

                // create new node
                if(idx == -1){
                    nodes.add(e.getPoint());
                    graph.add(new ArrayList<>());
                }
                else{
                    if(selectedNode == -1){
                        selectedNode = idx;
                    } else {

                        // prevent duplicate edges
                        if(!graph.get(selectedNode).contains(idx)){
                            graph.get(selectedNode).add(idx);
                            graph.get(idx).add(selectedNode);
                        }

                        selectedNode = -1;
                    }
                }

                repaint();
            }
        });

        JButton bfs = new JButton("BFS");
        JButton dfs = new JButton("DFS");
        JButton reset = new JButton("Reset");

        bfs.addActionListener(e -> new Thread(() -> bfs(0)).start());
        dfs.addActionListener(e -> new Thread(() -> dfs(0)).start());

        reset.addActionListener(e -> {
            visited = null;
            currentNode = -1;
            order.clear();
            repaint();
        });

        add(bfs);
        add(dfs);
        add(reset);
    }

    //  detect node click
    int getNodeAt(Point p){
        for(int i=0;i<nodes.size();i++){
            Point n = nodes.get(i);
            if(p.distance(n) < 20) return i;
        }
        return -1;
    }

    //  BFS
    void bfs(int start){

        if(nodes.size() == 0) return;

        visited = new boolean[nodes.size()];
        order.clear();

        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){

            int node = q.poll();
            currentNode = node;
            order.add(node);

            repaint();
            sleep(500);

            for(int nei : graph.get(node)){
                if(!visited[nei]){
                    visited[nei] = true;
                    q.add(nei);
                }
            }
        }

        currentNode = -1;
    }

    //  DFS
    void dfs(int start){

        if(nodes.size() == 0) return;

        visited = new boolean[nodes.size()];
        order.clear();

        dfsUtil(start);

        currentNode = -1;
    }

    void dfsUtil(int node){

        visited[node] = true;
        currentNode = node;
        order.add(node);

        repaint();
        sleep(500);

        for(int nei : graph.get(node)){
            if(!visited[nei])
                dfsUtil(nei);
        }
    }

    void sleep(int d){
        try{Thread.sleep(d);}catch(Exception e){}
    }

    //  DRAW
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //  draw edges (no duplicates)
        g.setColor(Color.WHITE);
        for(int i=0;i<graph.size();i++){
            for(int j : graph.get(i)){
                if(i < j){
                    Point p1 = nodes.get(i);
                    Point p2 = nodes.get(j);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }

        //  draw nodes
        for(int i=0;i<nodes.size();i++){

            Point p = nodes.get(i);
            int size = 30;

            if(i == currentNode)
                g.setColor(Color.RED);        // active
            else if(visited != null && visited[i])
                g.setColor(Color.GREEN);      // visited
            else if(i == selectedNode)
                g.setColor(Color.YELLOW);     // selected
            else
                g.setColor(Color.CYAN);       // default

            //  centered circle
            g.fillOval(p.x - size/2, p.y - size/2, size, size);

            g.setColor(Color.BLACK);
            g.drawString(""+i, p.x - 5, p.y + 5);
        }

        //  traversal order display
        g.setColor(Color.WHITE);
        g.drawString("Traversal: " + order.toString(), 20, 20);
    }
}
