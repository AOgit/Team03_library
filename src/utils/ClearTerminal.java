package utils;

public abstract class ClearTerminal {
    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();
//        System.out.println(os);
        try {
            ProcessBuilder pb;
            if (os.contains("win")) {
                // Для Windows
                 pb = new ProcessBuilder("powershell", "-Command", "Clear-Host");

            } else {
                // Для Linux/macOS
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor(); // Запускаем процесс
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Терминал грязный !");

        // Очищаем терминал
        clear();

        // Выводим сообщение после очистки
        System.out.println("Терминал очищен!");
    }
}
