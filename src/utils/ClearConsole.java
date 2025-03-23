package utils;

public class ClearConsole {
    public static void clearConsole() {
        try {
            // Для Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Для Unix/Linux/Mac
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Ошибка при очистке консоли.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Консоль неочищена!");
        clearConsole();  // Очистит консоль
        System.out.println("Консоль очищена!");
    }
}
