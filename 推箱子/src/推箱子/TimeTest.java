package 推箱子;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @file 倒计时
 * @author qzw
 *
 */
public class TimeTest {

	private JFrame frame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	

	public static void main(String[] args) {

		//new TimeTest().getTime();
		//倒计时

	}

	/* 倒计时的主要代码块 */
	public void getTime() {
		
		long time = 1 * 60; // 自定义倒计时时间
		long hour = 0;
		long minute = 0;
		long seconds = 0;

		while (time >= 0) {
			hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			jl1.setText(hour + "时");
			jl2.setText(minute + "分");
			jl3.setText(seconds + "秒");
			jl4.setText("请在有限的时间内完成关卡");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
		}
		
		//(改)当时间为0关闭窗口
		while (time<=0) {
			frame.setVisible(false);
		}

	}

	/* 构造 实现界面的开发 GUI */
	public TimeTest() {
		frame = new JFrame("倒计时");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jl4 = new JLabel();

		init();

	}

	/* 组件的装配 */
	private void init() {
		JPanel jp = new JPanel();
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);

		frame.add(jp);
		frame.setVisible(true);
		frame.setLocation(300, 400);
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}