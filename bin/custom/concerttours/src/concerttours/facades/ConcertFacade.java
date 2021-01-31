package concerttours.facades;

import concerttours.data.ConcertData;

public interface ConcertFacade {
    ConcertData getConcertDetails(final String concertId);
}
