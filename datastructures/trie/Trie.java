package com.algorithmica.datastructures.trie;

import java.util.*;

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

    public boolean searchWord(String word){
        TrieNode current = root;
        HashMap<Character, TrieNode> hmap = current.children;
        int i = 0;
        while (true){
            if(word.length()==0||hmap.size()==0||!hmap.containsKey(word.charAt(0)))
                return false;
            current=hmap.get(word.charAt(0));
            hmap= current.children;
            if(word.length()==1&&current.isEndofWord)
                return true;
            if(word.length()==1)
                return false;
            else
               word= word.substring(1);
        }
    }
    public Queue<String> getWordsWithSamePrefix(String prefix){
        Queue<String> queue;
        TrieNode current = root;
        String word=prefix;
        HashMap<Character, TrieNode> hmap = current.children;
        while (true){
            if(prefix.length()==0||hmap.size()==0||!hmap.containsKey(word.charAt(0)))
                return new LinkedList<String>();
            current= hmap.get(word.charAt(0));
            hmap=current.children;
            if(word.length()==1){
                queue = auxGetPrefixWords(current,prefix);
                return queue;
            }
            else
                word = word.substring(1);
        }
    }
    public Queue<String> auxGetPrefixWords(TrieNode current,String prefix){
        Queue<String> queue=new LinkedList<String>();
        queue=auxGetPrefixWords(current,prefix,queue);
        return queue;
    }

    private Queue<String> auxGetPrefixWords(TrieNode current,String s,Queue<String> queue){

        HashMap<Character, TrieNode> hmap = current.children;
        if(hmap.isEmpty()&&current.isEndofWord){
            queue.add(s);
            return queue;
        }
        if(current.isEndofWord) {
            queue.add(s);
        }
        for (Map.Entry<Character,TrieNode> entry:hmap.entrySet()  ) {
            char c= entry.getKey();
            queue= auxGetPrefixWords(entry.getValue(),s+c,queue);
        }
        return queue;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("xyz");
        trie.insert("aaa");
        trie.insert("cfgtyu");
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("bdv898");
        trie.insert("bdcf");
        trie.display();

        System.out.println(trie.searchWord("bdv8"));
        Queue<String> queue=trie.getWordsWithSamePrefix("b");
        for (String s :queue   ) {
            System.out.print(s+"\t");
        }

    }
}