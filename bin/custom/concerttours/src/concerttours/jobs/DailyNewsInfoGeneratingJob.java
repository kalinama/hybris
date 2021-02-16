package concerttours.jobs;

import concerttours.model.DailyNewsInfoGeneratingCronJobModel;
import concerttours.model.DailyNewsInfoModel;
import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.List;

public class DailyNewsInfoGeneratingJob extends AbstractJobPerformable<DailyNewsInfoGeneratingCronJobModel> {

    private NewsService newsService;
    private ModelService modelService;

    @Override
    public PerformResult perform(DailyNewsInfoGeneratingCronJobModel cronJobModel) {
        Date date = new Date();
        List<NewsModel> dailyNews = newsService.getNewsOfTheDay(date);
        String content = CollectionUtils.emptyIfNull(dailyNews)
                .stream()
                .map(NewsModel::getHeadline)
                .reduce((n1, n2) -> n1 + "\n" + n2).orElse("");

        DailyNewsInfoModel infoModel = new DailyNewsInfoModel();
        infoModel.setAmountOfNews(dailyNews.size());
        infoModel.setContent(content);
        infoModel.setDate(date);
        modelService.save(infoModel);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    @Required
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
