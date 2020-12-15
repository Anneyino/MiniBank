import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class AccountsChart extends JFrame
{
    private JPanel contentPane = new JPanel();
    private String[] columnNames;
    private Object[][] rowData;
        
    public AccountsChart()
    {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
    }
    
    public void setColumnNames(String[] columnNames)
    {
    	this.columnNames = columnNames;
    }
    
    public void setRowData(Object[][] rowData)
    {
    	this.rowData = rowData;
    }
    
    public void showTable()
    {
	    JTable table = new JTable(rowData, columnNames);
	    table.setForeground(Color.BLACK);              
	    table.setSelectionForeground(Color.DARK_GRAY);     
	    table.setSelectionBackground(Color.LIGHT_GRAY);     
	    table.setGridColor(Color.GRAY);                    
	    table.getTableHeader().setForeground(Color.RED);        
	    table.getTableHeader().setResizingAllowed(false);             
	    table.getTableHeader().setReorderingAllowed(false);             
	    table.setRowHeight(30);
	    table.getColumnModel().getColumn(0).setPreferredWidth(40);
	    table.setPreferredScrollableViewportSize(new Dimension(400, 300));
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPane.add(scrollPane);
    }



    
}
