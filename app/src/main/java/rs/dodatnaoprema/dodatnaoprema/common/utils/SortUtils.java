package rs.dodatnaoprema.dodatnaoprema.common.utils;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;

public class SortUtils {

    public static List<Article> sortArticlesByPriceAscending(List<Article> articles) {
        Collections.sort(articles, new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                if (Conversions.priceStringToFloat(a1.getCenaSamoBrojFormat()) - Conversions.priceStringToFloat(a2.getCenaSamoBrojFormat()) >= 0)
                    return 1;
                else
                    return -1;
            }

        });
        return articles;
    }

    public static List<Article> sortArticlesByPriceDescending(List<Article> articles) {
        Collections.sort(articles, new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                if (Conversions.priceStringToFloat(a1.getCenaSamoBrojFormat()) - Conversions.priceStringToFloat(a2.getCenaSamoBrojFormat()) >= 0)
                    return -1;
                else
                    return 1;
            }

        });
        return articles;
    }
}
