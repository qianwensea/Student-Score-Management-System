package stu_info;
/**
 *@classname: Student
 *@author: shijinhai
 *@time: 2019年10月29日
*/
public class Student {
	
		String Sno;
		int Score;
		public Student (String sno,int sco)
		{
			Sno = sno;
			Score = sco;
		}
		public String getSno() {
			return Sno;
		}
		public int getScore() {
			return Score;
		}
		public void updateSco(int s) {
			Score = s;
		}
}
