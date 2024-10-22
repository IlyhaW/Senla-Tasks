import java.util.Scanner;
import java.util.Random;

public class Hangman {
    private static final String[] WORDS = {"apple", "banana", "school", "teacher", "language"};
    private static final int MAX_LIVES = 6;
    private String wordToGuess;
    private StringBuilder currentGuess;
    private int lives;
    private boolean[] guessedLetters;

    // Конструктор игры
    public Hangman() {
        Random rand = new Random();
        wordToGuess = WORDS[rand.nextInt(WORDS.length)];
        currentGuess = new StringBuilder("_".repeat(wordToGuess.length()));
        lives = MAX_LIVES;
        guessedLetters = new boolean[26]; // Для отслеживания угаданных букв
    }

    // Запуск игры
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру Виселица!");
        while (lives > 0 && currentGuess.indexOf("_") != -1) {
            displayGameState();
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            // Проверка на пустую строку
            if (input.isEmpty()) {
                System.out.println("Вы не ввели ни одной буквы. Пожалуйста, введите букву.");
                continue;
            }

            char guessedChar = input.charAt(0);

            // Проверка, является ли символ буквой английского алфавита
            if (!Character.isLetter(guessedChar) || guessedChar < 'a' || guessedChar > 'z') {
                System.out.println("Пожалуйста, введите букву английского алфавита.");
                continue;
            }

            int letterIndex = guessedChar - 'a';
            if (guessedLetters[letterIndex]) {
                System.out.println("Эта буква уже была угадана.");
                continue;
            }

            guessedLetters[letterIndex] = true;
            processGuess(guessedChar);
        }

        if (currentGuess.indexOf("_") == -1) {
            System.out.println("Поздравляем! Вы угадали слово: " + wordToGuess);
        } else {
            System.out.println("Вы проиграли. Загаданное слово было: " + wordToGuess);
        }
    }

    // Обработка введенной буквы
    private void processGuess(char guessedChar) {
        boolean found = false;

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedChar) {
                currentGuess.setCharAt(i, guessedChar);
                found = true;
            }
        }

        if (found) {
            System.out.println("Вы угадали букву!");
        } else {
            lives--;
            System.out.println("Неправильно! Осталось жизней: " + lives);
        }
    }

    // Отображение состояния игры
    private void displayGameState() {
        System.out.println("Текущее слово: " + currentGuess);
        drawHangman();
    }

    // Отрисовка виселицы в зависимости от оставшихся жизней
    private void drawHangman() {
        switch (lives) {
            case 6:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 5:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 4:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|    |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 3:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|   /|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 2:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|   /|\\");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 1:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|   /|\\");
                System.out.println("|   /");
                System.out.println("|");
                System.out.println("|______");
                break;
            case 0:
                System.out.println("______");
                System.out.println("|    |");
                System.out.println("|    O");
                System.out.println("|   /|\\");
                System.out.println("|   / \\");
                System.out.println("|");
                System.out.println("|______");
                break;
        }
    }

    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.startGame();
    }
}
