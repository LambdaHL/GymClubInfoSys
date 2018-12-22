package gcis;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

public class SysGUI extends JFrame
{
	private JPanel contentPane;
	private JTable table_Sports, table_Bill, table_Coach, table_Consumer, table_Overview;
	private JTextField textField_SportsNo, textField_Sports, textField_CoachNo, textField_CoachSportsNo, textField_CoachName, textField_CoachAge, textField_CoachTel, textField_ConsumerTel;
	private JTextField textField_ConsumerAge, textField_ConsumerName, textField_ConsumerCoachNo, textField_ConsumerNo, textField_FeeNo, textField_FeeConsumerNo, textField_Fee;
	private JButton button_SportsAdd, button_SportsSearch, button_SportsDelete, button_SportsAlter;
	private JButton button_CoachAdd, button_CoachAlter, button_CoachSearch, button_CoachDelete;
	private JButton button_ConsumerAdd, button_ConsumerSearch, button_ConsumerAlter, button_ConsumerDelete;
	private JButton button_FeeAdd, button_FeeAlter, button_FeeDelete, button_FeeSearch;
	private JButton button_Refresh, button_Connect;
	private JTextArea textArea;
	private JTabbedPane tabbedPane;
	private static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DB_URL="jdbc:mysql://localhost:3306/GymClub?useSSL=false&serverTimezone=UTC";
	private static final String USER="root";
	private static final String PASSWORD="123456";
	private Connection connection;
	private ArrayList<String> arrayList_Sports=new ArrayList<>();
	private ArrayList<String> arrayList_Coach=new ArrayList<>();
	private ArrayList<String> arrayList_Consumer=new ArrayList<>();
	private ArrayList<String> arrayList_Bill=new ArrayList<>();
	private	String original_Sports, original_Coach, original_Consumer,original_Bill;
	
	public SysGUI() 
	{
		CreateGUI();
		AddEvent();
		
	}
	
