package sendMeg;
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import org.apache.commons.lang3.StringUtils;

/**
 * @author yanqing.qyq
 * @version $Id: sendMeg.OrgInfoEnum.java, v 0.1 2017-06-24 10:46 yanqing.qyq Exp $$
 */
public enum OrgInfoEnum {

                         //                         BEAR("BEAR",
                         //                              "https://oapi.dingtalk.com/robot/send?access_token=608c04ea6ce57c9c03e8a515605fcbe85f9c85e7b90bdb062299bca5563bfa22",
                         //                              "小黑熊"),
                         //
                         //                         HAMSTER("HAMSTER",
                         //                                 "https://oapi.dingtalk.com/robot/send?access_token=1cc1b0655c4c4a86b8ede1db8a0163eeae37d3ded3ee8b874ee42353ccb08894",
                         //                                 "小仓鼠"),
                         //
                         //                         TIGER("TIGER",
                         //                               "https://oapi.dingtalk.com/robot/send?access_token=95e3f61f6468376ccf90f32ca310bed222bbc2c616a3bc4cd674c86ba36a66a1",
                         //                               "东北虎")

                         DUMIAO("DUMIAO",
                                "https://oapi.dingtalk.com/robot/send?access_token=0bab0e503538402950b7800071ee5da4a80967199e431e6b8c5aa527b260dee5",
                                "读秒"),

                         QUDIAN("QUDIAN",
                                "https://oapi.dingtalk.com/robot/send?access_token=c632d23f6ed101a3c617ca348547384ca54d3cdd7384143cf1cd374bb5e7adc9",
                                "趣店"),

                         BHBK("BHBK",
                              "https://oapi.dingtalk.com/robot/send?access_token=cb580ae80daaa74004ae51c002af35d4d7d7db411f4714624b2a0eed454455d6",
                              "渤海"),

                         CSBK("CSBK",
                              "https://oapi.dingtalk.com/robot/send?access_token=7855e6e3f287a03eb16e26278f4f62e29ad56109ad5687eb222c4ea9733a4ff5",
                              "长沙"),

                         XABK("XABK",
                              "https://oapi.dingtalk.com/robot/send?access_token=d4f980ab5198331f5e6d62d940833978480a28e77154403171dc30d24e259a9e",
                              "西安"),

                         BSBK("BSBK",
                              "https://oapi.dingtalk.com/robot/send?access_token=1e2cf2f0443f2aa3f574b87a8cd5d3ef06d5b9a47c980d4650a6938b9d773f91",
                              "包商"),

                         SHBK("SHBK",
                              "https://oapi.dingtalk.com/robot/send?access_token=2690902855439f489d384d5bb899bf96592bc08a16ef28933bff991b34b03a22",
                              "上海"),

                         MUCFC("MUCFC",
                               "https://oapi.dingtalk.com/robot/send?access_token=a15e1d0c63a569ab2ac1f631a9ec9c1d8048268193d7b1136f3310cb3f54562d",
                               "招联"),

                         XWBK("XWBK",
                              "https://oapi.dingtalk.com/robot/send?access_token=59add69d1243a1c22bb7efcd63b5e2bb090d2ee12904f5ac85dae6a4cd472d0d",
                              "新网"),;

    /**
     * 机构编号
     */
    private String code;

    /**
     * webhook地址
     */
    private String webhook;

    /**
     * 描述
     */
    private String desc;

    OrgInfoEnum(String code, String webhook, String desc) {
        this.code = code;
        this.webhook = webhook;
        this.desc = desc;
    }

    public static OrgInfoEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (OrgInfoEnum item : values()) {
            if (StringUtils.equals(item.getCode(), code)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Getter method for property code.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property code.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property webhook.
     *
     * @return property value of webhook
     */
    public String getWebhook() {
        return webhook;
    }

    /**
     * Setter method for property webhook.
     *
     * @param webhook value to be assigned to property webhook
     */
    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    /**
     * Getter method for property desc.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property desc.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}