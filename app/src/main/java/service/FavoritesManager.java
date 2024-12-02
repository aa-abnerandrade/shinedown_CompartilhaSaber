package service;
import java.util.ArrayList;
import java.util.List;

import model.ShineLessonModel;

public class FavoritesManager {

  // Instância única do Singleton
  private static FavoritesManager instance;

  // Lista de favoritos
  private ArrayList<ShineLessonModel> favoritesList;

  // Construtor privado para evitar criação direta
  private FavoritesManager() {
    favoritesList = new ArrayList<>();
  }

  // Método para obter a única instância do FavoritesManager
  public static FavoritesManager getInstance() {
    if (instance == null) {
      instance = new FavoritesManager();
    }
    return instance;
  }

  // Método para adicionar um ShineLesson aos favoritos (evitando duplicação)
  public void addFavorite(ShineLessonModel shine) {
    if (!favoritesList.contains(shine)) {
      favoritesList.add(shine);
    }
  }

  // Método para obter a lista de favoritos
  public List<ShineLessonModel> getFavorites() {
    return new ArrayList<>(favoritesList); // Retorna uma cópia para evitar modificações externas
  }

  public List<ShineLessonModel> delFavorite(ShineLessonModel shine) {
    favoritesList.remove(shine);
    return new ArrayList<>(favoritesList);
  }

}
