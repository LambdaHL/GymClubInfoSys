package gcis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable table_Sports;
	private JTable table_Coach;
	private JTable table_Consumer;
	private JTable table_Bill;
	private JTable table_Overview;
	private JTextField textField_SportsNo;
	private JTextField textField_Sports;
	private JTextField textField_CoachNo;
	private JTextField textField_CoachSportsNo;
	private JTextField textField_CoachName;
	private JTextField textField_CoachAge;
	private JTextField textField_CoachTel;
	private JTextField textField_ConsumerTel;
	private JTextField textField_ConsumerAge;
	private JTextField textField_ConsumerName;
	private JTextField textField_ConsumerCoachNo;
	private JTextField textField_ConsumerNo;
	private JTextField textField_FeeNo;
	private JTextField textField_FeeConsumerNo;
	private JTextField textField_Fee;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
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
		table_Sports.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u9879\u76EE\u7F16\u53F7", "\u9879\u76EE"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
		table_Coach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u9879\u76EE\u7F16\u53F7", "\u59D3\u540D", "\u5E74\u9F84", "\u7535\u8BDD"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_Coach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(table_Coach);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(748, 492, 480, 188);
		contentPane.add(scrollPane_3);
		
		table_Consumer = new JTable();
		table_Consumer.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table_Consumer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6559\u7EC3\u7F16\u53F7", "\u59D3\u540D", "\u5E74\u9F84", "\u7535\u8BDD"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
		table_Bill.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u5BA2\u6237\u7F16\u53F7", "\u8D39\u7528"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
		table_Overview.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u9879\u76EE", "\u6559\u7EC3\u59D3\u540D", "\u6559\u7EC3\u7535\u8BDD", "\u5BA2\u6237\u59D3\u540D", "\u5BA2\u6237\u7535\u8BDD", "\u8D39\u7528"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_Overview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_Overview);
		
		JLabel label_4 = new JLabel("总览");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(382, 10, 36, 24);
		contentPane.add(label_4);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
		JButton button_SportsAdd = new JButton("添加");
		button_SportsAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsAdd.setBounds(13, 84, 70, 31);
		panel_TabSports.add(button_SportsAdd);
		
		JButton button_SportsSearch = new JButton("查找");
		button_SportsSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsSearch.setBounds(179, 84, 70, 31);
		panel_TabSports.add(button_SportsSearch);
		
		JButton button_SportsDelete = new JButton("删除");
		button_SportsDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_SportsDelete.setBounds(262, 84, 70, 31);
		panel_TabSports.add(button_SportsDelete);
		
		JButton button_SportsAlter = new JButton("修改");
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
		
		JButton button_CoachAdd = new JButton("添加");
		button_CoachAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachAdd.setBounds(17, 170, 70, 31);
		panel_TabCoach.add(button_CoachAdd);
		
		JButton button_CoachAlter = new JButton("修改");
		button_CoachAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachAlter.setBounds(99, 170, 70, 31);
		panel_TabCoach.add(button_CoachAlter);
		
		JButton button_CoachSearch = new JButton("查找");
		button_CoachSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachSearch.setBounds(181, 170, 70, 31);
		panel_TabCoach.add(button_CoachSearch);
		
		JButton button_CoachDelete = new JButton("删除");
		button_CoachDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_CoachDelete.setBounds(263, 170, 70, 31);
		panel_TabCoach.add(button_CoachDelete);
		
		JPanel panel_TabConsumer = new JPanel();
		tabbedPane.addTab("用户", null, panel_TabConsumer, null);
		panel_TabConsumer.setLayout(null);
		
		JButton button_ConsumerDelete = new JButton("删除");
		button_ConsumerDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerDelete.setBounds(263, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerDelete);
		
		JButton button_ConsumerSearch = new JButton("查找");
		button_ConsumerSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerSearch.setBounds(181, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerSearch);
		
		JButton button_ConsumerAlter = new JButton("修改");
		button_ConsumerAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_ConsumerAlter.setBounds(99, 170, 70, 31);
		panel_TabConsumer.add(button_ConsumerAlter);
		
		JButton button_ConsumerAdd = new JButton("添加");
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
		
		JButton button_FeeAdd = new JButton("添加");
		button_FeeAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeAdd.setBounds(17, 108, 70, 31);
		panel_Fee.add(button_FeeAdd);
		
		JButton button_FeeAlter = new JButton("修改");
		button_FeeAlter.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeAlter.setBounds(99, 108, 70, 31);
		panel_Fee.add(button_FeeAlter);
		
		JButton button_FeeSearch = new JButton("查找");
		button_FeeSearch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeSearch.setBounds(181, 108, 70, 31);
		panel_Fee.add(button_FeeSearch);
		
		JButton button_FeeDelete = new JButton("删除");
		button_FeeDelete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_FeeDelete.setBounds(263, 108, 70, 31);
		panel_Fee.add(button_FeeDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 284, 350, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 43, 350, 231);
		contentPane.add(scrollPane_5);
		
		JTextArea textArea = new JTextArea();
		scrollPane_5.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		//textArea.setEditable(false);
		
		JButton button_Refresh = new JButton("刷新");
		button_Refresh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_Refresh.setBounds(188, 10, 172, 30);
		contentPane.add(button_Refresh);
		
		JButton button_Connect = new JButton("连接");
		button_Connect.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_Connect.setBounds(10, 10, 172, 30);
		contentPane.add(button_Connect);
	}
	
	
}
