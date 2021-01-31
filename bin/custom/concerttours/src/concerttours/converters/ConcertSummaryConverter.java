package concerttours.converters;

import concerttours.data.ConcertSummaryData;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.i18n.LocaleContextHolder;

public class ConcertSummaryConverter implements Converter<ConcertModel, ConcertSummaryData> {

    private I18NService i18NService;

    @Override
    public ConcertSummaryData convert(ConcertModel concertModel) throws ConversionException {
        ConcertSummaryData concertSummaryData = new ConcertSummaryData();
        return convert(concertModel, concertSummaryData);
    }

    @Override
    public ConcertSummaryData convert(ConcertModel concertModel, ConcertSummaryData concertSummaryData) throws ConversionException {
        concertSummaryData.setId(concertModel.getCode());
        concertSummaryData.setDate(concertModel.getDate());
        concertSummaryData.setVenue(concertModel.getVenue(LocaleContextHolder.getLocale()));
        concertSummaryData.setType(concertModel.getConcertType().getCode());
        return concertSummaryData;
    }

    @Required
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
