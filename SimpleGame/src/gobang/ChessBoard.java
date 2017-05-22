package gobang;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

import javax.swing.*;

public class ChessBoard extends JPanel{
	
	// 棋盘线的开始坐标
	final int startX = 60;			
	final int startY = 60;			
	final int rowCount = 15;		// 棋盘的行数
	final int columnCount = 15;		// 棋盘的列数
	final int chessmanSize = 30;	// 棋子尺寸，必须为2的倍数
	
	// 棋盘默认尺寸
	final int defaultWidth = chessmanSize * (rowCount - 1);
	final int defaultHeight = chessmanSize * (columnCount - 1);
	
	ChessMan whiteChessman;
	ChessMan blackChessman;
	
	// 默认出手方为黑方
	public static final int BlackChessman = 0;
	public static final int WhiteChessman = 1;
	int currentChessman = BlackChessman;
	
	int lastIndex_x = -1;
	int lastIndex_y = -1;
	
	private Collection listeners;
	
	public ChessBoard() {
		
		// 监听鼠标移动事件和鼠标事件
		addMouseListener(new MouseHander());
		addMouseMotionListener(new MouseMoveHander());
		
		// 装载黑白棋
		buildAllChessman();
	}
	
	// 设置当前回合方
	public void setCurChessman(int chessman) {
		
		currentChessman = chessman;
	}
	
	// 获取当前回合方
	public int getCurChessman() {
		
		return currentChessman;
	}
	
	// 装载用于下棋的棋子
	public void buildAllChessman() {
		
		whiteChessman = new ChessMan(rowCount, columnCount, Color.WHITE);
		blackChessman = new ChessMan(rowCount, columnCount, Color.BLACK);
	}
	
	// 清除棋盘上的所有棋子
	public void clearAllChessman() {
		
		whiteChessman.clear();
		blackChessman.clear();
		lastIndex_x = -1;
		lastIndex_y = -1;
		currentChessman = BlackChessman; // 黑方为默认出手方
		
		repaint();
	}
	
	// 绘图
	public void paint(Graphics g) {
		
		super.paint(g);
		
		drawChessBoard(g); 	// 画棋盘
		drawChessMan(g);	// 画棋子
		
		ChessMan curChessman = currentChessman == BlackChessman ? blackChessman : whiteChessman;
		ChessMan waitChessman = currentChessman == BlackChessman ? whiteChessman : blackChessman;
		
		updateCurState(curChessman, InfoBoard.STATE.GOING.ordinal());
		updateCurState(waitChessman, InfoBoard.STATE.WAIT.ordinal());
	}
	
	// 画棋盘
	protected void drawChessBoard(Graphics g) {
			
		Color lineColor = Color.BLACK;
		g.setColor(lineColor);
			
		// 添加背景
		String path = "img\\chessboard.jpg";
		ImageIcon img = new ImageIcon(path);
		
		final int panelWidth = getWidth();
		final int panelHeight = getHeight();
						
		g.drawImage(img.getImage(), 0, 0, 
				defaultWidth > panelWidth ? defaultWidth : panelWidth, 
				defaultHeight > panelHeight ? defaultHeight : panelHeight, this);
			
		// 画横线
		for (int i = 0; i < rowCount; ++ i) {
				 
			g.drawLine(startX, startY + i * chessmanSize, 
					startX + (columnCount - 1) * chessmanSize, 
					startY + i * chessmanSize);
		}
			
		// 画竖线
		for (int i = 0; i < columnCount; ++ i) {
			
			g.drawLine(startX + i * chessmanSize, startY, 
					startX + i * chessmanSize, 
					startY + (rowCount - 1) * chessmanSize);
		}
		
		// 画天元和4个小星
		int width = 6;
		int height = 6;
		g.fillRect(startX + 7 * chessmanSize - width / 2, startY + 7 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 3 * chessmanSize - width / 2, startY + 3 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 11 * chessmanSize - width / 2, startY + 3 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 3 * chessmanSize - width / 2, startY + 11 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 11 * chessmanSize - width / 2, startY + 11 * chessmanSize - height / 2, width, height);
	}
		
	// 画棋子
	protected void drawChessMan(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		for (int i = 0; i < rowCount; ++ i) {
			for (int j = 0; j < columnCount; ++ j) {
				if (!whiteChessman.isPositionEmpty(i, j)) {
					Color chessColor = whiteChessman.getColor();
					// 设置颜色
					g2.setColor(chessColor);
					// 消除抗锯齿
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// 绘制圆
					g2.fillOval((int)(startX + chessmanSize * (i - 0.5) + 1),
							(int)(startY + chessmanSize * (j - 0.5) + 1), 
							chessmanSize, chessmanSize);
				}
				else if (!blackChessman.isPositionEmpty(i, j)) {
					Color chessColor = blackChessman.getColor();
					// 设置颜色
					g2.setColor(chessColor);
					// 消除抗锯齿
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// 绘制圆
					g2.fillOval((int)(startX + chessmanSize * (i - 0.5) + 1),
							(int)(startY + chessmanSize * (j - 0.5) + 1), 
							chessmanSize, chessmanSize);
				}
				
				// 绘制矩形
				drawChessmanRect(g2);
			}
		}
	}
	
