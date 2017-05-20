package taxcalculator;

/**
 * @author Darpan
 */
public class IncomeTax 
{
    //fields decleration
    private static float baseTax;
    private static float higherTax;
    private static float additionalTax;
    
    private static float tax;
    
    
    
    
    
    /**
     * Multiple constructors are declared.
     * 
     * First constructor is only called when the user is ineligible to pay tax.
     */
    public IncomeTax()
    {
        tax = 0.0f;
    }
    
    /**
     * This constructor is called every time when the user is eligible to pay
     * tax irrespective of the band he/she falls under.
     * @param taxableIncome is any value upto £32000.00 that is taxed at 20%
     */
    public IncomeTax(float taxableIncome)
    {
        baseTax = 0.00f;
        calculateBaseTax(taxableIncome);
    }
    
    /**
     * This constructor is called when the user is eligible to pay tax and the
     * taxable income falls between £32000.00 and £150001.00.  This constructor 
     * chains with the constructor above as there are two calculations required
     * to compute the tax.
     * @param baseThreshold the value of this parametre is 32000.00 and is
     * calculated at 20%. The first line chains with the constructor above and 
     * used this parametre.
     * @param highValue the value of this parametre is anything above 32000.00
     * but below 150001.00. This value is taxed at 40%.
     */
    public IncomeTax(float baseThreshold, float highValue)
    {
        this(baseThreshold);
        higherTax = 0.00f;
        calculateHigherTax(highValue);
    }
    
    /**
     * This constructor is called when the user is eligible to pay tax and the
     * taxable income is over £150000.00.  This constructor chains with the 
     * constructors above as there are three calculations required to compute 
     * the tax.
     * @param baseThreshold the value of this parametre is 32000.00 and is
     * calculated at 20%. The first line chains with the constructor above and 
     * used this parametre.
     * @param highValue the value of this parametre is 150000.00. This value 
     * is taxed at 40%.
     * @param excessValue the value of this parametre is anything over 150000.00.
     * It is taxed at 45%.
     */
    public IncomeTax(float baseThreshold, float higherThreshold, float excessValue)
    {
        this(baseThreshold, higherThreshold);
        additionalTax = 0.00f;
        calculateAdditionalTax(excessValue);
    }
    
    
    
    
    
    /**
     * This method calculates the tax at base rate.
     * @param taxableIncome values could be anything upto the first 32000.00 of 
     * the taxable income.
     */
    public static void calculateBaseTax(float taxableIncome)
    {
        
        baseTax = TB.getBaseRate() * taxableIncome;
    }
    
    
    /**
     * This method calculates the tax at higher rate.
     * @param highValue values could be anything between 32001.00 to 150000.00 
     */
    public static void calculateHigherTax(float highValue)
    {
        TaxBands TB = new TaxBands();
        higherTax = TB.getHigherRate() * highValue;
    }
    
    
    /**
     * This method calculates the tax at the additional rate.
     * @param excessValue any value that is over 150000.00 of the taxable income.
     */
    public static void calculateAdditionalTax(float excessValue)
    {
        TaxBands TB = new TaxBands();
        additionalTax = TaxBands.getAdditionalRate() * excessValue;
    }
    
    
    /**
     * This method returns the text that would be displayed in the user interface.
     * @return 
     */
    public static String getText()
    {
        String text ="";
        TaxCalculator TC = new TaxCalculator();
        if(TC.getCounter() == 0)
        {
            text = "You are ineligible to pay tax.";
        }
        else 
        {
            TaxBands TB = new TaxBands();
            if(TC.getCounter() == 1)
            {
                text = String.format("Your tax is calculated at %s.\n", String.valueOf(TaxBands.getBaseRate()*100)+"%\n"+NIContribution.getNIText());
            }
            else if(TC.getCounter() == 2)
            {
                if(View.getCalculationType() == "annually")
                    text = String.format("Your tax for the first £%s is calculated at\n%s. The remaining amount is taxed at %s.\n", String.valueOf(TB.getBaseThreshold()), String.valueOf(TB.getBaseRate()*100+"%"), String.valueOf(TB.getHigherRate()*100+"%\n")+NIContribution.getNIText());
                else
                    text = String.format("Your tax for the first £%s is calculated at\n%s. The remaining amount is taxed at %s.\n", String.valueOf(TB.getMonthlyBaseThreshold()), String.valueOf(TB.getBaseRate()*100+"%"), String.valueOf(TB.getHigherRate()*100+"%\n")+NIContribution.getNIText());
            }
            else if(TC.getCounter() == 3)
            {
                if(View.getCalculationType() == "annually")
                    text = String.format("Your tax for the first £%s is calculated at\n%s. The tax from £%s to £%s is calculated \nat %s, and the remaining amount is taxed at %s.\n", String.valueOf(TB.getBaseThreshold()), String.valueOf(TB.getBaseRate()*100+"%"), String.valueOf(TB.getBaseThreshold()), String.valueOf(TB.getHigherThreshold()), String.valueOf(TB.getHigherRate()*100+"%"), String.valueOf(TB.getAdditionalRate()*100+"%\n")+NIContribution.getNIText());
                else
                    text = String.format("Your tax for the first £%s is calculated at\n%s. The tax from £%s to £%s is calculated \nat %s, and the remaining amount is taxed at %s.\n", String.valueOf(TB.getMonthlyBaseThreshold()), String.valueOf(TB.getBaseRate()*100+"%"), String.valueOf(TB.getMonthlyBaseThreshold()), String.valueOf(TB.getMonthlyHigherThreshold()), String.valueOf(TB.getHigherRate()*100+"%"), String.valueOf(TB.getAdditionalRate()*100+"%\n")+NIContribution.getNIText());
            }
        }
        return text;
    }
        
    
    /**
     * @return this method returns tax when invoked
     */
    public static float getIncomeTax()
    {
        tax = 0.0f;
        tax = baseTax+higherTax+additionalTax;
        return tax;
    }
    
    
}
