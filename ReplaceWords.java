// In this problem, using a TrieNode to store all the words in dictionary, then iterating through the given sentence, one word at
// a time, and checking if that word in trie by iterating one char at a time, building a new short word if found in trie, and 
// building a answer sentence with that short word if found else keeping the original word as is in the answer.

// Time Complexity : O(mk+nl) m-total words in dictionary, k-avg length of each word in the dictionary, n-total words in sentence, 
// l-avg length of each word in sentence
// Space Complexity : O(mk+nl)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
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

    private void insert(String word) {
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

    public String replaceWords(List<String> dictionary, String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return sentence;
        }
        // Initialize root
        root = new TrieNode();
        // Put all words of dictionary in trienode
        for (String word : dictionary) {
            insert(word);
        }
        // Create a string array with all the words in sentences
        String[] words = sentence.split(" ");
        // Declare a string builder for answer
        StringBuilder answer = new StringBuilder();
        // Now for each word, look for it in trienode, if there is any short word
        for (int i = 0; i < words.length; i++) {
            if (i != 0) {
                // Append spaces after each word in our sentence
                answer.append(" ");
            }
            // Declare a current trienode
            TrieNode current = root;
            // Take one word
            String w = words[i];
            // Declare a stringbuilder for creating short word if present
            StringBuilder sb = new StringBuilder();
            // Iterate through each char in word
            for (int j = 0; j < w.length(); j++) {
                // Take one char at a time
                char wc = w.charAt(j);
                // Check if curr's children at position of that char is null or if curr letter
                // is having isend set to true
                if (current.children[wc - 'a'] == null || current.isEnd == true) {
                    // In that case break
                    break;
                }
                // Otherwise append the char to our stringbuilder
                sb.append(wc);
                // Move curr to next
                current = current.children[wc - 'a'];
            }
            // In end is isend true that means short word found, so append it in our ans
            if (current.isEnd == true) {
                answer.append(sb.toString());
            }
            // Else append the original word in our answer
            else {
                answer.append(w);
            }

        }
        // Return answer
        return answer.toString();
    }
}