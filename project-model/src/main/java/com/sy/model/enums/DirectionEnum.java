package com.sy.model.enums;

/**
 * @ClassName DirectionEnum
 * @Description 分页排序
 * @Author lichengyi
 * @Data 2018-07-12 9:35
 * @Version 1.0
 **/
public enum DirectionEnum {

    ASC("asc"),

    DESC("desc");


    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    DirectionEnum(String direction) {
        this.direction = direction;
    }


}
