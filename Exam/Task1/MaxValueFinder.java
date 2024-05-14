import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MaxValueFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть сід для генерації псевдовипадкових чисел: ");
        long seed = scanner.nextLong();

        System.out.print("Введіть кількість чисел для генерації: ");
        int count = scanner.nextInt();

        List<Integer> numbers = generateRandomNumbers(seed, count);
        System.out.println("Згенеровані числа: " + numbers);

        int maxValue = findMaxValue(numbers);
        System.out.println("Максимальне значення: " + maxValue);
    }

    private static List<Integer> generateRandomNumbers(long seed, int count) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random(seed);

        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(100)); // Генеруємо числа від 0 до 99
        }

        return numbers;
    }

    private static int findMaxValue(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(Integer.MIN_VALUE);
    }
}