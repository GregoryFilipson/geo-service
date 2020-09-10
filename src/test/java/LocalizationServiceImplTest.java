import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @Test
    public void test_method_locale() {
        LocalizationServiceImpl localizationServiceImpl = Mockito.spy(LocalizationServiceImpl.class);
        Assert.assertEquals("Welcome", localizationServiceImpl.locale(Country.USA));

    }
}
