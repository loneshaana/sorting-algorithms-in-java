package Trie;

public class Trie_Node {
    private static int NUMBER_OF_CHARACTERS = 26;
    Trie_Node[] children = new Trie_Node[NUMBER_OF_CHARACTERS];
    private int size = 0;
    private int freq = 0;
    private boolean isLeaf = false;

    private void setLeaf(boolean flag){
        this.isLeaf = flag;
    }
    public boolean getLeaf(){
        return this.isLeaf;
    }

    public int getFreq() {
        return this.freq;
    }

    private static int getCharIndex(char c) {
        if (Character.isUpperCase(c)) return c - 'A';
        if(Character.isDigit(c)) return c -'0';
        return c - 'a';
    }

    public Trie_Node getNode(char c) {
        return children[getCharIndex(c)];
    }

    private void setNode(char c, Trie_Node node) {
        children[getCharIndex(c)] = node;
    }

    public void add(String s) {
        add(s, 0);
    }

    public Trie_Node search(String s){
        Trie_Node root = this;
        for(int i=0;i<s.length();i++) {
            Trie_Node node = root.getNode(s.charAt(i));
            if(node == null) return new Trie_Node();
            root = node;
        }
        return root;
    }

    public void delete(String key){
       Trie_Node node = search(key);
       if(node.getLeaf()){
           node.setLeaf(false);
       }
    }

    public void add(String s, int index) {
        if (index == s.length()) return;
        char current = s.charAt(index);
        Trie_Node child = getNode(current);
        if (child == null) {
            child = new Trie_Node();
            setNode(current, child);
            if(s.length()-1 == index) child.setLeaf(true);
        }
        child.freq++;
        size++;
        child.add(s, index + 1);
    }

    public int findCount(String s, int index) {
        if (index == s.length()) return size;
        Trie_Node child = getNode(s.charAt(index));
        if (child == null) return 0;
        return findCount(s, index + 1);
    }
}