package gobang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import gobang.InfoBoard.STATE;

public class InfoBoard extends JPanel{

	InfoPanel infoPanel = new InfoPanel();
	
	// 玩家1面板
	JLabel topPlayerHead = new JLabel();
	JLabel topPlayerIcon = new JLabel();
	JLabel topPlayerName = new JLabel();
	JLabel topPlayerWinCount = new JLabel();
	JLabel topPlayerLoseCount = new JLabel();
	JLabel topPlayerState = new JLabel();
	
	// 玩家2面板
	JLabel bottomPlayerHead = new JLabel();
	JLabel bottomPlayerIcon = new JLabel();
	JLabel bottomPlayerName = new JLabel();
	JLabel bottomPlayerWinCount = new JLabel();
	JLabel bottomPlayerLoseCount = new JLabel();
	JLabel bottomPlayerState = new JLabel();
	
	int top_WinCount = 0;
	int top_LoseCount = 0;
	String top_Name = null;
	
	int bottom_WinCount = 0;
	int bottom_LoseCount = 0;
	String bottom_Name = null;
	
	// 状态类型
	public enum STATE{
		NONE(0), 	// 无状态
		WAIT(1), 	// 等待对手出手
		GOING(2);	// 思考中
		
		private int value;
		
		private STATE(int value) {
			
			this.value = value;
		}
		
		public int getValue() {
			
			return value;
		}
		
		public static STATE valueOf(int state) {
			
			if (state == 1)
				return WAIT;
			else if (state == 2)
				return GOING;
			
			return NONE;
		}
	}
	
	public InfoBoard() {
	
		topPlayerName.setFont(new Font("微软雅黑", Font.BOLD, 30));
		topPlayerName.setForeground(new Color(255, 255, 255));
		
		topPlayerWinCount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		topPlayerWinCount.setForeground(new Color(255, 255, 255));
		
		topPlayerLoseCount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		topPlayerLoseCount.setForeground(new Color(255, 255, 255));
		
		topPlayerState.setFont(new Font("微软雅黑", Font.BOLD, 15));
		topPlayerState.setForeground(new Color(255, 255, 255));
		
		infoPanel.addTopComponent(topPlayerHead, 0, 0, 4, 4);
		infoPanel.addTopComponent(topPlayerIcon, 4, 0, 1, 1);
		infoPanel.addTopComponent(topPlayerName, 4, 1, 3, 1);
		infoPanel.addTopComponent(topPlayerWinCount, 5, 0, 2, 1);
		infoPanel.addTopComponent(topPlayerLoseCount, 5, 2, 2, 1);
		infoPanel.addTopComponent(topPlayerState, 6, 0, 4, 1);
		
		bottomPlayerName.setFont(new Font("微软雅黑", Font.BOLD, 30));
		bottomPlayerName.setForeground(new Color(255, 255, 255));
		
		bottomPlayerWinCount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		bottomPlayerWinCount.setForeground(new Color(255, 255, 255));
		
		bottomPlayerLoseCount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		bottomPlayerLoseCount.setForeground(new Color(255, 255, 255));
		
		bottomPlayerState.setFont(new Font("微软雅黑", Font.BOLD, 15));
		bottomPlayerState.setForeground(new Color(255, 255, 255));
		
		infoPanel.addBottomComponent(bottomPlayerHead, 0, 0, 4, 4);
		infoPanel.addBottomComponent(bottomPlayerIcon, 4, 0, 1, 1);
		infoPanel.addBottomComponent(bottomPlayerName, 4, 1, 3, 1);
		infoPanel.addBottomComponent(bottomPlayerWinCount, 5, 0, 2, 1);
		infoPanel.addBottomComponent(bottomPlayerLoseCount, 5, 2, 2, 1);
		infoPanel.addBottomComponent(bottomPlayerState, 6, 0, 4, 1);
		
	//	infoPanel.setPreferredSize(new Dimension(200, 550));
		
		add(infoPanel);
	}
	
	public void setPanelDimension(Dimension dimension) {
		
		infoPanel.setPreferredSize(dimension);
		setPreferredSize(dimension);
	}
	
	// 设置玩家1头像
	public void setTopPlayerHeadImage(ImageIcon headImage) {
		
		topPlayerHead.setIcon(headImage);
	}
	
	// 设置玩家1图标
	public void setTopPlayerIcon(ImageIcon icon) {
		
		topPlayerIcon.setIcon(icon);
	}
	
	// 设置玩家1昵称
	public void setTopPlayerName(String playerName) {
		
		if (playerName.isEmpty() || playerName == null)
			return ;
		
		top_Name = playerName;
		topPlayerName.setText(playerName);
	}
	
	// 获取玩家1昵称
	public String getTopPlayerName() {
		
		return top_Name;
	}
	
	// 设置玩家1胜利场次
	public void setTopPlayerWinCount(int count) {
		
		if (count < 0)
			return ;
		
		top_WinCount = count;
		topPlayerWinCount.setText("胜：" + count);
	}
	
	// 获取玩家1胜利场次
	public int getTopPlayerWinCount() {
		
		return top_WinCount;
	}
	
	// 设置玩家1失败场次
	public void setTopPlayerLoseCount(int count) {
		
		if (count < 0)
			return ;
		
		top_LoseCount = count;
		topPlayerLoseCount.setText("负：" + count);
	}
	
	// 获取玩家1失败场次
	public int getTopPlayerLoseCount() {
		
		return top_LoseCount;
	}
	
	// 设置玩家1当前状态
	public void setTopPlayerState(STATE state) {
		
		int playerState = state.getValue();
		
		String text[] = new String[3];
		text[0] = new String(" ");
		text[1] = new String("等待对手出手");
		text[2] = new String("正在思考");
		
		topPlayerState.setText(text[playerState]);
	}
	
	// 设置玩家2头像
	public void setBottomPlayerHeadImage(ImageIcon headImage) {
		
		bottomPlayerHead.setIcon(headImage);
	}
	
	// 设置玩家2图标
	public void setBottomPlayerIcon(ImageIcon icon) {
		
		bottomPlayerIcon.setIcon(icon);
	}
	
	// 设置玩家2昵称
	public void setBottomPlayerName(String playerName) {
		
		if (playerName.isEmpty() || playerName == null)
			return ;
		
		bottom_Name = playerName;
		bottomPlayerName.setText(playerName);
	}
	
	// 获取玩家2昵称
	public String getBottomPlayerName() {
		
		return bottom_Name;
	}
	
	// 设置玩家2胜利场次
	public void setBottomPlayerWinCount(int count) {
		
		if (count < 0)
			return ;
		
		bottom_WinCount = count;
		bottomPlayerWinCount.setText("胜：" + count);
	}
	
	// 获取玩家2胜利场次
	public int getBottomPlayerWinCount() {
		
		return bottom_WinCount;
	}
	
	// 设置玩家2失败场次
	public void setBottomPlayerLoseCount(int count) {
		
		if (count < 0)
			return ;
		
		bottom_LoseCount = count;
		bottomPlayerLoseCount.setText("负：" + count);
	}
	
	// 获取玩家2失败场次
	public int getBottomPlayerLoseCount() {
		
		return bottom_LoseCount;
	}
	
	// 设置玩家2当前状态
	public void setBottomPlayerState(STATE state) {
		
		int playerState = state.getValue();
		
		String text[] = new String[3];
		text[0] = new String("");
		text[1] = new String("等待对手出手");
		text[2] = new String("正在思考");
		
		bottomPlayerState.setText(text[playerState]);
	}
}
