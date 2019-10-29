package stu_info;

import java.util.Comparator;

/**
 *@classname: SortByScore
 *@author: shijinhai
 *@time: 2019年10月30日
*/
class SortByScore implements Comparator {
	 public int compare(Object o1, Object o2) {
	  Student s1 = (Student) o1;
	  Student s2 = (Student) o2;
	  return s2.getScore()-s1.getScore();
	 	}
	 }
