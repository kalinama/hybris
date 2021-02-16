package concerttours.attributehandlers;

import concerttours.model.ProducerModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.attribute.DynamicLocalizedAttributeHandler;
import org.springframework.beans.factory.annotation.Required;

import java.util.Locale;

public class ProducerFullNameAttributeHandler implements DynamicLocalizedAttributeHandler<String, ProducerModel> {

    private I18NService i18NService;

    @Override
    public String get(ProducerModel model, Locale locale) {
        return model.getName(locale) + " " + model.getSurname(locale);
    }

    @Override
    public void set(ProducerModel model, String s, Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(ProducerModel model) {
        Locale locale = i18NService.getCurrentLocale();
        return model.getName(locale) + " " + model.getSurname(locale);

    }

    @Override
    public void set(ProducerModel model, String s) {
        throw new UnsupportedOperationException();
    }

    @Required
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}