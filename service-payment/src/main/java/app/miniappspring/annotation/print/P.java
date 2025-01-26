package app.miniappspring.annotation.print;

import app.miniappspring.console.ConsoleColors;


public class P {

    public static void print(Object object) {
        System.out.println(ConsoleColors.YELLOW_BOLD + "Print: " + object.toString());
    }

}


