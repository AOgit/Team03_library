package utils.ColorMe;

public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    ORANGE("\u001B[38;5;214m");
    private final String colorCode;

    Color(String color) {
        this.colorCode = color;
    }
    public String getColorCode() {
        return colorCode;
    }
}
