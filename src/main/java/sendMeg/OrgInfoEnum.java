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
  ;

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
