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
 * 1. ѧϰSwing�и�����Container,JPanel,JScrollPane,JTextArea,JCheckBox(��ѡ��),JButton
 * 2. �Ի����ʹ�� JOptionPane.showMessageDialog()
 * 3. ������ģʽ
 * 4. ���еļ�����ʵ��ʹ��������.��Ϊ�������������.ԭ������������г�Ա��Ҫ����Ϊȫ�ֱ���.�����ܶ���Ϊ�ֲ�����
 *
 *  @author yanqing.qyq
 * @version $Id: sendMeg.MyFrame.java, v 0.1 2017-06-18 11:46 yanqing.qyq Exp $$
 */
public class MyFrame extends JFrame {

    private static JTextArea jTextArea = new JTextArea(3, 20);

    public MyFrame() {
        this.setTitle("������Ϣ-����jar");
        //����ر�ʱ,�˳�����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //1. ����
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        //2. ѡ������
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(OrgInfoEnum.values().length + 2, 1));
        contentPane.add(optionPanel, BorderLayout.WEST);

        final JCheckBox all = new JCheckBox("ȫѡ");
        //defalut Selected
        all.setSelected(true);
        optionPanel.add(all);

        final JCheckBox noAll = new JCheckBox("ȫ��ѡ");
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

        //3.1 �ı������
        //3.2 Ԫ�� - �ı���
        jTextArea.setEnabled(true);

        JScrollPane textPane = new JScrollPane(jTextArea);
        contentPane.add(textPane, BorderLayout.CENTER);

        //4.1 ��ť���
        JPanel buttonPanel = new JPanel();
        //4.2 Ԫ�� - ��ť
        JButton button = new JButton("����");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                MsgSendRobot robot = new MsgSendRobot();
                try {
                    //1. ��ȡ�ı�
                    String text = jTextArea.getText();
                    System.out.println(text);

                    //2. ��ȡҪ֪ͨ��code
                    List<String> codeList = new ArrayList<String>();

                    if (all.isSelected()) {
                        for (OrgInfoEnum item : OrgInfoEnum.values()) {
                            codeList.add(item.getCode());
                        }
                    } else if (noAll.isSelected()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "û��ѡ���Ͷ���");
                        return;
                    } else {
                        for (JCheckBox boxItem : jCheckBoxes) {
                            if (boxItem.isSelected()) {
                                codeList.add(boxItem.getName());
                            }
                        }
                    }

                    //3. ������Ϣ
                    if (robot.sendMsg(text, codeList)) {
                        JOptionPane.showMessageDialog(MyFrame.this, "���ͳɹ�");
                    } else {
                        JOptionPane.showMessageDialog(MyFrame.this, "����ʧ��,������");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MyFrame.this, "�����쳣,������");
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