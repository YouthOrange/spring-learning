package cl.learning.mall.enums;

/**
 * @Author : 常亮
 * @Date : 13:53 2019-01-31
 * @Description :
 */
public enum GoodsEnum {
    /**
     * 物品名称
     */
    PHONE("phone", "手机"),
    SNACK("snack", "零食"),
    JEANS("jeans", "牛仔裤");
    private final String code;
    private final String desc;

    GoodsEnum(String code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "GoodsEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }}
