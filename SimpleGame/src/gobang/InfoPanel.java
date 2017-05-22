package gobang;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	GridLayout mainLayout = new GridLayout(2, 1);
	GridBagLayout topLayout = new GridBagLayout();
	GridBagLayout bottomLayout = new GridBagLayout();
	
	// 面板默认尺寸
	final int defaultWidth = 550;
	final int defaultHeight = 550;
	
	public InfoPanel() {
		
		setLayout(mainLayout);
		
		topPanel.setOpaque(false); //设置透明
		topPanel.setLayout(topLayout);
		
		bottomPanel.setOpaque(false); //设置透明
		bottomPanel.setLayout(bottomLayout);

		add(topPanel);
		add(bottomPanel);
	}
	
	// 设置顶部容器
	public void setTopLayout(GridBagLayout layout) {
		
		topLayout = layout;
	}
	
	// 获取顶部容器
	public GridBagLayout getTopLayout() {
		
		return topLayout;
	}
	
	// 设置底部容器
	public void setBottomLayout(GridBagLayout layout) {
		
		bottomLayout = layout;
	}
	
	// 获取底部容器
	public GridBagLayout getBottomLayout() {
		
		return bottomLayout;
	}
	
	// 为顶部容器装载组件
	public void addTopComponent(JComponent component, int row, int column, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	
		
		gbc.insets = new Insets(4, 4, 4, 4); // 设置网格间距
		gbc.fill = GridBagConstraints.BOTH;  // 充满整个单元格
		
		topLayout.setConstraints(component, gbc);
		topPanel.add(component);
	}
	
	// 为顶部容器移除组件
	public void removeTopComponent(JComponent component) {
		
		topPanel.remove(component);
	}
	
	// 为底部容器装载组件
	public void addBottomComponent(JComponent component, int row, int column, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	
		
		gbc.insets = new Insets(4, 4, 4, 4); // 设置网格间距
		gbc.fill = GridBagConstraints.BOTH;  // 充满整个单元格
		
		bottomLayout.setConstraints(component, gbc);
		bottomPanel.add(component);
	}
	
	// 为底部容器移除组件
	public void removeBottomComponent(JComponent component) {
		
		bottomPanel.remove(component);
	}
	
	// 绘图
	protected void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		
		// 添加背景
		String path = "img\\infoboard.jpg";
		ImageIcon img = new ImageIcon(path);
						
		final int panelWidth = getWidth();
		final int panelHeight = getHeight();
		
		g.drawImage(img.getImage(), 0, 0, 
				defaultWidth > panelWidth ? defaultWidth : panelWidth, 
				defaultHeight > panelHeight ? defaultHeight : panelHeight, this);
	}
}
