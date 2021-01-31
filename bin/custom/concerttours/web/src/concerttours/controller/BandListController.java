package concerttours.controller;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;
import static concerttours.constants.ConcerttoursConstants.CATALOG_VERSION_NAME;

@Controller
public class BandListController {

    @Resource(name = "defaultBandFacade")
    private BandFacade bandFacade;
    @Resource
    private CatalogVersionService catalogVersionService;

    @GetMapping(value = "/bands")
    public String showBands(Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        final List<BandData> bands = bandFacade.getBands();
        model.addAttribute("bands", bands);
        return "bandList";
    }
}
