package by.bsu.finalproject.validator;

import org.junit.Assert;
import org.junit.Test;

public class PersonInformationValidatorTest {

    private String name = "Артем";
    private String secondName = "Kuzmik";
    private int weight = 133;
    private int height = 188;

    @Test
    public void isNameValidTest() {
        boolean isValidFats = PersonalInformationValidator.INSTANCE.isValidName(name);
        Assert.assertTrue(isValidFats);

    }

    @Test
    public void isSecondNameValidTest() {
        boolean isValidDiet = PersonalInformationValidator.INSTANCE.isValidName(secondName);
        Assert.assertTrue(isValidDiet);

    }

    @Test
    public void isHeightValidTest() {
        boolean isValidDiet = PersonalInformationValidator.INSTANCE.isValidWeight(height);
        Assert.assertTrue(isValidDiet);
    }

    @Test
    public void isWeightValidTest() {
        boolean isValidDiet = PersonalInformationValidator.INSTANCE.isValidHeight(weight);
        Assert.assertTrue(isValidDiet);
    }
}
