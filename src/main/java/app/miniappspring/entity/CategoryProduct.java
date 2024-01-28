package app.miniappspring.entity;

public enum CategoryProduct {
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

    CategoryProduct(String russianValue) {
        this.russianValue = russianValue;
    }

    public String getRussianValue() {
        return russianValue;
    }

}
