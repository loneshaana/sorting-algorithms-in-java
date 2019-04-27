package Graph.Adjacency_list;

import Stack.Stack;
import Queue.Queue;
import java.util.Set;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertices;
    private int num;

    public int length(){
        return vertices.length;
    }
    public Graph(){
        vertices = new Vertex[MAX_VERTS];
        num=0;
    }

    private Vertex addVertex(Vertex v){
        vertices[num++] = v;
        return v;
    }

    private Vertex getUnVisitedVertex(Vertex v){
       Set<Vertex> vertices =  v.getAdjacent();
       for(Vertex a : vertices){
           if(!a.isVisited()) return a;
       }
       return null;
    }

    private void dfs(){
        Stack<Vertex> stack = new Stack<Vertex>();
        vertices[0].setVisited(true);
        System.out.print(vertices[0].getLabel()+" ");
        stack.push(vertices[0]);
        while (!stack.isEmpty()){
            Vertex currentVertex = getUnVisitedVertex(stack.peek());
            if(currentVertex != null){
                currentVertex.setVisited(true);
                stack.push(currentVertex);
                System.out.print(currentVertex.getLabel()+" ");
            }else{
                Vertex v = stack.pop();
                v.setVisited(true);
            }
        }
        /* reset */
        for(int i=1;i<num;i++){
            vertices[i].setVisited(false);
        }
    }

    private void bfs(){
        Queue<Vertex> queue = new Queue<Vertex>();
        vertices[0].setVisited(true);
        System.out.print(vertices[0].getLabel()+" ");
        queue.enqueue(vertices[0]);
        while (!queue.isEmpty()){
            Vertex currentVertex = getUnVisitedVertex(queue.front());
            if(currentVertex != null){
                currentVertex.setVisited(true);
                queue.enqueue(currentVertex);
                System.out.print(currentVertex.getLabel()+" ");
            }else{
                Vertex v = queue.dequeue();
                v.setVisited(true);
            }
        }
        /* reset */
        for(int i=1;i<num;i++){
            vertices[i].setVisited(false);
        }
    }
    /*
        Un-directed Graph
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex A = new Vertex('A');
        Vertex B = new Vertex('B');
        Vertex C = new Vertex('C');
        Vertex D = new Vertex('D');
        Vertex E = new Vertex('E');
        Vertex F = new Vertex('F');
        Vertex G = new Vertex('G');
        Vertex H = new Vertex('H');

        graph.addVertex(A)
                .addNeighbour(B);

        graph.addVertex(B).addNeighbour(H,C);
        graph.addVertex(H).addNeighbour(E);
        graph.addVertex(C).addNeighbour(E,D);
        graph.addVertex(E).addNeighbour(G,F);
        graph.addVertex(D);
        graph.addVertex(G);
        graph.addVertex(F);
        System.out.println("\nDFS");
        graph.dfs();
        System.out.println("\nBFS");
        graph.bfs();
    }
}
