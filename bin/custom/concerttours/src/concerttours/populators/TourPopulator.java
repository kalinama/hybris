package concerttours.populators;

import concerttours.data.TourData;
import concerttours.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Optional;

public class TourPopulator implements Populator<ProductModel, TourData> {
    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());

        Optional.ofNullable(productModel.getProducer())
                .ifPresent(producer -> tourData.setProducer(producer.getFullName()));
    }
}
