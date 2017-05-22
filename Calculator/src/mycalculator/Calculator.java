package mycalculator;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends JFrame{
	
	// 显示区域
	private JPanel screenPanel = new JPanel();
	private JTextField processScreen = new JTextField("");
	private JTextField resultScreen = new JTextField("0");
	
	// 创建网格包布局
	private GridBagLayout gbl = new GridBagLayout();
	
	// 按钮区域
	private JPanel buttonPanel = new JPanel(gbl);
	private JButton cButton = new JButton("C");
	private JButton multiplyButton = new JButton("*");
	private JButton divideButton = new JButton("/");
	private JButton backButton = new JButton("←");
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
	
	boolean symbolInputed = false;	// 是否输入了运算符（包括=）
	boolean equalButtonClicked = false; // 是否按下了等于号按键
	boolean pointButtonClicked = false; // 是否按下了小数点按键
	int numInputedCount = 0;	// 在显示区域输入的数字个数
	int maxInputedCount = 16;	// 用户一次最多输入的数字个数
	
	BigDecimal opNum1 = new BigDecimal("0");
	BigDecimal opNum2 = new BigDecimal("0");
	String opSymbol = "+"; // 默认为加号

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Calculator myCalculator = new Calculator();
		myCalculator.setVisible(true);
	}
	
	public Calculator() {
		
		init();
		registerActions();
	}

	public void init() {
		
		int frameWidth = 200;	// 窗口宽度
		int frameHeight = 300;	// 窗口高度
		setSize(frameWidth, frameHeight);	
		setTitle("简易计算器");
		setLocationRelativeTo(null);	// 设置初始位置为屏幕中央
		setResizable(false);			// 放大按钮不可用
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// 关闭后结束程序
		
		// 显示计算过程的文本框
		processScreen.setHorizontalAlignment(JTextField.RIGHT);
		processScreen.setBorder(new EmptyBorder(3, 3, 0, 3));
		processScreen.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		processScreen.setEditable(false);
		processScreen.setDisabledTextColor(new Color(53, 53, 53));
		processScreen.setEnabled(false);
		
		// 显示计算结果的文本框
		resultScreen.setHorizontalAlignment(JTextField.RIGHT);
		resultScreen.setBorder(new EmptyBorder(0, 3, 3, 3));
		resultScreen.setFont(new Font("宋体", Font.BOLD, 25));
		resultScreen.setEditable(false);
		resultScreen.setDisabledTextColor(new Color(53, 53, 53));
		resultScreen.setEnabled(false);
		
		// 显示区域面板
		screenPanel.setLayout(new BorderLayout());
		screenPanel.add("North", processScreen);
		screenPanel.add("Center", resultScreen);
		screenPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				Color.BLACK, Color.WHITE));
		
		// 添加按钮
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
		
		// 添加所有面板
		setLayout(new BorderLayout());
		Container container = getContentPane();
		container.add(screenPanel, "North");
		container.add(buttonPanel, "Center");
		
		pack(); // 取出多余空间
	}
	
	private void registerActions() {
		
		// 注册监听事件
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
		
		gbc.insets = new Insets(4, 4, 4, 4); // 设置网格间距
		gbc.fill = GridBagConstraints.BOTH;  // 充满整个单元格
		
		gbl.setConstraints(bt, gbc);
		buttonPanel.add(bt);
	}
	
	private class CalcuButtonListener implements ActionListener {
		
		private String buttonText;
		
		public CalcuButtonListener(String text) {
			// TODO 自动生成的构造函数存根
			buttonText = text;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			// 清除键
			if (buttonText == "C") {
				
				// 清除显示结果
				processScreen.setText("");
				updateResult("0");
				
				// 初始化操作数和运算符号
				opNum1 = BigDecimal.valueOf(0); 
				opNum2 = BigDecimal.valueOf(0);
				opSymbol = "+";
				
				// 初始化标志位和计数器
				equalButtonClicked = false;
				pointButtonClicked = false;
				symbolInputed = false;
				numInputedCount = 0;	// 输入的数字键个数重置为0
			}
			// 运算符号
			else if (buttonText == "/" || buttonText == "*" 
					|| buttonText == "-" || buttonText == "+") {
				
				String process = processScreen.getText();
				String result = resultScreen.getText();
				
				if (result.equals("除数不能为0") || result.equals("溢出"))	// 除数为0或溢出时不做操作
					return ;
				
				// 上一个指令是等于号时初始化所有操作数和运算符
				if (equalButtonClicked) {
					equalButtonClicked = false; // 重置标志位
					opNum1 = BigDecimal.valueOf(0); 
					opNum2 = BigDecimal.valueOf(0);
					opSymbol = "+";
				}
				
				// 上一个指令是符号（包括=）且过程行不为空时
				if (symbolInputed && !process.equals("")) {
					
					// 更改末尾的操作符
					opSymbol = buttonText;
					process = process.substring(0, process.length() - 1);
					process = process + buttonText;
					processScreen.setText(process);
					return ;
				}
				
				// 更新操作数
				opNum1 = opNum2;
				opNum2 = new BigDecimal(result);
				
				// 进行四则运算
				String newResult = getCalResult(opNum1, opNum2, opSymbol);
				// 产生新结果与旧结果不相同时更新操作数
				if (!result.equals(newResult)) {
					opNum1 = opNum2;
					opNum2 = new BigDecimal(newResult);
				}
				
				// 更新运算符
				opSymbol = buttonText;
				
				// 更新过程和结果显示
				processScreen.setText(process + " " + result + " " + buttonText);
				updateResult(newResult);
				
				// 更新标志位和计数器
				symbolInputed = true; 	// 输入了运算符号
				pointButtonClicked = false;	
				numInputedCount = 0;	// 输入的数字键个数重置为0
			}
			// 退格键
			else if (buttonText == "←") {
				
				String result = resultScreen.getText();
				
				if (result.equals("除数不能为0") || result.equals("溢出"))
					return ;
				
				numInputedCount --;	// 输入的数字键个数计数-1
				
				// 上一个指令不是运算符号（包括=）且结果不为0时才能退格
				if (!symbolInputed && !result.equals("0") ) {
					int length = result.length();
					if (length > 1) {
						if (result.substring(length - 1).equals("."))
							pointButtonClicked = false;
						result = result.substring(0, length - 1);
						updateResult(result);
					}
					else	// 只有一位数时按退格变成0
						updateResult("0");
				}
			}
			// 等于号
			else if (buttonText == "=") {
				
				String result = resultScreen.getText();
				
				if (result.equals("除数不能为0") || result.equals("溢出"))
					return ;
				
				if (equalButtonClicked) {
					opNum1 = new BigDecimal(result);
				}
				else {
					// 更新操作数
					opNum1 = opNum2;
					opNum2 = new BigDecimal(result);
				}
				
				// 计算结果
				String calResult = getCalResult(opNum1, opNum2, opSymbol);
				
				// 更新过程和结果显示
				processScreen.setText("");
				updateResult(calResult);
				
				// 更新标志位和计数器
				symbolInputed = true;		
				equalButtonClicked = true;	
				pointButtonClicked = false;	
				numInputedCount = 0;		// 输入的数字键个数重置为0
			}
			// 数字键和小数点
			else 
			{
				String result = resultScreen.getText();
				if (result.equals("除数不能为0") || result.equals("溢出"))
					return ;
				
				if (buttonText == "." && pointButtonClicked)
					return ;
				
				// 变更计数器
				numInputedCount ++;	// 输入的数字键个数计数+1	
				if (numInputedCount > maxInputedCount)
				{
					numInputedCount --;
					return ; 
				}
				
				// 有符号输入 或 结果为0且不为小数点  时直接变更显示结果为输入的数字
				if (symbolInputed || result.equals("0")) {
					if (buttonText.equals(".")) {
						updateResult("0" + buttonText);
					}
					else
						updateResult(buttonText);
				}			
				else
					updateResult(result + buttonText);
				
				// 更改标志位
				symbolInputed = false;
				if (buttonText == ".")
					pointButtonClicked = true;
			}
		}
		
		// 进行四则运算
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
					return "除数不能为0";
				}
				break;
			default: break;
			}
			
			return subZeroAndDot(String.valueOf(calResult));
		}
		
		private String subZeroAndDot(String s) {
			
			// 用正则表达式消除末尾多余的0和.
			if (s.indexOf(".") > 0 && s.indexOf("E") == -1) {
				s = s.replaceAll("0+?$", "");
				s = s.replaceAll("[.]$", "");
			}
			
			return s;
		}
		
		private void updateResult(String text) {
			
			double doubleValue = Double.valueOf(text);

			// 检查数值是否溢出
			if ((doubleValue < Double.MIN_VALUE && symbolInputed && text != "0")
					|| doubleValue > Double.MAX_VALUE) {
				System.out.println("溢出");
				text = "溢出";
			}
			else {
				// 设置字体大小
				if (text.length() < 14) {
					resultScreen.setFont(new Font("宋体", Font.BOLD, 25));
				}
				else if (text.length() < 18) {
					resultScreen.setFont(new Font("宋体", Font.BOLD, 20));
				}
				else if (text.length() < 20) {		
				//	text = new DecimalFormat("0.###############").format(doubleValue);
					resultScreen.setFont(new Font("宋体", Font.BOLD, 15));	
				}
				else {
					resultScreen.setFont(new Font("宋体", Font.BOLD, 13));
				}
			}
			resultScreen.setText(text);
		}
	}
}
