package GitHub.trunk.datastructures.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chch0316 on 7/11/2016.
 */

/*
       Trie -- a tree based data structure
            -- Used for to store collection of Strings
            -- Prefix based search/normal in Strings ie. auto-completion of a string
            -- Lexicographically sorting of Strings
       Apps-- Ideal data structure for storing a dictionary

       Alternative Ds: HashTable (But we can not do prefix based search and it occupies more space compared to Trie)
 */

public class Trie {

    private class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isEndofWord;

        TrieNode() {
            children = new HashMap<Character, TrieNode>();
            isEndofWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode current = root;
        HashMap<Character, TrieNode> hmap = current.children;
        int i = 0;
        while (true) {

            if (!hmap.containsKey(word.charAt(0))) {
                current = new TrieNode();
                hmap.put(word.charAt(i), current);
                //System.out.println(word.charAt(i)+" "+ i);
            } else {
                current = hmap.get(word.charAt(0));
            }

            if (i == word.length() - 1) {
                current.isEndofWord = true;
                break;
            }
            hmap = current.children;
            word = word.substring(1);
        }
        //display(root);
    }

    public void display() {
        TrieNode current = root;
        auxDisplay(current, "");
    }

    private void auxDisplay(TrieNode tNode, String s) {

        if (tNode.children.isEmpty()) {
            if (tNode.isEndofWord)
                System.out.println(s);
            return;
        } else {
            if (tNode.isEndofWord)
                System.out.println(s);
        }
        for (Map.Entry<Character, TrieNode> entry : tNode.children.entrySet()) {
            char c = entry.getKey();
            auxDisplay(entry.getValue(), s + c);
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("xyz");
        trie.insert("aaa");
        trie.insert("cfgtyu");
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("bdv");
        trie.insert("bdcf");
        trie.display();

    }
}