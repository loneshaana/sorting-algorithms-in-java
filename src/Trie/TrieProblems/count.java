package Trie.TrieProblems;
/*
Count of strings whose prefix match with the given string to a given length k

input: arr[] = {“abba”, “abbb”, “abbc”, “abbd”, “abaa”, “abca”}, str = “abbg”, k = 3
Output: 4
“abba”, “abbb”, “abbc” and “abbd” are the matching strings.
 */

import Trie.Trie_Node;

public class count extends Trie_Node {

    public int search(String toSearch, int k, Trie_Node root) {
        int count = 0;
        for ( int i = 0 ; i < toSearch.length() ; i++ ) {
            root = root.getNode(toSearch.charAt(i));
            if (root == null) return 0;
            count++;
            if (count == k) {
                return root.getFreq();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] elements = new String[]{"abba", "abbb", "abbc", "abbd", "abca", "adba", "adbc"};
        String toFind = "adbk";
        count Trie = new count();

        for ( String ele : elements ) {
            Trie.add(ele);
        }

        count c = new count();
        int times = c.search(toFind, 3, Trie);
        System.out.println(times);
    }
}
