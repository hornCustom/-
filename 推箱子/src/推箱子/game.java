package 推箱子;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//这是服务器  登录时候一定要先运行这个 账号为aaa 密码为111


public class game {
	static ServerSocket ss = null;
	static Socket s = null;

	public static void main(String[] args) {
		String u = null;
		String p = null;
		String validationResult = null;
		try {
//服务器在 8000 端口监听
			ss = new ServerSocket(8010);
			System.out.println("服务器正在 8010 端口监听......");
			s = ss.accept();
//接受用户名和密码
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String uandp = br.readLine();
//拆分用户名和密码
			try {
				u = uandp.split("%")[0];
				p = uandp.split("%")[1];
			} catch (Exception ee) {
				ee.printStackTrace();
			}
//判断用户名和密码是否都正确
			if (u.equals("aaa") && p.equals("111"))
				validationResult = "ok";
			if (!(u.equals("aaa")) && !(p.equals("111")))
				validationResult = "error";
//将拆分结果发送到客户端
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
