package Trie.TrieProblems;

import Trie.Trie_Node;

public class assigningUsernames extends Trie_Node{
    private static Trie_Node assignedPasswords = new Trie_Node();
    private static void printPassword(String password,int postfix){
        Trie_Node is;
        if(postfix == -1)   {
            is = assignedPasswords.search(password);
            if(!is.getLeaf()){
                assignedPasswords.add(password);
                System.out.println(password);
                return;
            }
        }
        else is = assignedPasswords.search(password+postfix);
        if(!is.getLeaf()){
            assignedPasswords.add(password+postfix);
            System.out.println(password+postfix);
        }else{
           printPassword(password,postfix+1);
        }
    }

    private static void printUsernames(String[] users){
        for(String user : users){
            printPassword(user,-1);
        }
    }

    public static void main(String[] args) {
        String[] users = new String[]{ "geek", "geek0", "geek", "geek0" };
        printUsernames(users);
    }
}
