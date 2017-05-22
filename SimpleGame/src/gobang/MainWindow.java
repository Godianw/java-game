package gobang;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gobang.InfoBoard.STATE;

public class MainWindow extends JFrame{
	
	ChessBoard chessBoardPanel = new ChessBoard();
	InfoBoard infoBoardPanel = new InfoBoard();
	
	JMenuBar gameMenubar = new JMenuBar();
	
	JMenu gameMenu = new JMenu("游戏");
	JMenuItem newGame = new JMenuItem("新游戏");
	
	JMenu helpMenu = new JMenu("帮助");
	JMenuItem gameHelp = new JMenuItem("游戏帮助");
	
	int chessboardWidth = 550;
	int chessboardHeight = 550;
	final int infoPanelWidth = 200;
	final int infoPanelHeight = chessboardHeight;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MainWindow mainWin = new MainWindow();
		mainWin.setVisible(true);
	}
	
	public MainWindow() {
		
		init();
	}

	public void init() {
			
		// 设置棋盘大小并获取实际棋盘大小
		chessBoardPanel.setSize(new Dimension(chessboardWidth, chessboardHeight));
		chessboardWidth = chessBoardPanel.getWidth();
		chessboardHeight = chessBoardPanel.getHeight();
		chessBoardPanel.addUpdateListener(new JPanelHander());
				
		// 窗口大小随棋盘大小改变
		int frameWidth = chessboardWidth + 200;		// 窗口宽度
		int frameHeight = chessboardHeight + 45;	// 窗口高度
		setSize(frameWidth, frameHeight);
		setLayout(new BorderLayout());
		setTitle("五子棋");
		setLocationRelativeTo(null);	// 设置初始位置为屏幕中央
		setResizable(false);			// 放大按钮不可用
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// 关闭后结束程序
		
		// 设置玩家1信息,头像图片大小为100px*100px，图标图片大小为25px*25px
		infoBoardPanel.setTopPlayerHeadImage(new ImageIcon("img\\zhuangbi.jpg"));
		infoBoardPanel.setTopPlayerIcon(new ImageIcon("img\\blackchessman.png"));
		infoBoardPanel.setTopPlayerName("玩家1");
		infoBoardPanel.setTopPlayerWinCount(0);
		infoBoardPanel.setTopPlayerLoseCount(0);
		infoBoardPanel.setTopPlayerState(InfoBoard.STATE.NONE);
		
		// 设置玩家2信息,头像图片大小为100px*100px，图标图片大小为25px*25px
		infoBoardPanel.setBottomPlayerHeadImage(new ImageIcon("img\\chaofeng.jpg"));
		infoBoardPanel.setBottomPlayerIcon(new ImageIcon("img\\whitechessman.png"));
		infoBoardPanel.setBottomPlayerName("玩家2");
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
	
	// 新游戏
	public void RestartGame() {
		
		chessBoardPanel.clearAllChessman();
		
		infoBoardPanel.setTopPlayerState(STATE.NONE);
		infoBoardPanel.setBottomPlayerState(STATE.NONE);
	}
	
	class JMenuHander implements ActionListener {
		
		int menuItemNum = 0;
		
		public JMenuHander(int select) {
			// TODO 自动生成的构造函数存根
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
			// TODO 自动生成的方法存根
			
			int blackChessmanState = e.getBlackChessState();
			int whiteChessmanState = e.getWhiteChessState();
			
			infoBoardPanel.setTopPlayerState(STATE.valueOf(blackChessmanState));
			infoBoardPanel.setBottomPlayerState(STATE.valueOf(whiteChessmanState));
		}
		
		@Override
		public void updateWinnerEvent(UpdateEvent e) {
			// TODO 自动生成的方法存根
			
			int winner = e.getWinner();
			
			// 黑方获胜
			if (winner == ChessBoard.BlackChessman) {
				
				String winnerName = infoBoardPanel.getTopPlayerName();
				infoBoardPanel.setTopPlayerWinCount(infoBoardPanel.getTopPlayerWinCount() + 1);
				infoBoardPanel.setBottomPlayerLoseCount(infoBoardPanel.getBottomPlayerLoseCount() + 1);
				
				JOptionPane.showMessageDialog(null, "游戏结束，" + winnerName + "胜利", 
						"游戏结束", JOptionPane.INFORMATION_MESSAGE);
			}
			// 白方获胜
			else if (winner == ChessBoard.WhiteChessman) {
				
				String winnerName = infoBoardPanel.getBottomPlayerName();
				infoBoardPanel.setBottomPlayerWinCount(infoBoardPanel.getBottomPlayerWinCount() + 1);
				infoBoardPanel.setTopPlayerLoseCount(infoBoardPanel.getTopPlayerLoseCount() + 1);
				
				JOptionPane.showMessageDialog(null, "游戏结束，" + winnerName + "胜利", 
						"游戏结束", JOptionPane.INFORMATION_MESSAGE);
			}
			// 平局
			else {
				
				JOptionPane.showMessageDialog(null, "游戏结束，双方平局，", 
						"游戏结束", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
}
