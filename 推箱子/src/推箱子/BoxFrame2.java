﻿package 推箱子;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class  BoxFrame2 extends JFrame {
	public BoxFrame2() {
		// 默认的窗体名称
		setTitle("2.5D推箱子游戏");
		// 获得我们自定义面板[PushBox面板]的实例
		//PushBox_manmove panel = new PushBox_manmove();
		PushBox panel1 = new PushBox();
		Container contentPane = getContentPane();
		contentPane.add(panel1);
		setSize(750, 700);
	}

	public static void main(String[] args) {
	
		
		//BoxFrame2 w = new BoxFrame2();
		// 设定允许窗体关闭操作
		//w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示窗体
		//w.setVisible(true);
		//new TimeTest().getTime();
		//倒计时
	}
	

}
