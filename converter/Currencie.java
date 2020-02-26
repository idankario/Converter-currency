package converter;



public class Currencie 
{
	private String unit;
	private String rate;
	private String curCode;
	private String country;
	private String name;
	private String change;
	
	public String getRate() {
		return rate;
	}

	public String[] getStringInfo() 
	{
		return new String[]{name ,unit, curCode,country, rate,change};
	}

	public double convertCurrency()
	{
		return Double.parseDouble(rate)/Double.parseDouble(unit);
	}

	/**
	 * @param unit
	 * @param rate
	 * @param curCode
	 * @param country
	 * @param name
	 * @param change
	 */
	public Currencie(String name,String unit,  String curCode, 
					  String country,  String rate,String change) 
	{
		super();
		this.unit = unit;
		this.rate = rate;
		this.curCode = curCode;
		this.country = country;
		this.name = name;
		this.change = change;
	}
	 
	@Override
	public String toString() {
		return "Currencie [unit=" + unit + ", rate=" + rate + ", curCode=" + curCode + ", country=" + country
				+ ", name=" + name + ", change=" + change + "]";
	}



	/**
	 * 
	 */
	public Currencie() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean checkValidationOfVariables(String unit, String rate, String curCode, String country,
												String name, String change) 
	{
		int num = 0;
		if(checkIfInt(unit))
			num=Integer.parseInt(unit);
		else
			return false;
		return (num==1||num==10||num==100)&&checkIfLetter(name)&&checkIfLetter(curCode)
			   &&checkIfLetter(country.replaceAll("\\s",""))&&checkIfInt(rate.replaceAll("\\.", ""))
			   &&checkIfInt(change.replaceAll("-","").replaceAll("\\.", ""));
	}
	
	public static boolean checkIfLetter(String myString)
	{
		if(myString.equals(""))
			return false;
        //Check if is letter
		return myString.chars().allMatch(Character::isLetter);
	}
	
	public static boolean checkIfInt(String myString)
	{
		if(myString.equals(""))
			return false;
        //Check if is digit
		return myString.chars().allMatch(Character::isDigit);
	}



	public void setRate(String rate)
	{
		this.rate = rate;
	}

	public String getCurCode() 
	{
		return curCode;
	}

	public void setCurCode(String curCode) 
	{
		this.curCode = curCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getChange() 
	{
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String currencyText() 
	{
		
		return new String(country.replaceAll("\\s","")+" - "+curCode.replaceAll("\\s",""));
	}
	
}
