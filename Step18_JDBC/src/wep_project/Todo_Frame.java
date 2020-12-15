package wep_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import todo_dao.TodoDao;
import todo_dto.TodoDto;

public class Todo_Frame extends JFrame implements ActionListener{
	//필드 
		JTextField text_content;
		DefaultTableModel model;
		JTable table;
		//생성자
		public Todo_Frame(String title) {
			super(title);
			//프레임의 레이아웃 법칙 지정하기
			setLayout(new BorderLayout());
			//상단 패널
			JPanel topPanel=new JPanel();
			topPanel.setBackground(Color.CYAN);
			//패널을 상단에 배치하기 
			add(topPanel, BorderLayout.NORTH);
			//패널에 추가할 UI 객체를 생성해서 
			JLabel label_content=new JLabel("내용");
			JLabel label_regdate=new JLabel("등록일");
			//아래 메소드에서 필요한값을 필드에 저장하기 
			text_content=new JTextField(20);
			
			JButton btn_add=new JButton("추가");
			//패널에 순서대로 추가하기
			//번호 : 시퀀스, 등록일 : sysdate 여서 content만 넣음
			topPanel.add(label_content);
			topPanel.add(text_content);
			topPanel.add(btn_add);
			
			//버튼에 Action command 지정
			btn_add.setActionCommand("add");
			//버튼에 리스너 등록
			btn_add.addActionListener(this);
			
			//할일을 출력할 테이블
			table=new JTable();
			//칼럼명을 String[] 에 순서대로 준비하기
			String[] colNames= {"번호","내용","등록일"};
			//테이블에 연결할 기본 모델 객체
			model=new DefaultTableModel(colNames, 0){
				@Override
				public boolean isCellEditable(int row, int column) {
					//모두 수정 불가 하도록 설정
					return false;
				}
			};
			//모델을 테이블에 연결하기
			table.setModel(model);
			//테이블의 내용이 scroll 될수 있도록 스크롤 페널로 감싼다.
			JScrollPane scPane=new JScrollPane(table);
			//스크롤 페널을 프레임의 중앙에 배치하기
			add(scPane, BorderLayout.CENTER);
			//할 일을 테이블에 출력하기
			printTodo();
		
			//삭제 버튼
			JButton btn_delete=new JButton("삭제");
			btn_delete.addActionListener(this);
			btn_delete.setActionCommand("delete");
			
			//삭제 버튼을 상단 패널에 추가
			topPanel.add(btn_delete);
			//할 일을 주기적으로 업데이트 해주는 스레드 시작 시키기
			new UpdateThread().start();
		}
		
		//할 일 목록을 테이블에 출력하는 메소드
		public void printTodo() {
			
			//할일 목록 불러오기
			TodoDao dao=new TodoDao();
			List<TodoDto> list=dao.selectAll();
			//기존에 출력된 내용 초기화
			model.setRowCount(0); // 0 개의 row 로 강제로 초기화 하고 

			for(TodoDto tmp:list) {
				// {1, "김구라", "노량진" }
				//Object[] row= {tmp.getNum(), tmp.getName(), tmp.getAddr()};
				Vector<Object> row=new Vector<>();
				row.add(tmp.getNum());
				row.add(tmp.getContent());
				row.add(tmp.getRegdate());
		
				model.addRow(row);
			}
		}//printTodo END
		
		public void actionPerformed(ActionEvent e) {
			//눌러진 버튼의 action command 를 읽어온다.
			String command=e.getActionCommand();
			if(command.equals("add")) { //추가 버튼을 눌렀을때
				addTodo();
			}else if(command.equals("delete")) {//삭제 버튼을 눌렀을 때
				deleteTodo();
			}
		}//actionPerformed END
		
		//메인 메소드
		public static void main(String[] args) {
			Todo_Frame f=new Todo_Frame("할일 목록 관리");
			f.setBounds(100, 100, 800, 500);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
		}

		 
			//할일 정보를 삭제하는 메소드
			public void deleteTodo() {
				//선택된 row 의 인덱스를 읽어온다.
				int selectedIndex=table.getSelectedRow();
				if(selectedIndex ==-1) {
					JOptionPane.showMessageDialog(this, "삭제할 row를 선택해라");
					return; //메소드를 여기서 끝내라
				}else {
					//선택한 row 의 0 번 칼럼의 값(번호)을 읽어와서  int 로 casting 하기
					int num=(int)table.getValueAt(selectedIndex, 0);
					//삭제하기전에 한번 확인하기
					int result=JOptionPane.showConfirmDialog(this, num+" 번 할일을 지우겠습니까?");
					if(result==JOptionPane.YES_OPTION) {					
						//TodoDao 객체를 이용해서 삭제하기
						new TodoDao().delete(num);
						//UI 업데이트 (목록 다시 출력하)
						printTodo();
					}
			}
		}//deleteTodo END
			
		//할일 정보를 추가하는 메소드 
		public void addTodo() {
			
			//1. 입력한 내용을읽어와서
			String content=text_content.getText();
			//2. TodoDto 객체에 담고
			TodoDto dto=new TodoDto();
			dto.setContent(content);
			//3. TodoDao 객체를 이용해서 DB 에 저장
			TodoDao dao=new TodoDao();
			//작업의 성공여부를 isSuccess 에 담기 
			boolean isSuccess=dao.insert(dto);
			//실제 저장되었는지 확인해 보세요.
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, content+" 추가성공");
				//테이블에 다시 목록 불러오기
				printTodo();
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패!");
			}
		}
		//화면을 주기적으로 update 해주는 스레드
		class UpdateThread extends Thread{
			@Override
			public void run() {
				// 바깥에 싸고 있는 클래스의 멤버 메소드 printTodo() 메소드를
				// 5초마다 한번씩 주기적으로 호출하기
				while(true) { //무한루프
					try {
						//5초 잠자다가
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					};
					//화면 업데이트
					printTodo();
				}
			}
		}
}
