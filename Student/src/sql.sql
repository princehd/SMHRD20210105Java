create table student(
	student_num number,
	name varchar2(20),
	age number(3),
	phone varchar2(20),
	email varchar2(50)
)

insert into student values(studentSeq.nextval, '이아현', 20, '010-0000-0000','lee@naver.com')



create sequence studentSeq;





select * from student where name='이아현';


