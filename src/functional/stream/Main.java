package functional.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static void task1() {
        // Task 1: Understand the Stream class in Java and iterate through a stream
        // using forEach

        /*
         * Stream classes: Stream, IntStream, DoubleStream Stream methods: stream()
         * forEach() of() range() findFirst() ifPresent() noneMatch()
         */
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers);

        integers.stream().forEach(System.out::println);

        integers.stream().forEach(x -> {
            System.out.println(" Custom " + x);
        });

        Stream.of(1, 2, 3, 4, 5, 6).forEach(System.out::println);

        IntStream.range(1, 10).forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5, 6).findFirst().ifPresent(System.out::println);

        System.out.println(IntStream.range(1, 5).noneMatch(x -> x > 6));


    }

    static void task2() {
        // Task 2: Perform statistics with numeric streams

        /*
         * Stream methods: mapToInt() sum() count() average() summaryStatistics()
         */
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(" Sum => " + integers.stream().mapToInt(Integer::intValue).sum());
        System.out.println(" Average => " + integers.stream().mapToInt(Integer::intValue).average());
        System.out.println(" Max => " + integers.stream().mapToInt(Integer::intValue).max());
        System.out.println(" Min => " + integers.stream().mapToInt(Integer::intValue).min());
        System.out.println(" Summary Statistics => " + integers.stream().mapToInt(Integer::intValue).summaryStatistics());


    }

    static void task3() {

        // Task 3: Perform basic data processing using the map and filter methods

        /*
         * Stream methods: map filter
         */


        // Streams cannot be reused. A terminal operation closes the stream.

        IntStream.range(1, 11).map(x -> x * x).forEach(System.out::println);

        IntStream.range(1, 6).map(x -> x * x).map(x -> x - 2).forEach(System.out::println);

        Stream.of(2, 6, 8, 7, 4).filter(x -> x > 7).forEach(System.out::println);

        Stream.of(2, 6, 8, 7, 4).filter(x -> x > 7).map(x -> x * x).forEach(System.out::println);

        IntStream intStream = IntStream.range(5, 11);
        System.out.println(" Sum -> " + intStream.sum());
        //System.out.println("Count -> " + intStream.count()); //intstream is already modified,this will give error

    }

    static void task4() {
        // Task 4: Understand the reduce method

        System.out.println(" Sum using reduce " + Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> (a + b)));
    /* This takes (1,2) -> (3), then (3+3)->(6), (6+4) -> (10), (10+5) -> (15)
    reduce(0, ..) -> zero is identity, which describes 0+a -> a*/

        System.out.println("Product using reduce " + Stream.of(1, 2, 3, 4, 5).reduce(1, (a, b) -> (a * b)));
    }

    static void task5() {
        // Task 5: Perform string operations on a stream
        Stream.of("key_500", "key_898", "key_934").map(s -> s.toUpperCase()).forEach(System.out::println);

        Stream.of("key_500", "key_898", "key_934").map(s -> s.split("_")).map(s -> s[1]).
                forEach(System.out::println);

        Stream.of("key_500", "key_898", "key_934").map(s -> s.split("_")).map(s -> s[1]).
                mapToInt(Integer::parseInt)
                .forEach(System.out::println);
    }

    // Task 6: Open the CSV dataset and perform basic stream operations

    static void task6to7() {

        class Row {
            String daily_vaccinations;
            String country;

            Row(List list) {
                this.daily_vaccinations = (String) (list.get(7));
                this.country = (String) (list.get(0));
            }

            public String toString() {
                return country + ", " + daily_vaccinations;
            }
        }

        try {

            List<Row> records = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("country_vaccinations.csv"));

            String line;
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new Row(Arrays.asList(values)));
            }

            //Extract top 10 records
            records.stream().limit(10).forEach(System.out::println);

            //last 10
            records.stream().skip(records.stream().count() - 10).forEach(System.out::println);

            //country
            records.stream().map(x -> x.country).distinct().forEach(System.out::println);

            //count
            System.out.println("count " + records.stream().count());
            System.out.println("Count of nulls " + records.stream().filter(x -> x.daily_vaccinations.
                    equals("")).count());

            //Summation
            System.out.println("Total vaccination of zimbabwe: " + records.stream()
                    .filter(x -> x.country.equals("Zimbabwe"))
                    .filter(x -> !x.daily_vaccinations.equals(""))
                    .map(x -> x.daily_vaccinations)
                    .mapToDouble(Double::parseDouble).
                    sum());

            //Total counties vaccinations
            records.stream().map(x -> x.country).distinct().forEach(x -> {
                System.out.println(" Total Vaccinations in " + x + " = " + records.stream()
                        .filter(y -> y.country.equals(x))
                        .filter(y -> !y.daily_vaccinations.equals(""))
                        .map(y -> y.daily_vaccinations)
                        .mapToDouble(Double::parseDouble)
                        .sum());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6to7();
    }
}
