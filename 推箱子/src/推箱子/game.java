package ������;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//���Ƿ�����  ��¼ʱ��һ��Ҫ��������� �˺�Ϊaaa ����Ϊ111


public class game {
	static ServerSocket ss = null;
	static Socket s = null;

	public static void main(String[] args) {
		String u = null;
		String p = null;
		String validationResult = null;
		try {
//�������� 8000 �˿ڼ���
			ss = new ServerSocket(8010);
			System.out.println("���������� 8010 �˿ڼ���......");
			s = ss.accept();
//�����û���������
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String uandp = br.readLine();
//����û���������
			try {
				u = uandp.split("%")[0];
				p = uandp.split("%")[1];
			} catch (Exception ee) {
				ee.printStackTrace();
			}
//�ж��û����������Ƿ���ȷ
			if (u.equals("aaa") && p.equals("111"))
				validationResult = "ok";
			if (!(u.equals("aaa")) && !(p.equals("111")))
				validationResult = "error";
//����ֽ�����͵��ͻ���
			OutputStream os = s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw, true);
			pw.println(validationResult);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("you are wrong");
		}
	}
}
