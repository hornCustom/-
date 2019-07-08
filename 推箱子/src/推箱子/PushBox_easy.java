package 推箱子;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class PushBox_easy extends JPanel implements KeyListener {
	private Image pic[] = null; // 图片
	int initX = 200, initY = 70;
	// map1为第一层，map2为第二层
	private int[][] map1 = { // 第一层地图，即地板层
			{ -1, -1, -1, 1, 0, 1, 0, 1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 3, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1 }, { 1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, -1, -1, -1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, -1, -1, -1 } };
	private int[][] map2 = { // 第二层地图，建筑物或人
			{ -1, -1, -1, 2, 2, 2, 2, 2, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2 }, { 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 1, 0, 2, 0, 0, 2, 0, 0, 0, 2 }, { 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2 },
			{ 2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 2 }, { 2, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2 }, { 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2 }, { 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, -1, -1, -1 }, { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, -1 } };
	// 定义一些常量，对应地图的元素
	final byte WALL = 1, BOX = 2, BOXONEND = 3, END = 4, MANDOWN = 5, MANLEFT = 6, MANRIGHT = 7, MANUP = 8, GRASS = 9,
			MANDOWNONEND = 10, MANLEFTONEND = 11, MANRIGHTONEND = 12, MANUPONEND = 13;
	private int row = 7, column = 7; // int manx,many;
	// 加载图片
	Image box = Toolkit.getDefaultToolkit().getImage("images\\box.png");
	Image wall = Toolkit.getDefaultToolkit().getImage("images\\wall.png");
	Image greenBox = Toolkit.getDefaultToolkit().getImage("images\\greenbox.png");
	Image man = Toolkit.getDefaultToolkit().getImage("images\\a1.png");// 人物
	Image background = Toolkit.getDefaultToolkit().getImage("images\\background.jpg");// 人物

	public PushBox_easy() {
		System.out.print("Werewr");
		// 设置焦点
		setFocusable(true);
		this.addKeyListener(this);
	}

	public void myDrawRect(Graphics g, int x, int y) {// 绘制多边形
		Graphics2D g2D = (Graphics2D) g;
		if (g2D == null) {
			return;
		}
		GeneralPath path = new GeneralPath();
		path.moveTo(x + 14, y);
		path.lineTo(x + 53, y + 10);
		path.lineTo(x + 37, y + 37);
		path.lineTo(x - 2, y + 26);
		path.lineTo(x + 14, y);
		g2D.fill(path); // g.draw(myPath);
	}

	// 画游戏界面
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, 800, 800, this);// 画游戏背景
		// 绘制第一层,即地板层
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[i].length; j++) {
				// 根据索引值进行坐标转换
				int X = initX + 36 * j - 15 * i;
				int Y = initY + 10 * j + 25 * i;
				if (map1[i][j] == 0) {// 白色空地
					/* 设置paint的颜色 */
					g.setColor(new Color(255, 220, 220, 220));
					this.myDrawRect(g, X, Y);
				} else if (map1[i][j] == 1) {// 灰色空地
					g.setColor(new Color(255, 170, 170, 170));
					this.myDrawRect(g, X, Y);
				} else if (map1[i][j] == 2) {// 目的地1
					g.setColor(new Color(255, 127, 255, 130));
					this.myDrawRect(g, X, Y);
				} else if (map1[i][j] == 3) {// 目的地2
					g.setColor(new Color(255, 60, 255, 120));
					this.myDrawRect(g, X, Y);
				}
			}
		}
		// 开始绘制第二层,及建筑所在层
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[i].length; j++) {
				// 根据索引值进行坐标转换
				int X = initX + 36 * j - 15 * i;
				int Y = initY + 10 * j + 25 * i;
				if (map2[i][j] == 1) {// 第二层上有箱子处
					g.drawImage(box, X - 1, Y - 27, this);

				} else if (map2[i][j] == 2) {// 墙
					g.drawImage(wall, X, Y - 25, this);
				} else if (map2[i][j] == 3) {// 绿色的箱子
					g.drawImage(greenBox, X - 1, Y - 27, this);
				}
				// 绘制人
				if (i == row && j == column) {
					g.drawImage(man, X - 1, Y - 27, this);
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// 向上
			moveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// 向下
			moveDown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 向左
			moveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 向右
			moveRight();
		}
		repaint();
	}

	private void moveLeft() {
		// TODO Auto-generated method stub
		column--;
	}

	private void moveDown() {
		// TODO Auto-generated method stub
		row++;
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		column++;
	}

	private void moveUp() {
		// TODO Auto-generated method stub
		row--;
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
