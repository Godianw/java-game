package gobang;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

import javax.swing.*;

public class ChessBoard extends JPanel{
	
	// �����ߵĿ�ʼ����
	final int startX = 60;			
	final int startY = 60;			
	final int rowCount = 15;		// ���̵�����
	final int columnCount = 15;		// ���̵�����
	final int chessmanSize = 30;	// ���ӳߴ磬����Ϊ2�ı���
	
	// ����Ĭ�ϳߴ�
	final int defaultWidth = chessmanSize * (rowCount - 1);
	final int defaultHeight = chessmanSize * (columnCount - 1);
	
	ChessMan whiteChessman;
	ChessMan blackChessman;
	
	// Ĭ�ϳ��ַ�Ϊ�ڷ�
	public static final int BlackChessman = 0;
	public static final int WhiteChessman = 1;
	int currentChessman = BlackChessman;
	
	int lastIndex_x = -1;
	int lastIndex_y = -1;
	
	private Collection listeners;
	
	public ChessBoard() {
		
		// ��������ƶ��¼�������¼�
		addMouseListener(new MouseHander());
		addMouseMotionListener(new MouseMoveHander());
		
		// װ�غڰ���
		buildAllChessman();
	}
	
	// ���õ�ǰ�غϷ�
	public void setCurChessman(int chessman) {
		
		currentChessman = chessman;
	}
	
	// ��ȡ��ǰ�غϷ�
	public int getCurChessman() {
		
		return currentChessman;
	}
	
	// װ���������������
	public void buildAllChessman() {
		
		whiteChessman = new ChessMan(rowCount, columnCount, Color.WHITE);
		blackChessman = new ChessMan(rowCount, columnCount, Color.BLACK);
	}
	
	// ��������ϵ���������
	public void clearAllChessman() {
		
		whiteChessman.clear();
		blackChessman.clear();
		lastIndex_x = -1;
		lastIndex_y = -1;
		currentChessman = BlackChessman; // �ڷ�ΪĬ�ϳ��ַ�
		
		repaint();
	}
	
	// ��ͼ
	public void paint(Graphics g) {
		
		super.paint(g);
		
		drawChessBoard(g); 	// ������
		drawChessMan(g);	// ������
		
		ChessMan curChessman = currentChessman == BlackChessman ? blackChessman : whiteChessman;
		ChessMan waitChessman = currentChessman == BlackChessman ? whiteChessman : blackChessman;
		
		updateCurState(curChessman, InfoBoard.STATE.GOING.ordinal());
		updateCurState(waitChessman, InfoBoard.STATE.WAIT.ordinal());
	}
	
	// ������
	protected void drawChessBoard(Graphics g) {
			
		Color lineColor = Color.BLACK;
		g.setColor(lineColor);
			
		// ��ӱ���
		String path = "img\\chessboard.jpg";
		ImageIcon img = new ImageIcon(path);
		
		final int panelWidth = getWidth();
		final int panelHeight = getHeight();
						
		g.drawImage(img.getImage(), 0, 0, 
				defaultWidth > panelWidth ? defaultWidth : panelWidth, 
				defaultHeight > panelHeight ? defaultHeight : panelHeight, this);
			
		// ������
		for (int i = 0; i < rowCount; ++ i) {
				 
			g.drawLine(startX, startY + i * chessmanSize, 
					startX + (columnCount - 1) * chessmanSize, 
					startY + i * chessmanSize);
		}
			
		// ������
		for (int i = 0; i < columnCount; ++ i) {
			
			g.drawLine(startX + i * chessmanSize, startY, 
					startX + i * chessmanSize, 
					startY + (rowCount - 1) * chessmanSize);
		}
		
		// ����Ԫ��4��С��
		int width = 6;
		int height = 6;
		g.fillRect(startX + 7 * chessmanSize - width / 2, startY + 7 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 3 * chessmanSize - width / 2, startY + 3 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 11 * chessmanSize - width / 2, startY + 3 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 3 * chessmanSize - width / 2, startY + 11 * chessmanSize - height / 2, width, height);
		g.fillRect(startX + 11 * chessmanSize - width / 2, startY + 11 * chessmanSize - height / 2, width, height);
	}
		
