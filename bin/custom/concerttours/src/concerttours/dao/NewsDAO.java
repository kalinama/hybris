package concerttours.dao;

import concerttours.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsDAO {
    public List<NewsModel> findNewsOfTheDay(Date date);
}
