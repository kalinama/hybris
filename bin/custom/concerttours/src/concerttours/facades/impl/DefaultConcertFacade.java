package concerttours.facades.impl;

import concerttours.converters.ConcertConverter;
import concerttours.data.ConcertData;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import de.hybris.platform.product.ProductService;
import org.springframework.beans.factory.annotation.Required;

public class DefaultConcertFacade implements ConcertFacade {
    private ProductService productService;
    private ConcertConverter concertConverter;

    @Override
    public ConcertData getConcertDetails(String concertId) {
        if (concertId == null) {
            throw new IllegalArgumentException("Concert's id can't be null");
        }
        ConcertModel concert = (ConcertModel) productService.getProductForCode(concertId);
        if (concert == null) {
            return null;
        }
        return concertConverter.convert(concert);
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Required
    public void setConcertConverter(ConcertConverter concertConverter) {
        this.concertConverter = concertConverter;
    }
}
