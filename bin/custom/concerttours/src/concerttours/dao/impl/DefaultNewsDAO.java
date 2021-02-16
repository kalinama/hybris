package concerttours.dao.impl;

import com.google.common.collect.ImmutableMap;
import concerttours.dao.NewsDAO;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Required;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DefaultNewsDAO implements NewsDAO {

    private final static String QUERY_FIND_NEWS_BY_DATE = "SELECT {" + NewsModel.PK + "} FROM {" + NewsModel._TYPECODE
            + "} WHERE {" + NewsModel.DATE + "} >= ?date AND {" + NewsModel.DATE + "} < ?dateDayLater";

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<NewsModel> findNewsOfTheDay(Date date) {
        Date dateWithoutTime = nullifyTime(date);
        Map<String, Object> params = ImmutableMap.of("date", dateWithoutTime,
                "dateDayLater", oneDayAfter(dateWithoutTime));
        SearchResult<NewsModel> result = flexibleSearchService.search(QUERY_FIND_NEWS_BY_DATE, params);
        return result.getResult();
    }

    private Date oneDayAfter(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private Date nullifyTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
