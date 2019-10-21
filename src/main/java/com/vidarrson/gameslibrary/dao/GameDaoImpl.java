package com.vidarrson.gameslibrary.dao;

import com.vidarrson.gameslibrary.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{
    private static final Logger logger = LoggerFactory.getLogger(GameDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addGame(Game game) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(game);
        logger.info("Game successfully added. Game details: " + game);
    }

    @Override
    public void updateGame(Game game) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(game);
        logger.info("Game successfully updated. Game details: " + game);
    }

    @Override
    public void removeGame(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Game game = session.load(Game.class, id);

        if (game != null) {
            session.delete(game);
        }
        logger.info("Game successfully removed. Game details: " + game);
    }

    @Override
    public Game getGameById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Game game = session.load(Game.class, id);
        logger.info("Game successfully loaded. Game details: " + game);

        return game;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Game> listGames() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Game> gameList = session.createQuery("from games").list();

        for (Game game: gameList) {
            logger.info("Game list: " + game);
        }

        return gameList;
    }
}
