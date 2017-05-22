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
	
	// ���Ĭ�ϳߴ�
	final int defaultWidth = 550;
	final int defaultHeight = 550;
	
	public InfoPanel() {
		
		setLayout(mainLayout);
		
		topPanel.setOpaque(false); //����͸��
		topPanel.setLayout(topLayout);
		
		bottomPanel.setOpaque(false); //����͸��
		bottomPanel.setLayout(bottomLayout);

		add(topPanel);
		add(bottomPanel);
	}
	
	// ���ö�������
	public void setTopLayout(GridBagLayout layout) {
		
		topLayout = layout;
	}
	
	// ��ȡ��������
	public GridBagLayout getTopLayout() {
		
		return topLayout;
	}
	
	// ���õײ�����
	public void setBottomLayout(GridBagLayout layout) {
		
		bottomLayout = layout;
	}
	
	// ��ȡ�ײ�����
	public GridBagLayout getBottomLayout() {
		
		return bottomLayout;
	}
	
	// Ϊ��������װ�����
	public void addTopComponent(JComponent component, int row, int column, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	
		
		gbc.insets = new Insets(4, 4, 4, 4); // ����������
		gbc.fill = GridBagConstraints.BOTH;  // ����������Ԫ��
		
		topLayout.setConstraints(component, gbc);
		topPanel.add(component);
	}
	
	// Ϊ���������Ƴ����
	public void removeTopComponent(JComponent component) {
		
		topPanel.remove(component);
	}
	
	// Ϊ�ײ�����װ�����
	public void addBottomComponent(JComponent component, int row, int column, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	
		
		gbc.insets = new Insets(4, 4, 4, 4); // ����������
		gbc.fill = GridBagConstraints.BOTH;  // ����������Ԫ��
		
		bottomLayout.setConstraints(component, gbc);
		bottomPanel.add(component);
	}
	
	// Ϊ�ײ������Ƴ����
	public void removeBottomComponent(JComponent component) {
		
		bottomPanel.remove(component);
	}
	
	// ��ͼ
	protected void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		
		// ��ӱ���
		String path = "img\\infoboard.jpg";
		ImageIcon img = new ImageIcon(path);
						
		final int panelWidth = getWidth();
		final int panelHeight = getHeight();
		
		g.drawImage(img.getImage(), 0, 0, 
				defaultWidth > panelWidth ? defaultWidth : panelWidth, 
				defaultHeight > panelHeight ? defaultHeight : panelHeight, this);
	}
}
