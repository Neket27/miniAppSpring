package app.miniappspring.annotation.print;

import app.miniappspring.console.ConsoleColors;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
@Aspect
public class PrintValueAspect {

    @Before("execution(* *(..)) && @annotation(printValue)")
    public void printValue(PrintValue printValue) throws IllegalAccessException {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerClassName = stackTrace[2].getClassName();
        String callerMethodName = stackTrace[2].getMethodName();
        try {
            Class<?> callerClass = Class.forName(callerClassName);
            for (Field field : callerClass.getClass().getDeclaredFields()) {
//                PrintValue printValue = field.getAnnotation(PrintValue.class);
                if (printValue != null) {
                    field.setAccessible(true);
                    Object value = field.get(callerClass);
                    if (value != null || value.toString().isEmpty()) {
                        String text = ConsoleColors.RED_BOLD + "Field annotated with @PrintValue is null or empty: " + field.getName() + ConsoleColors.RESET;
                        throw new RuntimeException(text);
                    } else {
                        System.out.println(value);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось найти класс: " + callerClassName, e);
        }

    }

}
