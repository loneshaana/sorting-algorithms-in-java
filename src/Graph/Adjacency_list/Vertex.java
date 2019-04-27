package Graph.Adjacency_list;

import java.util.HashSet;
import java.util.Set;

public class Vertex{
    private char label;
    private Set<Vertex> adjacent;
    private boolean visited;

    Set<Vertex> getAdjacent(){
        return this.adjacent;
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

    void addNeighbour(Vertex ...vertex){
        for(Vertex a: vertex){
            this.adjacent.add(a);
            a.adjacent.add(this);
        }
    }

    Vertex(char label) {
        this.label = label;
        this.adjacent = new HashSet<>();
        this.visited = false;
    }
}