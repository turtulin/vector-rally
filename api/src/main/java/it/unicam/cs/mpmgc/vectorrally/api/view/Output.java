package it.unicam.cs.mpmgc.vectorrally.api.view;

public interface Output {
    static void printlnMessage(String message) {
        System.out.println(message);
    }

    static void printMessage(String message) {
        System.out.print(message);
    }
}
