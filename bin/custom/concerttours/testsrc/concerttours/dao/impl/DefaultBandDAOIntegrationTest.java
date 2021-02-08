package concerttours.dao.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static de.hybris.platform.testframework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@IntegrationTest
public class DefaultBandDAOIntegrationTest extends ServicelayerTransactionalTest {

    private static final String TEST_BAND_NAME_1 = "Test band name - 1";
    private static final String TEST_BAND_CODE_1 = "Test band code - 1";

    private static final String TEST_BAND_NAME_2 = "Test band name - 2";
    private static final String TEST_BAND_CODE_2 = "Test band code - 2";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Resource(name = "defaultBandDAO")
    private BandDAO bandDAO;
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
    public void findBandsTest() {
        List<BandModel> actualBandModelList = bandDAO.findBands();

        assertEquals(testBandModelList, actualBandModelList);
    }

    @Test
    public void findBandsByCodeReturnOneTest() {
        BandModel expectedBandModel = testBandModelList.get(0);

        List<BandModel> actualBandModelList = bandDAO.findBandsByCode(expectedBandModel.getCode());
        BandModel actualBandModel = actualBandModelList.get(0);

        assertEquals(1, actualBandModelList.size());
        assertEquals(expectedBandModel, actualBandModel);
        assertEquals(expectedBandModel.getCode(), actualBandModel.getCode());
        assertEquals(expectedBandModel.getName(), actualBandModel.getName());
    }

    @Test
    public void findBandsByCodeEmptyListTest() {
        List<BandModel> actualBandModelList = bandDAO.findBandsByCode("");

        assertTrue(actualBandModelList.isEmpty());
    }

    @Test
    public void findBandsByCodeNullParamTest() {
        exception.expect(IllegalArgumentException.class);
        bandDAO.findBandsByCode(null);
    }
}