package org.example.logistic.entities;

public enum ShippingType {
    EXPRESS("EXP"),
    STANDARD("STD"),
    ECONOMIC("ECO");

    private final String code;

    ShippingType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ShippingType fromCode(String code) {
        for (ShippingType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de frete inválido: " + code);
    }
}
