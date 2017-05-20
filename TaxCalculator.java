package taxcalculator;

import java.math.BigDecimal;

/**
 * @author Darpan
 */
public class TaxCalculator 
{
    //fields decleration
    private static float input;
    private static int counter;
    private static int niCounter;
    private static float taxableIncome;
    private static float niTaxableIncome;
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        input = View.getInput();
        
        TaxCalculator TC = new TaxCalculator();
        TC.checkEligibility(input);
        TC.checkNIEligibility(input);
    }
    
    
    
    
    
    /**
     * This method checks the eligibility of the user to pay tax.
     * @param input this is the user input
     */
    private void checkEligibility(float input)
    {
        if(View.getCalculationType() == "monthly")
        {
            PersonalAllowance PA = new PersonalAllowance();
            if(input > PA.getMonthlyPersonalAllowance())
            {
                calculateMonthlyTaxableIncome(input);
                calculateMonthlyTax(taxableIncome);
            }
            else
            {
                counter = 0;
                taxableIncome = 0.0f;
                IncomeTax AIT = new IncomeTax();
            }
        }
        else if(View.getCalculationType() == "annually")
        {
            PersonalAllowance PA = new PersonalAllowance();
            if(input > PA.getAnnualPersonalAllowance())
            {

                calculateAnnualTaxableIncome(input);
                calculateAnnualTax(taxableIncome);

            }
            else
            {
                counter = 0;
                taxableIncome = 0.0f;
                IncomeTax AIT = new IncomeTax();

            }
        }
        else
        {
            //DO NOTHING
        }
    }
        
    
    /**
     * This method checks the eligibility of the user for NI Contribution.
     * @param input this is the user input
     */
    private void checkNIEligibility(float input)
    {
        if(View.getCalculationType() == "monthly")
        {
            NIContributionBand NICB = new NIContributionBand();
            if(input > NICB.getNIMonthlyAllowance() )
            {
                calculateMonthlyNIContribution(input);
            }
            else 
            {
                niCounter = 0;
                niTaxableIncome = 0.0f;
                NIContribution NIC = new NIContribution();
            }
        }
        else if (View.getCalculationType() == "annually")
        {
            NIContributionBand NICB = new NIContributionBand();
            if(input > NICB.getNIAllowance() )
            {
                calculateAnnualNIContribution(input);
            }
            else 
            {
                niCounter = 0;
                niTaxableIncome = 0.0f;
                NIContribution NIC = new NIContribution();
            }
        }
        else
        {
            //DO NOTHING
        }
    }
    
    
    /**
     * This method calculates the taxable income for the user
     * @param input is the user input
     */
    private void calculateAnnualTaxableIncome(float input)
    {
        PersonalAllowance PA = new PersonalAllowance();
        taxableIncome = input - PA.getAnnualPersonalAllowance();
    }
     
    
    /**
     * This method calculates the taxable income for the user
     * @param input is the user input
     */
    private void calculateMonthlyTaxableIncome(float input)
    {
        PersonalAllowance PA = new PersonalAllowance();
        taxableIncome = input - PA.getMonthlyPersonalAllowance();
    }
    
    
    /**
     * This method checks for the tax band that the user qualifies and then 
     * invokes necessary constructors to calculate the tax.
     * @param input is the taxable income that was calculated
     */
    private void calculateAnnualTax(float input)
    {
        TaxBands TB = new TaxBands();
        if(input <= TB.getBaseThreshold())
        {
            counter = 1;
            IncomeTax AIT = new IncomeTax(input);
            
        }
        else if (input >TB.getBaseThreshold() && input <= TB.getHigherThreshold())
        {
            counter = 2;
            float highValue = input - TB.getBaseThreshold();
            IncomeTax AIT = new IncomeTax(TB.getBaseThreshold(), highValue);
        }
        else if (input > TB.getHigherThreshold())
        {
            counter = 3;
            float excessValue = input - TB.getHigherThreshold();
            IncomeTax AIT = new IncomeTax(TB.getBaseThreshold(), TB.getHigherThreshold(), excessValue);
        }
    }
    
    
    /**
     * This method checks for the tax band that the user qualifies and then 
     * invokes necessary constructors to calculate the tax.
     * @param input is the taxable income that was calculated
     */
    private void calculateMonthlyTax(float input)
    {
        TaxBands TB = new TaxBands();
        if(input <= TB.getMonthlyBaseThreshold())
        {
            counter = 1;
            IncomeTax AIT = new IncomeTax(input);
            
        }
        else if (input >TB.getMonthlyBaseThreshold() && input <= TB.getMonthlyHigherThreshold())
        {
            counter = 2;
            float highValue = input - TB.getMonthlyBaseThreshold();
            IncomeTax AIT = new IncomeTax(TB.getMonthlyBaseThreshold(), highValue);
        }
        else if (input > TB.getMonthlyHigherThreshold())
        {
            counter = 3;
            float excessValue = input - TB.getMonthlyHigherThreshold();
            IncomeTax AIT = new IncomeTax(TB.getMonthlyBaseThreshold(), TB.getMonthlyHigherThreshold(), excessValue);
        }
    }
    
    
    /**
     * This method checks for the monthly NI tax band that the user qualifies and then 
     * invokes necessary constructors to calculate the tax.
     * @param input is the amount that the user inputs
     */
    private void calculateMonthlyNIContribution(float input)
    {
        NIContributionBand NICB = new NIContributionBand();
        float niTaxable = input - NICB.getMonthlyLowerThreshold();
        if(input > NICB.getMonthlyLowerThreshold() && input <= NICB.getMonthlyUpperThreshold())
        {
            niCounter = 1;
            NIContribution NIC = new NIContribution(niTaxable);
        }
        else if (input > NICB.getMonthlyUpperThreshold())
        {
            niCounter = 2;
            float excessValue = input - NICB.getMonthlyUpperThreshold();
            NIContribution NIC = new NIContribution(niTaxable, excessValue);
        }
    }
    
    
    /**
     * This method checks the annual NI tax band that the user qualifies and then 
     * invokes necessary constructors to calculate the tax.
     * @param input is the amount that the user inputs
     */
    private void calculateAnnualNIContribution(float input)
    {
        NIContributionBand NICB = new NIContributionBand();
        float niTaxable = input - NICB.getLowerThreshold();
        if(input > NICB.getLowerThreshold() && input <= NICB.getUpperThreshold())
        {
            niCounter = 1;
            NIContribution NIC = new NIContribution(niTaxable);
        }
        else if (input > NICB.getUpperThreshold())
        {
            niCounter = 2;
            float excessValue = input - NICB.getUpperThreshold();
            NIContribution NIC = new NIContribution(niTaxable, excessValue);
        }
    }
     
    
    /**
     * @return This method returns the taxable income to be displayed in the user interface
     */
    public static String getAnnualTaxableIncome()
    {
        return String.valueOf(taxableIncome);
    }
    
    
    /**
     * @return This method returns the taxable income to be displayed in the user interface
     */
    public static String getMonthlyTaxableIncome()
    {
        return String.valueOf(taxableIncome);
    }
    
    
    /**
     * @return the counter value to display the appropriate text for Income Tax.
     */
    public static int getCounter()
    {
        return counter;
    }
    
    
    /**
     * @return the counter value to display the appropriate text for NI Contribution.
     */
    public static int getNICounter()
    {
        return niCounter;
    }

    
    /**
     * This method has been copied form 
     * https://stackoverflow.com/questions/8911356/whats-the-best-practice-to-round-a-float-to-2-decimals
     * this was provided by Jav_Rock.
     * 
     * This method limits the decimal places to two.
     * @param input the float value that requires trimming 
     * @param decimalPlace number of decimal places
     * @return the trimmed value.
     */
    public static BigDecimal round(float input, int decimalPlace)
    {
        BigDecimal bd = new BigDecimal(Float.toString(input));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
}
