package com.tcurt.javasandbox.commonwords;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class StreamSolution {

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

        System.out.println(String.format("wordMap: %s", wordMap));
        return wordMap;
    }

    public List<String> mostCommonWords(String input, int topN) {
        Map<String, Integer> wordMap = parseWordCounts(input);
        List<String> topWords = wordMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(topN)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList());
        System.out.println(String.format("Top %d words: %s", topN, topWords));
        return topWords;
    }

    public static void main(String[] args) {
        StreamSolution solution = new StreamSolution();
        String test = "A test that we did a good test. Did we? We are happy.";
        solution.mostCommonWords(test, 5);
    }
}
