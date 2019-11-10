package stu_info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *@classname: StuScore
 *@author: shijinhai
 *@time: 2019年10月29日
*/
public class StuScore extends JFrame{
		
		JFrame fra; 
		JTabbedPane tap; //建立选项卡
		JPanel insert;   //录入页
		JPanel search;	//查询页
		JPanel sort;	//排序页
		
		//录入页组件
		JLabel t1;
		JLabel t2;
		JLabel title;
		JTextField t3;
		JTextField t4;
		JButton confirm;
		
		//查询页组件
		JLabel s1;
		JTextField s2;
		JButton sch;
		JTextArea show;
		
		//排序页组件
		JButton sot;
		JTextArea show2;
		JTable ta1;
		DefaultTableModel tableModel;
		ArrayList<Student> students = new ArrayList<Student>();
		public StuScore() {
			
			fra = new JFrame("学生成绩管理系统");
			fra.setSize(500, 650);//设置位置
			fra.setVisible(true); //设置可见
			fra.setLocationRelativeTo(getOwner());
			fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置可关闭
			insert = new JPanel();
			search = new JPanel();
			sort = new JPanel();
			tap = new JTabbedPane();
			tap.insertTab("成绩录入", null, insert, null, 0);
			tap.insertTab("成绩查询", null, search, null, 1);
			tap.insertTab("成绩排序", null, sort, null, 2);
			tap.setSelectedIndex(0); //设置默认显示选项卡索引为0
			fra.add(tap);
			Font font = new Font("楷体", Font.BOLD, 16);
			Font font2 = new Font("黑体", Font.BOLD, 25);
			
			//录入页组件实现
			title = new JLabel("欢迎使用本系统");
			title.setFont(font2);
			t1 = new JLabel("学号");
			t1.setFont(font);
			t2 = new JLabel("成绩");
			t2.setFont(font);
			t3 = new JTextField(10);
			t4 = new JTextField(10);
			t3.setDocument(new NumberOnly()); //设置只能输入数字
			t4.setDocument(new NumberOnly());
			confirm = new JButton("确认");
			confirm.addActionListener(new ClickListen()); //监听comfirm按钮
			Box til = Box.createHorizontalBox();
			til.add(title);
			Box box_in = Box.createHorizontalBox();//建立水平box
			box_in.add(Box.createVerticalStrut(20));
			box_in.add(t1);
			box_in.add(Box.createHorizontalStrut(15));
			box_in.add(t3);
			Box box_in2 = Box.createHorizontalBox();
			box_in2.add(t2);
			box_in2.add(Box.createHorizontalStrut(15));
			box_in2.add(t4);
			Box abox_in = Box.createVerticalBox(); //建立垂直box
			abox_in.add(Box.createVerticalStrut(20));
			abox_in.add(til);
			abox_in.add(Box.createVerticalStrut(100));
			abox_in.add(box_in);
			abox_in.add(Box.createVerticalStrut(12));
			abox_in.add(box_in2);
			abox_in.add(Box.createVerticalStrut(100));
			abox_in.add(confirm,BorderLayout.CENTER);
			insert.add(abox_in);
			
			//查询页组件实现
			show = new JTextArea(25,25);
			show.setEditable(false);
			s1 = new JLabel("学号");
			s1.setFont(font2);
			s2 = new JTextField(10);
			s2.setDocument(new NumberOnly());
			sch = new JButton("查询");
			sch.addActionListener(new ClickListen()); //监听sch按钮
			Box box_se = Box.createHorizontalBox();
			box_se.add(Box.createVerticalStrut(20));
			box_se.add(s1);
			box_se.add(Box.createHorizontalStrut(15));
			box_se.add(s2);
			Box box_se2 = Box.createHorizontalBox();
			box_se2.setPreferredSize(new Dimension( 200,370 ) );//设置box的大小
			box_se2.add(new JScrollPane(show));
			Box abox_se = Box.createVerticalBox();
			abox_se.add(Box.createVerticalStrut(20));
			abox_se.add(box_se);
			abox_se.add(Box.createVerticalStrut(40));
			abox_se.add(sch,BorderLayout.CENTER);
			abox_se.add(Box.createVerticalStrut(40));
			abox_se.add(box_se2);
			search.add(abox_se);
			
			//排序页组件实现
			sot = new JButton("排序");
			sot.addActionListener(new ClickListen()); //监听sot按钮
			show2 = new JTextArea(30,30);
			show2.setEditable(false);
			tableModel = new DefaultTableModel();
			ta1 = new JTable(tableModel);
			ta1.setEnabled(false);//设置不可编辑
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   //设置数据居中显示
			ta1.setDefaultRenderer(Object.class,   r);
			tableModel.addColumn("学号");
			tableModel.addColumn("成绩");
			Box box_st1 = Box.createHorizontalBox();
			box_st1.setPreferredSize(new Dimension(200,370));
			box_st1.add(new JScrollPane(ta1));
			Box abox_st = Box.createVerticalBox();
			abox_st.add(Box.createVerticalStrut(30));
			abox_st.add(sot,BorderLayout.CENTER);
			abox_st.add(Box.createVerticalStrut(40));
			abox_st.add(box_st1);
			sort.add(abox_st);
			
			fra.repaint();//刷新frame，否则不会显示
		}
		
