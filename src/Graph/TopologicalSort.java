package Graph;
import Stack.Stack;

import java.util.*;

// A -> B
class Edge{
    private DirectedVertex from ;
    private DirectedVertex to;

    private Edge(DirectedVertex from ,DirectedVertex to){
        this.from = from;
        this.to = to;
    }

}

class DirectedVertex{
    private char label;
    private Set<DirectedVertex> adjacent;
    private boolean visited;
    private int inDegree = 0;

    DirectedVertex(char label) {
        this.label = label;
        this.adjacent = new HashSet<>();
        this.visited = false;
    }

    Set<DirectedVertex> getAdjacent(){
        return this.adjacent;
    }

    void inDegInc(){
        this.inDegree += 1;
    }

    void setInDegree(int num){
        this.inDegree = num;
    }

    Integer getInDegree(){
        return this.inDegree;
    }

    void setVisited(boolean flag){
        this.visited = flag;
    }

    boolean isVisited(){
        return this.visited;
    }

    char getLabel(){
        return this.label;
    }

    void addNeighbour(DirectedVertex ...vertex){
        for(DirectedVertex a : vertex){
            this.adjacent.add(a);
        }
    }
}
/*
    Ordering of vertices in the DAG ( direct acyclic graph) ;
 */
class DirectedGraph {
    private static final int MAX = 20;
    private DirectedVertex[] vertices = new DirectedVertex[MAX];
    private Map<DirectedVertex , Set<DirectedVertex>> edges = new HashMap<>();
    private int currentIndex = 0;

    private void addEdge(DirectedVertex a, DirectedVertex b){
        if(edges.containsKey(a)){
            Set<DirectedVertex> paths = edges.get(a);
            paths.add(b);
            edges.put(a,paths);
        }else{
            Set<DirectedVertex> set = new HashSet<>();
            set.add(b);
            edges.put(a,set);
        }
        b.inDegInc();
    }

    private DirectedVertex addVertex(char label){
        DirectedVertex vertex = new DirectedVertex(label);
        vertices[currentIndex++] = vertex;
        return vertex;
    }

    private DirectedVertex getUnVisitedVertex(DirectedVertex v){
//        if(v.getLabel() == 'A'){
//            Set<DirectedVertex> vertices =  edges.get(v);
//            for(DirectedVertex a : vertices){
//                System.out.println(a.getLabel());
//            }
//        }
        if(v.isVisited()) return null;
        Set<DirectedVertex> vertices =  edges.get(v);
        if(vertices == null) return null;
        for(DirectedVertex a : vertices){
            if(!a.isVisited()) {
                return a;
            }
        }
        return null;
    }

    private void dfs(DirectedVertex vertex){
        if(vertex.isVisited()) return;
        Stack<DirectedVertex> stack = new Stack<DirectedVertex>();
        vertex.setVisited(true);
        stack.push(vertex);

        while (!stack.isEmpty()){
            DirectedVertex currentVertex = getUnVisitedVertex(stack.peek());
            if(currentVertex != null){
                currentVertex.setVisited(true);
                stack.push(currentVertex);
            }else{
                DirectedVertex v = stack.pop();
                v.setVisited(true);
                System.out.print(v.getLabel()+" ");
            }
        }
    }

    private void dfsUtil(){
        for(int i=0;i<currentIndex;i++){
            if(i != 0) {
                vertices[i-1].setVisited(false); // keep resetting the previous ones
            }
            if(!vertices[i].isVisited())
                dfs(vertices[i]);
        }
        vertices[currentIndex-1].setVisited(false); // reset the last one
    }

