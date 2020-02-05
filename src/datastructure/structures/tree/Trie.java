package datastructure.structures.tree;


import java.util.TreeMap;

public class Trie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 添加一个新的单词
     * @param word
     */
    public void add(String word){
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if(curr.next.get(c) == null)
                curr.next.put(c,new Node());
            curr = curr.next.get(c);
            if(!curr.isWord){
                curr.isWord = true;
               size++;
            }

        }
    }

    /**
     * 查找一个单词
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if(curr.next.get(c) == null)
                return false;
            curr = curr.next.get(c);
        }
        return curr.isWord;
    }

    /**
     * 查找一个前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if(curr.next.get(c) == null)
                return false;
            curr = curr.next.get(c);
        }
        return true;
    }
}
