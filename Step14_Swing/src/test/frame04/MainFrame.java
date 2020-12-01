package test.frame04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import test.mypac.MyFrame;

public class MainFrame extends JFrame implements ActionListener, KeyListener{
	//필드
	JTextField inputText1;
	JTextField inputText2;
	JLabel label1;
	JLabel label2;

	//생성자
	public MainFrame(String title) {
		super(title);
		//레이아웃 매니저 설정
		setLayout(new BorderLayout());
		
		//텍스트필드와 버튼 객체를 만들어서
		JTextField inputText1=new JTextField(10); //참조값을 필드에 저장
		JTextField inputText2=new JTextField(10); //참조값을 필드에 저장
		
		//+,-,*,/
		JButton plusBtn=new JButton("+");
		JButton minusBtn=new JButton("-");
		JButton multiBtn=new JButton("*");
		JButton subBtn=new JButton("/");
		//패널 객체 생성한 다음
		JPanel topPanel=new JPanel();
		//패널에 텍스트 필드와 버튼을 추가하고
		topPanel.add(inputText1);
		topPanel.add(plusBtn);
		topPanel.add(minusBtn);
		topPanel.add(multiBtn);
		topPanel.add(subBtn);
		topPanel.add(inputText2);
		//패널의 배경색지정
		topPanel.setBackground(Color.YELLOW);
		
		//패널 째로 프레임의 북쪽에 배치하기
		add(topPanel, BorderLayout.NORTH);
		
		
		//버튼에 리스너 등록하기
		plusBtn.addActionListener(this);
		minusBtn.addActionListener(this);
		multiBtn.addActionListener(this);
		subBtn.addActionListener(this);
		
		
		//레이블을 만들어서
		label1=new JLabel();
		label2 = new JLabel();
		
		//패널을 추가하기
		topPanel.add(label1);
		topPanel.add(label2);
		//JTextField 에 키보드 리스너 등록하기 
		inputText1.addKeyListener(this);
		inputText2.addKeyListener(this);
	}
	
	//run 했을 때 실행순서가 시작되는 main 메소드
	public static void main(String[] args) {
		MainFrame f=new MainFrame("메인 프레임");
		f.setBounds(100, 100, 500, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//1. JTextField 에 입력한 문자열을 읽어와서
		String msg1=this.inputText1.getText();
		String msg2=this.inputText2.getText();
		double num1=Double.parseDouble(msg1);
		double num2=Double.parseDouble(msg2);

		String command=e.getActionCommand();
		//문자열(String)의 내용을 비교할 때는 ==를 사용하지말고
		// .equals() 메소드를 이용해야 한다.
		if(command.equals("+")) {
			String sum=String.valueOf(num1+num2);
			
		}else if(command.equals("-")) {
			String minus=String.valueOf(num1-num2);
			label2.setText(minus);
		}
		else if(command.equals("*")) {
			String multi=String.valueOf(num1*num2);
			label2.setText(multi);
		}
		else if(command.equals("/")) {
			String sub=String.valueOf(num1/num2);
			label2.setText(sub);
		}
	}
	
	
	//키를 눌렀을 때 호출되는 메소드
	@Override
	public void keyPressed(KeyEvent e) {
		//눌러진 키의 코드값 읽어오기
		int code=e.getKeyCode();
		System.out.println(code);
		//만일 엔터기를 눌렀으면
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
