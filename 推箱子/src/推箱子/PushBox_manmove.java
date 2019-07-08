package 推箱子;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//人物可动
public class PushBox_manmove extends JPanel implements KeyListener {
	private Image pic[] = null; // 图片
	int initX = 200, initY = 70;
	// map1为第一层，map2为第二层
	private int[][] map1 = { // 第一层地图，即地板层
			{ -1, -1, -1, 1, 0, 1, 0, 1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1 }, { 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, -1, -1, -1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, -1, -1, -1 } };
	private int[][] map2 = { // 第二层地图，建筑物
			{ -1, -1, -1, 2, 2, 2, 2, 2, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2 }, { 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 1, 0, 2, 0, 0, 2, 0, 0, 0, 2 }, { 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2 },
			{ 2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 2 }, { 2, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2 }, { 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2 }, { 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, -1, -1, -1 }, { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, -1 } };
	// 定义一些常量，对应地图的元素
	final byte WALL = 2, BOX = 1, BOXONEND = 3, END = 2, WhiteGRASS = 0, BlackGRASS = 1;
	private int row = 7, column = 7;
	// 加载图片
	Image box = Toolkit.getDefaultToolkit().getImage("images\\box.png");
	Image wall = Toolkit.getDefaultToolkit().getImage("images\\wall.png");
	Image greenBox = Toolkit.getDefaultToolkit().getImage("images\\greenbox.png");
	Image man = Toolkit.getDefaultToolkit().getImage("images\\b1.png");// 人物
	Image background = Toolkit.getDefaultToolkit().getImage("images\\background.jpg");// 背景

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	// 增加计步器
	private int count = 1;
	private Thread threadAnime;
	private int direction = LEFT; // 角色所对方向

