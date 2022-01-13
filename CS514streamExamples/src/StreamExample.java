import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    LinkedList<String> stringList;

    public StreamExample() {
        stringList = new LinkedList<>();
    }

    public void test1() {
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");

        Stream<String> stream = stringList.stream();

        // note the use of format here.
        System.out.format("There are %d objects%n", stream.count());

    }

    public void test2() {
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");

        Stream<String> stream = stringList.stream();

        long c = stream
                .filter(word -> word.startsWith("c"))
                .count();
        System.out.format("There are %d 'c' words%n", c);
    }

    // how to construct a stream
    public void test3() {

        Stream<String> s1 = Stream.of("cat, dog");
        LinkedList<String> l = new LinkedList<>();
        l.add("moose");
        l.add("penguin");
        s1 = l.stream();

        try {
            Stream<String> fileWords = Files.lines(Paths.get("src/streamExample.java"));
        } catch (IOException e) {
            System.out.println("unable to open file.");
        }
    }

    public void test4() {
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .collect(Collectors.toList());

        Set<String> resultsNoDups = stringList.stream()
                .collect(Collectors.toSet());
        System.out.println(results);
        System.out.println(resultsNoDups);

    }

    public void test5() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                                            .filter(w ->w.startsWith("c"))
                                            .collect(Collectors.toList());

        System.out.println(results);
    }

    public void test6() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .filter((String w) ->w.length() > 4) // we can indicate a type if necessary.
                .collect(Collectors.toList());
        System.out.println(results);

    }

    // here's a more complex lambda function.
    public void test7() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .filter((String w) -> {
                            int count = w.length();
                            if (count / 2 < 4) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                )
            .collect(Collectors.toList());
        System.out.println(results);
    }

    public void test8() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");

        Predicate<String> fakeCount = (String w) ->
                {
                    int count = w.length();
                    if (count / 2 < 4) {
                        return true;
                    } else {
                        return false;
                    }
                };

        List<String> results = stringList.stream()
                .filter((String w) -> fakeCount.test(w))
                .collect(Collectors.toList());
        System.out.println(results);
    }

    public void test9() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .filter((String w) ->w.length() < 4)
                .filter((String w) -> w.startsWith("c"))
                .collect(Collectors.toList());
        System.out.println(results);

    }

    public void test10()  {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .filter((String w) ->w.length() < 4)
                .filter((String w) -> w.startsWith("c"))
                .map(w -> w.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(results);

    }

    public void testmap() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .map(w -> w.toLowerCase())
                .collect(Collectors.toList());
    }


    public void test11() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        List<String> results = stringList.stream()
                .sorted((x,y) -> (x.compareTo(y)))
                .collect(Collectors.toList());
        System.out.println(results);

    }

    public void test12() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        if(stringList.stream()
                .allMatch(w -> w.length() > 4)) {
            System.out.println("All words have length > 4");
        } else {
            System.out.println("Not all words have length > 4");
        }

    }

    public void test13() {
        stringList.clear();
        stringList.add("cat");
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("llama");
        stringList.add("kangaroo");
        System.out.println(stringList.stream().filter(w -> w.length() > 4).count());


    }





    public static void main(String[] args) {
        StreamExample s = new StreamExample();

        s.test1();
        s.test2();
        s.test3();
        s.test4();
        s.test5();
        s.test6();
        s.test7();
        s.test8();
        s.test9();
        s.test10();
        s.test11();
        s.test12();
        s.test13();
    }


}