	// ������
	protected void drawChessMan(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		for (int i = 0; i < rowCount; ++ i) {
			for (int j = 0; j < columnCount; ++ j) {
				if (!whiteChessman.isPositionEmpty(i, j)) {
					Color chessColor = whiteChessman.getColor();
					// ������ɫ
					g2.setColor(chessColor);
					// ���������
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// ����Բ
					g2.fillOval((int)(startX + chessmanSize * (i - 0.5) + 1),
							(int)(startY + chessmanSize * (j - 0.5) + 1), 
							chessmanSize, chessmanSize);
				}
				else if (!blackChessman.isPositionEmpty(i, j)) {
					Color chessColor = blackChessman.getColor();
					// ������ɫ
					g2.setColor(chessColor);
					// ���������
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// ����Բ
					g2.fillOval((int)(startX + chessmanSize * (i - 0.5) + 1),
							(int)(startY + chessmanSize * (j - 0.5) + 1), 
							chessmanSize, chessmanSize);
				}
				
				// ���ƾ���
				drawChessmanRect(g2);
			}
		}
	}
	
	protected void drawChessmanRect(Graphics2D g2) {
		
		if (lastIndex_x >= 0 && lastIndex_y >= 0) {
			g2.setColor(Color.RED);
			
			// �Ϸ�����
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1));
			
			// ������
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			
			// �·�����
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			g2.drawLine((int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize / 4 * 3), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize), 
					(int)(startX + chessmanSize * (lastIndex_x - 0.5) + 1 + chessmanSize), 
					(int)(startY + chessmanSize * (lastIndex_y - 0.5) + 1 + chessmanSize));
			
			// �ҷ�����
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
	
	// �жϵ�ǰλ���Ƿ���������
	public boolean isInChessBoard(int x, int y) {
		
		return (x > startX - chessmanSize / 2 && y > startY - chessmanSize / 2
        		&& x < startX + chessmanSize * (columnCount - 1) + chessmanSize / 2
        		&& y < startY + chessmanSize * (rowCount - 1) + chessmanSize / 2);
	}
	
	// ��Ӽ����¼� 
	public void addUpdateListener(UpdateListener listener) {
		
		if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
	}
	
	// �Ƴ������¼�
	public void removeUpdateListener(UpdateListener listener) {
		
		if (listeners == null)
            return;
        listeners.remove(listener);
	}
	
	
	// �������巽��״̬
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
	
	// ���»�ʤ��
	protected void updateWinner(int winner) {
		
		if (listeners == null)
            return;
		
		UpdateEvent event = new UpdateEvent(this);
		event.setWinner(winner);
        notifyWinnerListener(event);
	}
	
	// ֪ͨUpdateListener
    private void notifyStateListener(UpdateEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
        	UpdateListener listener = (UpdateListener) iter.next();
            listener.updateStateEvent(event);
        }
    }
	
    // ֪ͨUpdateListener
    private void notifyWinnerListener(UpdateEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
        	UpdateListener listener = (UpdateListener) iter.next();
            listener.updateWinnerEvent(event);
        }
    }
	
	// ����¼�������
	class MouseHander extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e){   //�����ɵ���¼�
			//e.getButton�ͻ᷵�ص������Ǹ�����1�������
            if(e.getButton() == MouseEvent.BUTTON1){
                int x = e.getX();  //�õ����x����
                int y = e.getY();  //�õ����y����
                
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
                	
                	// �ж��Ƿ��ʤ
                	if (chessman.isWin()) {

                		int win = (chessman == blackChessman) ? BlackChessman : WhiteChessman;
                		updateWinner(win);
                		
                		clearAllChessman();
                		return ;
                	}
                		
                	// �����غϷ�
                    currentChessman = (currentChessman == BlackChessman) ? WhiteChessman : BlackChessman;
                }
            }
        }
	}
	
	// ����ƶ�������
	class MouseMoveHander extends MouseMotionAdapter {
		
		public void mouseMoved(MouseEvent e) {
			
			int x = e.getX();  //�õ����x����
            int y = e.getY();  //�õ����y����
            if (isInChessBoard(x, y)) {
            	setCursor(new Cursor(Cursor.HAND_CURSOR)); // ��״���
            }
            else {
            	setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ĭ�Ϲ��
            }
		}
	}
}
