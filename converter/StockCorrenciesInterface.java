package converter;

import java.util.ArrayList;

public interface StockCorrenciesInterface {

	String convertMoney(String curFrom, String curTo, double amount);

	/**
	    *
	    * @param curFrom
	    * @param curTo
	    * @param amount
	    * @param urlS
	    * @return
	    */
	String convert(String curFrom, String curTo, double amount);

	String getLastUpdate();

	String[][] getCurrencyTable();

	boolean isUpdate();

	ArrayList<Currencie> getCurrencyNodeList();

	void setCurrency(ArrayList<Currencie> currencyList);

	boolean addNewCurrency(String name, String curCode, String unit, String country, String change, String rate);

	int size();

	Currencie getCurrency(int i);

	String[] getCurrenciesList();

	void RemoveAt(int selectedRow);

	void refrechList();

}