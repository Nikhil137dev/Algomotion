import java.util.*;

public class TestBackend {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        while(true){

            System.out.println("\n===== AlgoMotion Backend =====");
            System.out.println("1. Sorting");
            System.out.println("2. Searching");
            System.out.println("3. Graph");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch(choice){
                case 1: sortingMenu(); break;
                case 2: searchingMenu(); break;
                case 3: graphMenu(); break;
                case 4: return;
            }
        }
    }

    //  SORTING algorithm implementation
    static void sortingMenu(){

        int[] arr = {5,3,1,4,2};

        System.out.println("\nSorting Algorithms:");
        System.out.println("1. Bubble");
        System.out.println("2. Selection");
        System.out.println("3. Insertion");

        int ch = sc.nextInt();

        switch(ch){
            case 1: bubbleSort(arr); break;
            case 2: selectionSort(arr); break;
            case 3: insertionSort(arr); break;
        }

        System.out.println("Sorted: " + Arrays.toString(arr));
    }

    static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }
    }

    static void selectionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[min]) min=j;
            }
            int t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }

    static void insertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int key = arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }

    // SEARCHING algorithm implementation
    static void searchingMenu(){

        int[] arr = {1,2,3,4,5,6,7};

        System.out.println("\nSearching Algorithms:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");

        int ch = sc.nextInt();

        System.out.print("Enter target: ");
        int target = sc.nextInt();

        if(ch == 1){
            int idx = linearSearch(arr, target);
            System.out.println("Index: " + idx);
        } else {
            int idx = binarySearch(arr, target);
            System.out.println("Index: " + idx);
        }
    }

    static int linearSearch(int[] arr, int target){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target) return i;
        }
        return -1;
    }

    static int binarySearch(int[] arr, int target){
        int l=0, r=arr.length-1;
        while(l<=r){
            int mid=(l+r)/2;
            if(arr[mid]==target) return mid;
            else if(arr[mid]<target) l=mid+1;
            else r=mid-1;
        }
        return -1;
    }

    // GRAPH algorithm implementation
    static void graphMenu(){

        System.out.println("\nGraph Algorithms:");
        System.out.println("1. BFS");
        System.out.println("2. DFS");

        int ch = sc.nextInt();

        // Testing graph implmentation
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
        for(int i=0;i<5;i++) g.add(new ArrayList<>());

        g.get(0).add(1);
        g.get(0).add(2);
        g.get(1).add(3);
        g.get(2).add(4);

        if(ch == 1){
            System.out.print("BFS: ");
            bfs(g);
        } else {
            System.out.print("DFS: ");
            dfs(g,0,new boolean[5]);
        }
    }

    static void bfs(ArrayList<ArrayList<Integer>> g){

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[g.size()];

        q.add(0);
        vis[0]=true;

        while(!q.isEmpty()){
            int node = q.poll();
            System.out.print(node + " ");

            for(int nei : g.get(node)){
                if(!vis[nei]){
                    vis[nei]=true;
                    q.add(nei);
                }
            }
        }
        System.out.println();
    }

    static void dfs(ArrayList<ArrayList<Integer>> g, int node, boolean[] vis){

        vis[node] = true;
        System.out.print(node + " ");

        for(int nei : g.get(node)){
            if(!vis[nei])
                dfs(g, nei, vis);
        }
    }
}