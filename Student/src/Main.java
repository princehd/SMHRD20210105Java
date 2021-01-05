import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("======SMHRD�л��������α׷�======");

		while(true) {
			System.out.println("1. �л���� 2. ��ü�����ȸ 3. Ư���л���ȸ "
					+ "4. �л��������� 5. �л����� 6. ���α׷�����");
			
			System.out.print("�޴� ���� >> ");
			int menu = sc.nextInt();
			
			if(menu==1) {
				System.out.println("����� �л��� ������ �Է��Ͻÿ�");
				System.out.print("�̸� : ");
				String name = sc.next();
				System.out.print("���� : ");
				int age = sc.nextInt();
				System.out.print("��ȭ��ȣ : ");
				String phone = sc.next();
				System.out.print("�̸��� : ");
				String email = sc.next();
		
				//�����Ͽ���
				//��Ÿ�ӿ���
				Connection conn = null;
				PreparedStatement pst = null;
				
				try {
				//1. jdbc ����̹� �����ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					//2. �����ͺ��̽� ���ᰴü(Connection) ����
					
					String url ="jdbc:oracle:thin:@localhost:1521:xe";
					String user ="hr";
					String password ="hr";
					
					conn = DriverManager.getConnection(url, user, password);
	
					//3. sql ���� �غ� ��ü(PreparedStatement) ���� // ? : ���ε庯��
					pst = conn.prepareStatement("insert into student values(studentSeq.nextval, ?, ?, ?, ?)");
				
					//4. ���ε� ������ ä���.
					pst.setString(1,name);
					pst.setInt(2, age);
					pst.setString(3, phone);
					pst.setString(4, email);
					
					//5. sql���� �����Ͽ� ��� ó��
					int cnt = pst.executeUpdate();
					
					if(cnt>0) {
						System.out.println("�л���ϼ���");
					}else {
						System.out.println("�л���Ͻ���");
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
					//1. jdbc ����̹� �����ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. ������ ���̽� ���ᰴü(Connection)����
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student";
					//3. sql ���� �غ� ��ü(preparedStatement) ����
					pst = conn.prepareStatement(sql);
					
					rs = pst.executeQuery();
					
					while(rs.next()) {
						int student_num = rs.getInt(1);
						String name = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("�л���ȣ : "+student_num );
						System.out.println("�л��̸� : "+name);
						System.out.println("�л����� : "+age);
						System.out.println("�л���ȭ��ȣ : "+phone);
						System.out.println("�л��̸��� : "+email);
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
				
				System.out.print("�л� �̸� �Է� : ");
				String name = sc.next();
				
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				
				try {
					//1. jdbc ����̹� �����ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. ������ ���̽� ���ᰴü(Connection)����
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student where name=?";
					//3. sql ���� �غ� ��ü(preparedStatement) ����
					pst = conn.prepareStatement(sql);
					
					pst.setNString(1,name);

					rs = pst.executeQuery();
					//db�� ���� �л� �Է��� ��� -> ���� �л��Դϴ� ���!!! �غ�����!!
					int cnt = 0;
					while(rs.next()) {
						cnt++;
						int student_num = rs.getInt(1);
						String name1 = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("�л���ȣ : "+student_num );
						System.out.println("�л��̸� : "+name1);
						System.out.println("�л����� : "+age);
						System.out.println("�л���ȭ��ȣ : "+phone);
						System.out.println("�л��̸��� : "+email);
					}
					
					if(cnt==0) {
						System.out.println("���� �л��Դϴ�");
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
				System.out.print("������ �л��� �̸��� �Է��Ͻÿ� >> ");
				String name = sc.next();
				
				Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				
				try {
					//1. jdbc ����̹� �����ε�
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					//2. ������ ���̽� ���ᰴü(Connection)����
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from student where name=?";
					//3. sql ���� �غ� ��ü(preparedStatement) ����
					pst = conn.prepareStatement(sql);
					
					pst.setNString(1,name);

					rs = pst.executeQuery();
					//db�� ���� �л� �Է��� ��� -> ���� �л��Դϴ� ���!!! �غ�����!!
					int cnt = 0;
					while(rs.next()) {
						cnt++;
						int student_num = rs.getInt(1);
						String name1 = rs.getString(2);
						int age = rs.getInt("age");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						
						System.out.println("===================");
						System.out.println("�л���ȣ : "+student_num );
						System.out.println("�л��̸� : "+name1);
						System.out.println("�л����� : "+age);
						System.out.println("�л���ȭ��ȣ : "+phone);
						System.out.println("�л��̸��� : "+email);
					}
					
					if(cnt==0) {
						System.out.println("���� �л��Դϴ�");
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
				
				
				System.out.println("������ ������ �Է��Ͻÿ�(1.��ȭ��ȣ 2.�̸���) >> ");
				int updatemenu = sc.nextInt();
				
				if(updatemenu==1) {
					System.out.print("�л���ȭ��ȣ : ");
					String phone = sc.next();
					
					Connection conn2 = null;
					PreparedStatement pst2 = null;
					try {
						//1. jdbc ����̹� �����ε�
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String user = "hr";
						String password="hr";
						
						//2. ������ ���̽� ���ᰴü(Connection)����
						conn2 = DriverManager.getConnection(url, user, password);
						
						String sql = "update student set phone=? where name=?";
						pst2 = conn2.prepareStatement(sql);
						
						pst2.setString(1, phone);
						pst2.setString(2, name);
						
						int cnt = pst2.executeUpdate();
						
						if(cnt>0) {
							System.out.println("�л�������������");
						}else {
							System.out.println("�л�������������");
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

					System.out.print("�л� �̸��� : ");
					String email = sc.next();
					
					Connection conn2 = null;
					PreparedStatement pst2 = null;
					try {
						//1. jdbc ����̹� �����ε�
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String user = "hr";
						String password="hr";
						
						//2. ������ ���̽� ���ᰴü(Connection)����
						conn2 = DriverManager.getConnection(url, user, password);
						
						String sql = "update student set email=? where name=?";
						pst2 = conn2.prepareStatement(sql);
						
						pst2.setString(1, email);
						pst2.setString(2, name);
						
						int cnt = pst2.executeUpdate();
						
						if(cnt>0) {
							System.out.println("�л�������������");
						}else {
							System.out.println("�л�������������");
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
				System.out.print("������ �л� �̸� �Է� >>");
				String name = sc.next(); // ������ �л� �̸�
				
				Connection conn2=null;
				PreparedStatement pst2=null;
				
				try {
					// 1. DB�� �����ϱ����� Ŭ������ �޸𸮿� �ε�(�����ε�)
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password="hr";
					
					// 2. Connection ��ü ����
					conn2 = DriverManager.getConnection(url, user, password);
					
					// 3. SQL �غ�(��üȭ)
					String sql = "delete from student where name=?";
					pst2 = conn2.prepareStatement(sql);
					pst2.setString(1, name);
					
					// 4. SQL�� ����
					int cnt = pst2.executeUpdate();
					
					if(cnt == 0) {
						System.out.println("���� x");
					}else {
						System.out.println("���� o");
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
