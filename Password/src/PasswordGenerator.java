import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    // Набор символов для генерации пароля
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;

    // Случайный генератор для криптографических задач
    private static final SecureRandom random = new SecureRandom();

    // Метод для генерации пароля
    public static String generatePassword(int length) {
        // Для обеспечения безопасности добавляем по одному символу из каждой категории
        StringBuilder password = new StringBuilder();

        // Добавляем хотя бы один символ из каждой группы
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Оставшиеся символы добавляем случайным образом из общего набора символов
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Перемешиваем символы для большей случайности
        return shuffleString(password.toString());
    }

    // Метод для перемешивания символов в строке
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            // Меняем местами символы
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в генератор паролей!");
        System.out.println("Введите желаемую длину пароля (от 8 до 12 символов):");

        int length = scanner.nextInt();

        // Проверяем, что длина находится в пределах 8-12 символов
        if (length < 8 || length > 12) {
            System.out.println("Длина пароля должна быть от 8 до 12 символов.");
        } else {
            String password = generatePassword(length);
            System.out.println("Ваш случайный безопасный пароль: " + password);
        }

        scanner.close();
    }
}