	public PushBox_manmove() {

		// 设置焦点
		setFocusable(true);
		this.addKeyListener(this);
		// 控制人物移动的线程
		// 实例化内部线程AnimationThread
		threadAnime = new Thread(new AnimationThread());
		// 启动线程
		threadAnime.start();
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
		// WhiteGRASS = 0, END = 2,BlackGRASS = 1;
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[i].length; j++) {
				// 根据索引值进行坐标转换
				int X = initX + 36 * j - 15 * i;
				int Y = initY + 10 * j + 25 * i;
				if (map1[i][j] == WhiteGRASS) {// 白色空地
					/* 设置paint的颜色 */
					g.setColor(new Color(255, 220, 220, 220));
					this.myDrawRect(g, X, Y);
				} else if (map1[i][j] == BlackGRASS) {// 灰色空地
					g.setColor(new Color(255, 170, 170, 170));
					this.myDrawRect(g, X, Y);
				} else if (map1[i][j] == END) {// 目的地
					g.setColor(new Color(255, 60, 255, 120));
					this.myDrawRect(g, X, Y);
				}
			}
		}
		// 开始绘制第二层,即建筑所在层
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[i].length; j++) {
				// 根据索引值进行坐标转换
				int X = initX + 36 * j - 15 * i;
				int Y = initY + 10 * j + 25 * i;
				if (map2[i][j] == BOX) {// 第二层上有箱子处
					g.drawImage(box, X - 1, Y - 27, this);

				} else if (map2[i][j] == WALL) {// 墙
					g.drawImage(wall, X, Y - 25, this);
				} else if (map2[i][j] == BOXONEND) {// 目的地的绿色箱子
					g.drawImage(greenBox, X - 1, Y - 27, this);
				}
				// 绘制人
				if (i == row && j == column) {
					g.drawImage(man, X - 1, Y - 27, this);
				}
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

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
		if (isWin()) {
			JOptionPane.showMessageDialog(this, "恭喜您通过一关！！！");
		}
	}

	private void moveLeft() {
		// TODO Auto-generated method stub
		// 左一位p1为WALL
		if (map2[row][column - 1] == WALL)
			return;
		// 左一位p1为 BOX
		if (map2[row][column - 1] == BOX || map2[row][column - 1] == BOXONEND) {
			if (map2[row][column - 2] == WALL)
				return;
			if (map2[row][column - 2] == BOX)
				return;
			if (map2[row][column - 2] == BOXONEND)
				return;
			// 左2位p2为 END,GRASS则向上一步
			if (map1[row][column - 2] == END || map1[row][column - 2] == WhiteGRASS
					|| map1[row][column - 2] == BlackGRASS) {
				// 左左一位p2为 END
				if (map1[row][column - 2] == END) // 上上一位p2为 END
					map2[row][column - 2] = BOXONEND;
				if (map1[row][column - 2] == WhiteGRASS // 上上一位p2为GRASS
						|| map1[row][column - 2] == BlackGRASS)
					map2[row][column - 2] = BOX;
				map2[row][column - 1] = -1;// 原来箱子被移掉
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\b1.png");// 向左人物
				direction = LEFT;
				column--;
			}
		} else {
			// 左一位为 GRASS,END,其他情况不用处理
			if (map1[row][column - 1] == WhiteGRASS || map1[row][column - 1] == BlackGRASS
					|| map1[row][column - 1] == END) {
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\b1.png");// 人物
				direction = LEFT;
				column--;
			}
		}
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		// 右一位p1为WALL
		if (map2[row][column + 1] == WALL)
			return;
		// 右一位p1为 BOX
		if (map2[row][column + 1] == BOX || map2[row][column + 1] == BOXONEND) {
			if (map2[row][column + 2] == WALL)
				return;
			if (map2[row][column + 2] == BOX)
				return;
			if (map2[row][column + 2] == BOXONEND)
				return;
			// 右2位p2为 END,GRASS则向上一步
			if (map1[row][column + 2] == END || map1[row][column + 2] == WhiteGRASS
					|| map1[row][column + 2] == BlackGRASS) {
				// 右2位p2为 END
				if (map1[row][column + 2] == END) // 上上一位p2为 END
					map2[row][column + 2] = BOXONEND;
				if (map1[row][column + 2] == WhiteGRASS // 上上一位p2为GRASS
						|| map1[row][column + 2] == BlackGRASS)
					map2[row][column + 2] = BOX;
				map2[row][column + 1] = -1;// 原来箱子被移掉
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\d1.png");// 向右人物
				direction = RIGHT;
				column++;
			}
		} else {
			// 左一位为 GRASS,END,其他情况不用处理
			if (map1[row][column + 1] == WhiteGRASS || map1[row][column + 1] == BlackGRASS
					|| map1[row][column + 1] == END) {
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\d1.png");// 人物
				direction = RIGHT;
				column++;
			}
		}
	}

	private void moveUp() {
		// TODO Auto-generated method stub
		// 上一位p1为WALL
		if (map2[row - 1][column] == WALL)
			return;
		// 上一位p1为 BOX,须考虑P2
		if (map2[row - 1][column] == BOX || map2[row - 1][column] == BOXONEND) {
			if (map2[row - 2][column] == WALL)
				return;
			if (map2[row - 2][column] == BOX)
				return;
			if (map2[row - 2][column] == BOXONEND)
				return;
			// 上2位p2为 END,GRASS则向上一步
			if (map1[row - 2][column] == END || map1[row - 2][column] == WhiteGRASS
					|| map1[row - 2][column] == BlackGRASS) {
				// 上2位p2为 END
				if (map1[row - 2][column] == END) // 上上一位p2为 END
					map2[row - 2][column] = BOXONEND;
				if (map1[row - 2][column] == WhiteGRASS // 上上一位p2为GRASS
						|| map1[row - 2][column] == BlackGRASS)
					map2[row - 2][column] = BOX;
				map2[row - 1][column] = -1;// 原来箱子被移掉
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\c1.png");// 向上人物
				direction = UP;
				row--;
			}
		} else {
			// 上一位为 GRASS,END,不须考虑P2。
			if (map1[row - 1][column] == WhiteGRASS || map1[row - 1][column] == BlackGRASS
					|| map1[row - 1][column] == END) {
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\c1.png");// 人物
				direction = UP;
				row--;
			}
		}
	}

	private void moveDown() {
		// TODO Auto-generated method stub
		// 上一位p1为WALL
		if (map2[row + 1][column] == WALL)
			return;
		// 上一位p1为 BOX,须考虑P2
		if (map2[row + 1][column] == BOX || map2[row + 1][column] == BOXONEND) {
			if (map2[row + 2][column] == WALL)
				return;
			if (map2[row + 2][column] == BOX)
				return;
			if (map2[row + 2][column] == BOXONEND)
				return;
			// 上2位p2为 END,GRASS则向上一步
			if (map1[row + 2][column] == END || map1[row + 2][column] == WhiteGRASS
					|| map1[row + 2][column] == BlackGRASS) {
				// 上2位p2为 END
				if (map1[row + 2][column] == END) // 上上一位p2为 END
					map2[row + 2][column] = BOXONEND;
				if (map1[row + 2][column] == WhiteGRASS // 上上一位p2为GRASS
						|| map1[row + 2][column] == BlackGRASS)
					map2[row + 2][column] = BOX;
				map2[row + 1][column] = -1;// 原来箱子被移掉
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\a1.png");// 向下人物
				direction = DOWN;
				row++;
			}
		} else {
			// 上一位为 GRASS,END,不须考虑P2。
			if (map1[row + 1][column] == WhiteGRASS || map1[row + 1][column] == BlackGRASS
					|| map1[row + 1][column] == END) {
				// 人离开后修改人的坐标
				// man = Toolkit.getDefaultToolkit().getImage("images\\a1.png");//人物
				direction = DOWN;
				row++;
			}
		}
	}

	/**
	 * 判断当前是否已经胜利 只需检查当前界面是否还存在没有变绿的箱子即可
	 */
	public boolean isWin() {
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[i].length; j++) {
				if (map2[i][j] == BOX) {// 有不是绿色的箱子
					return false;
				}
			}
		}
		return true;
	}

	// 内部类，用于处理计步动作。
	// 内部类，用于处理计步动作。
	private class AnimationThread extends Thread {
		public void run() {
			while (true) {
				if (direction == LEFT) {
					String f = "images\\b" + count + ".png";
					man = Toolkit.getDefaultToolkit().getImage(f);// 向左人物
				}
				if (direction == RIGHT) {
					String f = "images\\d" + count + ".png";
					man = Toolkit.getDefaultToolkit().getImage(f);// 向右人物
				}
				if (direction == UP) {
					String f = "images\\c" + count + ".png";
					man = Toolkit.getDefaultToolkit().getImage(f);// 向上人物
				}
				if (direction == DOWN) {
					String f = "images\\a" + count + ".png";
					man = Toolkit.getDefaultToolkit().getImage(f);// 向下人物
				}
				// count 计步
				count++;
				if (count == 4) {
					count = 1;
				}
				// 重绘画面。
				repaint();
				// 每300 毫秒改变一次动作。
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}// 内部类结束
}
