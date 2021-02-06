package concerttours.populators;

import concerttours.data.TourSummaryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class TourSummaryPopulator implements Populator<ProductModel, TourSummaryData> {
    @Override
    public void populate(ProductModel productModel, TourSummaryData tourSummaryData) throws ConversionException {
        tourSummaryData.setId(productModel.getCode());
        tourSummaryData.setTourName(productModel.getName());
        tourSummaryData.setNumberOfConcerts(productModel.getVariants().size());
    }
}
