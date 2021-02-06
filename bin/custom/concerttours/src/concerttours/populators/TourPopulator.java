package concerttours.populators;

import concerttours.data.TourData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class TourPopulator implements Populator<ProductModel, TourData> {
    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
    }
}
