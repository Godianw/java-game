package gobang;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gobang.InfoBoard.STATE;

public class MainWindow extends JFrame{
	
	ChessBoard chessBoardPanel = new ChessBoard();
	InfoBoard infoBoardPanel = new InfoBoard();
	
	JMenuBar gameMenubar = new JMenuBar();
	
	JMenu gameMenu = new JMenu("��Ϸ");
	JMenuItem newGame = new JMenuItem("����Ϸ");
	
	JMenu helpMenu = new JMenu("����");
	JMenuItem gameHelp = new JMenuItem("��Ϸ����");
	
	int chessboardWidth = 550;
	int chessboardHeight = 550;
	final int infoPanelWidth = 200;
	final int infoPanelHeight = chessboardHeight;

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		MainWindow mainWin = new MainWindow();
		mainWin.setVisible(true);
	}
	
	public MainWindow() {
		
		init();
	}

	public void init() {
			
		// �������̴�С����ȡʵ�����̴�С
		chessBoardPanel.setSize(new Dimension(chessboardWidth, chessboardHeight));
		chessboardWidth = chessBoardPanel.getWidth();
		chessboardHeight = chessBoardPanel.getHeight();
		chessBoardPanel.addUpdateListener(new JPanelHander());
				
		// ���ڴ�С�����̴�С�ı�
		int frameWidth = chessboardWidth + 200;		// ���ڿ��
		int frameHeight = chessboardHeight + 45;	// ���ڸ߶�
		setSize(frameWidth, frameHeight);
		setLayout(new BorderLayout());
		setTitle("������");
		setLocationRelativeTo(null);	// ���ó�ʼλ��Ϊ��Ļ����
		setResizable(false);			// �Ŵ�ť������
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// �رպ��������
		
		// �������1��Ϣ,ͷ��ͼƬ��СΪ100px*100px��ͼ��ͼƬ��СΪ25px*25px
		infoBoardPanel.setTopPlayerHeadImage(new ImageIcon("img\\zhuangbi.jpg"));
		infoBoardPanel.setTopPlayerIcon(new ImageIcon("img\\blackchessman.png"));
		infoBoardPanel.setTopPlayerName("���1");
		infoBoardPanel.setTopPlayerWinCount(0);
		infoBoardPanel.setTopPlayerLoseCount(0);
		infoBoardPanel.setTopPlayerState(InfoBoard.STATE.NONE);
		
		// �������2��Ϣ,ͷ��ͼƬ��СΪ100px*100px��ͼ��ͼƬ��СΪ25px*25px
		infoBoardPanel.setBottomPlayerHeadImage(new ImageIcon("img\\chaofeng.jpg"));
		infoBoardPanel.setBottomPlayerIcon(new ImageIcon("img\\whitechessman.png"));
		infoBoardPanel.setBottomPlayerName("���2");
		infoBoardPanel.setBottomPlayerWinCount(0);
		infoBoardPanel.setBottomPlayerLoseCount(0);
		infoBoardPanel.setBottomPlayerState(InfoBoard.STATE.NONE);
		
		infoBoardPanel.setPanelDimension(new Dimension(infoPanelWidth, infoPanelHeight));
		
		initMenu();
		
		add("Center", chessBoardPanel);
		add("East", infoBoardPanel);
	//	RestartGame();
	//	pack();
	}
	
	public void initMenu() {
		
		newGame.setAccelerator(KeyStroke.getKeyStroke("F2"));
		newGame.addActionListener(new JMenuHander(1));
		
		gameHelp.setAccelerator(KeyStroke.getKeyStroke("F1"));
		
		gameMenu.add(newGame);
		helpMenu.add(gameHelp);
		
		gameMenubar.add(gameMenu);
		gameMenubar.add(helpMenu);
		
		setJMenuBar(gameMenubar);
	}
	
	// ����Ϸ
	public void RestartGame() {
		
		chessBoardPanel.clearAllChessman();
		
		infoBoardPanel.setTopPlayerState(STATE.NONE);
		infoBoardPanel.setBottomPlayerState(STATE.NONE);
	}
	
	class JMenuHander implements ActionListener {
		
		int menuItemNum = 0;
		
		public JMenuHander(int select) {
			// TODO �Զ����ɵĹ��캯�����
			menuItemNum = select;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			switch (menuItemNum) {
			case 1 : RestartGame(); break;
			case 2 : break;
			default : break;
			}
			
		}
	}
	
	public class JPanelHander implements UpdateListener{

		@Override
		public void updateStateEvent(UpdateEvent e) {
			// TODO �Զ����ɵķ������
			
			int blackChessmanState = e.getBlackChessState();
			int whiteChessmanState = e.getWhiteChessState();
			
			infoBoardPanel.setTopPlayerState(STATE.valueOf(blackChessmanState));
			infoBoardPanel.setBottomPlayerState(STATE.valueOf(whiteChessmanState));
		}
		
		@Override
		public void updateWinnerEvent(UpdateEvent e) {
			// TODO �Զ����ɵķ������
			
			int winner = e.getWinner();
			
			// �ڷ���ʤ
			if (winner == ChessBoard.BlackChessman) {
				
				String winnerName = infoBoardPanel.getTopPlayerName();
				infoBoardPanel.setTopPlayerWinCount(infoBoardPanel.getTopPlayerWinCount() + 1);
				infoBoardPanel.setBottomPlayerLoseCount(infoBoardPanel.getBottomPlayerLoseCount() + 1);
				
				JOptionPane.showMessageDialog(null, "��Ϸ������" + winnerName + "ʤ��", 
						"��Ϸ����", JOptionPane.INFORMATION_MESSAGE);
			}
			// �׷���ʤ
			else if (winner == ChessBoard.WhiteChessman) {
				
				String winnerName = infoBoardPanel.getBottomPlayerName();
				infoBoardPanel.setBottomPlayerWinCount(infoBoardPanel.getBottomPlayerWinCount() + 1);
				infoBoardPanel.setTopPlayerLoseCount(infoBoardPanel.getTopPlayerLoseCount() + 1);
				
				JOptionPane.showMessageDialog(null, "��Ϸ������" + winnerName + "ʤ��", 
						"��Ϸ����", JOptionPane.INFORMATION_MESSAGE);
			}
			// ƽ��
			else {
				
				JOptionPane.showMessageDialog(null, "��Ϸ������˫��ƽ�֣�", 
						"��Ϸ����", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
}
