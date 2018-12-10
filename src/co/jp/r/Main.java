    package co.jp.r;

import jdk.dynalink.Operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

    public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        a();
        b();
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println(PowerSet.of(set));
    }

    public static void a() throws FileNotFoundException {
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(new FileInputStream("sample.txt")).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }
        System.out.println(freq);
    }

    public static void b() throws FileNotFoundException {
        Map<String, Long> freq = new HashMap<>();
        try(Stream<String> words = new Scanner(new FileInputStream("sample.txt")).tokens()) {
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }
        System.out.println(freq);

        List<String> topTen = freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(topTen);
    }
}
