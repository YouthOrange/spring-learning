package cl.learning.mall.enums;

/**
 * @Author : 常亮
 * @Date : 11:14 2019-01-17
 * @Description :
 */
public enum GoodsTypeEnum {
    /**
     * 服装
     */
    CLOTHS("1", "服装"),
    /**
     * 鞋类
     */
    SHOWS("2", "鞋类"),
    ELECS("3", "电子产品"),
    DAILYS("4", "日化用品"),
    FOODS("5", "食品"),
    VIRTS("6", "虚拟商品"),
    OTHERS("9", "其他");
    private String type;
    private String desc;

    GoodsTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }}
