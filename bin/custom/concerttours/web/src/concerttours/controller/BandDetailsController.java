package concerttours.controller;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;
import static concerttours.constants.ConcerttoursConstants.CATALOG_VERSION_NAME;

@Controller
public class BandDetailsController {
    @Resource(name = "defaultBandFacade")
    private BandFacade bandFacade;
    @Resource
    private CatalogVersionService catalogVersionService;

    @GetMapping(value = "/bands/{bandId}")
    public String showBands(@PathVariable String bandId, Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        final BandData band = bandFacade.getBand(bandId);
        model.addAttribute("band", band);
        return "bandDetails";
    }
}
