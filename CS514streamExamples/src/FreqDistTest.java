import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FreqDistTest {

    FreqDist freq;
    static String str;

    @BeforeAll
    static void initialSetup() {
       System.out.println("Static variables can be set up here.");
       str = "I love Java";
    }

    @BeforeEach
    void setup() {
        System.out.println("Instance variables are set up here.");
        freq = new FreqDist();
        freq.add("bunny");
        freq.add("bunny");
        freq.add("dragon");
    }


    @ParameterizedTest
    @DisplayName("Here's a parameterized test")
    @ValueSource(strings= {"cat", "dog", "fish"})
    void add(String s) {
        freq.add(s);
        System.out.println(s);
    }

    @ParameterizedTest
    @CsvSource({ "bunny, 2", "dragon, 1"})
    void get(String key, int val) {
        assertEquals(freq.get(key), val);
    }

    @ParameterizedTest
    @CsvFileSource(resources="inputdata.csv")
    void get2(String key, int val) {
        assertEquals(freq.get(key), val);
    }

    @RepeatedTest(10)
    void repeatAdd() {
        freq.add("giraffe");
    }



}