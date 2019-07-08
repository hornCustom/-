package ������;

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

//�������Ϸ��¼��������������������������ҳ��

@SuppressWarnings("serial")
//��Ϸǰ��Ҫ��¼�������½����
public class GameLogin extends JFrame implements ActionListener {
	static JTextField userName;
	static JPasswordField passWord;
	private Socket s;

	public GameLogin() {
//��������
		this.setSize(400, 150);
		this.setLocation(110, 250);
//��������
		JPanel uppanel = new JPanel();
		JPanel toppanel = new JPanel();
		JPanel downpanel = new JPanel();
//�����ϰ벿��
		JLabel userlabel = new JLabel("�û���");
		JLabel passwordlabel = new JLabel("����");
		userName = new JTextField();
		passWord = new JPasswordField();
		uppanel.setLayout(new GridLayout(2, 2));
		uppanel.add(userlabel);
		uppanel.add(userName);
		uppanel.add(passwordlabel);
		uppanel.add(passWord);
//�����м䲿��
		JLabel welcomelabel = new JLabel("�����ӵ�¼����");
		toppanel.add(welcomelabel);
//�����°벿��
		JButton loginbutton = new JButton("��¼");
		JButton registerbutton = new JButton("ע��");
		JButton cancelbutton = new JButton("ע��");
		downpanel.setLayout(new FlowLayout());
		downpanel.add(loginbutton);
		downpanel.add(registerbutton);
		downpanel.add(cancelbutton);
//���������
		this.setLayout(new BorderLayout());
		this.add(uppanel, BorderLayout.CENTER);
		this.add(toppanel, BorderLayout.NORTH);
		this.add(downpanel, BorderLayout.SOUTH);
		this.setVisible(true);
//ע���¼�
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
		if (e.getActionCommand().equals("��¼")) {
			try {
// �����û��������뵽������
				String user = userName.getText();
				String pass = passWord.getText();
				OutputStream os = s.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osw, true);
				pw.println(user + "%" + pass);
//���ܷ��������ͻ�����ȷ����Ϣ
				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String yorn = br.readLine();
//����û����������Ƿ���ȷ��������Ϣ
				if (yorn.equals("ok")) {
//��ʾ��Ϸ����
		
					//(��)�ĳ���ʾ�����ӽ���
					BoxFrame2 w = new BoxFrame2();
					w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					w.setVisible(true); 
					
					//���ִ���
					//BoxFrame2 x = new BoxFrame2();
					//x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//x.setVisible(true); 
					//x.setTitle("���֣�");
//�رյ�½����
					this.setVisible(false);
				} else
					JOptionPane.showMessageDialog(this, "�û������������");
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("ע��")) {
			System.out.println("�û�����ע��");
		}
		if (e.getActionCommand().equals("ȡ��")) {
			System.out.println("�û�����ȡ��");
		}
	}
	
}