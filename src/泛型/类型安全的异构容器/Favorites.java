package 泛型.类型安全的异构容器;

import net.mindview.util.Print;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wulei on 16/3/31.
 * Favorites是类型安全的异构容器,每个Favorites都有一个
 * favorites的私有Map<Class<?>,Object>.它的每个键都可以有
 * 一个不同的参数化类型.
 */
public class Favorites {
    private Map<Class<?>, Object> favorites =
            new HashMap<Class<?>, Object>();

    public <T> void putFavorie(Class<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");
        favorites.put(type, type.cast(instance));//确保instance是type类型的一个实例
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));//取出type类型的实例.
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorie(String.class, "Java");
        f.putFavorie(Integer.class, 123);
        f.putFavorie(Class.class, Favorites.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favariteClass = f.getFavorite(Class.class);
        Print.printf("%s %x %s%n", favoriteString, favoriteInteger, favariteClass);
    }
}
