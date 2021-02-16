package concerttours.interceptors;

import concerttours.events.BandAlbumSalesEvent;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.servicelayer.model.ModelContextUtils;
import org.springframework.beans.factory.annotation.Required;

import static concerttours.constants.ConcerttoursConstants.*;

public class BandAlbumSalesInterceptor implements PrepareInterceptor<BandModel>, ValidateInterceptor<BandModel> {

    private EventService eventService;

    @Override
    public void onPrepare(BandModel bandModel, InterceptorContext interceptorContext) throws InterceptorException {
        if (isAlbumSalesExceedBoundary(bandModel, interceptorContext)) {
            eventService.publishEvent(new BandAlbumSalesEvent(bandModel.getCode(), bandModel.getName(),
                    bandModel.getAlbumSales()));
        }
    }

    @Override
    public void onValidate(BandModel bandModel, InterceptorContext interceptorContext) throws InterceptorException {
        Long albumSales = bandModel.getAlbumSales();
        if (albumSales != null && albumSales < 0) {
            throw new InterceptorException("Album sales must be positive");
        }
    }

    private boolean isAlbumSalesExceedBoundary(BandModel bandModel, InterceptorContext interceptorContext) {
        Long albumSalesCurrentValue = bandModel.getAlbumSales();
        if (albumSalesCurrentValue != null && albumSalesCurrentValue >= ALBUM_SALES_BOUNDARY_VALUE) {
            if (interceptorContext.isNew(bandModel)) {
                return true;
            }
            Long albumSalesOldValue = ModelContextUtils.getItemModelContext(bandModel).getOriginalValue("albumSales");
            return albumSalesOldValue == null
                    || albumSalesOldValue < albumSalesCurrentValue;
        }
        return false;
    }

    @Required
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
