package app.miniappspring.annotation.print;

import app.miniappspring.console.ConsoleColors;


public class P {


    public static void print(Object object) {
        System.out.println(ConsoleColors.YELLOW_BOLD + "Print: " + object.toString());
    }

//    public static void print(Object object) throws IllegalAccessException {
//        for (Field field : object.getClass().getDeclaredFields()) {
//            PrintValue printValue = field.getAnnotation(PrintValue.class);
//            if (printValue != null) {
//                field.setAccessible(true);
//                Object value = field.get(object);
//                if (value != null || value.toString().isEmpty()) {
//                    throw new RuntimeException(printValue.message());
//                } else {
//                    System.out.println(value);
//                }
//            }
//        }

//}

//    @PostConstruct
//    public void initPrintValueVariableInConsole(){
//
    }


