package concerttours.populators;

import concerttours.data.ConcertSummaryData;
import concerttours.data.TourData;
import concerttours.model.ConcertModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

public class TourConcertsPopulator implements Populator<ProductModel, TourData> {

    private Converter<ConcertModel, ConcertSummaryData> concertSummaryConverter;

    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        List<ConcertModel> concertModels = CollectionUtils.emptyIfNull(productModel.getVariants())
                .stream()
                .map(variant -> (ConcertModel) variant)
                .collect(Collectors.toList());
        List<ConcertSummaryData> concerts = Converters.convertAll(concertModels, concertSummaryConverter);
        tourData.setConcerts(concerts);
    }

    @Required
    public void setConcertSummaryConverter(Converter<ConcertModel, ConcertSummaryData> concertSummaryConverter) {
        this.concertSummaryConverter = concertSummaryConverter;
    }
}
