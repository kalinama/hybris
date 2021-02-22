package concerttours.populators;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import org.springframework.beans.factory.annotation.Required;

import static concerttours.constants.ConcerttoursConstants.DEFAULT_BAND_IMAGE;

public class BandImageUrlPopulator implements Populator<BandModel, BandData> {

    private MediaService mediaService;
    private MediaContainerService mediaContainerService;
    private String mediaFormatName;

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        if(bandModel.getImage() == null) {
            bandData.setImageUrl(getDefaultImageUrl());
            return;
        }
        String imageUrl;
        try {
            MediaModel mediaModel = mediaService.getMediaByFormat(bandModel.getImage(),
                    mediaService.getFormat(mediaFormatName));
            imageUrl = mediaModel.getDownloadURL();
        } catch (ModelNotFoundException e) {
            imageUrl = getDefaultImageUrl();
        }
        bandData.setImageUrl(imageUrl);
    }

    private String getDefaultImageUrl() {
        MediaContainerModel containerModel = mediaContainerService
                .getMediaContainerForQualifier(DEFAULT_BAND_IMAGE);
        MediaFormatModel formatModel = mediaService.getFormat(mediaFormatName);
        return mediaService
                .getMediaByFormat(containerModel, formatModel)
                .getDownloadURL();
    }

    @Required
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Required
    public void setMediaContainerService(MediaContainerService mediaContainerService) {
        this.mediaContainerService = mediaContainerService;
    }

    @Required
    public void setMediaFormatName(String mediaFormatName) {
        this.mediaFormatName = mediaFormatName;
    }

}
