// In this problem, declaring a TrieNode class which will have 2 variables, one TrieNode Array of length 26 and other boolean isEnd
// for indicating that this is end of a word. So each node in Trie will have 26 childrens, each children will is also a TrieNode 
// which will have its own set of 26 TrieNode childrens. We are using an array datastructure for it. 

// Time Complexity : O(n) for insert, search, and startswith - where n is the length of the input word
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Trie {
    // Declaring class TrieNode
    class TrieNode {
        // 2 variables for this class
        // Array of type TrieNode
        TrieNode[] children;
        // IsEnd for keeping the track of end of one word
        boolean isEnd;

        public TrieNode() {
            // Initialize children array of length 26 and isEnd as false
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    // Declare root of the TrieNode
    TrieNode root;

    public Trie() {
        // Initialize root
        root = new TrieNode();
    }

    public void insert(String word) {
        // Decare curr trienode and give the value of root
        TrieNode curr = root;
        // Iterate through the word
        for (int i = 0; i < word.length(); i++) {
            // Take one char at a time
            char c = word.charAt(i);
            // Check if the children of root for that character is null?
            if (curr.children[c - 'a'] == null) {
                // Yes then intialize a new TrieNode for that character
                curr.children[c - 'a'] = new TrieNode();
            }
            // Move the curr to this newly added trienode, so that next character we append
            // as a child of this current char
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        // Declare a current trienode
        TrieNode curr = root;
        // Iterate through the word
        for (int i = 0; i < word.length(); i++) {
            // One char at a time
            char c = word.charAt(i);
            // Check if the children of root for this char having null?
            if (curr.children[c - 'a'] == null) {
                // Yes means that this word is not present in trienode, return false
                return false;
            }
            // Else, move to this char and continue to check next
            curr = curr.children[c - 'a'];
        }
        // Then after iterating through whole word, return isEnd for that last char
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        // Declare a current trienode
        TrieNode curr = root;
        // Iterate through the prefix
        for (int i = 0; i < prefix.length(); i++) {
            // One char at a time
            char c = prefix.charAt(i);
            // Check if the children of root for this char having null?
            if (curr.children[c - 'a'] == null) {
                // Yes that means no word with this prefix is present in trienode, return false
                return false;
            }
            // Else, move to this char and continue to check next
            curr = curr.children[c - 'a'];

        }
        // Atlast if the condition on line 74 is not fulfilled, that means prefix found,
        // so return true
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */