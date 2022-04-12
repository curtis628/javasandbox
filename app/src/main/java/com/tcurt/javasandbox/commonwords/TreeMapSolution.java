package com.tcurt.javasandbox.commonwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class TreeMapSolution {

    private Map<String, Integer> parseWordCounts(String input) {
        if (StringUtils.isBlank(input)) {
            return Collections.emptyMap();
        }

        String parsedInput = input.toLowerCase().replaceAll("[^a-zA-Z ]", "");
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : parsedInput.split("\\s+")) {
            Integer count = wordMap.getOrDefault(word, 0);
            wordMap.put(word, ++count);
        }

        return wordMap;
    }

    private SortedMap<Integer, List<String>> sortedMap(Map<String, Integer> wordMap) {
        SortedMap<Integer, List<String>> orderedMap = new TreeMap<>(Comparator.reverseOrder());
        for (String word : wordMap.keySet()) {
            Integer count = wordMap.get(word);
            orderedMap.putIfAbsent(count, new ArrayList<>());
            List<String> wordsWithCount = orderedMap.get(count);
            wordsWithCount.add(word);
        }
        return orderedMap;
    }

    public List<String> mostCommonWords(String input, int topN) {
        List<String> topWords = new ArrayList<>(topN);
        Map<String, Integer> wordMap = parseWordCounts(input);
        SortedMap<Integer, List<String>> sortedMap = sortedMap(wordMap);

        for (Integer wordCount : sortedMap.keySet()) {
            System.out.println(String.format("count=%s: %s", wordCount, sortedMap.get(wordCount)));
        }

        Iterator<Integer> iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            int count = iterator.next();;
            List<String> wordsWithCount = sortedMap.get(count);
            for (String word : wordsWithCount) {
                topWords.add(word);
                if (topWords.size() == topN) {
                    break;
                }
            }
        }

        System.out.println(String.format("Top %d words: %s", topN, topWords));
        return topWords;
    }

    /** Example showing how to parse punctuation using regular expression. */
    public static String parsePunctuation(String test) {
        String str = "String - is a sequence of chars:~!@#$%^&*(). Test.";
        System.out.println(str);
        String result = str.replaceAll("\\p{Punct}", "");
        System.out.println(String.format("after parsePunctuation: %s", result));
        return result;
    }

    public static void main(String[] args) {
        TreeMapSolution solution = new TreeMapSolution();
        int topN = 5;
        String test = "A test that we did a good test. Did we? We are happy.";
        parsePunctuation(test);

        solution.mostCommonWords(test, topN);
    }
}
