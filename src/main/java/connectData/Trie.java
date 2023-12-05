package connectData;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private static final ArrayList<String> searchedWords = new ArrayList<>();
    private static final TrieNode root = new TrieNode();
    public static ArrayList<String> getSearchedWords() {
        return searchedWords;
    }
    public static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();

        boolean isEndOfWord;

        public TrieNode() {
            isEndOfWord = false;
        }
    }

    // insert 1 từ vào trie
    public static void insert(String word) {
        // node hiện tại
        TrieNode current = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            // nếu từ này chưa có trong trie thì thêm từ này vào (thêm vào node mới)
            if (current.children.get(ch) == null) {
                current.children.put(ch, new TrieNode());
            }
            // sang node tiếp theo
            current = current.children.get(ch);
        }
        // flag để dừng
        current.isEndOfWord = true;
    }

    private static void dfs(TrieNode current, String word) {
        // nếu gặp flag kết thúc thì thêm từ này vào list
        if (current.isEndOfWord) {
            searchedWords.add(word);
        }
        // trong từng từ , gọi dfs để duyệt hết
        for (char ch : current.children.keySet()) {
            if (current.children.get(ch) != null) {
                dfs(current.children.get(ch), word + ch);
            }

        }
    }

    // tìm từ bằng tiền tố
    public static ArrayList<String> search(String  prefix){
        // nếu tiền tố là null , sẽ không có từ này trong từ điển
        if (prefix.isEmpty()){
            return new ArrayList<>();
        }
        searchedWords.clear();
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); ++i){
            char ch = prefix.charAt(i);

            if (current.children.get(ch) == null){
                return getSearchedWords();
            }
            current = current.children.get(ch);
        }
        dfs(current, prefix);
        return getSearchedWords();
    }

    public static void delete(String word){
        delete(root, word, 0);
    }
    private static boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                // The word doesn't exist in the Trie
                return false;
            }
            current.isEndOfWord = false;

            // Return true if the current node has no other children
            return current.children.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        if (node == null) {
            // The word doesn't exist in the Trie
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        // If true is returned, remove the mapping of the character and the node
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);

            // Return true if the current node has no other children
            return current.children.isEmpty();
        }

        return false;
    }
}
