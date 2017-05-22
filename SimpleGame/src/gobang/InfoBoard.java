package gobang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import gobang.InfoBoard.STATE;

public class InfoBoard extends JPanel{

	InfoPanel infoPanel = new InfoPanel();
	
	// ���1���
	JLabel topPlayerHead = new JLabel();
	JLabel topPlayerIcon = new JLabel();
	JLabel topPlayerName = new JLabel();
	JLabel topPlayerWinCount = new JLabel();
	JLabel topPlayerLoseCount = new JLabel();
	JLabel topPlayerState = new JLabel();
	
	// ���2���
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
	
	// ״̬����
	public enum STATE{
		NONE(0), 	// ��״̬
		WAIT(1), 	// �ȴ����ֳ���
		GOING(2);	// ˼����
		
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
	
		topPlayerName.setFont(new Font("΢���ź�", Font.BOLD, 30));
		topPlayerName.setForeground(new Color(255, 255, 255));
		
		topPlayerWinCount.setFont(new Font("΢���ź�", Font.BOLD, 15));
		topPlayerWinCount.setForeground(new Color(255, 255, 255));
		
		topPlayerLoseCount.setFont(new Font("΢���ź�", Font.BOLD, 15));
		topPlayerLoseCount.setForeground(new Color(255, 255, 255));
		
		topPlayerState.setFont(new Font("΢���ź�", Font.BOLD, 15));
		topPlayerState.setForeground(new Color(255, 255, 255));
		
		infoPanel.addTopComponent(topPlayerHead, 0, 0, 4, 4);
		infoPanel.addTopComponent(topPlayerIcon, 4, 0, 1, 1);
		infoPanel.addTopComponent(topPlayerName, 4, 1, 3, 1);
		infoPanel.addTopComponent(topPlayerWinCount, 5, 0, 2, 1);
		infoPanel.addTopComponent(topPlayerLoseCount, 5, 2, 2, 1);
		infoPanel.addTopComponent(topPlayerState, 6, 0, 4, 1);
		
		bottomPlayerName.setFont(new Font("΢���ź�", Font.BOLD, 30));
		bottomPlayerName.setForeground(new Color(255, 255, 255));
		
		bottomPlayerWinCount.setFont(new Font("΢���ź�", Font.BOLD, 15));
		bottomPlayerWinCount.setForeground(new Color(255, 255, 255));
		
		bottomPlayerLoseCount.setFont(new Font("΢���ź�", Font.BOLD, 15));
		bottomPlayerLoseCount.setForeground(new Color(255, 255, 255));
		
		bottomPlayerState.setFont(new Font("΢���ź�", Font.BOLD, 15));
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
	
	// �������1ͷ��
	public void setTopPlayerHeadImage(ImageIcon headImage) {
		
		topPlayerHead.setIcon(headImage);
	}
	
	// �������1ͼ��
	public void setTopPlayerIcon(ImageIcon icon) {
		
		topPlayerIcon.setIcon(icon);
	}
	
	// �������1�ǳ�
	public void setTopPlayerName(String playerName) {
		
		if (playerName.isEmpty() || playerName == null)
			return ;
		
		top_Name = playerName;
		topPlayerName.setText(playerName);
	}
	
	// ��ȡ���1�ǳ�
	public String getTopPlayerName() {
		
		return top_Name;
	}
	
	// �������1ʤ������
	public void setTopPlayerWinCount(int count) {
		
		if (count < 0)
			return ;
		
		top_WinCount = count;
		topPlayerWinCount.setText("ʤ��" + count);
	}
	
	// ��ȡ���1ʤ������
	public int getTopPlayerWinCount() {
		
		return top_WinCount;
	}
	
	// �������1ʧ�ܳ���
	public void setTopPlayerLoseCount(int count) {
		
		if (count < 0)
			return ;
		
		top_LoseCount = count;
		topPlayerLoseCount.setText("����" + count);
	}
	
	// ��ȡ���1ʧ�ܳ���
	public int getTopPlayerLoseCount() {
		
		return top_LoseCount;
	}
	
	// �������1��ǰ״̬
	public void setTopPlayerState(STATE state) {
		
		int playerState = state.getValue();
		
		String text[] = new String[3];
		text[0] = new String(" ");
		text[1] = new String("�ȴ����ֳ���");
		text[2] = new String("����˼��");
		
		topPlayerState.setText(text[playerState]);
	}
	
	// �������2ͷ��
	public void setBottomPlayerHeadImage(ImageIcon headImage) {
		
		bottomPlayerHead.setIcon(headImage);
	}
	
	// �������2ͼ��
	public void setBottomPlayerIcon(ImageIcon icon) {
		
		bottomPlayerIcon.setIcon(icon);
	}
	
	// �������2�ǳ�
	public void setBottomPlayerName(String playerName) {
		
		if (playerName.isEmpty() || playerName == null)
			return ;
		
		bottom_Name = playerName;
		bottomPlayerName.setText(playerName);
	}
	
	// ��ȡ���2�ǳ�
	public String getBottomPlayerName() {
		
		return bottom_Name;
	}
	
	// �������2ʤ������
	public void setBottomPlayerWinCount(int count) {
		
		if (count < 0)
			return ;
		
		bottom_WinCount = count;
		bottomPlayerWinCount.setText("ʤ��" + count);
	}
	
	// ��ȡ���2ʤ������
	public int getBottomPlayerWinCount() {
		
		return bottom_WinCount;
	}
	
	// �������2ʧ�ܳ���
	public void setBottomPlayerLoseCount(int count) {
		
		if (count < 0)
			return ;
		
		bottom_LoseCount = count;
		bottomPlayerLoseCount.setText("����" + count);
	}
	
	// ��ȡ���2ʧ�ܳ���
	public int getBottomPlayerLoseCount() {
		
		return bottom_LoseCount;
	}
	
	// �������2��ǰ״̬
	public void setBottomPlayerState(STATE state) {
		
		int playerState = state.getValue();
		
		String text[] = new String[3];
		text[0] = new String("");
		text[1] = new String("�ȴ����ֳ���");
		text[2] = new String("����˼��");
		
		bottomPlayerState.setText(text[playerState]);
	}
}
