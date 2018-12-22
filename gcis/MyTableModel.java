package gcis;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel
{
	@Override
	public boolean isCellEditable(int row,int column)
	{
		return false;
	}
	
	public MyTableModel(Vector<Vector<String>> data, Vector<String> columns)
	{
		super(data,columns);
	}
}