	protected void drawChessmanRect(Graphics2D g2) {
		
		if (lastIndex_x >= 0 && lastIndex_y >= 0) {
			g2.setColor(Color.RED);
			
			// 上方线条
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1));
			
			// 左方线条
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			
			// 下方线条
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			
			// 右方线条
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
		}
	}
	
	// 判断当前位置是否在棋盘中
	public boolean isInChessBoard(int x, int y) {
		
		return (x > startX - chessmanSize / 2 && y > startY - chessmanSize / 2
        		&& x < startX + chessmanSize * (columnCount - 1) + chessmanSize / 2
        		&& y < startY + chessmanSize * (rowCount - 1) + chessmanSize / 2);
	}
	
	// 添加监听事件 
	public void addUpdateListener(UpdateListener listener) {
		
		if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
	}
	
	// 移除监听事件
	public void removeUpdateListener(UpdateListener listener) {
		
		if (listeners == null)
            return;
        listeners.remove(listener);
	}
	
	
	// 更新下棋方的状态
	protected void updateCurState(ChessMan chessman, int state) {
		
		if (listeners == null)
            return;
		
		UpdateEvent event = new UpdateEvent(this);
		if (chessman == blackChessman)
			event.setBlackChessState(state);
		else if (chessman == whiteChessman)
			event.setWhiteChessState(state);

        notifyStateListener(event);
	}
	
	// 更新获胜者
	protected void updateWinner(int winner) {
		
		if (listeners == null)
            return;
		
		UpdateEvent event = new UpdateEvent(this);
		event.setWinner(winner);
        notifyWinnerListener(event);
	}
	
	// 通知UpdateListener
    private void notifyStateListener(UpdateEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
        	UpdateListener listener = (UpdateListener) iter.next();
            listener.updateStateEvent(event);
        }
    }
	
    // 通知UpdateListener
    private void notifyWinnerListener(UpdateEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
        	UpdateListener listener = (UpdateListener) iter.next();
            listener.updateWinnerEvent(event);
        }
    }
	
	// 鼠标事件适配器
	class MouseHander extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e){   //鼠标完成点击事件
			//e.getButton就会返回点鼠标的那个键，1代表左键
            if(e.getButton() == MouseEvent.BUTTON1){
                int x = e.getX();  //得到鼠标x坐标
                int y = e.getY();  //得到鼠标y坐标
                
                if (isInChessBoard(x, y)) {
                	double _x1 = ( ((x - 1 - startX) / (double)chessmanSize) );
                	BigDecimal _x2 = new BigDecimal(_x1);
                	x = _x2.setScale(0, RoundingMode.HALF_UP).intValue();
                	double _y1 = ( ((y - 1 - startY) / (double)chessmanSize) );
                	BigDecimal _y2 = new BigDecimal(_y1);
                	y = _y2.setScale(0, RoundingMode.HALF_UP).intValue();
                	
                	ChessMan chessman = (currentChessman == BlackChessman) ? blackChessman : whiteChessman;
                	if (!whiteChessman.isPositionEmpty(x, y) 
                			|| !blackChessman.isPositionEmpty(x, y))
                		return ;
                	
                	lastIndex_x = x;
                	lastIndex_y = y;
                	chessman.setChessInPosition(lastIndex_x, lastIndex_y, true);

                	repaint();
                	
                	// 判断是否获胜
                	if (chessman.isWin()) {

                		int win = (chessman == blackChessman) ? BlackChessman : WhiteChessman;
                		updateWinner(win);
                		
                		clearAllChessman();
                		return ;
                	}
                		
                	// 更换回合方
                    currentChessman = (currentChessman == BlackChessman) ? WhiteChessman : BlackChessman;
                }
            }
        }
	}
	
	// 鼠标移动适配器
	class MouseMoveHander extends MouseMotionAdapter {
		
		public void mouseMoved(MouseEvent e) {
			
			int x = e.getX();  //得到鼠标x坐标
            int y = e.getY();  //得到鼠标y坐标
            if (isInChessBoard(x, y)) {
            	setCursor(new Cursor(Cursor.HAND_CURSOR)); // 手状光标
            }
            else {
            	setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认光标
            }
		}
	}
}
