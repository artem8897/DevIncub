package by.bsu.finalproject.validator;
import by.bsu.finalproject.entity.UserType;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorUserTest {

    private final String password = "199788Art";
    private final String mail = "mails@mail.com";
    private final String username = "uSerName";
    private final UserType userType = UserType.USER;

    @Test
    public void isUserNameValidTest() {

        boolean isValidName = UserValidator.INSTANCE.isUsernameValid(username);
        Assert.assertTrue(isValidName);

    }

    @Test
    public void isEmailValidTest() {
        boolean isValidEmail = UserValidator.INSTANCE.isEmailValid(mail);
        Assert.assertTrue(isValidEmail);

    }

    @Test
    public void isPasswordValidTest() {
        boolean isValidPassword = UserValidator.INSTANCE.isPasswordValid(password);
        Assert.assertTrue(isValidPassword);
    }

    @Test
    public void isValidUserTypeTest() {
        boolean isUserTypeValid = UserValidator.INSTANCE.isValidUserType(userType.toString());
        Assert.assertTrue(isUserTypeValid);
    }
}
