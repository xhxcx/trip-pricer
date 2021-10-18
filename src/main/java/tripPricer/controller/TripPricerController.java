package tripPricer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;
import tripPricer.service.TripPricerService;

import java.util.List;
import java.util.UUID;

@RestController
public class TripPricerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TripPricerController.class);

    @Autowired
    private TripPricerService tripPricerService;

    /**
     * Endpoint to get a list of providers with their price
     * @param apiKey
     * @param attractionId
     * @param adults
     * @param children
     * @param nightsStay
     * @param rewardsPoints
     * @return ResponseEntity and provider list as response content
     */
    @GetMapping("/getPrice")
    public ResponseEntity<List<Provider>> getPrice(@RequestParam String apiKey, @RequestParam UUID attractionId,
                                                   @RequestParam int adults, @RequestParam int children,
                                                   @RequestParam int nightsStay, @RequestParam int rewardsPoints) {
        LOGGER.info("GET /getPrice apiKey = " + apiKey + ", attractionId = " + attractionId + ", adults = " + adults + ", children = " + children
                    + ", nightsStay = " + nightsStay + ", rewardsPoints = " + rewardsPoints);
        return new ResponseEntity<>(tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints), HttpStatus.OK);
    }

    /**
     * Endpoint to get a provider name
     * @param apiKey
     * @param adults
     * @return ResponseEntity with provider name as response content
     */
    @GetMapping("/getProviderName")
    public ResponseEntity<String> getProviderName(@RequestParam String apiKey, @RequestParam int adults) {
        LOGGER.info("GET /getProviderName apiKey = " + apiKey + ", adults = " + adults);
        return new ResponseEntity<>(tripPricerService.getProviderName(apiKey, adults), HttpStatus.OK);
    }
}
