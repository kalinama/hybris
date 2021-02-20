package concerttours.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.io.InputStream;

@SystemSetup(extension = "concerttours")
public class ConcerttoursCustomSetup {

    private static final Logger LOG = LoggerFactory.getLogger(ConcerttoursCustomSetup.class);

    private ImportService importService;

    @SystemSetup(type = SystemSetup.Type.ESSENTIAL)
    public boolean addEssentialData() {
        LOG.info("Starting custom essential data loading for the Concerttours extension");
        LOG.info("Custom essential data loading for the Concerttours extension completed.");
        return true;
    }

    @SystemSetup(type = SystemSetup.Type.PROJECT)
    public boolean addProjectData() {
        LOG.info("Starting custom project data loading for the Concerttours extension");
        impexImport("/impex/concerttours-catalogs.impex");
        impexImport("/impex/concerttours-bands.impex");
        impexImport("/impex/concerttours-producers.impex");
        impexImport("/impex/concerttours-yRockTour.impex");
        impexImport("/impex/concerttours-media.impex");
        LOG.info("Custom project data loading for the Concerttours extension completed.");
        return true;
    }

    protected boolean impexImport(final String filename) {
        final String message = "Concerttours impexing [" + filename + "]...";
        try (final InputStream resourceAsStream = getClass().getResourceAsStream(filename)) {
            LOG.info(message);
            final ImportConfig importConfig = new ImportConfig();
            importConfig.setScript(new StreamBasedImpExResource(resourceAsStream, "UTF-8"));
            importConfig.setLegacyMode(Boolean.FALSE);
            final ImportResult importResult = importService.importData(importConfig);
            if (importResult.isError()) {
                LOG.error(message + " FAILED");
                return false;
            }
        } catch (final Exception e) {
            LOG.error(message + " FAILED", e);
            return false;
        }
        return true;
    }

    @Required
    public void setImportService(ImportService importService) {
        this.importService = importService;
    }
}