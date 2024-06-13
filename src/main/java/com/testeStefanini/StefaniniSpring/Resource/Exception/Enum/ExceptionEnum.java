package com.testeStefanini.StefaniniSpring.Resource.Exception.Enum;

public enum ExceptionEnum {
    Database_error(1),
    Validation_error(2),
    New_user_error(3),
    password_invalid(4),
    Resource_not_found(5);

    private int code;

    private ExceptionEnum(int code){
        this.code = code;
    }
    public int getCode() {
        return code;
    }

        public static ExceptionEnum valueOf(int code){
        for(ExceptionEnum value: ExceptionEnum.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
