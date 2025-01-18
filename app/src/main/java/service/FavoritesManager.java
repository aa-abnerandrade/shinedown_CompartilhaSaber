package service;
import java.util.ArrayList;
import java.util.List;

import model.ShineLessonModel;

public class FavoritesManager {

  private static FavoritesManager instance;
  private ArrayList<ShineLessonModel> favoritesList;

  private FavoritesManager() {
    favoritesList = new ArrayList<>();
  }


  public static FavoritesManager getInstance() {
    if (instance == null) {
      instance = new FavoritesManager();
    }
    return instance;
  }

  // add os favs
  public void addFavorite(ShineLessonModel shine) {
    if (!favoritesList.contains(shine)) {
      favoritesList.add(shine);
    }
  }

  //pegar os favs
  public List<ShineLessonModel> getFavorites() {
    return new ArrayList<>(favoritesList); // CÓPIAAAA!!
  }

  // del os favs
  public List<ShineLessonModel> delFavorite(ShineLessonModel shine) {
    favoritesList.remove(shine);
    return new ArrayList<>(favoritesList);
  }

}
