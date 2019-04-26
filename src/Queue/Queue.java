package Queue;

import java.util.ArrayList;
import java.util.List;

public class Queue<T>{
    private List<T> list = new ArrayList<>();

    public void enqueue(T node){
        list.add(node);
    }

    public T dequeue(){
        return list.remove(0);
    }

    public T front(){ return list.get(0);}

    public boolean isEmpty(){
        if(list.size()  >0) return false;
        return true;
    }
}
