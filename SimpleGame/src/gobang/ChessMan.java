package gobang;

import java.awt.Color;

public class ChessMan {

	private int board[][];
	private Color chessmanColor = Color.BLACK;
	private final int MAX_ROW;
	private final int MAX_COLUMN;
	
	int last_index_x;
	int last_index_y;
	
	int winCount = 0;
	int loseCount = 0;
	
	public ChessMan(int maxRow, int maxColumn) {
			
		board = new int[maxRow][maxColumn];
		MAX_ROW = maxRow;
		MAX_COLUMN = maxColumn;
			
		clear();
	}
	
	public ChessMan(int maxRow, int maxColumn, Color color) {
		
		board = new int[maxRow][maxColumn];
		MAX_ROW = maxRow;
		MAX_COLUMN = maxColumn;
		chessmanColor = color;
		
		clear();
	}
	
	public void setColor(Color color) {
		
		chessmanColor = color;
	}
	
	public Color getColor() {
		
		return chessmanColor;
	}
	
	public void setChessInPosition(int index_x, int index_y, boolean setChess) {
		
		if (index_x < 0 || index_x >= MAX_ROW || index_y < 0 || index_y >= MAX_COLUMN)
			return ;
		
		board[index_x][index_y] = setChess ? 1 : 0;
		last_index_x = index_x;
		last_index_y = index_y;
	}
	
	public boolean isPositionEmpty(int index_x, int index_y) {
		
		if (index_x < 0 || index_x >= MAX_ROW || index_y < 0 || index_y >= MAX_COLUMN)
			return false;
		
		return board[index_x][index_y] == 0;
	}
	
	public boolean isWin() {
		
		int continueCount = 0;
		
		// 查找垂直方向
		for (int i = last_index_y - 4; i != last_index_y + 5; ++ i)
		{
			if (i < 0 || i > MAX_COLUMN - 1)
				continue;
			if (!isPositionEmpty(last_index_x, i))
				++ continueCount;
			else
				continueCount = 0;
			if (5 == continueCount)
				return true;
		}
		
		// 查找水平方向
		continueCount = 0;
		for (int i = last_index_x - 4; i != last_index_x + 5; ++ i)
		{
			if (i < 0 || i > MAX_ROW - 1)
				continue;
			if (!isPositionEmpty(i, last_index_y))
				++ continueCount;
			else
				continueCount = 0;
			if (5 == continueCount)
				return true;
		}
		
		// 查找东北-西南方向
		continueCount = 0;
		for (int i = last_index_x + 4, j = last_index_y - 4; i != last_index_x - 5; i --, j ++) {
			if (i < 0 || j < 0 || i > MAX_ROW - 1 || j > MAX_COLUMN - 1)
				continue;
			if (!isPositionEmpty(i, j))
				++ continueCount;
			else
				continueCount = 0;
			if (5 == continueCount)
				return true;
		}
		
		// 查找西北-东南方向
		continueCount = 0;
		for (int i = last_index_x + 4, j = last_index_y + 4; i != last_index_x - 5; i --, j --) {
			if (i < 0 || j < 0 || i > MAX_ROW - 1 || j > MAX_COLUMN - 1)
				continue;
			if (!isPositionEmpty(i, j))
				++ continueCount;
			else
				continueCount = 0;
			if (5 == continueCount)
				return true;
		}
		
		return false;
	}
	
	// 清除所有棋子
	public void clear() {
		
		for (int i = 0; i < MAX_ROW; ++ i)
			for (int j = 0; j < MAX_COLUMN; ++ j)
				board[i][j] = 0;
	}
}
