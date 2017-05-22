package mycalculator;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends JFrame{
	
	// ��ʾ����
	private JPanel screenPanel = new JPanel();
	private JTextField processScreen = new JTextField("");
	private JTextField resultScreen = new JTextField("0");
	
	// �������������
	private GridBagLayout gbl = new GridBagLayout();
	
	// ��ť����
	private JPanel buttonPanel = new JPanel(gbl);
	private JButton cButton = new JButton("C");
	private JButton multiplyButton = new JButton("*");
	private JButton divideButton = new JButton("/");
	private JButton backButton = new JButton("��");
	private JButton num7Button = new JButton("7"); 
	private JButton num8Button = new JButton("8");
	private JButton num9Button = new JButton("9");
	private JButton subButton = new JButton("-");
	private JButton num4Button = new JButton("4");
	private JButton num5Button = new JButton("5");
	private JButton num6Button = new JButton("6");
	private JButton addButton = new JButton("+");
	private JButton num1Button = new JButton("1");
	private JButton num2Button = new JButton("2");
	private JButton num3Button = new JButton("3");
	private JButton num0Button = new JButton("0");
	private JButton pointButton = new JButton(".");
	private JButton equalButton = new JButton("=");
	
	boolean symbolInputed = false;	// �Ƿ������������������=��
	boolean equalButtonClicked = false; // �Ƿ����˵��ںŰ���
	boolean pointButtonClicked = false; // �Ƿ�����С���㰴��
	int numInputedCount = 0;	// ����ʾ������������ָ���
	int maxInputedCount = 16;	// �û�һ�������������ָ���
	
	BigDecimal opNum1 = new BigDecimal("0");
	BigDecimal opNum2 = new BigDecimal("0");
	String opSymbol = "+"; // Ĭ��Ϊ�Ӻ�

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Calculator myCalculator = new Calculator();
		myCalculator.setVisible(true);
	}
	
	public Calculator() {
		
		init();
		registerActions();
	}

	public void init() {
		
		int frameWidth = 200;	// ���ڿ��
		int frameHeight = 300;	// ���ڸ߶�
		setSize(frameWidth, frameHeight);	
		setTitle("���׼�����");
		setLocationRelativeTo(null);	// ���ó�ʼλ��Ϊ��Ļ����
		setResizable(false);			// �Ŵ�ť������
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// �رպ��������
		
		// ��ʾ������̵��ı���
		processScreen.setHorizontalAlignment(JTextField.RIGHT);
		processScreen.setBorder(new EmptyBorder(3, 3, 0, 3));
		processScreen.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		processScreen.setEditable(false);
		processScreen.setDisabledTextColor(new Color(53, 53, 53));
		processScreen.setEnabled(false);
		
		// ��ʾ���������ı���
		resultScreen.setHorizontalAlignment(JTextField.RIGHT);
		resultScreen.setBorder(new EmptyBorder(0, 3, 3, 3));
		resultScreen.setFont(new Font("����", Font.BOLD, 25));
		resultScreen.setEditable(false);
		resultScreen.setDisabledTextColor(new Color(53, 53, 53));
		resultScreen.setEnabled(false);
		
		// ��ʾ�������
		screenPanel.setLayout(new BorderLayout());
		screenPanel.add("North", processScreen);
		screenPanel.add("Center", resultScreen);
		screenPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				Color.BLACK, Color.WHITE));
		
		// ��Ӱ�ť
		addButton(cButton, 0, 0, 1, 1);
		addButton(divideButton, 0, 1, 1, 1);
		addButton(multiplyButton, 0, 2, 1, 1);
		addButton(backButton, 0, 3, 1, 1);
		addButton(num7Button, 1, 0, 1, 1);
		addButton(num8Button, 1, 1, 1, 1);
		addButton(num9Button, 1, 2, 1, 1);
		addButton(subButton, 1, 3, 1, 1);
		addButton(num4Button, 2, 0, 1, 1);
		addButton(num5Button, 2, 1, 1, 1);
		addButton(num6Button, 2, 2, 1, 1);
		addButton(addButton, 2, 3, 1, 1);
		addButton(num1Button, 3, 0, 1, 1);
		addButton(num2Button, 3, 1, 1, 1);
		addButton(num3Button, 3, 2, 1, 1);
		addButton(equalButton, 3, 3, 1, 2);
		addButton(num0Button, 4, 0, 2, 1);
		addButton(pointButton, 4, 2, 1, 1);
		
		// ����������
		setLayout(new BorderLayout());
		Container container = getContentPane();
		container.add(screenPanel, "North");
		container.add(buttonPanel, "Center");
		
		pack(); // ȡ������ռ�
	}
	
	private void registerActions() {
		
		// ע������¼�
		cButton.addActionListener(new CalcuButtonListener(cButton.getText()));
		multiplyButton.addActionListener(new CalcuButtonListener(multiplyButton.getText()));
		divideButton.addActionListener(new CalcuButtonListener(divideButton.getText()));
		backButton.addActionListener(new CalcuButtonListener(backButton.getText()));
		num7Button.addActionListener(new CalcuButtonListener(num7Button.getText()));
		num8Button.addActionListener(new CalcuButtonListener(num8Button.getText()));
		num9Button.addActionListener(new CalcuButtonListener(num9Button.getText()));
		subButton.addActionListener(new CalcuButtonListener(subButton.getText()));
		num4Button.addActionListener(new CalcuButtonListener(num4Button.getText()));
		num5Button.addActionListener(new CalcuButtonListener(num5Button.getText()));
		num6Button.addActionListener(new CalcuButtonListener(num6Button.getText()));
		addButton.addActionListener(new CalcuButtonListener(addButton.getText()));
		num1Button.addActionListener(new CalcuButtonListener(num1Button.getText()));
		num2Button.addActionListener(new CalcuButtonListener(num2Button.getText()));
		num3Button.addActionListener(new CalcuButtonListener(num3Button.getText()));
		num0Button.addActionListener(new CalcuButtonListener(num0Button.getText()));
		pointButton.addActionListener(new CalcuButtonListener(pointButton.getText()));
		equalButton.addActionListener(new CalcuButtonListener(equalButton.getText()));
	}
	
	protected void addButton(JButton bt, int row, int column, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		gbc.insets = new Insets(4, 4, 4, 4); // ����������
		gbc.fill = GridBagConstraints.BOTH;  // ����������Ԫ��
		
		gbl.setConstraints(bt, gbc);
		buttonPanel.add(bt);
	}
	
	private class CalcuButtonListener implements ActionListener {
		
		private String buttonText;
		
		public CalcuButtonListener(String text) {
			// TODO �Զ����ɵĹ��캯�����
			buttonText = text;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			// �����
			if (buttonText == "C") {
				
				// �����ʾ���
				processScreen.setText("");
				updateResult("0");
				
				// ��ʼ�����������������
				opNum1 = BigDecimal.valueOf(0); 
				opNum2 = BigDecimal.valueOf(0);
				opSymbol = "+";
				
				// ��ʼ����־λ�ͼ�����
				equalButtonClicked = false;
				pointButtonClicked = false;
				symbolInputed = false;
				numInputedCount = 0;	// ��������ּ���������Ϊ0
			}
			// �������
			else if (buttonText == "/" || buttonText == "*" 
					|| buttonText == "-" || buttonText == "+") {
				
				String process = processScreen.getText();
				String result = resultScreen.getText();
				
				if (result.equals("��������Ϊ0") || result.equals("���"))	// ����Ϊ0�����ʱ��������
					return ;
				
				// ��һ��ָ���ǵ��ں�ʱ��ʼ�����в������������
				if (equalButtonClicked) {
					equalButtonClicked = false; // ���ñ�־λ
					opNum1 = BigDecimal.valueOf(0); 
					opNum2 = BigDecimal.valueOf(0);
					opSymbol = "+";
				}
				
				// ��һ��ָ���Ƿ��ţ�����=���ҹ����в�Ϊ��ʱ
				if (symbolInputed && !process.equals("")) {
					
					// ����ĩβ�Ĳ�����
					opSymbol = buttonText;
					process = process.substring(0, process.length() - 1);
					process = process + buttonText;
					processScreen.setText(process);
					return ;
				}
				
				// ���²�����
				opNum1 = opNum2;
				opNum2 = new BigDecimal(result);
				
				// ������������
				String newResult = getCalResult(opNum1, opNum2, opSymbol);
				// �����½����ɽ������ͬʱ���²�����
				if (!result.equals(newResult)) {
					opNum1 = opNum2;
					opNum2 = new BigDecimal(newResult);
				}
				
				// ���������
				opSymbol = buttonText;
				
				// ���¹��̺ͽ����ʾ
				processScreen.setText(process + " " + result + " " + buttonText);
				updateResult(newResult);
				
				// ���±�־λ�ͼ�����
				symbolInputed = true; 	// �������������
				pointButtonClicked = false;	
				numInputedCount = 0;	// ��������ּ���������Ϊ0
			}
			// �˸��
			else if (buttonText == "��") {
				
				String result = resultScreen.getText();
				
				if (result.equals("��������Ϊ0") || result.equals("���"))
					return ;
				
				numInputedCount --;	// ��������ּ���������-1
				
				// ��һ��ָ���������ţ�����=���ҽ����Ϊ0ʱ�����˸�
				if (!symbolInputed && !result.equals("0") ) {
					int length = result.length();
					if (length > 1) {
						if (result.substring(length - 1).equals("."))
							pointButtonClicked = false;
						result = result.substring(0, length - 1);
						updateResult(result);
					}
					else	// ֻ��һλ��ʱ���˸���0
						updateResult("0");
				}
			}
			// ���ں�
			else if (buttonText == "=") {
				
				String result = resultScreen.getText();
				
				if (result.equals("��������Ϊ0") || result.equals("���"))
					return ;
				
				if (equalButtonClicked) {
					opNum1 = new BigDecimal(result);
				}
				else {
					// ���²�����
					opNum1 = opNum2;
					opNum2 = new BigDecimal(result);
				}
				
				// ������
				String calResult = getCalResult(opNum1, opNum2, opSymbol);
				
				// ���¹��̺ͽ����ʾ
				processScreen.setText("");
				updateResult(calResult);
				
				// ���±�־λ�ͼ�����
				symbolInputed = true;		
				equalButtonClicked = true;	
				pointButtonClicked = false;	
				numInputedCount = 0;		// ��������ּ���������Ϊ0
			}
			// ���ּ���С����
			else 
			{
				String result = resultScreen.getText();
				if (result.equals("��������Ϊ0") || result.equals("���"))
					return ;
				
				if (buttonText == "." && pointButtonClicked)
					return ;
				
				// ���������
				numInputedCount ++;	// ��������ּ���������+1	
				if (numInputedCount > maxInputedCount)
				{
					numInputedCount --;
					return ; 
				}
				
				// �з������� �� ���Ϊ0�Ҳ�ΪС����  ʱֱ�ӱ����ʾ���Ϊ���������
				if (symbolInputed || result.equals("0")) {
					if (buttonText.equals(".")) {
						updateResult("0" + buttonText);
					}
					else
						updateResult(buttonText);
				}			
				else
					updateResult(result + buttonText);
				
				// ���ı�־λ
				symbolInputed = false;
				if (buttonText == ".")
					pointButtonClicked = true;
			}
		}
		
		// ������������
		private String getCalResult(BigDecimal num1, BigDecimal num2, String op) {
			
			double calResult = 0.0;
			switch(op) {
			case "+" : calResult = num1.add(num2).doubleValue(); break;
			case "-" : calResult = num1.subtract(num2).doubleValue(); break;
			case "*" : calResult = num1.multiply(num2).doubleValue(); break;
			case "/" : 
				if (!num2.equals(BigDecimal.valueOf(0))) {
					calResult = BigDecimal.valueOf(num1.doubleValue() / num2.doubleValue())
							.doubleValue();
				}
				else {
					return "��������Ϊ0";
				}
				break;
			default: break;
			}
			
			return subZeroAndDot(String.valueOf(calResult));
		}
		
		private String subZeroAndDot(String s) {
			
			// ��������ʽ����ĩβ�����0��.
			if (s.indexOf(".") > 0 && s.indexOf("E") == -1) {
				s = s.replaceAll("0+?$", "");
				s = s.replaceAll("[.]$", "");
			}
			
			return s;
		}
		
		private void updateResult(String text) {
			
			double doubleValue = Double.valueOf(text);

			// �����ֵ�Ƿ����
			if ((doubleValue < Double.MIN_VALUE && symbolInputed && text != "0")
					|| doubleValue > Double.MAX_VALUE) {
				System.out.println("���");
				text = "���";
			}
			else {
				// ���������С
				if (text.length() < 14) {
					resultScreen.setFont(new Font("����", Font.BOLD, 25));
				}
				else if (text.length() < 18) {
					resultScreen.setFont(new Font("����", Font.BOLD, 20));
				}
				else if (text.length() < 20) {		
				//	text = new DecimalFormat("0.###############").format(doubleValue);
					resultScreen.setFont(new Font("����", Font.BOLD, 15));	
				}
				else {
					resultScreen.setFont(new Font("����", Font.BOLD, 13));
				}
			}
			resultScreen.setText(text);
		}
	}
}
