package concerttours.converters;

import concerttours.data.TourSummaryData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class TourSummaryConverter implements Converter<ProductModel, TourSummaryData> {

    @Override
    public TourSummaryData convert(ProductModel productModel) throws ConversionException {
        TourSummaryData tourSummaryData = new TourSummaryData();
        return convert(productModel, tourSummaryData);
    }

    @Override
    public TourSummaryData convert(ProductModel productModel, TourSummaryData tourSummaryData) throws ConversionException {
        tourSummaryData.setId(productModel.getCode());
        tourSummaryData.setTourName(productModel.getName());
        tourSummaryData.setNumberOfConcerts(productModel.getVariants().size());
        return tourSummaryData;
    }
}
