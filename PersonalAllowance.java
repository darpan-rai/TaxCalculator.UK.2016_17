package taxcalculator;

/**
 * @author Darpan
 */
public class PersonalAllowance 
{
    //fields declared
    private static float annualPersonalAllowance;
    private static float monthlyPersonalAllowance;
    
    
    
    
    
    /**
     * constructor initialises the fields
     */
    public PersonalAllowance()
    {
        this.annualPersonalAllowance = 11000.00f;
        this.monthlyPersonalAllowance = 916.67f;
    }
    
    
    
    
    
    /**
     * @return the annual personal allowance
     */
    public static float getAnnualPersonalAllowance()
    {
        return annualPersonalAllowance;
    }
    
    
    /**
     * @return the monthly personal allowance
     */
    public static float getMonthlyPersonalAllowance()
    {
        return monthlyPersonalAllowance;
    }
}
