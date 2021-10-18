package tripPricer.service;

import org.springframework.stereotype.Service;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

@Service
public class TripPricerServiceImpl implements TripPricerService {
    private final TripPricer tripPricer;

    public TripPricerServiceImpl(TripPricer tripPricer){
        this.tripPricer = tripPricer;
    }

    @Override
    public List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) {
        return tripPricer.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

    @Override
    public String getProviderName(String apiKey, int adults) {
        return tripPricer.getProviderName(apiKey, adults);
    }
}
