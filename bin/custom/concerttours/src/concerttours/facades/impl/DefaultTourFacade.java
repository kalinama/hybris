package concerttours.facades.impl;

import concerttours.data.ConcertSummaryData;
import concerttours.data.TourData;
import concerttours.facades.TourFacade;
import concerttours.model.ConcertModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultTourFacade implements TourFacade {

    private ProductService productService;
    private Converter<ProductModel, TourData> tourConverter;
    private Converter<ConcertModel, ConcertSummaryData> concertSummaryConverter;

    @Override
    public TourData getTourDetails(String tourId) {
        if (tourId == null) {
            throw new IllegalArgumentException("Tour's id can't be null");
        }
        ProductModel tour = productService.getProductForCode(tourId);
        if (tour == null) {
            return null;
        }
        TourData tourData = tourConverter.convert(tour);
        List<ConcertSummaryData> concertSummaryData = tour.getVariants()
                .stream()
                .map(concert -> concertSummaryConverter.convert((ConcertModel) concert))
                .collect(Collectors.toList());
        tourData.setConcerts(concertSummaryData);
        return tourData;
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Required
    public void setTourConverter(Converter<ProductModel, TourData> tourConverter) {
        this.tourConverter = tourConverter;
    }

    @Required
    public void setConcertSummaryConverter(Converter<ConcertModel, ConcertSummaryData> concertSummaryConverter) {
        this.concertSummaryConverter = concertSummaryConverter;
    }
}
