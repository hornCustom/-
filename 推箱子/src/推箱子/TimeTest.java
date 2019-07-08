package ������;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @file ����ʱ
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
		//����ʱ

	}

	/* ����ʱ����Ҫ����� */
	public void getTime() {
		
		long time = 1 * 60; // �Զ��嵹��ʱʱ��
		long hour = 0;
		long minute = 0;
		long seconds = 0;

		while (time >= 0) {
			hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			jl1.setText(hour + "ʱ");
			jl2.setText(minute + "��");
			jl3.setText(seconds + "��");
			jl4.setText("�������޵�ʱ������ɹؿ�");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
		}
		
		//(��)��ʱ��Ϊ0�رմ���
		while (time<=0) {
			frame.setVisible(false);
		}

	}

	/* ���� ʵ�ֽ���Ŀ��� GUI */
	public TimeTest() {
		frame = new JFrame("����ʱ");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jl4 = new JLabel();

		init();

	}

	/* �����װ�� */
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