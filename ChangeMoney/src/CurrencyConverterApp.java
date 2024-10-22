import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Класс для представления валюты
class Currency {
    private String name;   // Название валюты
    private double rate;   // Обменный курс относительно базовой валюты (USD)

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double convertFromUSD(double amountInUSD) {
        return amountInUSD * rate;
    }

    public double convertToUSD(double amountInCurrency) {
        return amountInCurrency / rate;
    }
}

// Класс для выполнения операций конвертации
class CurrencyConverter {
    private Map<String, Currency> currencies = new HashMap<>();

    public CurrencyConverter() {
        // Добавляем валюты с их курсами
        currencies.put("USD", new Currency("USD", 1.0));       // Базовая валюта - доллар США
        currencies.put("EUR", new Currency("EUR", 0.95));      // Евро
        currencies.put("RUB", new Currency("RUB", 100.0));     // Российский рубль
        currencies.put("JPY", new Currency("JPY", 150.0));     // Японская иена
        currencies.put("GBP", new Currency("GBP", 0.75));      // Британский фунт
    }

    public void convertCurrency(double amount, String fromCurrencyCode) {
        if (!currencies.containsKey(fromCurrencyCode)) {
            System.out.println("Неизвестная валюта: " + fromCurrencyCode);
            return;
        }

        Currency fromCurrency = currencies.get(fromCurrencyCode);
        double amountInUSD = fromCurrency.convertToUSD(amount);  // Конвертируем в доллары (базовую валюту)

        System.out.println("Конвертация " + amount + " " + fromCurrency.getName() + " в другие валюты:");
        for (Currency currency : currencies.values()) {
            if (!currency.getName().equals(fromCurrencyCode)) {
                double convertedAmount = currency.convertFromUSD(amountInUSD);
                System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency.getName(), convertedAmount, currency.getName());
            }
        }
    }
}

// Основной класс приложения
public class CurrencyConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        System.out.println("Добро пожаловать в Конвертер Валют!");
        System.out.println("Доступные валюты: USD, EUR, RUB, JPY, GBP");

        while (true) {
            System.out.print("Введите сумму для конвертации: ");
            double amount = scanner.nextDouble();

            System.out.print("Введите код валюты (например, USD, EUR, RUB, JPY, GBP): ");
            String fromCurrencyCode = scanner.next().toUpperCase();

            converter.convertCurrency(amount, fromCurrencyCode);

            System.out.print("Хотите выполнить ещё одну конвертацию? (да/нет): ");
            String continueChoice = scanner.next().toLowerCase();

            if (!continueChoice.equals("да")) {
                System.out.println("Спасибо за использование конвертера валют!");
                break;
            }
        }

        scanner.close();
    }
}
