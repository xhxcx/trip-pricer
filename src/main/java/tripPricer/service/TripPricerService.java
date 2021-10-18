package tripPricer.service;

import org.springframework.stereotype.Service;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

@Service
public interface TripPricerService {
    /**
     * Get provider list with their price regarding parameters of the stay
     *
     * @param apiKey
     * @param attractionId
     * @param adults
     * @param children
     * @param nightsStay
     * @param rewardsPoints
     * @return provider list
     */
    List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints);

    /**
     * Get name of the provider for a given apiKey and number of adults
     * @param apiKey
     * @param adults
     * @return name of the provider as String
     */
    String getProviderName(String apiKey, int adults);
}
