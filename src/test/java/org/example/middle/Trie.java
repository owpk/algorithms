package org.example.middle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.example.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Trie {

    public static class TrieNode {

        private TrieNode[] children = new TrieNode['z' - 'A' + 1];
        private boolean isEnd;

        public TrieNode() { }

        /**
         * Inserts a word into the trie.
         *
         * Time complexity: O(word.length())
         * Space complexity: O(1)
         */
        public void insert(String word) {
            var next = this;
            for (int i = 0; i < word.length(); i++) {
                var curr = word.charAt(i);
                var children = next.children;
                var nextCode = 'z' - curr;

                if (children[nextCode] == null)
                    children[nextCode] = new TrieNode();

                next = children[nextCode];

                if (i == word.length() - 1)
                    next.isEnd = true;
            }
        }

        public boolean search(String word) {
            var next = this;
            var matched = false;

            for (char c : word.toCharArray()) {
                var children = next.children;
                var nextCode = 'z' - c;
                next = children[nextCode];
                if (next != null && next.isEnd)
                    matched = true;
                else if (next == null)
                    return false;
            }

            return matched;
        }

        public boolean startsWith(String prefix) {
            var next = this;
            for (char c : prefix.toCharArray()) {
                var children = next.children;
                var nextCode = 'z' - c;
                next = children[nextCode];
                if (next == null)
                    return false;
            }
            return true;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
    """
        '[\"insert\",   \"search\", \"search\", \"startsWith\", \"insert\", \"search\"]'
        '[\"apple\",    \"app\",    \"apple\",  \"app\",        \"app\",    \"app\"]'
        '[\"true\",     \"false\",  \"true\",   \"true\",       \"true\",   \"true\"]'
        """,
    }, delimiterString = "\n")
    void test(String commands, String data, String expected) {
        var cArr = ArrayUtils.toArray(commands, String.class);
        var dArr = ArrayUtils.toArray(data, String.class);
        var eArr = ArrayUtils.toArray(expected, Boolean.class);

        var trie = new TrieNode();

        for (int i = 0; i < cArr.length; i++) {
            var command = cArr[i];
            var word = dArr[i];
            var expectedData = eArr[i];
            if (command.equals("insert"))
                trie.insert(dArr[i]);
            else if (command.equals("search")) {
                var search = trie.search(word);
                Assertions.assertEquals(search, expectedData);
            } else if (command.equals("startsWith")) {
                var startsWith = trie.startsWith(word);
                Assertions.assertEquals(startsWith, expectedData);
            }
        }
    }

    // @Test
    void test2() throws IOException {
        var data = Files.readString(Path.of("src/test/resources/trie.csv"));
        var split = data.split("\n");
        var cArr = ArrayUtils.toArray(split[0], String.class);
        var dArr = ArrayUtils.toArray(split[1], String.class);
        var eArr = ArrayUtils.toArray(split[2], Boolean.class);

        var trie = new TrieNode();

        for (int i = 0; i < cArr.length; i++) {
            var command = cArr[i];
            var word = dArr[i];
            var expectedData = eArr[i];
            if (command.equals("insert"))
                trie.insert(dArr[i]);
            else if (command.equals("search")) {
                var search = trie.search(word);
                if (search != expectedData) {
                    log(command, word, expectedData, search);
                }
                Assertions.assertEquals(search, expectedData);
            } else if (command.equals("startsWith")) {
                var startsWith = trie.startsWith(word);
                if (startsWith != expectedData) {
                    log(command, word, expectedData, startsWith);
                }
                Assertions.assertEquals(startsWith, expectedData);
            }
        }
    }

    private void log(String command, String word, boolean expecte, boolean actual) {
        System.out.println("action : " + command);
        System.out.println("word : " + word);
        System.out.println("expected : " + expecte);
        System.out.println("actual : " + actual);
    }
}
