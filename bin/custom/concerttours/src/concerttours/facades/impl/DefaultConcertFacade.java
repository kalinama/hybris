package concerttours.facades.impl;

import concerttours.data.ConcertData;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class DefaultConcertFacade implements ConcertFacade {
    private ProductService productService;
    private Converter<ConcertModel, ConcertData> concertConverter;

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
    public void setConcertConverter(Converter<ConcertModel, ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }
}
