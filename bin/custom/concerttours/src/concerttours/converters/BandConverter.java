package concerttours.converters;

import concerttours.data.BandData;
import concerttours.enums.MusicType;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class BandConverter implements Converter<BandModel, BandData> {

    private I18NService i18NService;

    @Override
    public BandData convert(BandModel bandModel) throws ConversionException {
        BandData bandData = new BandData();
        return convert(bandModel, bandData);
    }

    @Override
    public BandData convert(BandModel bandModel, BandData bandData) throws ConversionException {
        bandData.setId(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setDescription(bandModel.getHistory(LocaleContextHolder.getLocale()));
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setPlayers(bandModel.getPlayersInPopularityOrder());

        List<String> genres = CollectionUtils.emptyIfNull(bandModel.getTypes())
                .stream()
                .map(MusicType::getCode)
                .collect(Collectors.toList());
        bandData.setGenres(genres);
        return bandData;
    }

    @Required
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
