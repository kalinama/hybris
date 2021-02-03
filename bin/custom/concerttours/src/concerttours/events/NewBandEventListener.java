package concerttours.events;

import concerttours.model.BandModel;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private static final String NEW_BAND_HEADLINE = "New band, %s";
    private static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";
    private ModelService modelService;

    @Override
    protected void onEvent(AfterItemCreationEvent afterItemCreationEvent) {
        if (afterItemCreationEvent != null && afterItemCreationEvent.getSource() != null) {
            Object object = modelService.get(afterItemCreationEvent.getSource());
            if (object instanceof BandModel) {
                BandModel bandModel = (BandModel) object;
                NewsModel newsModel = new NewsModel();
                newsModel.setDate(new Date());
                newsModel.setHeadline(String.format(NEW_BAND_HEADLINE, bandModel.getName()));
                newsModel.setContent(String.format(NEW_BAND_CONTENT, bandModel.getName()));
                modelService.save(newsModel);
            }
        }
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
