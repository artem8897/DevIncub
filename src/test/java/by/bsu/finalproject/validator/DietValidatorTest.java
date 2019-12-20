package by.bsu.finalproject.validator;

import org.junit.Assert;
import org.junit.Test;

public class DietValidatorTest {

    private String dietType = "low calorite";
    private String fats = "133";

    @Test
    public void isParameterValidTest(){

        boolean isValidFats = DietValidator.INSTANCE.isValidParameter(fats);
        Assert.assertTrue(isValidFats);

    }
    @Test
    public void isDietTypeValidTest(){
        boolean isValidDiet = DietValidator.INSTANCE.isValidDietType(dietType);
        Assert.assertTrue(isValidDiet);
    }
}
