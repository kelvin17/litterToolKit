package sendMeg;
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

import java.util.List;

/**
 * @author yanqing.qyq
 * @version $Id: sendMeg.MsgSendRobot.java, v 0.1 2017-06-18 11:47 yanqing.qyq Exp $$
 */
public class MsgSendRobot {

    public static boolean sendMsg(String msg, List<String> codeList) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();

        //1. 组装要通知的内容 - text是需要输入的内容.
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("{\n\"msgtype\": \"text\", \n\"text\": {\n\"content\": \"");
        textMsg.append(msg);
        textMsg.append("\"\n}\n}");

        //2. 给每个webhook发送信息
        boolean flag = true;
        for (String code : codeList) {
            OrgInfoEnum item = OrgInfoEnum.getByCode(code);
            HttpPost httpPost = new HttpPost(item.getWebhook());
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(textMsg.toString(), "utf-8");
            httpPost.setEntity(se);

            HttpResponse response = httpclient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                System.out.println(response.getStatusLine().getStatusCode());
                flag = false;
            }
        }
        return flag;

    }
}
