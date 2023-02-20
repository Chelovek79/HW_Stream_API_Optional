import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] numbers = new Integer[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = random.nextInt(25);
        }

        for (Integer integers : numbers) {
            System.out.print(integers + " ");
        }

        List<Integer> listNum = Arrays.asList(numbers);
        System.out.println(" ");

        Stream<Integer> streamInt = Stream.of(numbers);

        BiConsumer<Integer, Integer> biConsumer = (min, max) -> {
            System.out.println(min + " - минимальное число.");
            System.out.println(max + " - максимальное число.");
        };
        Comparator<Integer> comparator = Comparator.naturalOrder();

        findMinMax(streamInt, comparator, biConsumer);
        System.out.println(" ");

        evenNum(listNum);
    }

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.collect(Collectors.toList());
        if (list.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            list.sort(order);
            minMaxConsumer.accept(list.get(0), list.get(list.size() - 1));
        }
    }

    public static void evenNum(List<Integer> list) {
        Predicate<Integer> evenNum = x -> x % 2 == 0;
        for (Integer integer : list) {
            if (evenNum.test(integer)) {
                System.out.print(integer + " ");
            }
        }
        System.out.println("\n" + list.stream().filter(evenNum).count() + " - количество чётных чисел.");
    }
}