	public void CreateGUI()
	{
		setTitle("GymClubInfoSys");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1254, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(382, 284, 846, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(370, 10, 2, 668);
		contentPane.add(separator_2);
		
		JLabel label = new JLabel("项目");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(382, 307, 36, 24);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(428, 296, 265, 188);
		contentPane.add(scrollPane);
		
		table_Sports = new JTable();
		table_Sports.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Sports.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Sports.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_Sports);
		
		JLabel label_1 = new JLabel("教练");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(701, 307, 36, 24);
		contentPane.add(label_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(748, 296, 480, 188);
		contentPane.add(scrollPane_2);
		
		table_Coach = new JTable();
		table_Coach.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Coach.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Coach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(table_Coach);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(748, 492, 480, 188);
		contentPane.add(scrollPane_3);
		
		table_Consumer = new JTable();
		table_Consumer.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Consumer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Consumer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(table_Consumer);
		
		JLabel label_2 = new JLabel("客户");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(701, 503, 36, 24);
		contentPane.add(label_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(428, 492, 265, 188);
		contentPane.add(scrollPane_4);
		
		table_Bill = new JTable();
		table_Bill.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Bill.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Bill.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(table_Bill);
		
		JLabel label_3 = new JLabel("收费");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(382, 503, 36, 24);
		contentPane.add(label_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(428, 10, 800, 264);
		contentPane.add(scrollPane_1);
		
		table_Overview = new JTable();
		table_Overview.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Overview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Overview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_Overview);
		scrollPane_1.setAutoscrolls(true);
		
		JLabel label_4 = new JLabel("总览");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(382, 10, 36, 24);
		contentPane.add(label_4);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 296, 350, 382);
		contentPane.add(tabbedPane);
		
		JPanel panel_TabSports = new JPanel();
		tabbedPane.addTab("项目", null, panel_TabSports, null);
		panel_TabSports.setLayout(null);
		
		JLabel label_5 = new JLabel("编号：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_5.setBounds(10, 10, 48, 22);
		panel_TabSports.add(label_5);
		
		textField_SportsNo = new JTextField();
		textField_SportsNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_SportsNo.setBounds(68, 10, 260, 25);
		panel_TabSports.add(textField_SportsNo);
		textField_SportsNo.setColumns(10);
		
		JLabel label_6 = new JLabel("项目：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_6.setBounds(10, 42, 48, 22);
		panel_TabSports.add(label_6);
		
		textField_Sports = new JTextField();
		textField_Sports.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_Sports.setColumns(10);
		textField_Sports.setBounds(68, 42, 260, 25);
		panel_TabSports.add(textField_Sports);
		
		button_SportsAdd = new JButton("添加");
		button_SportsAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsAdd.setBounds(13, 84, 70, 31);
		panel_TabSports.add(button_SportsAdd);
		
		button_SportsSearch = new JButton("查找");
		button_SportsSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsSearch.setBounds(179, 84, 70, 31);
		panel_TabSports.add(button_SportsSearch);
		
		button_SportsDelete = new JButton("删除");
		button_SportsDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsDelete.setBounds(262, 84, 70, 31);
		panel_TabSports.add(button_SportsDelete);
		
		button_SportsAlter = new JButton("修改");
		button_SportsAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsAlter.setBounds(96, 84, 70, 31);
		panel_TabSports.add(button_SportsAlter);
		
		JPanel panel_TabCoach = new JPanel();
		tabbedPane.addTab("教练", null, panel_TabCoach, null);
		panel_TabCoach.setLayout(null);
		
		JLabel label_7 = new JLabel("编号：");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_7.setBounds(26, 10, 48, 22);
		panel_TabCoach.add(label_7);
		
		textField_CoachNo = new JTextField();
		textField_CoachNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_CoachNo.setColumns(10);
		textField_CoachNo.setBounds(84, 10, 245, 25);
		panel_TabCoach.add(textField_CoachNo);
		
		JLabel label_8 = new JLabel("项目编号：");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_8.setBounds(10, 42, 80, 22);
		panel_TabCoach.add(label_8);
		
		textField_CoachSportsNo = new JTextField();
		textField_CoachSportsNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_CoachSportsNo.setColumns(10);
		textField_CoachSportsNo.setBounds(84, 42, 245, 25);
		panel_TabCoach.add(textField_CoachSportsNo);
		
		JLabel label_9 = new JLabel("姓名：");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_9.setBounds(26, 74, 48, 22);
		panel_TabCoach.add(label_9);
		
		textField_CoachName = new JTextField();
		textField_CoachName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_CoachName.setColumns(10);
		textField_CoachName.setBounds(84, 74, 245, 25);
		panel_TabCoach.add(textField_CoachName);
		
		JLabel label_10 = new JLabel("年龄：");
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_10.setBounds(26, 106, 48, 22);
		panel_TabCoach.add(label_10);
		
		textField_CoachAge = new JTextField();
		textField_CoachAge.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_CoachAge.setColumns(10);
		textField_CoachAge.setBounds(84, 106, 245, 25);
		panel_TabCoach.add(textField_CoachAge);
		
		JLabel label_11 = new JLabel("电话：");
		label_11.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_11.setBounds(26, 138, 48, 22);
		panel_TabCoach.add(label_11);
		
		textField_CoachTel = new JTextField();
		textField_CoachTel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_CoachTel.setColumns(10);
		textField_CoachTel.setBounds(84, 138, 245, 25);
		panel_TabCoach.add(textField_CoachTel);
		
		button_CoachAdd = new JButton("添加");
		button_CoachAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachAdd.setBounds(17, 170, 70, 31);
		panel_TabCoach.add(button_CoachAdd);
		
		button_CoachAlter = new JButton("修改");
		button_CoachAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachAlter.setBounds(99, 170, 70, 31);
		panel_TabCoach.add(button_CoachAlter);
		
		button_CoachSearch = new JButton("查找");
		button_CoachSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachSearch.setBounds(181, 170, 70, 31);
		panel_TabCoach.add(button_CoachSearch);
		
		button_CoachDelete = new JButton("删除");
		button_CoachDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachDelete.setBounds(263, 170, 70, 31);
		panel_TabCoach.add(button_CoachDelete);
		
		JPanel panel_TabConsumer = new JPanel();
		tabbedPane.addTab("用户", null, panel_TabConsumer, null);
		panel_TabConsumer.setLayout(null);
		
		button_ConsumerDelete = new JButton("删除");
		button_ConsumerDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerDelete.setBounds(263, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerDelete);
		
		button_ConsumerSearch = new JButton("查找");
		button_ConsumerSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerSearch.setBounds(181, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerSearch);
		
		button_ConsumerAlter = new JButton("修改");
		button_ConsumerAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerAlter.setBounds(99, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerAlter);
		
		button_ConsumerAdd = new JButton("添加");
		button_ConsumerAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerAdd.setBounds(17, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerAdd);
		
		textField_ConsumerTel = new JTextField();
		textField_ConsumerTel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_ConsumerTel.setColumns(10);
		textField_ConsumerTel.setBounds(84, 138, 245, 25);
		panel_TabConsumer.add(textField_ConsumerTel);
		
		JLabel label_12 = new JLabel("电话：");
		label_12.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_12.setBounds(26, 138, 48, 22);
		panel_TabConsumer.add(label_12);
		
		textField_ConsumerAge = new JTextField();
		textField_ConsumerAge.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_ConsumerAge.setColumns(10);
		textField_ConsumerAge.setBounds(84, 106, 245, 25);
		panel_TabConsumer.add(textField_ConsumerAge);
		
		JLabel label_13 = new JLabel("年龄：");
		label_13.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_13.setBounds(26, 106, 48, 22);
		panel_TabConsumer.add(label_13);
		
		textField_ConsumerName = new JTextField();
		textField_ConsumerName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_ConsumerName.setColumns(10);
		textField_ConsumerName.setBounds(84, 74, 245, 25);
		panel_TabConsumer.add(textField_ConsumerName);
		
		JLabel label_14 = new JLabel("姓名：");
		label_14.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_14.setBounds(26, 74, 48, 22);
		panel_TabConsumer.add(label_14);
		
		textField_ConsumerCoachNo = new JTextField();
		textField_ConsumerCoachNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_ConsumerCoachNo.setColumns(10);
		textField_ConsumerCoachNo.setBounds(84, 42, 245, 25);
		panel_TabConsumer.add(textField_ConsumerCoachNo);
		
		JLabel label_15 = new JLabel("教练编号：");
		label_15.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_15.setBounds(10, 42, 80, 22);
		panel_TabConsumer.add(label_15);
		
		textField_ConsumerNo = new JTextField();
		textField_ConsumerNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_ConsumerNo.setColumns(10);
		textField_ConsumerNo.setBounds(84, 10, 245, 25);
		panel_TabConsumer.add(textField_ConsumerNo);
		
		JLabel label_16 = new JLabel("编号：");
		label_16.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_16.setBounds(26, 10, 48, 22);
		panel_TabConsumer.add(label_16);
		
		JPanel panel_Fee = new JPanel();
		tabbedPane.addTab("收费", null, panel_Fee, null);
		panel_Fee.setLayout(null);
		
		JLabel label_17 = new JLabel("编号：");
		label_17.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_17.setBounds(26, 9, 48, 22);
		panel_Fee.add(label_17);
		
		textField_FeeNo = new JTextField();
		textField_FeeNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_FeeNo.setColumns(10);
		textField_FeeNo.setBounds(86, 10, 249, 25);
		panel_Fee.add(textField_FeeNo);
		
		JLabel label_18 = new JLabel("客户编号：");
		label_18.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_18.setBounds(10, 41, 80, 22);
		panel_Fee.add(label_18);
		
		textField_FeeConsumerNo = new JTextField();
		textField_FeeConsumerNo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_FeeConsumerNo.setColumns(10);
		textField_FeeConsumerNo.setBounds(86, 42, 249, 25);
		panel_Fee.add(textField_FeeConsumerNo);
		
		JLabel label_19 = new JLabel("费用：");
		label_19.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_19.setBounds(26, 73, 48, 22);
		panel_Fee.add(label_19);
		
		textField_Fee = new JTextField();
		textField_Fee.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_Fee.setColumns(10);
		textField_Fee.setBounds(86, 74, 249, 25);
		panel_Fee.add(textField_Fee);
		
		button_FeeAdd = new JButton("添加");
		button_FeeAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeAdd.setBounds(17, 108, 70, 31);
		panel_Fee.add(button_FeeAdd);
		
		button_FeeAlter = new JButton("修改");
		button_FeeAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeAlter.setBounds(99, 108, 70, 31);
		panel_Fee.add(button_FeeAlter);
		
		button_FeeSearch = new JButton("查找");
		button_FeeSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeSearch.setBounds(181, 108, 70, 31);
		panel_Fee.add(button_FeeSearch);
		
		button_FeeDelete = new JButton("删除");
		button_FeeDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeDelete.setBounds(263, 108, 70, 31);
		panel_Fee.add(button_FeeDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 284, 350, 2);
		contentPane.add(separator);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textArea.setEditable(false);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 43, 350, 231);
		scrollPane_5.setViewportView(textArea);
		contentPane.add(scrollPane_5);
		
		
		button_Refresh = new JButton("刷新");
		button_Refresh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_Refresh.setBounds(188, 10, 172, 30);
		button_Refresh.setEnabled(false);
		contentPane.add(button_Refresh);
		
		button_Connect = new JButton("连接");
		button_Connect.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_Connect.setBounds(10, 10, 172, 30);
		contentPane.add(button_Connect);
		
		this.setVisible(true);
	}
	
	private void ConnectToMySql()
	{
		try 
		{
			Class.forName(JDBC_DRIVER);
			connection=DriverManager.getConnection(DB_URL, USER, PASSWORD);
			textArea.append("连接成功\n");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			textArea.append("连接失败\n");
		}
	}
	
	private void UpdateOverview()
	{
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM overview");
			ResultSet resultSet=preparedStatement.executeQuery();
			Vector<String> columnHead=new Vector<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			columnHead.addElement("项目");
			columnHead.addElement("教练姓名");
			columnHead.addElement("教练电话");
			columnHead.addElement("客户姓名");
			columnHead.addElement("客户电话");
			columnHead.addElement("费用");
			Vector<Vector<String>> rows=new Vector<>();
			while(resultSet.next())
			{
				Vector<String> row=new Vector<>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
				{
					row.addElement(resultSet.getString(i));
				}
				rows.addElement(row);
			}
			table_Overview.setModel(new MyTableModel(rows, columnHead));
			textArea.append("总览刷新完成\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			textArea.append("总览刷新失败\n");
		}
	}
	
	private void UpdateSports()
	{
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Sports");
			ResultSet resultSet=preparedStatement.executeQuery();
			Vector<String> columnHead=new Vector<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			columnHead.addElement("项目编号");
			columnHead.addElement("项目");
			Vector<Vector<String>> rows=new Vector<>();
			arrayList_Sports=new ArrayList<>();
			while(resultSet.next())
			{
				Vector<String> row=new Vector<>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
				{
					row.addElement(resultSet.getString(i));
				}
				arrayList_Sports.add(row.get(0));
				rows.addElement(row);
			}
			table_Sports.setModel(new MyTableModel(rows, columnHead));
			textArea.append("项目刷新完成\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			textArea.append("项目刷新失败\n");
		}
	}
	
	private void UpdateCoach()
	{
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Coach");
			ResultSet resultSet=preparedStatement.executeQuery();
			Vector<String> columnHead=new Vector<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			columnHead.addElement("编号");
			columnHead.addElement("项目编号");
			columnHead.addElement("姓名");
			columnHead.addElement("年龄");
			columnHead.addElement("电话");
			Vector<Vector<String>> rows=new Vector<>();
			arrayList_Coach=new ArrayList<>();
			while(resultSet.next())
			{
				Vector<String> row=new Vector<>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
				{
					row.addElement(resultSet.getString(i));
				}
				arrayList_Coach.add(row.get(0));
				rows.addElement(row);
			}
			table_Coach.setModel(new MyTableModel(rows, columnHead));
			textArea.append("教练刷新完成\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			textArea.append("教练刷新失败\n");
		}
	}
	
	private void UpdateConsumer()
	{
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Consumer");
			ResultSet resultSet=preparedStatement.executeQuery();
			Vector<String> columnHead=new Vector<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			columnHead.addElement("编号");
			columnHead.addElement("教练编号");
			columnHead.addElement("姓名");
			columnHead.addElement("年龄");
			columnHead.addElement("电话");
			Vector<Vector<String>> rows=new Vector<>();
			arrayList_Consumer=new ArrayList<>();
			while(resultSet.next())
			{
				Vector<String> row=new Vector<>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
				{
					row.addElement(resultSet.getString(i));
				}
				arrayList_Consumer.add(row.get(0));
				rows.addElement(row);
			}
			table_Consumer.setModel(new MyTableModel(rows, columnHead));
			textArea.append("客户刷新完成\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			textArea.append("客户刷新失败\n");
		}
	}
	
	private void UpdateBill()
	{
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Bill");
			ResultSet resultSet=preparedStatement.executeQuery();
			Vector<String> columnHead=new Vector<>();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			columnHead.addElement("编号");
			columnHead.addElement("客户编号");
			columnHead.addElement("费用");
			Vector<Vector<String>> rows=new Vector<>();
			arrayList_Bill=new ArrayList<>();
			while(resultSet.next())
			{
				Vector<String> row=new Vector<>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
				{
					row.addElement(resultSet.getString(i));
				}
				arrayList_Bill.add(row.get(0));
				rows.addElement(row);
			}
			table_Bill.setModel(new MyTableModel(rows, columnHead));
			textArea.append("收费刷新完成\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			textArea.append("收费刷新失败\n");
		}
	}
	
	private boolean inputCheck()
	{
		switch (tabbedPane.getSelectedIndex()) 
		{
			case 0://Sports
			{
				if(textField_SportsNo.getText().isEmpty() || textField_Sports.getText().isEmpty())
					return false;
				else
					return true;
			}
			case 1://Coach
			{
				if(textField_CoachNo.getText().isEmpty() || textField_CoachName.getText().isEmpty() || textField_CoachAge.getText().isEmpty() || textField_CoachSportsNo.getText().isEmpty() || textField_CoachTel.getText().isEmpty())
					return false;
				else
					return true;
			}
			case 2://Consumer
			{
				if(textField_ConsumerNo.getText().isEmpty() || textField_ConsumerName.getText().isEmpty() || textField_ConsumerAge.getText().isEmpty() || textField_ConsumerCoachNo.getText().isEmpty() || textField_ConsumerTel.getText().isEmpty())
					return false;
				else
					return true;
			}
			case 3://Fee
			{
				if(textField_FeeNo.getText().isEmpty() || textField_Fee.getText().isEmpty() || textField_FeeConsumerNo.getText().isEmpty())
					return false;
				else
					return true;
			}
			default:
				return false;
		}
	}
	
	private void deleteRow(String table, String number)
	{
		PreparedStatement preparedStatement;
		try
		{
			switch (tabbedPane.getSelectedIndex())
			{
				case 0://Sports
				{
					String sql="DELETE FROM Sports WHERE sports_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, number);
					System.out.println(preparedStatement.toString());
					preparedStatement.executeUpdate();
					textArea.append("删除完成\n");
					break;
				}
				case 1://Coach
				{
					String sql="DELETE FROM Coach WHERE coach_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, number);
					System.out.println(preparedStatement.toString());
					preparedStatement.executeUpdate();
					textArea.append("删除完成\n");
					break;
				}
				case 2://Consumer
				{
					String sql="DELETE FROM Consumer WHERE consumer_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, number);
					System.out.println(preparedStatement.toString());
					preparedStatement.executeUpdate();
					textArea.append("删除完成\n");
					break;
				}
				case 3://Fee
				{
					String sql="DELETE FROM Bill WHERE fee_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, number);
					System.out.println(preparedStatement.toString());
					preparedStatement.executeUpdate();
					textArea.append("删除完成\n");
					break;
				}
			}
		}
		catch (Exception e)
		{
			textArea.append("删除失败\n");
			e.printStackTrace();
		}
	}
	
	private void addRow(String table, Vector<String> vector)
	{
		try
		{
			PreparedStatement preparedStatement;
			switch (tabbedPane.getSelectedIndex())
			{
				case 0://Sports
				{
					String sql="INSERT INTO Sports(sports_number,sports_name) VALUES(?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("添加完成\n");
					break;
				}
				case 1://Coach
				{
					String sql="INSERT INTO Coach(coach_number,sports_number,coach_name,coach_age,coach_tel) VALUES(?,?,?,?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setString(3, vector.get(2).toString());
					preparedStatement.setInt(4, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(5, vector.get(4).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("添加完成\n");
					break;
				}
				case 2://Consumer
				{
					String sql="INSERT INTO Consumer(consumer_number,coach_number,consumer_name,consumer_age,consumer_tel) VALUES(?,?,?,?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setString(3, vector.get(2).toString());
					preparedStatement.setInt(4, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(5, vector.get(4).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("添加完成\n");
					break;
				}
				case 3://Fee
				{
					String sql="INSERT INTO Bill(fee_number,consumer_number,fee) VALUES(?,?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setInt(3, Integer.parseInt(vector.get(2).toString()));
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("添加完成\n");
					break;
				}
			}
		}
		catch (Exception e)
		{
			textArea.append("添加失败\n");
			e.printStackTrace();
		}
	}
	
	private void updateRow(String table, Vector<String> vector)
	{
		PreparedStatement preparedStatement;
		try
		{
			switch (tabbedPane.getSelectedIndex())
			{
				case 0://Sports
				{
					String sql="UPDATE Sports SET sports_name=? WHERE sports_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(1).toString());
					preparedStatement.setString(2, vector.get(0).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 1://Coach
				{
					String sql="UPDATE Coach SET sports_number=?, coach_name=?, coach_age=?, coach_tel=? WHERE coach_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(1).toString());
					preparedStatement.setString(2, vector.get(2).toString());
					preparedStatement.setInt(3, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(4, vector.get(4).toString());
					preparedStatement.setString(5, vector.get(0).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 2://Consumer
				{
					String sql="UPDATE Consumer SET coach_number=?, consumer_name=?, consumer_age=?, consumer_tel=? WHERE consumer_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(1).toString());
					preparedStatement.setString(2, vector.get(2).toString());
					preparedStatement.setInt(3, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(4, vector.get(4).toString());
					preparedStatement.setString(5, vector.get(0).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 3://Fee
				{
					String sql="UPDATE Bill SET consumer_name=?, fee=? WHERE fee_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(1).toString());
					preparedStatement.setInt(2, Integer.parseInt(vector.get(2).toString()));
					preparedStatement.setString(3, vector.get(0).toString());
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
			}
		}
		catch (Exception e)
		{
			textArea.append("修改失败\n");
			e.printStackTrace();
		}
	}
	
	private void updateRow(String table, Vector<String> vector, String original)
	{
		PreparedStatement preparedStatement;
		try
		{
			switch (tabbedPane.getSelectedIndex())
			{
				case 0://Sports
				{
					String sql="UPDATE Sports SET sports_number=?, sports_name=? WHERE sports_name=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setString(3, original);
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 1://Coach
				{
					String sql="UPDATE Coach SET coach_number=?, sports_number=?, coach_name=?, coach_age=?, coach_tel=? WHERE coach_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setString(3, vector.get(2).toString());
					preparedStatement.setInt(4, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(5, vector.get(4).toString());
					preparedStatement.setString(6, original);
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 2://Consumer
				{
					String sql="UPDATE Consumer SET consumer_number=?, coach_number=?, consumer_name=?, consumer_age=?, consumer_tel=? WHERE consumer_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setString(3, vector.get(2).toString());
					preparedStatement.setInt(4, Integer.parseInt(vector.get(3).toString()));
					preparedStatement.setString(5, vector.get(4).toString());
					preparedStatement.setString(6, original);
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
				case 3://Fee
				{
					String sql="UPDATE Bill SET fee_number=?, consumer_name=?, fee=? WHERE fee_number=?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, vector.get(0).toString());
					preparedStatement.setString(2, vector.get(1).toString());
					preparedStatement.setInt(3, Integer.parseInt(vector.get(2).toString()));
					preparedStatement.setString(4, original);
					preparedStatement.executeUpdate();
					System.out.println(preparedStatement.toString());
					textArea.append("修改完成\n");
					break;
				}
			}
		}
		catch (Exception e)
		{
			textArea.append("修改失败\n");
			e.printStackTrace();
		}
	}
	
	public void AddEvent()
	{
		button_Connect.addActionListener(new ActionListener()  
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConnectToMySql();
				try
				{
					UpdateOverview();
					UpdateSports();
					UpdateCoach();
					UpdateConsumer();
					UpdateBill();
					button_Refresh.setEnabled(true);
					button_Connect.setEnabled(false);
					textArea.append("刷新完成\n");
				}
				catch (Exception E)
				{
					E.printStackTrace();
					textArea.append("刷新失败\n");
				}
			}
		});
		
		button_Refresh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					UpdateOverview();
					UpdateSports();
					UpdateCoach();
					UpdateConsumer();
					UpdateBill();

					button_Refresh.setEnabled(true);
					button_Connect.setEnabled(false);
					textArea.append("刷新完成\n");
				}
				catch (Exception E)
				{
					E.printStackTrace();
					textArea.append("刷新失败\n");
				}
			}
		});
		
		KeyListener keyListener_String=new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				int keyChar=e.getKeyChar();
				if(keyChar==KeyEvent.VK_SPACE)
				{
					System.out.println("Space Consumed");
					e.consume();
					return;
				}
				if(keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9)
				{
				
				}
				else
				{
					if(keyChar>=KeyEvent.VK_A && keyChar<=KeyEvent.VK_Z)
					{
						
					}
					else
					{
						if(keyChar>='a' && keyChar<='z')
						{
							
						}
						else
						{
							e.consume();
						}
					}
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)	{}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		};
		
		KeyListener keyListener_int=new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				int keyChar=e.getKeyChar();
				if(keyChar==KeyEvent.VK_SPACE)
				{
					e.consume();
				}
				if(keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9)
				{

				}
				else
				{
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)	{}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		};
		
		textField_Sports.addKeyListener(keyListener_String);
		textField_SportsNo.addKeyListener(keyListener_String);
		textField_CoachNo.addKeyListener(keyListener_String);
		textField_CoachName.addKeyListener(keyListener_String);
		textField_CoachSportsNo.addKeyListener(keyListener_String);
		textField_CoachTel.addKeyListener(keyListener_String);
		textField_ConsumerNo.addKeyListener(keyListener_String);
		textField_ConsumerCoachNo.addKeyListener(keyListener_String);
		textField_ConsumerName.addKeyListener(keyListener_String);
		textField_ConsumerAge.addKeyListener(keyListener_String);
		textField_ConsumerTel.addKeyListener(keyListener_String);
		textField_FeeNo.addKeyListener(keyListener_String);
		textField_FeeConsumerNo.addKeyListener(keyListener_String);
		textField_Fee.addKeyListener(keyListener_String);
		textField_CoachAge.addKeyListener(keyListener_int);
		textField_ConsumerAge.addKeyListener(keyListener_int);
		
		table_Sports.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MyTableModel tableModel=(MyTableModel)table_Sports.getModel();
				original_Sports=tableModel.getValueAt(table_Sports.getSelectedRow(), 0).toString();
			}
		});
		
		table_Coach.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MyTableModel tableModel=(MyTableModel)table_Coach.getModel();
				original_Coach=tableModel.getValueAt(table_Coach.getSelectedRow(), 0).toString();
			}
		});
		
		table_Consumer.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MyTableModel tableModel=(MyTableModel)table_Consumer.getModel();
				original_Consumer=tableModel.getValueAt(table_Consumer.getSelectedRow(), 0).toString();
			}
		});
		
		table_Bill.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MyTableModel tableModel=(MyTableModel)table_Bill.getModel();
				original_Bill=tableModel.getValueAt(table_Bill.getSelectedRow(), 0).toString();
			}
		});
		
