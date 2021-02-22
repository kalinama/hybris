package concerttours.events;

import concerttours.constants.ConcerttoursConstants;
import concerttours.model.NewsModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

import static concerttours.constants.ConcerttoursConstants.ALBUM_SALES_BOUNDARY_VALUE;
import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;
import static concerttours.constants.ConcerttoursConstants.CATALOG_VERSION_NAME;

public class BandAlbumSalesEventListener extends AbstractEventListener<BandAlbumSalesEvent> {

    private static final String BAND_SALES_HEADLINE = "%s album sales exceed " + ALBUM_SALES_BOUNDARY_VALUE;
    private static final String BAND_SALES_CONTENT = "%s album sales reported as %d";

    private ModelService modelService;
    private KeyGenerator keyGenerator;
    private CatalogVersionService catalogVersionService;


    @Override
    protected void onEvent(BandAlbumSalesEvent bandAlbumSalesEvent) {
        NewsModel newsModel = new NewsModel();
        newsModel.setDate(new Date());
        newsModel.setHeadline(String.format(BAND_SALES_HEADLINE, bandAlbumSalesEvent.getName()));
        newsModel.setContent(String.format(BAND_SALES_CONTENT,
                bandAlbumSalesEvent.getName(), bandAlbumSalesEvent.getAlbumSales()));
        newsModel.setCode(keyGenerator.generate().toString());
        newsModel.setCatalogVersion(catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME));
        modelService.save(newsModel);
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Required
    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Required
    public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }
}
