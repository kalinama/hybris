package concerttours.controller;

import concerttours.data.ConcertData;
import concerttours.facades.ConcertFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;
import static concerttours.constants.ConcerttoursConstants.CATALOG_VERSION_NAME;

@Controller
public class ConcertDetailsController {
    @Resource(name = "defaultConcertFacade")
    private ConcertFacade concertFacade;
    @Resource
    private CatalogVersionService catalogVersionService;

    @GetMapping(value = "/concerts/{concertId}")
    public String showConcertDetails(@PathVariable String concertId, Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        ConcertData concertData = concertFacade.getConcertDetails(concertId);
        model.addAttribute("concert", concertData);
        return "concertDetails";
    }
}