	//按钮点击监听
	class ClickListen implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent a) {
				
					if(a.getSource()==confirm)
					{
						String sno = t3.getText();
						String sco = t4.getText();
						int flag=1;
						if(sno.equals("")||sco.equals(""))
						{
							JOptionPane.showMessageDialog(null, "输入不能为空", "错误", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							int score = Integer.parseInt(sco);
							Student stu = new Student(sno, score);
							for(int i=0 ; i< students.size();i++)
							{
								if(students.get(i).getSno().equals(sno)) //判断是否存在该学号
								{
									students.get(i).updateSco(score); //更新成绩
									flag=0;
								}
							}
							if(flag==1)
							{
								students.add(stu);
								JOptionPane.showMessageDialog(null, "学号为"+sno+"的成绩已成功添加", "添加成功", JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "学号为"+sno+"的成绩已成功修改", "修改成功", JOptionPane.INFORMATION_MESSAGE);
							}
							t3.setText( null );
							t4.setText( null ); 
						}
						
					}
					else if(a.getSource()==sch)
					{
						String sno = s2.getText();
						if(sno.equals(""))
						{
							JOptionPane.showMessageDialog(null, "输入不能为空", "错误", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							int flag=0;
							for(int i=0 ;i< students.size() ; i++)
							{
								if(students.get(i).getSno().equals(sno))
								{
									flag=1;
									show.setText("已找到该生，该生信息为：\n\n");
									show.append("学号："+students.get(i).getSno()+" ,"+"成绩："+students.get(i).getScore());
									break;
								}
							}
								if(flag == 0)
								{
									show.setText("抱歉，该生信息不存在！");
								}
							}
						s2.setText(null);
						}
						
					else if(a.getSource()==sot)
					{
						//show2.setText(null);
						tableModel.setRowCount(0);
						if(students.size()>0)
						{
							Collections.sort(students, new SortByScore());
							//shows.setText("学生成绩从高到低排序如下：\n\n");
							for(int i = 0; i<students.size(); i++)
							{
								String []arr = new String[2];
								arr[0] = students.get(i).getSno();
								arr[1] = students.get(i).getScore()+"";
								tableModel.addRow(arr);
								//show2.append("学号："+students.get(i).getSno()+" , "+"成绩："+students.get(i).getScore()+"\r\n");
							}
							ta1.invalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "暂无学生信息无法排序！", "错误", JOptionPane.INFORMATION_MESSAGE);
							//show2.setText("抱歉，暂无学生信息，无法排序！");
						}
					}
			}
					
		}
}
