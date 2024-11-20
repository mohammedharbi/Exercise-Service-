package com.example.article.Service;

import com.example.article.Model.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleService {

    ArrayList<Article> articles = new ArrayList<>();

    public ArrayList<Article> getNewsArticale(){
        return articles;
    }

    public void addNewsArticale(Article article){
        articles.add(article);
    }

    public boolean updateNewsArticale(String id, Article article){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getID().equals(id)){
                articles.set(i,article);
                return true;
            }
        }
        return false;
    }
    public boolean deleteNewsArticale(String id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getID().equals(id)){
                articles.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean publishNewsArticale(String id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getID().equals(id)){
                if (articles.get(i).isPublished() == false){
                articles.get(i).setPublished(true);
                    articles.get(i).setPublishDate(LocalDate.now());
                return true;}
            }
        }
        return false;
    }

    public ArrayList<Article> allPublishedNewsArticles(){
        ArrayList <Article> articles1 = new ArrayList<>();

        for (Article a: articles){
            if (a.isPublished()){articles1.add(a);}
        }
        if (articles1.isEmpty()){
            return null;
        }else return articles1;
    }
    public ArrayList<Article>  getNewsArticlesbyCategory(String category){
        ArrayList<Article> articles1 = new ArrayList<>();
        boolean arrayvalue=false;
        for (Article a: articles){
            if (a.getCategory().equals(category)){
                articles1.add(a);
                arrayvalue=true;
            }
        }
        if (arrayvalue){return articles1;
        }else return null;
    }
}
