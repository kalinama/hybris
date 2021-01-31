package concerttours.controller;

import concerttours.data.TourData;
import concerttours.facades.TourFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;
import static concerttours.constants.ConcerttoursConstants.CATALOG_VERSION_NAME;

@Controller
public class TourDetailsController {
    @Resource(name = "defaultTourFacade")
    private TourFacade tourFacade;
    @Resource
    private CatalogVersionService catalogVersionService;

    @GetMapping("tours/{tourId}")
    public String showTourDetails(@PathVariable String tourId, Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        TourData tourData = tourFacade.getTourDetails(tourId);
        model.addAttribute("tour", tourData);
        return "tourDetails";
    }
}
