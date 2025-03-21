package utils.ColorMe;

public abstract class ColorMe {
    /**
     * Раскрашивает текст
     * ColorMe.text(Color.RED, "Это красная строка"));
     * @param color здесь выбираем цвет из уже заданного списка Enum
     * @return раскрашенную строку
     */
    public static String text(Color color, String text) {
        return color.getColorCode() + text + Color.RESET.getColorCode();
    }
}