    private void topologicalSort(){
        Stack<DirectedVertex> stack = new Stack<DirectedVertex>();
        for(int i=0;i<currentIndex;i++){
            if(!vertices[i].isVisited()) topologicalSort(i,stack);
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop().getLabel()+" ");
        }
    }

    private void topologicalSort(int i, Stack<DirectedVertex> stack){
        while (getUnVisitedVertex(vertices[i]) != null){
            DirectedVertex v = getUnVisitedVertex(vertices[i]);
            if(!v.isVisited())  topologicalSort(Arrays.asList(vertices).indexOf(v),stack);
        }
        vertices[i].setVisited(true);
        stack.push(vertices[i]);
    }

    /*
        Topological sort using kahn's Algorithm
     */
    private void kanhsAlgorithms(){
        Queue<DirectedVertex> queue = new LinkedList<>();
        Map<DirectedVertex,Integer> indegree = new HashMap<>();
        for(int i=0;i<currentIndex;i++){
            if(vertices[i].getInDegree() == 0)queue.add(vertices[i]);
            indegree.put(vertices[i],vertices[i].getInDegree());
        }
        int count = 0;

        Vector<DirectedVertex> topOrder = new Vector<>();
        while (!queue.isEmpty()){
            DirectedVertex v = queue.poll();
            topOrder.add(v);

            Set<DirectedVertex> neighbours = edges.get(v);
            if(neighbours != null) {
                for ( DirectedVertex n : neighbours ) {
                    indegree.put(n,indegree.get(n)-1);
                    if(indegree.get(n) == 0) queue.add(n);
                }
            }
            count++;
        }
        if(count != currentIndex){
            System.out.println("There is a loop");
            return;
        }
        for(int i=topOrder.size()-1;i>=0;i--){
            System.out.print(topOrder.get(i).getLabel()+" ");
        }
    }

   private List<DirectedVertex> findShortestPath(DirectedVertex a,DirectedVertex b){
        if(a == null || b == null) return null;
        Queue<DirectedVertex> toVisit = new LinkedList<>();
        HashMap<DirectedVertex,DirectedVertex> parents = new HashMap<>();
        toVisit.add(a);
        parents.put(a,null);
        
        while (!toVisit.isEmpty()){
            DirectedVertex curr = toVisit.remove();
            if(curr == b) break;
            Set<DirectedVertex> childrens = edges.get(curr);

            if(childrens != null){
                for(DirectedVertex n : childrens){
                    toVisit.add(n);
                    parents.put(n,curr);
                }
            }
        }

        if(parents.get(b) == null) return null;
        List<DirectedVertex> output = new LinkedList<>();
        DirectedVertex curr = b;
        while (curr != null){
            output.add(0,curr);
            curr = parents.get(curr);
        }
        return output;
   }

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph();
        DirectedVertex A = directedGraph.addVertex('A');
        DirectedVertex B = directedGraph.addVertex('B');
        DirectedVertex C = directedGraph.addVertex('C');
        DirectedVertex D = directedGraph.addVertex('D');
        DirectedVertex E = directedGraph.addVertex('E');
        DirectedVertex F = directedGraph.addVertex('F');
        DirectedVertex G = directedGraph.addVertex('G');
        DirectedVertex H = directedGraph.addVertex('H');
        DirectedVertex I = directedGraph.addVertex('I');
        DirectedVertex J = directedGraph.addVertex('J');

        directedGraph.addEdge(A,B);
        directedGraph.addEdge(A,F);
        directedGraph.addEdge(G,A);
        directedGraph.addEdge(G,B);
        directedGraph.addEdge(G,C);
        directedGraph.addEdge(B,H);
        directedGraph.addEdge(D,H);
        directedGraph.addEdge(D,I);
        directedGraph.addEdge(I,C);
        directedGraph.addEdge(D,E);
        directedGraph.addEdge(D,C);
        directedGraph.addEdge(E,I);
        directedGraph.addEdge(J,E);

//        directedGraph.dfsUtil();
//        System.out.println("\n________________________\n");
//        directedGraph.topologicalSort(); // E B C A D
//        System.out.println("\n________________________\n");
//        directedGraph.kanhsAlgorithms();

        System.out.println("\nIn Degree of A "+A.getInDegree());

        DirectedGraph newGraph = new DirectedGraph();
        DirectedVertex a = newGraph.addVertex('0');
        DirectedVertex b =newGraph.addVertex('1');
        DirectedVertex c =newGraph.addVertex('2');
        DirectedVertex d =newGraph.addVertex('3');
        DirectedVertex e =newGraph.addVertex('4');
//        newGraph.addEdge(a,b);
        newGraph.addEdge(b,a);
        newGraph.addEdge(c,a);
        newGraph.addEdge(d,b);
        newGraph.addEdge(d,c);
        newGraph.addEdge(e,d);
//        newGraph.topologicalSort();
        System.out.println( );
        newGraph.kanhsAlgorithms();
        List<DirectedVertex> shortestPath = newGraph.findShortestPath(e,a);
        System.out.println("Shortest Path");
        for(DirectedVertex p : shortestPath){
            System.out.print(p.getLabel()+" ");
        }
    }
}
