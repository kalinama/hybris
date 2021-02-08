package concerttours.populators;

import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class BandToursPopulator implements Populator<BandModel, BandData> {

    private Converter<ProductModel, TourSummaryData> tourSummaryConverter;

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        List<TourSummaryData> tours = Converters.convertAll(bandModel.getTours(), tourSummaryConverter);
        bandData.setTours(tours);
    }

    @Required
    public void setTourSummaryConverter(Converter<ProductModel, TourSummaryData> tourSummaryConverter) {
        this.tourSummaryConverter = tourSummaryConverter;
    }
}
