package concerttours.populators;

import concerttours.data.ConcertData;
import concerttours.model.ConcertModel;
import concerttours.model.ConcertProgramModel;
import concerttours.model.SongModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ConcertPopulator implements Populator<ConcertModel, ConcertData> {
    @Override
    public void populate(ConcertModel concertModel, ConcertData concertData) throws ConversionException {
        ConcertProgramModel programModel = concertModel.getConcertProgram();

        concertData.setId(concertModel.getCode());
        concertData.setDate(concertModel.getDate());
        concertData.setVenue(concertModel.getVenue());
        concertData.setType(concertModel.getConcertType().getCode());
        concertData.setName(concertModel.getName());
        concertData.setDaysUntil(concertModel.getDaysUntil());
        concertData.setDescription(programModel.getDescription());
        concertData.setDuration(programModel.getDuration());

        List<String> songs = CollectionUtils.emptyIfNull(programModel.getSongs())
                .stream()
                .map(SongModel::getName)
                .collect(Collectors.toList());
        concertData.setSongs(songs);
    }
}
