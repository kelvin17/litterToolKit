package sendMeg;
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 学习Swing中各个类Container,JPanel,JScrollPane,JTextArea,JCheckBox(复选框),JButton
 * 2. 对话框的使用 JOptionPane.showMessageDialog()
 * 3. 监听器模式
 * 4. 所有的监听器实现使用匿名类.因为如果不用匿名类.原来的类里的所有成员都要定义为全局变量.而不能定义为局部变量
 *
 *  @author yanqing.qyq
 * @version $Id: sendMeg.MyFrame.java, v 0.1 2017-06-18 11:46 yanqing.qyq Exp $$
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

        //2. 选择框面板
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(OrgInfoEnum.values().length + 2, 1));
        contentPane.add(optionPanel, BorderLayout.WEST);

        final JCheckBox all = new JCheckBox("全选");
        //defalut Selected
        all.setSelected(true);
        optionPanel.add(all);

        final JCheckBox noAll = new JCheckBox("全不选");
        //defalut No Selected
        noAll.setSelected(false);
        optionPanel.add(noAll);

        final List<JCheckBox> jCheckBoxes = new ArrayList<JCheckBox>();
        for (OrgInfoEnum item : OrgInfoEnum.values()) {
            final JCheckBox itemBox = new JCheckBox(item.getDesc());
            itemBox.setName(item.getCode());
            //defalut Selected
            itemBox.setSelected(true);
            itemBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    if (itemBox.isSelected()) {
                        all.setSelected(false);
                        noAll.setSelected(false);
                    }
                }
            });

            jCheckBoxes.add(itemBox);
            optionPanel.add(itemBox);
        }

        all.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                if (all.isSelected()) {
                    noAll.setSelected(false);
                    for (JCheckBox box : jCheckBoxes) {
                        box.setSelected(true);
                    }
                }
            }
        });

        noAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (noAll.isSelected()) {
                    all.setSelected(false);
                    for (JCheckBox box : jCheckBoxes) {
                        box.setSelected(false);
                    }
                }
            }
        });

        //3.1 文本框面板
        //3.2 元素 - 文本框
        jTextArea.setEnabled(true);

        JScrollPane textPane = new JScrollPane(jTextArea);
        contentPane.add(textPane, BorderLayout.CENTER);

        //4.1 按钮面板
        JPanel buttonPanel = new JPanel();
        //4.2 元素 - 按钮
        JButton button = new JButton("发送");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                MsgSendRobot robot = new MsgSendRobot();
                try {
                    //1. 获取文本
                    String text = jTextArea.getText();
                    System.out.println(text);

                    //2. 获取要通知的code
                    List<String> codeList = new ArrayList<String>();

                    if (all.isSelected()) {
                        for (OrgInfoEnum item : OrgInfoEnum.values()) {
                            codeList.add(item.getCode());
                        }
                    } else if (noAll.isSelected()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "没有选择发送对象");
                        return;
                    } else {
                        for (JCheckBox boxItem : jCheckBoxes) {
                            if (boxItem.isSelected()) {
                                codeList.add(boxItem.getName());
                            }
                        }
                    }

                    //3. 发送消息
                    if (robot.sendMsg(text, codeList)) {
                        JOptionPane.showMessageDialog(MyFrame.this, "发送成功");
                    } else {
                        JOptionPane.showMessageDialog(MyFrame.this, "发送失败,请重试");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MyFrame.this, "发送异常,请重试");
                }
            }
        });
        buttonPanel.add(button);
        contentPane.add(buttonPanel, BorderLayout.EAST);

        this.setLocation(20, 20);
        this.setSize(480, 320);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}