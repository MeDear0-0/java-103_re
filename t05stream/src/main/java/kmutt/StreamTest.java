package kmutt;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamTest {
    
    // print utilities ---------------------------------
    static final DecimalFormat DECIMAL =new DecimalFormat("0.##");
    static final DoubleConsumer PRINT_DOUBLE = d -> System.out.print("[:" + DECIMAL.format(d) + "]");
    static final IntConsumer PRINT_INT = i -> System.out.print("[:" + i + "]");

    private static DoubleConsumer printDouble(String s, String e) { 
        return t -> System.out.print(s + DECIMAL.format(t) + e); 
    }
    private static IntConsumer printInt(String s, String e) { 
        return t -> System.out.print(s + t + e); 
    }
    private static <T> Consumer<T> print(String s, String e) { 
        return t -> System.out.print(s + t.toString() + e); 
    }
    private static <T> String asString(Stream<T> strm) {
        return strm.map(T::toString).collect(Collectors.joining("],[","[" ,"]"));
    }
    private static String asIntString(IntStream strm) {
        return strm.mapToObj(String::valueOf).collect(Collectors.joining("],[","[" ,"]"));
    }
    private static String asDoubleString(DoubleStream strm) {
        return strm.mapToObj(DECIMAL::format).collect(Collectors.joining("],[","[" ,"]"));
    }
    // end of print utilities

    public static void main(String[] args) {
        test01StreamCreation();
        //test02StreamNonTerminal();
        //test03StreamTerminal();
        //test04StreamCollector();
    }
    
    static void test01StreamCreation() {
        System.out.println("[1] Stream Creation =================");
        
        // Stream from Varargs (Variable Arguments)
        System.out.println("1.1) Stream.of(varargs), Stream.of(array), Arrays.stream(array) ----");
        Stream<String> strStrm0 = Stream.of("One", "Two", "Three", "Four");
        IntStream intStrm0 = IntStream.of(2473, -384, 453, 94, 532);
        System.out.print("   IntStream.of(..., ..., ...): ");
        intStrm0.forEach(PRINT_INT);
        System.out.println();

        // Stream from an array: Stream.of(array)
        String[] strArr = {"First", "Second", "Third"};
        Stream<String> strStrm1 = Stream.of(strArr);

        // Stream from an array: Arrays.stream(array)
        Stream<String> strStrm2 = Arrays.stream(strArr);
        System.out.println("   Arrays.tream(array): " + asString(strStrm2));
        
        // Stream from two streams: Stream.concat(stream0,stream1)
        System.out.println("1.2) Stream.concat(stream0, stream1) ----");
        Stream<String> strStrm3 = Stream.concat(strStrm0, strStrm1);
        System.out.println("   concat Stream.of(String[]): " + asString(strStrm3));
        
        // reuse a pseudo random number generator
        Random random = new Random();
        
        // Stream from a Supplier: Stream.generate(()->T) vs. random.ints()
        System.out.println("1.3) Stream.generate(supplier) vs. random.ints(count, min, max) ----");
        IntStream intStrm1 = IntStream.generate(random::nextInt).limit(5);
        System.out.println("   IntStream.generate(random::nextInt).limit(5): " + asIntString(intStrm1));
        IntStream intStrm2 = IntStream.generate(() -> 50 + random.nextInt(20)).limit(4);
        System.out.println("   IntStream.generate(() -> 50 + random.nextInt(20)).limit(4): " + asIntString(intStrm2));
        IntStream intStrm3 = random.ints(4, 50, 70); // 4 ints in [50,70)
        System.out.println("   random.ints(4, 50, 70);: " + asIntString(intStrm3));
        DoubleStream doubleStrm0 = random.doubles(4, 50.0, 70.0); // 4 doubles in [50.0,70.0)
        System.out.println("   random.doubles(4, 50.0, 70.0): " + asDoubleString(doubleStrm0));
        
        // Stream from a UnaryOperator: Stream.iterate(start, i -> f(i)); = i, f(i), f(f(i)), f(f(f(i))), ...
        System.out.println("1.4) Stream.iterate(start, [hasNextPredicate,] nextUnaryOperator) ----");
        IntStream intStrm4 = IntStream.iterate(2, i -> i * i).limit(5); // 2, 4, 16, 256, 65536
        System.out.println("   Stream.iterate(2, i -> i*i).limit(5): " + asIntString(intStrm4));
        IntStream intStrm5 = IntStream.iterate(0, i -> i < 5, i -> i +1);
        System.out.println("   Stream.iterate(0, i -> i < 5, i -> i + 1): " + asIntString(intStrm5));
        Stream<int[]> factorial = Stream.iterate(new int[] {1,1} , i -> new int[] { i[0]+1, i[1] * (i[0]+1) } );
        System.out.println("   sequence of factorials => " + asIntString(factorial.limit(7).mapToInt(i -> i[1])));
        
        // Stream from int/long range
        System.out.println("1.5) Int/LongStream.range(start, end) and Int/LongStream.rangeClosed(start,end) ----");
        System.out.println("   Stream.range(10,15): " + asIntString(IntStream.range(10,15)));
        System.out.println("   Stream.rangeClosed(10,15): " + asIntString(IntStream.rangeClosed(10,15)));
        
        // Stream from Collection
        System.out.println("1.6) collection.stream() ----");
        List<String> ls = List.of("None", "One", "Second", "End");
        Set<String> ss = Set.of("Cat", "Rat", "Bat", "Hat", "Mat");
        Map<String,Integer> lives = Map.ofEntries(
                Map.entry("Dog", 22), Map.entry("Cat", 25), Map.entry("Mouse", 4));
        System.out.println("   list.stream(): " + asString(ls.stream()));
        System.out.println("   set.stream(): " + asString(ss.stream()));
        System.out.println("   map.entrySet().stream(): " + asString(lives.entrySet().stream()));
        
        System.out.println("1.7) empty stream ----");
        System.out.println("   Stream.empty(): <<" + asString(Stream.empty()) + ">>");
    }
    
    static void test02StreamNonTerminal() {
        System.out.println("[2] Stream Non-Terminal Processing =================");
        
        // peek(), skip(), limit()
        System.out.println("2.1) peek(), skip(), limit() ------------");
        IntStream iStrm0 = IntStream.rangeClosed(1,10)
                .peek(PRINT_INT) // show each element that passes by
                .skip(3) // ignore the first 3 elements
                .limit(4); // take only 4 elements
        System.out.println("   Notice the output of IntStream.rangeClosed(1,10).peek(intPeek).skip(3).limit(4);");
        System.out.println("\n   [1...10].skip(3).limit(4): " + asIntString(iStrm0));

        // filter()
        System.out.print("2.2) filter() ------------\n   ");
        Stream.of("A1","A2","A3","B4","A5","A6","B7","A8")
                .peek(print("",";"))
                .filter(s->s.contains("A")) // filter only strings containing "A"
                .forEach(print("[select: ","] "));
        System.out.println();

        // takeWhile()
        System.out.print("2.3) takeWhile() ------------\n   ");
        Stream.of("A1","A2","A3","B4","A5","A6","B7","A8")
                .peek(print("",";"))
                .takeWhile(s->s.contains("A")) // take while strings containing "A"
                .forEach(print("[select: ","] "));
        System.out.println();

        // dropWhile()
        System.out.print("2.4) dropWhile() ------------\n   ");
        Stream.of("A1","A2","A3","B4","A5","A6","B7","A8")
                .peek(print("",";"))
                .dropWhile(s->s.contains("A")) // drop while strings containing "A"
                .forEach(print("[select: ","] "));
        System.out.println();

        // distinct()
        System.out.print("2.5) distinct() ------------\n   ");
        Stream.of("A","B","D","F","C","D","A","E")
                .peek(print("",";"))
                .distinct() // remove duplicate values
                .forEach(print("[select: ","] "));
        System.out.println();
        
        // sorted()
        System.out.print("2.6) sorted() ------------\n   ");
        Stream.of("A","B","D","F","C","D","A","E")
                .peek(print("",";"))
                .sorted() // sorted in the natural order
                .peek(print("<",">"))
                .sorted(Comparator.reverseOrder()) // sorted in the reverse order
                .forEach(print("[","]"));
        System.out.println();

        // map(), mapToObj(), mapToInt()
        System.out.print("2.7) map() ------------\n   ");
        Stream.of("one", "second", "three", "four", "fifth")
                .mapToInt(String::length)
                .forEach(PRINT_INT);
        System.out.println();

        // flatMap(), flatMapToInt()
        System.out.print("2.8) flatMap() ------------\n   ");
        Arrays.stream(new int[][] {{1},{2,3},{4,5,6},{7,8},{9}})
                .flatMapToInt(Arrays::stream)
                .forEach(PRINT_INT);
        System.out.println();

        // mapMulti(), 
        // mapToInt(), mapToObj(), asDoubleStream(), boxed()
        // parallel(), sequential(), unordered()
    }
    
    static void test03StreamTerminal() {
        System.out.println("[3] Stream Terminal Processing =================");
        
        // allMatch(), anyMatch(), noneMatch(), findFirst(), findAny()
        // collect(), reduce(), forEach(), forEachOrdered()
        // count(), min(), max(), sum(), average(), summaryStatistics()
        // iterator(), spliterator()
        // toArray(), toList()
        
        // 
        System.out.println("3.1)  ----");
    }
    
    static void test04StreamCollector() {
        System.out.println("[4] Stream Collector =================");
        
        // Collectors, tee

        // 
        System.out.println("4.1)  ----");
    }

}
