import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("======SMHRD학생관리프로그램======");

		while(true) {
			System.out.println("1. 학생등록 2. 전체명단조회 3. 특정학생조회 "
					+ "4. 학생정보수정 5. 학생삭제 6. 프로그램종료");
			
			System.out.print("메뉴 선택 >> ");
			int menu = sc.nextInt();
			
			if(menu==1) {
				System.out.println("등록할 학생의 정보를 입력하시오");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("나이 : ");
				int age = sc.nextInt();
				System.out.print("전화번호 : ");
				String phone = sc.next();
				System.out.print("이메일 : ");
				String email = sc.next();
		
				//컴파일오류
				//런타임오류
				Connection conn = null;
				PreparedStatement pst = null;
				
				try {
				//1. jdbc 드라이버 동적로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					//2. 데이터베이스 연결객체(Connection) 생성
					
					String url ="jdbc:oracle:thin:@localhost:1521:xe";
					String user ="hr";
					String password ="hr";
					
					conn = DriverManager.getConnection(url, user, password);
	
					//3. sql 구문 준비 객체(PreparedStatement) 생성 // ? : 바인드변수
					pst = conn.prepareStatement("insert into student values(studentSeq.nextval, ?, ?, ?, ?)");
				
					//4. 바인드 변수를 채운다.
					pst.setString(1,name);
					pst.setInt(2, age);
					pst.setString(3, phone);
					pst.setString(4, email);
					
					//5. sql문을 실행하여 결과 처리
					int cnt = pst.executeUpdate();
					
					if(cnt>0) {
						System.out.println("학생등록성공");
					}else {
						System.out.println("학생등록실패");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						pst.close();
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				
			}else if(menu==2) {
				
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				
				try {
					//1. jdbc 드라이버 동적로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. 데이터 베이스 연결객체(Connection)생성
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student";
					//3. sql 구문 준비 객체(preparedStatement) 생성
					pst = conn.prepareStatement(sql);
					
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int student_num = rs.getInt(1);
						String name = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("학생번호 : "+student_num );
						System.out.println("학생이름 : "+name);
						System.out.println("학생나이 : "+age);
						System.out.println("학생전화번호 : "+phone);
						System.out.println("학생이메일 : "+email);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}else if(menu==3) {
				
				System.out.print("학생 이름 입력 : ");
				String name = sc.next();
				
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				
				try {
					//1. jdbc 드라이버 동적로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. 데이터 베이스 연결객체(Connection)생성
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student where name=?";
					//3. sql 구문 준비 객체(preparedStatement) 생성
					pst = conn.prepareStatement(sql);
					
					pst.setNString(1,name);

					rs = pst.executeQuery();
					//db에 없는 학생 입력할 경우 -> 없는 학생입니다 출력!!! 해보세요!!
					int cnt = 0;
					while(rs.next()) {
						cnt++;
						int student_num = rs.getInt(1);
						String name1 = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("학생번호 : "+student_num );
						System.out.println("학생이름 : "+name1);
						System.out.println("학생나이 : "+age);
						System.out.println("학생전화번호 : "+phone);
						System.out.println("학생이메일 : "+email);
					}
					
					if(cnt==0) {
						System.out.println("없는 학생입니다");
					}
					
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				
			}else if(menu==4) {
				System.out.print("수정할 학생의 이름을 입력하시오 >> ");
				String name = sc.next();
				
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				
				try {
					//1. jdbc 드라이버 동적로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. 데이터 베이스 연결객체(Connection)생성
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student where name=?";
					//3. sql 구문 준비 객체(preparedStatement) 생성
					pst = conn.prepareStatement(sql);
					
					pst.setNString(1,name);

					rs = pst.executeQuery();
					//db에 없는 학생 입력할 경우 -> 없는 학생입니다 출력!!! 해보세요!!
					int cnt = 0;
					while(rs.next()) {
						cnt++;
						int student_num = rs.getInt(1);
						String name1 = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("학생번호 : "+student_num );
						System.out.println("학생이름 : "+name1);
						System.out.println("학생나이 : "+age);
						System.out.println("학생전화번호 : "+phone);
						System.out.println("학생이메일 : "+email);
					}
					
					if(cnt==0) {
						System.out.println("없는 학생입니다");
					}
					
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						pst.close();
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				
				System.out.println("수정할 정보를 입력하시오(1.전화번호 2.이메일) >> ");
				int updatemenu = sc.nextInt();
				
				if(updatemenu==1) {
					System.out.print("학생전화번호 : ");
					String phone = sc.next();
					
					Connection conn2 = null;
					PreparedStatement pst2 = null;
					try {
						//1. jdbc 드라이버 동적로딩
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String user = "hr";
						String password="hr";
						
						//2. 데이터 베이스 연결객체(Connection)생성
						conn2 = DriverManager.getConnection(url, user, password);
						
						String sql = "update student set phone=? where name=?";
						pst2 = conn2.prepareStatement(sql);
						
						pst2.setString(1, phone);
						pst2.setString(2, name);
						
						int cnt = pst2.executeUpdate();
						
						if(cnt>0) {
							System.out.println("학생정보수정성공");
						}else {
							System.out.println("학생정보수정실패");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally{
						try {
						pst2.close();
						conn2.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}else if(updatemenu==2) {

					System.out.print("학생 이메일 : ");
					String email = sc.next();
					
					Connection conn2 = null;
					PreparedStatement pst2 = null;
					try {
						//1. jdbc 드라이버 동적로딩
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String user = "hr";
						String password="hr";
						
						//2. 데이터 베이스 연결객체(Connection)생성
						conn2 = DriverManager.getConnection(url, user, password);
						
						String sql = "update student set email=? where name=?";
						pst2 = conn2.prepareStatement(sql);
						
						pst2.setString(1, email);
						pst2.setString(2, name);
						
						int cnt = pst2.executeUpdate();
						
						if(cnt>0) {
							System.out.println("학생정보수정성공");
						}else {
							System.out.println("학생정보수정실패");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally{
						try {
						pst2.close();
						conn2.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				
			}else if(menu==5) {
				System.out.print("삭제할 학생 이름 입력 >>");
				String name = sc.next(); // 삭제할 학생 이름
				
				Connection conn2=null;
				PreparedStatement pst2=null;
				
				try {
					// 1. DB와 연결하기위한 클래스를 메모리에 로딩(동적로딩)
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					// 2. Connection 객체 생성
					conn2 = DriverManager.getConnection(url, user, password);
					
					// 3. SQL 준비(객체화)
					String sql = "delete from student where name=?";
					pst2 = conn2.prepareStatement(sql);
					pst2.setString(1, name);
					
					// 4. SQL문 실행
					int cnt = pst2.executeUpdate();
					
					if(cnt == 0) {
						System.out.println("삭제 x");
					}else {
						System.out.println("삭제 o");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						pst2.close();
						conn2.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}else if(menu==6) {
				System.out.println("6");
				break;
			}
		}
	}

}
