package converter;
import java.awt.color.*;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



/*
 * Class that extend frame class and classes of GUI 
 */

public  class StructuredComponentGui extends javax.swing.JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StockCorrenciesInterface stockCurrencies;
	public StructuredComponentGui()
	{
		try
		{
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Background.jpg")))));
			setResizable(false);
			setVisible(true);
		}catch(IOException e)
		{
			System.out.println("Image dont found");
		}
		stockCurrencies=StockCurrencies.getInstanse();
	}


	protected class curJList extends javax.swing.JList<String>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		curJList()
		{
			setAtributes();
			setCurModel();
		}
		private void setAtributes()
		{
			this.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
			this.setForeground(new java.awt.Color(51, 0, 204));		
			
		}
		private void setCurModel()
		{
			this.setModel(new javax.swing.AbstractListModel<String>() 
			{
				private static final long serialVersionUID = 1L;
				String[] strings =stockCurrencies.getCurrenciesList();
	            public int getSize() { return strings.length; }
	            public String getElementAt(int i) { return strings[i]; }
	        });
			this.setSelectedIndex(0);
		}
	}
	protected class CurJtext extends javax.swing.JTextField
	{
	    /**
	    *
	    * @param font
	    * @param background
	    * @param text
	    * @param editable
	    * @return JTextField
	    */
		private static final long serialVersionUID = 1L;
		CurJtext(Color font,Color background,String info,boolean editable,boolean cleanTextFiled)
		{
			if(info!=null)
				this.setText(info);
			setAtributes(font,background,editable,cleanTextFiled);
		}
		private void setAtributes(Color font,Color background,boolean editable,boolean cleanTextFiled)
		{
	 		this.setForeground(font);
	  		this.setEditable(editable);
	  		this.setBackground(background);
	  		this.setFont(new java.awt.Font("Tahoma", 1, 14)); 
	  		if(cleanTextFiled)
	  		{
	  			this.setColumns(8);
				this.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						setText("");
					}
				});
	  		}
		}
		
	}
	
	protected class CurJLabel extends javax.swing.JLabel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		CurJLabel(String info)
		{
			if(info!=null)
				this.setText(info);
			setAtributes();
		}
		private void setAtributes()
		{
			this.setOpaque(true);
			this.setBackground(SystemColor.info);
			this.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
			this.setForeground(new java.awt.Color(255, 102, 0));
			this.setBorder(javax.swing.BorderFactory.createCompoundBorder());
		}
	}
	

  

	protected class CurJButton extends javax.swing.JButton
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public CurJButton(String info)
		{
			if(info!=null)
				this.setText(info);
			setAtributes();
		}
		public void setAtributes()
		{
			
		  	  this.setBackground(new java.awt.Color(0, 0, 204));
		      this.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		      this.setForeground(new java.awt.Color(255, 255, 255));
		      this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		      
		}
	}
	

}
