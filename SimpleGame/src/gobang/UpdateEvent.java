package gobang;

import java.util.*;

public class UpdateEvent extends EventObject{
	
	public static int blackChessmanState = 0;
	public static int whiteChessmanState = 0;
	int chessmanWinner = -1;
	
	public UpdateEvent(Object source) {
		super(source);
		// TODO �Զ����ɵĹ��캯�����
		
	}
	
	public void setBlackChessState(int state) {
		
		blackChessmanState = state;
	}
	
	public int getBlackChessState() {
		
		return blackChessmanState;
	}
	
	public void setWhiteChessState(int state) {
		
		whiteChessmanState = state;
	}
	
	public int getWhiteChessState() {
		
		return whiteChessmanState;
	}
	
	public void setWinner(int winner) {
		
		chessmanWinner = winner;
	}

	public int getWinner() {
		
		return chessmanWinner;
	}
}
