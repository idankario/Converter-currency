package converter;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class CurTable extends javax.swing.JTable 
{
	private String[] columnNames =new String[] {"NAME", "UNIT", "CURCODE", "COUNTRY","RATE","CHANGE" };
	private DefaultTableModel model;
	CurTable()
	{
		setModel();
	}
	public void setModel()
	{
		StockCorrenciesInterface stockCurrencies =StockCurrencies.getInstanse();
		setModel(stockCurrencies.getCurrencyTable());
	}
	
	public void setModel (String[][] data)
	{
        model = new DefaultTableModel(data,columnNames) 
        {
			private static final long serialVersionUID = 1L;
            @Override   
            public int getColumnCount() 
            {
            	return columnNames.length;
        	}
			@Override
		    public int getRowCount() 
			{
		        return data.length;
		    }

		    public String getColumnName(int col) 
		    {
		    	return columnNames[col];
		    }

			public String getValueAt(int row, int col)
			{
				return data[row][col];
			}

        };
        setAtributes();
	}

	private void setAtributes()
	{
        this.setModel(model);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	this.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        this.setForeground(Color.BLUE);
        for(int i=model.getColumnCount()-1;i>0;i--)
        	this.getColumnModel().getColumn(i).setPreferredWidth(117);
        this.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 13));
        this.setBackground(SystemColor.info);
        this.setRowHeight(32);  
	}
	public DefaultTableModel getModel() 
	{
		return model;
	}
	private static final long serialVersionUID = -8558480307130852728L;
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{   
         Component result = super.prepareRenderer(renderer, row, column);   
         if (result instanceof JComponent) 
         {   
             
             ((JComponent) result).setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));   
         }   
         return result;   
     }   
	
	 public void setValueAt(Object s, int row, int col)
     {}
}