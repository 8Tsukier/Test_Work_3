package ua.telesens.io;

import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueueOfStrings {
    private Queue<String> queue;

    public QueueOfStrings(int maxCount) {
        queue = new ArrayQueue<>(maxCount);
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public boolean add(String s) {
        Pattern pattern = Pattern.compile("\\p{Lu}.*");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            return false;
        }
        return queue.add(s);
    }

    public String inAlphabeticOrder() {
        List<String> list = new ArrayList<>(queue);
        Collator collator = Collator.getInstance(new Locale("uk"));
        Collections.sort(list, (s1, s2) -> collator.compare(s1, s2));
        return list.toString();
    }
}