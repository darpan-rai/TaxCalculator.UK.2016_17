package taxcalculator;

/**
 * @author Darpan
 */
public class NIContributionBand 
{
    private static float standardRate;
    private static float higherRate;
    
    private static float lowerThreshold;
    private static float lowerMonthlyThreshold;
    private static float upperThreshold;
    private static float upperMonthlyThreshold;
    
    private static float niAllowance;
    private static float niMonthlyAllowance;
    
    
    
    
    
    public NIContributionBand()
    {
        standardRate = 0.12f;           
        higherRate = 0.02f;
        
        lowerThreshold = 8060.00f;
        lowerMonthlyThreshold = 671.67f;
        upperThreshold = 43004.00f;
        upperMonthlyThreshold = 3583.67f;
        
        niAllowance = 8060.00f;
        niMonthlyAllowance = 671.67f;
    }
    
    
    
    
    
    /**
     * @return the NI Contribution rate to calculate the NI Contribution for
     * incomes within the lower and the upper threshold.
     */
    public static float getStandardRate()
    {
        return standardRate;
    }
    
    
    /**
     * @return the NI Contribution rate to calculate the NI Contribution for
     * incomes over the upper threshold.
     */
    public static float getHigherRate()
    {
        return higherRate;
    }
    
    
    /**
     * @return the lower threshold to check the eligibility for NI Contributions.
     */
    public static float getLowerThreshold()
    {
        return lowerThreshold;
    }
    
    
    /**
     * @return the lower threshold to check the eligibility for NI Contributions.
     */
    public static float getMonthlyLowerThreshold()
    {
        return lowerMonthlyThreshold;
    }
    
    
    /**
     * @return the upper threshold to check the NI Contribution rate for the user.
     */
    public static float getUpperThreshold()
    {
        return upperThreshold;
    }
    
    
    /**
     * @return the upper threshold to check the NI Contribution rate for the user.
     */
    public static float getMonthlyUpperThreshold()
    {
        return upperMonthlyThreshold;
    }
    
    
    /**
     * @return the upper threshold to check the NI Contribution rate for the user.
     */
    public static float getNIAllowance()
    {
        return niAllowance;
    }
    
    
    /**
     * @return the upper threshold to check the NI Contribution rate for the user.
     */
    public static float getNIMonthlyAllowance()
    {
        return niMonthlyAllowance;
    }
}
