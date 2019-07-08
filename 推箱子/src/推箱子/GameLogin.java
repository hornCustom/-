package 推箱子;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

//这个是游戏登录窗口运行完服务器后再运行这个页面

@SuppressWarnings("serial")
//游戏前需要登录，定义登陆界面
public class GameLogin extends JFrame implements ActionListener {
	static JTextField userName;
	static JPasswordField passWord;
	private Socket s;

	public GameLogin() {
//建立窗口
		this.setSize(400, 150);
		this.setLocation(110, 250);
//建立画布
		JPanel uppanel = new JPanel();
		JPanel toppanel = new JPanel();
		JPanel downpanel = new JPanel();
//创建上半部分
		JLabel userlabel = new JLabel("用户名");
		JLabel passwordlabel = new JLabel("密码");
		userName = new JTextField();
		passWord = new JPasswordField();
		uppanel.setLayout(new GridLayout(2, 2));
		uppanel.add(userlabel);
		uppanel.add(userName);
		uppanel.add(passwordlabel);
		uppanel.add(passWord);
//创建中间部分
		JLabel welcomelabel = new JLabel("推箱子登录窗口");
		toppanel.add(welcomelabel);
//创建下半部分
		JButton loginbutton = new JButton("登录");
		JButton registerbutton = new JButton("注册");
		JButton cancelbutton = new JButton("注销");
		downpanel.setLayout(new FlowLayout());
		downpanel.add(loginbutton);
		downpanel.add(registerbutton);
		downpanel.add(cancelbutton);
//组合三画布
		this.setLayout(new BorderLayout());
		this.add(uppanel, BorderLayout.CENTER);
		this.add(toppanel, BorderLayout.NORTH);
		this.add(downpanel, BorderLayout.SOUTH);
		this.setVisible(true);
//注册事件
		loginbutton.addActionListener(this);
		registerbutton.addActionListener(this);
		cancelbutton.addActionListener(this);
	}

	public static void main(String[] args) {
		new GameLogin();
		new TimeTest().getTime();
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		try {
			s = new Socket("127.0.0.1", 8010);
		} catch (UnknownHostException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (e.getActionCommand().equals("登录")) {
			try {
// 发送用户名和密码到服务器
				String user = userName.getText();
				String pass = passWord.getText();
				OutputStream os = s.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osw, true);
				pw.println(user + "%" + pass);
//接受服务器发送回来的确认信息
				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String yorn = br.readLine();
//检查用户名和密码是否正确并反馈信息
				if (yorn.equals("ok")) {
//显示游戏界面
		
					//(改)改成显示推箱子界面
					BoxFrame2 w = new BoxFrame2();
					w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					w.setVisible(true); 
					
					//积分窗口
					//BoxFrame2 x = new BoxFrame2();
					//x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//x.setVisible(true); 
					//x.setTitle("积分：");
//关闭登陆界面
					this.setVisible(false);
				} else
					JOptionPane.showMessageDialog(this, "用户名或密码错误");
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("注册")) {
			System.out.println("用户点了注册");
		}
		if (e.getActionCommand().equals("取消")) {
			System.out.println("用户点了取消");
		}
	}
	
}