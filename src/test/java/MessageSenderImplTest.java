import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    public void test_method_sends_russian_message_if_ip_from_Russia(){
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.any(String.class))).thenReturn(new Location("Москва", Country.RUSSIA,
                null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Добро пожаловать";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(geoService.byIp("192.1.1.1").getCountry().toString(), localizationService.locale(Country.RUSSIA));

        messageSender.send(headers);

        Assert.assertEquals(expected, messageSender.send(headers));
    }

    @Test
    public void test_method_sends_russian_message_if_ip_from_USA(){
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.any(String.class))).thenReturn(new Location("Washington",
                Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Welcome";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(geoService.byIp("96.1.1.1").getCountry().toString(), localizationService.locale(Country.USA));

        messageSender.send(headers);

        Assert.assertEquals(expected, messageSender.send(headers));
    }

}
