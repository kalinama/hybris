package concerttours.service.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultBandServiceUnitTest {

    private static final String TEST_BAND_NAME = "Test band name";
    private static final String TEST_BAND_CODE = "Test band code";

    private BandModel bandModel;

    @Mock
    private BandDAO bandDAO;
    @InjectMocks
    private DefaultBandService bandService;

    @Before
    public void setUp() {
        bandModel = new BandModel();
        bandModel.setCode(TEST_BAND_CODE);
        bandModel.setName(TEST_BAND_NAME);
    }

    @Test
    public void getBandsTest() {
        List<BandModel> expectedBandModelList = Collections.singletonList(bandModel);
        when(bandDAO.findBands()).thenReturn(expectedBandModelList);

        List<BandModel> actualBandModelList = bandService.getBands();

        assertEquals(expectedBandModelList, actualBandModelList);
    }

    @Test
    public void getBandForCodeTest() {
        when(bandDAO.findBandsByCode(TEST_BAND_CODE)).thenReturn(Collections.singletonList(bandModel));

        BandModel actualBandModel = bandService.getBandForCode(TEST_BAND_CODE);

        assertEquals(bandModel, actualBandModel);
    }

}