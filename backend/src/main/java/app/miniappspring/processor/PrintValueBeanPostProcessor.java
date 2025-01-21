//package app.miniappspring.processor;
//
//import app.miniappspring.annotation.print.PrintValue;
//import app.miniappspring.console.ConsoleColors;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//
//@Component
//public class PrintValueBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        Class<?> clazz = bean.getClass();
//
//        // Проходим по всем полям класса
//        for (Field field : clazz.getDeclaredFields()) {
//            if (field.isAnnotationPresent(PrintValue.class)) {
//                field.setAccessible(true); // Делаем поле доступным для изменения
//                try {
//                    Object value = field.get(bean);
//                    if (value == null || value.toString().isEmpty()) {
//                        throw new RuntimeException(
//                                ConsoleColors.RED_BOLD + "Field annotated with @PrintValue is null or empty: " + field.getName() + ConsoleColors.RESET
//                        );
//                    } else {
//                        System.out.println(
//                                ConsoleColors.YELLOW_BOLD+ "Value of field " + field.getName() + ": " + value + ConsoleColors.YELLOW
//                        );
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(
//                            ConsoleColors.RED + "Failed to access field " + field.getName() + ConsoleColors.RESET, e
//                    );
//                }
//            }
//        }
//
//        return bean;
//    }
//}