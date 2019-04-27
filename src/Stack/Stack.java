package Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<T>{
    private List<T> stack = new ArrayList<>();
    public void push(T node){
        if(!stack.contains(node))
            stack.add(node);
    }

    public T peek(){
        return stack.get(stack.size() -1);
    }

    public T pop(){
        return stack.remove(stack.size()-1);
    }

    public int length(){return stack.size();}

    public boolean isEmpty(){
        if(stack.size() >0) return false;
        return  true;
    }
}
