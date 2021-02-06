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
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultTourFacade implements TourFacade {

    private ProductService productService;
    private Converter<ProductModel, TourData> tourConverter;

    @Override
    public TourData getTourDetails(String tourId) {
        ProductModel tour = productService.getProductForCode(tourId);
        return tourConverter.convert(tour);
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Required
    public void setTourConverter(Converter<ProductModel, TourData> tourConverter) {
        this.tourConverter = tourConverter;
    }

}
