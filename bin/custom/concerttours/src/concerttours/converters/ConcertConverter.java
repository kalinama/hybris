package concerttours.converters;

import concerttours.data.ConcertData;
import concerttours.model.ConcertModel;
import concerttours.model.ConcertProgramModel;
import concerttours.model.SongModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ConcertConverter implements Converter<ConcertModel, ConcertData> {

    private I18NService i18NService;

    @Override
    public ConcertData convert(ConcertModel concertModel) throws ConversionException {
        ConcertData concertData = new ConcertData();
        return convert(concertModel, concertData);
    }

    @Override
    public ConcertData convert(ConcertModel concertModel, ConcertData concertData) throws ConversionException {
        Locale locale = LocaleContextHolder.getLocale();
        ConcertProgramModel programModel = concertModel.getConcertProgram();

        concertData.setId(concertModel.getCode());
        concertData.setDate(concertModel.getDate());
        concertData.setVenue(concertModel.getVenue(locale));
        concertData.setType(concertModel.getConcertType().getCode());
        concertData.setName(concertModel.getName());
        concertData.setDaysUntil(concertModel.getDaysUntil());
        concertData.setDescription(programModel.getDescription(locale));
        concertData.setDuration(programModel.getDuration());

        List<String> songs = CollectionUtils.emptyIfNull(programModel.getSongs())
                .stream()
                .map(SongModel::getName)
                .collect(Collectors.toList());
        concertData.setSongs(songs);
        return concertData;
    }

    @Required
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
