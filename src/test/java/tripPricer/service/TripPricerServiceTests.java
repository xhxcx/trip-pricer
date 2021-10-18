package tripPricer.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class TripPricerServiceTests {

    private final TripPricer tripPricerMock = Mockito.mock(TripPricer.class);
    private final TripPricerServiceImpl tripPricerService = new TripPricerServiceImpl(tripPricerMock);

    @Test
    public void getPriceTest(){
        Provider provider = new Provider(new UUID(1,2),"providerName",10);
        List<Provider> expectedList = Collections.singletonList(provider);

        when(tripPricerMock.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(expectedList);

        List<Provider> resultList = tripPricerService.getPrice("apiKey", UUID.randomUUID(), 2, 1, 3, 10);

        Assert.assertEquals(1, resultList.size());
        Assert.assertTrue(resultList.containsAll(expectedList));
    }

    @Test
    public void getProviderNameTest(){
        when(tripPricerMock.getProviderName("apiKey", 2)).thenReturn("providerName");

        Assert.assertEquals("providerName", tripPricerService.getProviderName("apiKey", 2));
    }

}
