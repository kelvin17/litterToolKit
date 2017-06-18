
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author yanqing.qyq
 * @version $Id: MyFrame.java, v 0.1 2017-06-18 11:46 yanqing.qyq Exp $$
 */
public class MyFrame extends JFrame {

    private static JTextArea jTextArea = new JTextArea(3, 20);

    public MyFrame() {
        this.setTitle("公告信息-独立jar");
        //点击关闭时,退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //1. 容器
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        //2. 面板
        JPanel jp = new JPanel();

        //3. 元素 - 文本框
        jTextArea.setEnabled(true);
        jp.add(jTextArea);

        //3. 元素 - 按钮
        JButton button = new JButton("发送消息");
        button.addActionListener(new ClickAction());
        jp.add(button);

        contentPane.add(jp);

        this.setLocation(20, 20);
        this.setSize(480, 120);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }

    //Todo 执行完成后,弹框提示
    private static class ClickAction implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            MsgSendRobot robot = new MsgSendRobot();
            try {
                String text = jTextArea.getText();
                System.out.println(text);
                robot.sendMsg(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}