package utils;

public class LOGGER {
    public static void info(String msg) {
        System.out.print("Info: " + msg);
        System.out.println();
    }

    public static void error(String msg) {
        System.out.println();
        System.out.print("Error: " + msg);
        System.out.println();
    }
}
