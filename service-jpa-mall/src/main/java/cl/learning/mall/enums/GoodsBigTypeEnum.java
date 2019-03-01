package cl.learning.mall.enums;

/**
 * @Author : 常亮
 * @Date : 11:14 2019-01-17
 * @Description :
 */
public enum GoodsBigTypeEnum {
    /**
     * 服装
     */
    CLOTHS("1", "服装", new GoodsEnum[]{
            GoodsEnum.JEANS
    }),
    /**
     * 鞋类
     */
    SHOWS("2", "鞋类", new GoodsEnum[]{
    }),
    ELECT("3", "电子产品", new GoodsEnum[]{
            GoodsEnum.PHONE
    }),
    DAILYS("4", "日化用品", new GoodsEnum[]{
    }),
    FOODS("5", "食品", new GoodsEnum[]{
            GoodsEnum.SNACK
    }),
    VIRTS("6", "虚拟商品", new GoodsEnum[]{
    }),
    OTHERS("9", "其他", new GoodsEnum[]{

    });
    private final String type;
    private final String desc;
    private final GoodsEnum[] goodsEnums;

    GoodsBigTypeEnum(String type, String desc, GoodsEnum[] goodsEnums) {
        this.type = type;
        this.desc = desc;
        this.goodsEnums = goodsEnums;
    }

    public String getType() {
        return type;
    }


    public String getDesc() {
        return desc;
    }

    public static GoodsBigTypeEnum getGoodsBigTypeEnum(String goodsName) {
        GoodsBigTypeEnum[] goodsBigTypeEnums = GoodsBigTypeEnum.values();
        for (GoodsBigTypeEnum goodsBigTypeEnum : goodsBigTypeEnums) {
            GoodsEnum[] goodsEnums = goodsBigTypeEnum.goodsEnums;
            if (goodsEnums == null) {
                return OTHERS;
            }
            for (GoodsEnum goodsEnum : goodsEnums) {
                if (goodsName.equals(goodsEnum.getDesc())) {
                    return goodsBigTypeEnum;
                }
            }
        }
        return OTHERS;
    }

}
