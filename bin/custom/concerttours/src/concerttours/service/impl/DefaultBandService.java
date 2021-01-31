package concerttours.service.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultBandService implements BandService {
    private BandDAO bandDAO;

    @Override
    public List<BandModel> getBands() {
        return bandDAO.findBands();
    }

    @Override
    public BandModel getBandForCode(String code) throws UnknownIdentifierException, AmbiguousIdentifierException {
        List<BandModel> bands = bandDAO.findBandsByCode(code);
        if (bands.size() == 0) {
            throw new UnknownIdentifierException("Band with code '" + code + "' not found!");
        }
        if (bands.size() > 1) {
            throw new AmbiguousIdentifierException("There are " + bands.size() + " bands found with code '" + code + "'");
        }
        return bands.get(0);
    }

    @Required
    public void setBandDAO(BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }
}
