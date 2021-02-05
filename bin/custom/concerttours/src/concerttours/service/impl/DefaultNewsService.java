package concerttours.service.impl;

import concerttours.dao.NewsDAO;
import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.List;

public class DefaultNewsService implements NewsService {
    private NewsDAO newsDAO;

    @Override
    public List<NewsModel> getNewsOfTheDay(Date date) {
        return newsDAO.findNewsOfTheDay(date);
    }

    @Required
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
