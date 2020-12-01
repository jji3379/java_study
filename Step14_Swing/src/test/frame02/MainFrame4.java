package test.frame02;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame4 extends JFrame implements ActionListener{
	
	//생성자
	public MainFrame4(String title) {
		super(title); //부모생성자에 전달하기
		
		ActionListener a=this;
		JFrame b=this;
		MainFrame4 c=this;
		Object d=this;
		
		//레이아웃 매니저 지정하기
		setLayout(new FlowLayout()); //물 흐르듯이 배치되는 레이아웃
		//버튼
		JButton sendBtn=new JButton("전송");
		JButton updateBtn=new JButton("수정");
		JButton deleteBtn=new JButton("삭제");
		//버튼을 프레임에 배치하기
		add(sendBtn);
		add(updateBtn);
		add(deleteBtn);
		//전송 버튼에 리스너 등록하기
		sendBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		//버튼에 ActionCommand 를 원하는대로 지정할 수 있다.
		sendBtn.setActionCommand("send");
		updateBtn.setActionCommand("update");
		deleteBtn.setActionCommand("delete");
	}
	public static void main(String[] args) {
		MainFrame4 f=new MainFrame4("메인 프레임");
		//위치와 크기 지정
		f.setBounds(100,100,500,300);
		//프레임을 닫으면 프로세스가 종료 되도록 (앱이 종료 되도록)
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//화면상에 실제 보이게 된다.
		f.setVisible(true);
		
	}
	//MainFrame4가 implements ActionListener 했으므로 강제 구현된 메소드
		@Override
		public void actionPerformed(ActionEvent e) {
			//이벤트가 일어난 UI (Button)의  ActionCommand 값을 읽어온다.
			String command=e.getActionCommand();
			//문자열(String)의 내용을 비교할 때는 ==를 사용하지말고
			// .equals() 메소드를 이용해야 한다.
			if(command.equals("send")) {
				JOptionPane.showConfirmDialog(this, "전송합니다.");
			}else if(command.equals("update")) {
				JOptionPane.showConfirmDialog(this, "수정합니다.");
			}else if(command.equals("delete")) {
				JOptionPane.showConfirmDialog(this, "삭제합니다.");
			}
		}
}