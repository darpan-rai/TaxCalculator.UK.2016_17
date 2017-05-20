
package taxcalculator;

/**
 * @author Darpan
 */
public class NIContribution 
{
    private static float standardContribution;
    private static float higherContribution;
    
    private static float totalContribution;
    
    
    
    
    
    /**
     * Multiple constructors are declared.
     * 
     * First constructor is only called when the user is ineligible to pay tax.
     */
    public NIContribution()
    {
        this.totalContribution = 0.0f;
    }
    
    /**
     * This constructor is called every time when the user is eligible to pay
     * the NI Contribution irrespective of the band he/she falls under.
     * @param input any value in excess of the allowance and between £8060.00 and
     * £43004.00 that is taxed at 12%.
     */
    public NIContribution(float input)
    {
        this.standardContribution = 0.0f;
        calculateStandardContribution(input);
    }
    
    /**
     * This constructor is called when the user is eligible to pay for NI Contribution
     * and the income is in excess of £43004.00 and is taxed at 2%.  This constructor 
     * chains with the constructor above as there are two calculations required
     * to compute the tax.
     * @param input The value of this variable is 43004.00 and is taxed at 12%.
     * The first line chains to the constructor above.
     * @param excessValue the value of this paramenre is anything above 43004.00.
     * This is taxed at 2%.
     */
    public NIContribution(float input, float excessValue)
    {
        this(input);
        this.higherContribution = 0.0f;
        calculateHigherContribution(excessValue);
    }
    
    
    
    
    
    /**
     * @returns the NI Contribution that has been taxed at the standard rate.
     */
    private float calculateStandardContribution(float input)
    {
        NIContributionBand NICB = new NIContributionBand();
        standardContribution = NICB.getStandardRate() * input;
        
        return standardContribution;
    }
    
    
    /**
     * @returns the NI Contribution that has been taxed at the higher rate.
     */
    private float calculateHigherContribution(float input)
    {
        NIContributionBand NICB = new NIContributionBand();
        higherContribution = NICB.getHigherRate() * input;
        
        return higherContribution;
    }
    
    
    /**
     * @return the total NI Contribution of the user.
     */
    public static float getContribution()
    {
        totalContribution = 0.0f;
        totalContribution = standardContribution + higherContribution;
        
        return totalContribution;
    }
    
    
    public static String getNIText()
    {
        String text = " ";
        TaxCalculator TC = new TaxCalculator();
        if(TC.getNICounter() == 0)
        {
            text ="You are not eligible to pay for NI Contribution.";
        }
        else if (TC.getNICounter() == 1)
        {
            text = String.format("Your NI Contribution was calculated at %s.", String.valueOf(NIContributionBand.getStandardRate()*100)+"%");
            
        }
        else if (TC.getNICounter() == 2)
        {
            if(View.getCalculationType() == "monthly")
            {
                text = String.format("Your NI Contribution was calculated at %s for\nearnings upto £%s and at %s for the remaining\namount", String.valueOf(NIContributionBand.getStandardRate()*100)+"%", String.valueOf(NIContributionBand.getMonthlyUpperThreshold()), String.valueOf(NIContributionBand.getHigherRate()*100)+"%");
            }
            else
            {
                text = String.format("Your NI Contribution was calculated at %s for\nearnings upto £%s and at %s for the remaining\namount", String.valueOf(NIContributionBand.getStandardRate()*100)+"%", String.valueOf(NIContributionBand.getUpperThreshold()), String.valueOf(NIContributionBand.getHigherRate()*100)+"%");
            }
        }
        
        return text;
    }
}
