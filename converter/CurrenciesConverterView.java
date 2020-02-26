/*
 * convert.java
 * To change this template file, choose Tools | Templates
 * Created on 2016 , final project  created by ortal & idan
 * Java course Shenkar - Engineering. Design. Art
 * Professor Haim Michael and practitioner Tomer
 */
package converter;
import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class CurrenciesConverterView extends StructuredComponentGui
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4572632024638401660L;
	/**
     * Creates GUI application that convert from currency to other currency
     */
	public CurrenciesConverterView() 
	{
		
        initComponents();
        showConverter(); 
    }

    private void initComponents()
    {   
    	curTo = new curJList();
        curFrom = new curJList(); 
    	converterButton = new CurJButton("Convert");
        buttonSwitch = new CurJButton("Switch");
        instructionAmount = new CurJLabel("Enter Amount :"); 
        instructionTable = new CurJLabel("Table currency:");
        instructionFrom = new CurJLabel("Currency From :");
        instructionTo = new CurJLabel("Currency To :");
        resultDisplay = new CurJtext(Color.BLUE, SystemColor.info,"",false,false);
        displayTime = new CurJtext(Color.BLUE, SystemColor.info,"",false,false);
        curAmount = new CurJtext(Color.BLUE, Color.white,"Amount",true,true);
        jScrollPane1 = new javax.swing.JScrollPane(curTo); 
        jScrollPane2 = new javax.swing.JScrollPane(curFrom);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	addEvents();      

    }// </editor-fold>
    
    private void showConverter()
    {

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup().addComponent(instructionAmount)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(curAmount, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                        .addComponent(instructionFrom)
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(resultDisplay, Alignment.LEADING).addComponent(converterButton,
                                        Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(instructionTo)
                        .addGroup(layout.createSequentialGroup().addComponent(instructionTable)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(buttonSwitch, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(displayTime, Alignment.LEADING).addComponent(jScrollPane1,
                                        Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                .addContainerGap(63, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
                .addGap(28)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(instructionAmount, GroupLayout.PREFERRED_SIZE, 24,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(buttonSwitch, GroupLayout.PREFERRED_SIZE, 39,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(instructionTable, GroupLayout.PREFERRED_SIZE, 24,
                                                GroupLayout.PREFERRED_SIZE)))
                                .addGap(14)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(instructionFrom)
                                        .addComponent(instructionTo, GroupLayout.PREFERRED_SIZE, 24,
                                                GroupLayout.PREFERRED_SIZE)))
                        .addComponent(curAmount, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addGap(7)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(14)
                                .addComponent(converterButton, GroupLayout.PREFERRED_SIZE, 46,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(resultDisplay,
                                        GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup().addGap(18).addComponent(displayTime,
                                GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                .addGap(21)));
        this.setTitle("Currencies Data From Bank Israel");
        if (stockCurrencies.isUpdate())
            displayTime.setText("Last update: " + stockCurrencies.getLastUpdate());
        else
            JOptionPane.showMessageDialog(this, "Error could not get to data");
        getContentPane().setLayout(layout);
        pack();

    }

    private void addEvents() {
        buttonSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                switchButtonJframe(evt);
            }
        });
        converterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                converterButtonActionPerformed(evt);
            }
        });
    }

    private void switchButtonJframe(final java.awt.event.ActionEvent evt) {
        this.dispose();
        new CurrenciesTableView();
    }

    private void converterButtonActionPerformed(final java.awt.event.ActionEvent evt) {

        // Get the amount to convert
        final String curAmountText = curAmount.getText();
        if (!stockCurrencies.isUpdate()) {
            JOptionPane.showMessageDialog(this, "Error could not get to data");
            return;
        }
        if (!Currencie.checkIfInt(curAmountText)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount");
            return;
        }
        final String curFromText = curFrom.getSelectedValue();
        final String curToText = curTo.getSelectedValue();
        //get currency from and to
        resultDisplay.setText("Result: "+stockCurrencies.convertMoney(curFromText.split(" - ")[1],
        		curToText.split(" - ")[1] ,Double.parseDouble(curAmountText)));
   
        
    }                                               


    
    // Variables declaration - do not modify                     
    private CurJButton buttonSwitch;
    private CurJButton converterButton;
   
    private javax.swing.JList<String> curFrom;
    private javax.swing.JList<String> curTo;
    private CurJtext curAmount;
    private CurJtext resultDisplay;
    private CurJtext displayTime;
    private CurJLabel instructionAmount;
    private CurJLabel instructionFrom;
    private CurJLabel instructionTable;
    private CurJLabel instructionTo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
   
    // End of variables declaration                   
}
