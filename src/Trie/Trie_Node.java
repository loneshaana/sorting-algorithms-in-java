package Trie;

public class Trie_Node {
    private static int NUMBER_OF_CHARACTERS = 26;
    Trie_Node[] children = new Trie_Node[NUMBER_OF_CHARACTERS];
    int size = 0;
    int freq = 0;

    public int getFreq() {
        return this.freq;
    }

    public static int getCharIndex(char c) {
        if (Character.isUpperCase(c)) return c - 'A';
        return c - 'a';
    }

    public Trie_Node getNode(char c) {
        return children[getCharIndex(c)];
    }

    public void setNode(char c, Trie_Node node) {
        children[getCharIndex(c)] = node;
    }

    public void add(String s) {
        add(s, 0);
    }

    public void add(String s, int index) {
        size++;
        if (index == s.length()) return;
        char current = s.charAt(index);
        Trie_Node child = getNode(current);
        if (child == null) {
            child = new Trie_Node();
            setNode(current, child);
        }
        child.freq++;
        child.add(s, index + 1);
    }

    public int findCount(String s, int index) {
        if (index == s.length()) return size;
        Trie_Node child = getNode(s.charAt(index));
        if (child == null) return 0;
        return findCount(s, index + 1);
    }
}