		//Delete
		
		button_SportsDelete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel)table_Sports.getModel();
				int row=table_Sports.getSelectedRow();
				if(row>=0)
				{
					int i=JOptionPane.showConfirmDialog(null, new JLabel("所有与该条目编号有依赖关系的记录都会被删除,确认？"), "警告", JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION)
					{
						deleteRow("Sports", myTableModel.getValueAt(row, 0).toString());
						arrayList_Sports.remove(row);
						myTableModel.removeRow(row);
						table_Sports.clearSelection();
						button_Refresh.doClick();
					}
					else
					{
						textArea.append("删除撤销\n");
					}
				}
				else
				{
					textArea.append("未选中删除项\n");
				}
			}
		});
		
		button_CoachDelete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel) table_Coach.getModel();
				int row=table_Coach.getSelectedRow();
				if(row>=0)
				{
					int i=JOptionPane.showConfirmDialog(null, new JLabel("所有与该条目编号有依赖关系的记录都会被删除,确认？"), "警告", JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION)
					{
						deleteRow("Coach", myTableModel.getValueAt(row, 0).toString());
						arrayList_Coach.remove(row);
						myTableModel.removeRow(row);
						table_Coach.clearSelection();
						button_Refresh.doClick();
					}
					else
					{
						textArea.append("删除撤销\n");
					}
				}
				else
				{
					textArea.append("未选中删除项\n");
				}
			}
		});
		
		button_ConsumerDelete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel) table_Consumer.getModel();
				int row=table_Consumer.getSelectedRow();
				if(row>=0)
				{
					int i=JOptionPane.showConfirmDialog(null, new JLabel("所有与该条目编号有依赖关系的记录都会被删除,确认？"), "警告", JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION)
					{
						deleteRow("Consumer", myTableModel.getValueAt(row, 0).toString());
						arrayList_Consumer.remove(row);
						myTableModel.removeRow(row);
						table_Consumer.clearSelection();
						button_Refresh.doClick();
					}
					else
					{
						textArea.append("未选中删除项\n");
					}
				}
				else
				{
					textArea.append("未选中删除项\n");
				}
			}
		});
		
		button_FeeDelete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel) table_Bill.getModel();
				int row=table_Bill.getSelectedRow();
				if(row>=0)
				{
					int i=JOptionPane.showConfirmDialog(null, new JLabel("所有与该条目编号有依赖关系的记录都会被删除,确认？"), "警告", JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION)
					{
						deleteRow("Bill", myTableModel.getValueAt(row, 0).toString());
						arrayList_Bill.remove(row);
						myTableModel.removeRow(row);
						table_Bill.clearSelection();
						button_Refresh.doClick();
					}
					else
					{
						textArea.append("未选中删除项\n");
					}
				}
				else
				{
					textArea.append("未选中删除项\n");
				}
			}
		});
		
		//Add
		
		button_SportsAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				MyTableModel myTableModel=(MyTableModel)table_Sports.getModel();
				if(inputCheck() && !arrayList_Sports.contains(textField_SportsNo.getText()))
				{
					Vector<String> vector=new Vector<>();
					vector.addElement(textField_SportsNo.getText());
					vector.addElement(textField_Sports.getText());
					addRow("Sports", vector);
					myTableModel.addRow(vector);
					arrayList_Sports.add(textField_SportsNo.getText());
					table_Sports.clearSelection();
					UpdateSports();
					UpdateOverview();
				}
				else
				{
					textArea.append("非法输入(空值或已存在的值)\n");
				}
			}
		});
		
		button_CoachAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				MyTableModel myTableModel=(MyTableModel)table_Coach.getModel();
				if(inputCheck() && !arrayList_Coach.contains(textField_CoachNo.getText()))
				{
					Vector<String> vector=new Vector<>();
					vector.addElement(textField_CoachNo.getText());
					vector.addElement(textField_CoachSportsNo.getText());
					vector.addElement(textField_CoachName.getText());
					vector.addElement(textField_CoachAge.getText());
					vector.addElement(textField_CoachTel.getText());
					addRow("Coach", vector);
					myTableModel.addRow(vector);
					arrayList_Coach.add(textField_CoachNo.getText());
					table_Coach.clearSelection();
					UpdateCoach();
					UpdateOverview();
				}
				else
				{
					textArea.append("非法输入(空值或已存在的值)\n");
				}
			}
		});
		
		button_ConsumerAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				MyTableModel myTableModel=(MyTableModel)table_Consumer.getModel();
				if(inputCheck() && !arrayList_Consumer.contains(textField_ConsumerNo.getText()))
				{
					Vector<String> vector=new Vector<>();
					vector.addElement(textField_ConsumerNo.getText());
					vector.addElement(textField_ConsumerCoachNo.getText());
					vector.addElement(textField_ConsumerName.getText());
					vector.addElement(textField_ConsumerAge.getText());
					vector.addElement(textField_ConsumerTel.getText());
					addRow("Consumer", vector);
					myTableModel.addRow(vector);
					arrayList_Consumer.add(textField_ConsumerNo.getText());
					table_Consumer.clearSelection();
					UpdateConsumer();
					UpdateOverview();
				}
				else
				{
					textArea.append("非法输入(空值或已存在的值)\n");
				}
			}
		});
		
		button_FeeAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				MyTableModel myTableModel=(MyTableModel)table_Bill.getModel();
				if(inputCheck() && !arrayList_Bill.contains(textField_FeeNo.getText()))
				{
					Vector<String> vector=new Vector<>();
					vector.addElement(textField_FeeNo.getText());
					vector.addElement(textField_FeeConsumerNo.getText());
					vector.addElement(textField_Fee.getText());
					addRow("Bill", vector);
					myTableModel.addRow(vector);
					arrayList_Bill.add(textField_FeeNo.getText());
					table_Bill.clearSelection();
					UpdateBill();
					UpdateOverview();
				}
				else
				{
					textArea.append("非法输入(空值或已存在的值)\n");
				}
			}
		});
		
		//Alter
		
		button_SportsAlter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel)table_Sports.getModel();
				if(table_Sports.getSelectedRow()!=-1)
				{
					if(inputCheck() && table_Sports.getRowCount()>0 && textField_SportsNo.getText().equals(myTableModel.getValueAt(table_Sports.getSelectedRow(), 0)))
					{
						int row=table_Sports.getSelectedRow();
						Vector<String> vector=new Vector<>();
						myTableModel.setValueAt(textField_SportsNo.getText(), row, 0);
						myTableModel.setValueAt(textField_Sports.getText(), row, 1);
						for(int column=0;column<myTableModel.getColumnCount();column++)
						{
							vector.addElement(myTableModel.getValueAt(row, column).toString());
						}
						updateRow("Sports", vector);
						table_Sports.clearSelection();
						button_Refresh.doClick();
					}
					else
						if(inputCheck() && myTableModel.getRowCount()>0 && !arrayList_Sports.contains(textField_SportsNo.getText()))
						{
							int row=table_Sports.getSelectedRow();
							Vector<String> vector=new Vector<>();
							arrayList_Sports.set(row, textField_SportsNo.getText());
							myTableModel.setValueAt(textField_SportsNo.getText(), row, 0);
							myTableModel.setValueAt(textField_Sports.getText(), row, 1);
							for(int column=0;column<myTableModel.getColumnCount();column++)
							{
								vector.addElement(myTableModel.getValueAt(row, column).toString());
							}
							updateRow("Sports", vector, original_Sports);
							table_Sports.clearSelection();
							button_Refresh.doClick();
						}
						else
						{
							if(arrayList_Sports.contains(textField_SportsNo.getText()))
								textArea.append("非法输入（已存在的值）\n");
						}
				}
				else
				{
					textArea.append("未选中修改行\n");
				}
			}
		});
		
		button_CoachAlter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel)table_Coach.getModel();
				if(table_Coach.getSelectedRow()!=-1)
				{
					if(inputCheck() && table_Coach.getRowCount()>0 && textField_CoachNo.getText().equals(myTableModel.getValueAt(table_Coach.getSelectedRow(), 0)))
					{
						int row=table_Coach.getSelectedRow();
						Vector<String> vector=new Vector<>();
						myTableModel.setValueAt(textField_CoachNo.getText(), row, 0);
						myTableModel.setValueAt(textField_CoachSportsNo.getText(), row, 1);
						myTableModel.setValueAt(textField_CoachName.getText(), row, 2);
						myTableModel.setValueAt(textField_CoachAge.getText(), row, 3);
						myTableModel.setValueAt(textField_CoachTel.getText(), row, 4);
						for(int column=0;column<myTableModel.getColumnCount();column++)
						{
							vector.addElement(myTableModel.getValueAt(row, column).toString());
						}
						updateRow("Coach", vector);
						table_Coach.clearSelection();
						button_Refresh.doClick();
					}
					else
						if(inputCheck() && myTableModel.getRowCount()>0 && !arrayList_Coach.contains(textField_CoachNo.getText()))
						{
							int row=table_Coach.getSelectedRow();
							Vector<String> vector=new Vector<>();
							arrayList_Coach.set(row, textField_CoachNo.getText());
							myTableModel.setValueAt(textField_CoachNo.getText(), row, 0);
							myTableModel.setValueAt(textField_CoachSportsNo.getText(), row, 1);
							myTableModel.setValueAt(textField_CoachName.getText(), row, 2);
							myTableModel.setValueAt(textField_CoachAge.getText(), row, 3);
							myTableModel.setValueAt(textField_CoachTel.getText(), row, 4);
							for(int column=0;column<myTableModel.getColumnCount();column++)
							{
								vector.addElement(myTableModel.getValueAt(row, column).toString());
							}
							updateRow("Coach", vector, original_Coach);
							table_Coach.clearSelection();
							button_Refresh.doClick();
						}
						else
						{
							if(arrayList_Coach.contains(textField_CoachNo.getText()))
								textArea.append("非法输入（已存在的值）\n");
						}
				}
				else
				{
					textArea.append("未选中修改行\n");
				}
			}
		});
		
		button_ConsumerAlter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyTableModel myTableModel=(MyTableModel)table_Consumer.getModel();
				if(table_Consumer.getSelectedRow()!=-1)
				{
					if(inputCheck() && table_Consumer.getRowCount()>0 && textField_ConsumerNo.getText().equals(myTableModel.getValueAt(table_Consumer.getSelectedRow(), 0)))
					{
						int row=table_Consumer.getSelectedRow();
						Vector<String> vector=new Vector<>();
						myTableModel.setValueAt(textField_ConsumerNo.getText(), row, 0);
						myTableModel.setValueAt(textField_ConsumerCoachNo.getText(), row, 1);
						myTableModel.setValueAt(textField_ConsumerName.getText(), row, 2);
						myTableModel.setValueAt(textField_ConsumerAge.getText(), row, 3);
						myTableModel.setValueAt(textField_ConsumerTel.getText(), row, 4);
						for(int column=0;column<myTableModel.getColumnCount();column++)
						{
							vector.addElement(myTableModel.getValueAt(row, column).toString());
						}
						updateRow("Consumer", vector);
						table_Consumer.clearSelection();
						button_Refresh.doClick();
					}
					else
						if(inputCheck() && myTableModel.getRowCount()>0 && !arrayList_Consumer.contains(textField_ConsumerNo.getText()))
						{
							int row=table_Consumer.getSelectedRow();
							Vector<String> vector=new Vector<>();
							arrayList_Consumer.set(row, textField_ConsumerNo.getText());
							myTableModel.setValueAt(textField_ConsumerNo.getText(), row, 0);
							myTableModel.setValueAt(textField_ConsumerCoachNo.getText(), row, 1);
							myTableModel.setValueAt(textField_ConsumerName.getText(), row, 2);
							myTableModel.setValueAt(textField_ConsumerAge.getText(), row, 3);
							myTableModel.setValueAt(textField_ConsumerTel.getText(), row, 4);
							for(int column=0;column<myTableModel.getColumnCount();column++)
							{
								vector.addElement(myTableModel.getValueAt(row, column).toString());
							}
							updateRow("Consumer", vector, original_Consumer);
							table_Consumer.clearSelection();
							button_Refresh.doClick();
						}
						else
						{
							if(arrayList_Coach.contains(textField_ConsumerNo.getText()))
								textArea.append("非法输入（已存在的值）\n");
						}
				}
				else
				{
					textArea.append("未选中修改行\n");
				}
			}
		});
		
		button_FeeAlter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				MyTableModel myTableModel=(MyTableModel)table_Bill.getModel();
				if(table_Bill.getSelectedRow()!=-1)
				{
					if(inputCheck() && table_Bill.getRowCount()>0 && textField_FeeNo.getText().equals(myTableModel.getValueAt(table_Bill.getSelectedRow(), 0)))
					{
						int row=table_Bill.getSelectedRow();
						Vector<String> vector=new Vector<>();
						myTableModel.setValueAt(textField_FeeNo.getText(), row, 0);
						myTableModel.setValueAt(textField_FeeConsumerNo.getText(), row, 1);
						myTableModel.setValueAt(textField_Fee.getText(), row, 2);
						for(int column=0;column<myTableModel.getColumnCount();column++)
						{
							vector.addElement(myTableModel.getValueAt(row, column).toString());
						}
						updateRow("Bill", vector);
						table_Bill.clearSelection();
						button_Refresh.doClick();
					}
					else
						if(inputCheck() && myTableModel.getRowCount()>0 && !arrayList_Bill.contains(textField_FeeNo.getText()))
						{
							int row=table_Bill.getSelectedRow();
							Vector<String> vector=new Vector<>();
							arrayList_Bill.set(row, textField_FeeNo.getText());
							myTableModel.setValueAt(textField_FeeNo.getText(), row, 0);
							myTableModel.setValueAt(textField_FeeConsumerNo.getText(), row, 1);
							myTableModel.setValueAt(textField_FeeNo.getText(), row, 2);
							for(int column=0;column<myTableModel.getColumnCount();column++)
							{
								vector.addElement(myTableModel.getValueAt(row, column).toString());
							}
							updateRow("Bill", vector, original_Bill);
							table_Bill.clearSelection();
							button_Refresh.doClick();
						}
						else
						{
							if(arrayList_Bill.contains(textField_FeeNo.getText()))
								textArea.append("非法输入（已存在的值）\n");
						}
				}
				else
				{
					textArea.append("未选中修改行\n");
				}
			}
		});
		
		//Search
		
		button_SportsSearch.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(table_Sports.getRowCount()>0)
				{
					if(!textField_SportsNo.getText().isEmpty())
					{
						String number=textField_SportsNo.getText();
						int row;
						boolean isFound=false;
						MyTableModel myTableModel=(MyTableModel)table_Sports.getModel();
						for(row=0;row<table_Sports.getRowCount();row++)
						{
							if(myTableModel.getValueAt(row, 0).equals(number))
							{
								isFound=true;
								break;
							}
						}
						if(isFound)
						{
							table_Sports.setRowSelectionInterval(row, row);
							table_Sports.scrollRectToVisible(table_Sports.getCellRect(row, 0, true));
							textArea.append("查找完成\n");
						}
						else
						{
							textArea.append("未查找到该值\n");
						}
					}
					else
					{
						textArea.append("未输入查找主键值\n");
					}
				}
				else
				{
					textArea.append("该表为空\n");
				}
			}
		});
		
		button_CoachSearch.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(table_Coach.getRowCount()>0)
				{
					if(!textField_CoachNo.getText().isEmpty())
					{
						String number=textField_CoachNo.getText();
						int row;
						boolean isFound=false;
						MyTableModel myTableModel=(MyTableModel)table_Coach.getModel();
						for(row=0;row<table_Coach.getRowCount();row++)
						{
							if(myTableModel.getValueAt(row, 0).equals(number))
							{
								isFound=true;
								break;
							}
						}
						if(isFound)
						{
							table_Coach.setRowSelectionInterval(row, row);
							table_Coach.scrollRectToVisible(table_Coach.getCellRect(row, 0, true));
							textArea.append("查找完成\n");
						}
						else
						{
							textArea.append("未查找到该值\n");
						}
					}
					else
					{
						textArea.append("未输入查找主键值\n");
					}
				}
				else
				{
					textArea.append("该表为空\n");
				}
			}
		});
		
		button_ConsumerSearch.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(table_Consumer.getRowCount()>0)
				{
					if(!textField_ConsumerNo.getText().isEmpty())
					{
						String number=textField_ConsumerNo.getText();
						int row;
						boolean isFound=false;
						MyTableModel myTableModel=(MyTableModel)table_Consumer.getModel();
						for(row=0;row<table_Consumer.getRowCount();row++)
						{
							if(myTableModel.getValueAt(row, 0).equals(number))
							{
								isFound=true;
								break;
							}
						}
						if(isFound)
						{
							table_Consumer.setRowSelectionInterval(row, row);
							table_Consumer.scrollRectToVisible(table_Consumer.getCellRect(row, 0, true));
							textArea.append("查找完成\n");
						}
						else
						{
							textArea.append("未查找到该值\n");
						}
					}
					else
					{
						textArea.append("未输入查找主键值\n");
					}
				}
				else
				{
					textArea.append("该表为空\n");
				}
			}
		});
		
		button_FeeSearch.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(table_Bill.getRowCount()>0)
				{
					if(!textField_FeeNo.getText().isEmpty())
					{
						String number=textField_FeeNo.getText();
						int row;
						boolean isFound=false;
						MyTableModel myTableModel=(MyTableModel)table_Bill.getModel();
						for(row=0;row<table_Bill.getRowCount();row++)
						{
							if(myTableModel.getValueAt(row, 0).equals(number))
							{
								isFound=true;
								break;
							}
						}
						if(isFound)
						{
							table_Bill.setRowSelectionInterval(row, row);
							table_Bill.scrollRectToVisible(table_Bill.getCellRect(row, 0, true));
							textArea.append("查找完成\n");
						}
						else
						{
							textArea.append("未查找到该值\n");
						}
					}
					else
					{
						textArea.append("未输入查找主键值\n");
					}
				}
				else
				{
					textArea.append("该表为空\n");
				}
			}
		});
		
	}
}
