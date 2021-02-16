package concerttours.service.impl;

import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static de.hybris.platform.testframework.Assert.assertEquals;


@IntegrationTest
public class DefaultBandServiceIntegrationTest extends ServicelayerTransactionalTest {

    private static final String TEST_BAND_NAME_1 = "Test band name - 1";
    private static final String TEST_BAND_CODE_1 = "Test band code - 1";

    private static final String TEST_BAND_NAME_2 = "Test band name - 2";
    private static final String TEST_BAND_CODE_2 = "Test band code - 2";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Resource(name = "defaultBandService")
    private BandService bandService;
    @Resource
    private ModelService modelService;

    private List<BandModel> testBandModelList;

    @Before
    public void setUp() {
        BandModel bandModel1 = new BandModel();
        bandModel1.setCode(TEST_BAND_CODE_1);
        bandModel1.setName(TEST_BAND_NAME_1);

        BandModel bandModel2 = new BandModel();
        bandModel2.setCode(TEST_BAND_CODE_2);
        bandModel2.setName(TEST_BAND_NAME_2);

        modelService.saveAll(bandModel1, bandModel2);
        testBandModelList = Arrays.asList(bandModel1, bandModel2);
    }

    @Test
    public void getBandsTest() {
        List<BandModel> actualBandModelList = bandService.getBands();

        assertEquals(testBandModelList, actualBandModelList);
    }

    @Test
    public void getBandForCodeSuccessfulTest() {
        BandModel expectedBandModel = testBandModelList.get(0);

        BandModel actualBandModel = bandService.getBandForCode(expectedBandModel.getCode());

        assertEquals(expectedBandModel, actualBandModel);
    }

    @Test
    public void getBandForCodeNullParamTest() {
        exception.expect(IllegalArgumentException.class);
        bandService.getBandForCode(null);
    }

    @Test
    public void getBandForCodeNotFoundTest() {
        exception.expect(UnknownIdentifierException.class);
        bandService.getBandForCode("");
    }

}