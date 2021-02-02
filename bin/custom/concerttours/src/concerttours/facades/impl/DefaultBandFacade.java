package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {

    private BandService bandService;
    private Converter<BandModel, BandData> bandConverter;
    private Converter<ProductModel, TourSummaryData> tourSummaryConverter;

    @Override
    public BandData getBand(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Band's id can't be null");
        }
        BandModel bandModel = bandService.getBandForCode(id);
        return getBand(bandModel);
    }

    @Override
    public List<BandData> getBands() {
        return CollectionUtils.emptyIfNull(bandService.getBands())
                .stream()
                .map(this::getBand)
                .collect(Collectors.toList());
    }

    private BandData getBand(BandModel bandModel) {
        if (bandModel == null) {
            return null;
        }
        BandData bandData = bandConverter.convert(bandModel);
        List<TourSummaryData> tourSummaryData = CollectionUtils.emptyIfNull(bandModel.getTours())
                .stream()
                .map(tour -> tourSummaryConverter.convert(tour))
                .collect(Collectors.toList());
        bandData.setTours(tourSummaryData);
        return bandData;
    }

    @Required
    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    @Required
    public void setBandConverter(Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }

    @Required
    public void setTourSummaryConverter(Converter<ProductModel, TourSummaryData> tourSummaryConverter) {
        this.tourSummaryConverter = tourSummaryConverter;
    }
}
