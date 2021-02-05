package concerttours.populators;

import concerttours.data.BandData;
import concerttours.enums.MusicType;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

public class BandPopulator implements Populator<BandModel, BandData> {

    public static final String BAND_NAME_PREFIX = "concerttours.band.name.prefix";

    private ConfigurationService configurationService;

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        String prefix = configurationService.getConfiguration().getString(BAND_NAME_PREFIX);
        bandData.setId(bandModel.getCode());
        bandData.setName(prefix + bandModel.getName());
        bandData.setDescription(bandModel.getHistory());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setPlayers(bandModel.getPlayersInPopularityOrder());

        List<String> genres = CollectionUtils.emptyIfNull(bandModel.getTypes())
                .stream()
                .map(MusicType::getCode)
                .collect(Collectors.toList());
        bandData.setGenres(genres);
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
