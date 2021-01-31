package concerttours.converters;

import concerttours.data.TourData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class TourConverter implements Converter<ProductModel, TourData> {

    @Override
    public TourData convert(ProductModel productModel) throws ConversionException {
        TourData tourData = new TourData();
        return convert(productModel, tourData);
    }

    @Override
    public TourData convert(ProductModel productModel, TourData tourData) throws ConversionException {
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
        return tourData;
    }
}
