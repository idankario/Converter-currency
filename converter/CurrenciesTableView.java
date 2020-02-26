package converter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;



public class CurrenciesTableView extends StructuredComponentGui 
{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public CurrenciesTableView() 
	    {
	        initComponents();
	        showTableCurrency();
	    }

	                       
	    public void initComponents() 
	    {
			tableCur = new CurTable();
			ScrollTable = new javax.swing.JScrollPane(tableCur);
			buttonSwitch = new CurJButton("Switch");
			addNewCurrency = new CurJButton("Add"); 
	        removeCurrency = new CurJButton("remove");
	        refresh = new CurJButton("Refresh "); 
	        graph = new CurJButton("Graph ");
			instructionAddCurrency = new CurJLabel("New Currency :");
			instructionTable = new CurJLabel("Conversion money :");
	        Name = new CurJtext(Color.BLUE, Color.white,"Name",true,true);
	        Currencycode = new CurJtext(Color.BLUE, Color.white,"Currency code",true,true);
	        Unit = new CurJtext(Color.BLUE, Color.white,"Unit",true,true);
	        Country = new CurJtext(Color.BLUE, Color.white,"Country",true,true);
	        Change = new CurJtext(Color.BLUE, Color.white,"Change",true,true);
	        Rate = new CurJtext(Color.BLUE, Color.white,"Rate",true,true);
	        displayTime = new CurJtext(Color.BLUE, SystemColor.info,"",false,false);
	        searchVirable = new CurJtext(Color.BLUE, Color.white,"Search currency:",true,true);
	       
	        String[] curInfo=new String[] {"NAME ", "UNIT", "CURCODE ", "COUNTRY", "RATE", "CHANGE "};        
	        comboBoxSelect = new JComboBox<String>(curInfo);
	        comboBoxSelect.setSelectedIndex(0);

			addEvents();
	      
	    }// </editor-fold>                        
	  


		private void showTableCurrency()
	    {
	        //Frame attributes
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			setMinimumSize(new java.awt.Dimension(620, 440));
	        
			
	        GroupLayout groupLayout = new GroupLayout(getContentPane());
	        groupLayout.setHorizontalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(19)
	        			.addComponent(instructionTable)
	        			.addGap(18)
	        			.addComponent(buttonSwitch, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
	        			.addGap(98)
	        			.addComponent(comboBoxSelect, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(searchVirable, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(10)
	        			.addComponent(ScrollTable, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE))
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(10)
	        			.addComponent(instructionAddCurrency)
	        			.addGap(10)
	        			.addComponent(addNewCurrency, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
	        			.addGap(18)
	        			.addComponent(removeCurrency, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
	        			.addGap(18)
	        			.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
	        			.addGap(18)
	        			.addComponent(graph, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(10)
	        			.addComponent(Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(29)
	        			.addComponent(Currencycode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(30)
	        			.addComponent(Rate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(549)
	        			.addComponent(displayTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(10)
	        			.addComponent(Unit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(29)
	        			.addComponent(Country, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(30)
	        			.addComponent(Change, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        );
	        groupLayout.setVerticalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(17)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        					.addGroup(groupLayout.createSequentialGroup()
	        						.addGap(25)
	        						.addComponent(instructionTable))
	        					.addComponent(buttonSwitch, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(comboBoxSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(searchVirable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(18)
	        			.addComponent(ScrollTable, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
	        			.addGap(7)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addGap(12)
	        					.addComponent(instructionAddCurrency))
	        				.addComponent(addNewCurrency, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(removeCurrency, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(graph, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(18)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(Currencycode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(Rate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(displayTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addGap(11)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(Unit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(Country, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(Change, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        );
	        getContentPane().setLayout(groupLayout);
	        if(stockCurrencies.isUpdate())
	        	displayTime.setText("Last update: "+stockCurrencies.getLastUpdate());
	        else
	        	JOptionPane.showMessageDialog(this, "Error could not get to data");
	        this.setTitle("Currencies Data From Bank Israel");
	        pack();
	    }
		private void addEvents()
		{
			refresh.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt)
			    {
			    	stockCurrencies.refrechList();
					tableCur.setModel ();
			    }
			});
			graph.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt)
			    {
			    	createGraph(evt);  	
			    }
			});
			searchVirable.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyReleased(KeyEvent arg0)
				{
					int selection=comboBoxSelect.getSelectedIndex();
					TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(tableCur.getModel());
					tableCur.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter(searchVirable.getText(),selection));
				}
			});
			removeCurrency.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt)
			    {
			    	removeCurrency(evt);  	
			    }
			});
			addNewCurrency.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt)
			    {
			    	addNewCurrency(evt);  	
			    }
			});
			buttonSwitch.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt)
			    {
			    	switchButtonJframe(evt);
			    }
			});

		        
		}
	    private void switchButtonJframe(java.awt.event.ActionEvent evt)
	    {
	    	
	    	this.dispose();
	    	new CurrenciesConverterView();
	    }    
	    protected void createGraph(ActionEvent evt) 
	    {
	    	if(tableCur.getSelectedRow()==-1)
	    		if(tableCur.getRowCount()==0)
	    			JOptionPane.showMessageDialog(this,"Table is empty");
	    		else
	    			JOptionPane.showMessageDialog(this,"You need to select a currency");
	    		else
	    		{//need to create list first
	    			return;
	    		}	
	    }
	    protected void removeCurrency(ActionEvent evt) 
	    {
	    	if(tableCur.getSelectedRow()==-1)
	    		if(tableCur.getRowCount()==0)
	    			JOptionPane.showMessageDialog(this,"Table is empty");
	    		else
	    			JOptionPane.showMessageDialog(this,"You need to select a currency");
	    		else
	    		{
	    			stockCurrencies.RemoveAt(tableCur.getSelectedRow());
	    			tableCur.setModel ();
	    		}	
		}
	    private void addNewCurrency(java.awt.event.ActionEvent evt)
	    {
	    	
	    	if(stockCurrencies.addNewCurrency(Name.getText(), Currencycode.getText(),Unit.getText(),
	    							Country.getText(),Change.getText(),Rate.getText()))
	    		tableCur.setModel ();
	    	else
	    		JOptionPane.showMessageDialog(this, "Please enter a valid amount");
	    }

	    // Variables declaration - do not modify  
	    private CurJButton buttonSwitch;
	    private CurJButton addNewCurrency ; 
	    private CurJButton removeCurrency ; 
	    private CurJButton refresh;
	    private CurJButton graph;
	    private CurJLabel instructionAddCurrency ;
	    private CurJLabel instructionTable ;
	    private javax.swing.JScrollPane ScrollTable;
	    private CurTable tableCur;
	    private CurJtext displayTime;
	    private CurJtext Name;
	    private CurJtext Currencycode;
	    private CurJtext Unit;
	    private CurJtext Country;
	    private CurJtext Change;
	    private CurJtext Rate;
	    private CurJtext searchVirable;	    
	    private JComboBox<String> comboBoxSelect;

}
