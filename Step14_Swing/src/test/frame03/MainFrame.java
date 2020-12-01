package test.frame03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import test.mypac.MyFrame;

public class MainFrame extends JFrame implements ActionListener{
	JTextField inputText;

	//생성자
	public MainFrame(String title) {
		super(title);
		//레이아웃 매니저 설정
		setLayout(new BorderLayout());
		
		//텍스트필드와 버튼 객체를 만들어서
		inputText=new JTextField(10); //참조값을 필드에 저장
		JButton sendBtn=new JButton("전송");
		//패널 객체 생성한 다음
		JPanel topPanel=new JPanel();
		//패널에 텍스트 필드와 버튼을 추가하고
		topPanel.add(inputText);
		topPanel.add(sendBtn);
		//패널의 배경색지정
		topPanel.setBackground(Color.YELLOW);
		
		//패널 째로 프레임의 북쪽에 배치하기
		add(topPanel, BorderLayout.NORTH);
		
		//내가 짠거 선생님 커밋 확인해보기
		ActionListener listener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, inputText.getText());
			}
		};
			
			sendBtn.addActionListener(listener);
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
		String msg=this.inputText.getText();
		//2. 알림창에 출력한다.
		JOptionPane.showMessageDialog(this, msg);		
	}
}
