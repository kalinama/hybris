package concerttours.dao.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.List;

public class DefaultBandDAO implements BandDAO {
    private final static String QUERY_FIND_BANDS = "SELECT {" + BandModel.PK + "} FROM {" + BandModel._TYPECODE + "}";
    private final static String QUERY_FIND_BANDS_BY_CODE = "SELECT {" + BandModel.PK + "} FROM {" + BandModel._TYPECODE
            + "} WHERE {" + BandModel.CODE + "} = ?code";

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<BandModel> findBands() {
        SearchResult<BandModel> result = flexibleSearchService.search(QUERY_FIND_BANDS);
        return result.getResult();
    }

    @Override
    public List<BandModel> findBandsByCode(String code) {
        SearchResult<BandModel> result = flexibleSearchService
                .search(QUERY_FIND_BANDS_BY_CODE, Collections.singletonMap("code", code));
        return result.getResult();
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
