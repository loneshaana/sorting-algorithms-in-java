package Graph;

import Queue.Queue;
import Stack.Stack;

class Vertex{
    private char label;
    private boolean wasVisited;
    Vertex(char label){
        this.label = label;
        this.wasVisited = false;
    }
    char getLabel(){
        return this.label;
    }
    boolean isVisited(){
        return this.wasVisited;
    }
    void setWasVisited(boolean flag){
        this.wasVisited = flag;
    }
}

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int nVerts;

    Graph() {
        this.vertexList = new Vertex[MAX_VERTS];
        this.adjMat = new int[MAX_VERTS][MAX_VERTS];
        this.nVerts = 0;
    }
    private void addVertex(char label){
        vertexList[nVerts++] = new Vertex(label);
    }
    private void addEdge(int from,int to){
        adjMat[from][to] = 1;
        adjMat[to][from] = 1;
    }
    private void displayVertex(int v){
        System.out.println(vertexList[v].getLabel());
    }

    private int getAdjUnVisitedVertex(int index){
        for(int j=0;j<nVerts;j++){
            if(adjMat[index][j] == 1 && !vertexList[j].isVisited())
                return j;
        }
        return -1;
    }

    private void bfs(){
        vertexList[0].setWasVisited(true);
        displayVertex(0);
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(0);
        while (!queue.isEmpty()){
            int v = getAdjUnVisitedVertex(queue.front());
            if(v == -1){
                queue.dequeue();
            }else{
                queue.enqueue(v);
                vertexList[v].setWasVisited(true);
                displayVertex(v);
            }
        }
        for(int j=0;j<nVerts;j++){
            vertexList[j].setWasVisited(false);
        }
    }
    /* depth-first-search */
    private void dfs(){
        vertexList[0].setWasVisited(true);
        displayVertex(0);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        while (!stack.isEmpty()){
            int v = getAdjUnVisitedVertex(stack.peek());
            /*
                Either There is no path to other node or everyNode Was Visited
             */
            if(v == -1){
                stack.pop();
            }else{
                vertexList[v].setWasVisited(true);
                displayVertex(v);
                stack.push(v);
            }
        }

        for(int j=0;j<nVerts;j++){
            vertexList[j].setWasVisited(false);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4

        graph.addEdge(0,1); // A->B || B->A
        graph.addEdge(1,2); // B->C || C->B
        graph.addEdge(0,3); // A->D || D->A
        graph.addEdge(3,4); // D->E || E->D
        System.out.println("DFS");
        graph.dfs();
        System.out.println("BFS");
        graph.bfs();
    }
}
