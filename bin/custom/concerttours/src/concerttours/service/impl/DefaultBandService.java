package concerttours.service.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;

public class DefaultBandService implements BandService {
    private BandDAO bandDAO;

    @Override
    public List<BandModel> getBands() {
        return bandDAO.findBands();
    }

    @Override
    public BandModel getBandForCode(String code) throws UnknownIdentifierException, AmbiguousIdentifierException {
        validateParameterNotNull(code, "Parameter code must not be null");
        List<BandModel> bands = bandDAO.findBandsByCode(code);

        validateIfSingleResult(bands, format("Band with code '%s' not found!", code),
                format("Band code '%s' is not unique, %d bands found!", code, bands.size()));

        return bands.get(0);
    }

    @Required
    public void setBandDAO(BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }
}
