package app.miniappspring.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Category {
    СLOTHES("Одежда"),
    SHOES("Обувь"),
    TOOLS("Инструменты"),
    DIFFERENT_GOODS("Различные товары"),
    NEW_PRODUCTS("Новые продукты"),
    PHONES("Телефоны"),
    TV("Телевизоры"),
    COMPUTERS("Компьютеры"),
    SPEAKERS("Колонки");

    private String russianValue;

    Category(String russianValue) {
        this.russianValue = russianValue;
    }

    public String getRussianValue() {
        return russianValue;
    }


}
