
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanqing.qyq
 * @version $Id: MsgSendRobot.java, v 0.1 2017-06-18 11:47 yanqing.qyq Exp $$
 */
public class MsgSendRobot {

    private final static Map<String, HttpPost> ORG_HOOK_POST_MAP = new HashMap<String, HttpPost>();

    static {
        ORG_HOOK_POST_MAP.put("BEAR", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=608c04ea6ce57c9c03e8a515605fcbe85f9c85e7b90bdb062299bca5563bfa22"));
        ORG_HOOK_POST_MAP.put("HAMSTER", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=1cc1b0655c4c4a86b8ede1db8a0163eeae37d3ded3ee8b874ee42353ccb08894"));
        ORG_HOOK_POST_MAP.put("TIGER", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=b822e5bb5da4143e823dedbe054eb7b2046f0ed8c93d302ab62ed652a143ae39"));

        ORG_HOOK_POST_MAP.put("DUMIAO",
            new HttpPost("https://oapi.dingtalk"
                         + ".com/robot/send?access_token=0bab0e503538402950b7800071ee5da4a80967199e431e6b8c5aa527b260dee5"));
        ORG_HOOK_POST_MAP.put("QUDIAN",
            new HttpPost("https://oapi.dingtalk"
                         + ".com/robot/send?access_token=c632d23f6ed101a3c617ca348547384ca54d3cdd7384143cf1cd374bb5e7adc9"));
        ORG_HOOK_POST_MAP.put("BHBK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=cb580ae80daaa74004ae51c002af35d4d7d7db411f4714624b2a0eed454455d6"));
        ORG_HOOK_POST_MAP.put("CSBK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=7855e6e3f287a03eb16e26278f4f62e29ad56109ad5687eb222c4ea9733a4ff5"));
        ORG_HOOK_POST_MAP.put("XABK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=d4f980ab5198331f5e6d62d940833978480a28e77154403171dc30d24e259a9e"));
        ORG_HOOK_POST_MAP.put("BSBK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=1e2cf2f0443f2aa3f574b87a8cd5d3ef06d5b9a47c980d4650a6938b9d773f91"));
        ORG_HOOK_POST_MAP.put("SHBK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=2690902855439f489d384d5bb899bf96592bc08a16ef28933bff991b34b03a22"));
        ORG_HOOK_POST_MAP.put("MUCFC", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=a15e1d0c63a569ab2ac1f631a9ec9c1d8048268193d7b1136f3310cb3f54562d"));
        ORG_HOOK_POST_MAP.put("XWBK", new HttpPost(
            "https://oapi.dingtalk.com/robot/send?access_token=59add69d1243a1c22bb7efcd63b5e2bb090d2ee12904f5ac85dae6a4cd472d0d"));
    }

    public static void sendMsg(String msg) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();

        //1. 组装要通知的内容 - text是需要输入的内容.
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("{\n\"msgtype\": \"text\", \n\"text\": {\n\"content\": \"");
        textMsg.append(msg);
        textMsg.append("\"\n}\n}");

        //2. 给每个webhook发送信息
        for (String key : ORG_HOOK_POST_MAP.keySet()) {
            HttpPost httppostForBear = ORG_HOOK_POST_MAP.get(key);
            httppostForBear.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(textMsg.toString(), "utf-8");
            httppostForBear.setEntity(se);

            HttpResponse response = httpclient.execute(httppostForBear);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        }

    }
}
