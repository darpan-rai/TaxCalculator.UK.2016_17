package taxcalculator;

/**
 * @author Darpan
 */
public class TaxBands 
{
    //fields decleration
    private static float baseRate;
    private static float baseThreshold;
    private static float monthlyBaseThreshold;
    
    private static float higherRate;
    private static float higherThreshold;
    private static float monthlyHigherThreshold;
    
    private static float additionalRate;
    
    
    
    
    
    /**
     * Constructor initialises the fields.
     */
    public TaxBands()
    {
        this.baseRate = 0.20f;                      // base tax rate
        this.higherRate = 0.40f;                    // higher tax rate
        this.additionalRate = 0.45f;                // tax rate for income over higher threshold
        
        this.baseThreshold = 32000.00f;             // annual base threshold
        this.higherThreshold = 150000.00f;          // annual higher tax threshold
        
        this.monthlyBaseThreshold = 2666.67f;        // monthly base threshold
        this.monthlyHigherThreshold = 12500.00f;    //monthly higher threshold
    }
    
    
    
    
    
    /**
     * @return the tax rate to calculate the income within the base threshold. 
     */
    public static float getBaseRate()
    {
        return baseRate;
    }
    
    
    /**
     * @return the annual base threshold to calculate the tax on the income.
     */
    public static float getBaseThreshold()
    {
        return baseThreshold;
    }
    
    
    /**
     * @return the tax rate to calculate the income within the base threshold 
     * and the higher threshold. 
     */
    public static float getHigherRate()
    {
        return higherRate;
    }
    
    
    /**
     * @return the annual base threshold to calculate the tax on the income.
     */
    public static float getHigherThreshold()
    {
        return higherThreshold;
    }
    
    
    /**
     * @return the tax rate to calculate the income that is above the higher
     * threshold. 
     */
    public static float getAdditionalRate()
    {
        return additionalRate;
    }
    
    
    /**
     * @return the monthly base threshold to calculate the tax on the income.
     */
    public static float getMonthlyBaseThreshold()
    {
        return monthlyBaseThreshold;
    }
    
    
    /**
     * @return the monthly base threshold to calculate the tax on the income.
     */
    public static float getMonthlyHigherThreshold()
    {
        return monthlyHigherThreshold;
    }
}
