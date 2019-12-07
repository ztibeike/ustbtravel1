package edu.ustb.service.impl;

import edu.ustb.dao.FavoriteDao;
import edu.ustb.dao.impl.FavoriteDaoImpl;
import edu.ustb.domain.Route;
import edu.ustb.service.FavoriteService;

/**
 * @author 2441632735
 */
public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDao dao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(Route route) {

        return dao.isFavorite(route);
    }
}
