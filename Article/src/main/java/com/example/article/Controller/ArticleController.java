package com.example.article.Controller;

import com.example.article.ApiResponse.ApiResponse;
import com.example.article.Model.Article;
import com.example.article.Service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle(){
        ArrayList<Article> articles = articleService.getNewsArticale();
        return ResponseEntity.status(200).body(articles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticale(@RequestBody @Valid Article article, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        articleService.addNewsArticale(article);
        return ResponseEntity.status(200).body(new ApiResponse("Article added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticale(@PathVariable String id, @RequestBody @Valid Article article, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = articleService.updateNewsArticale(id,article);
        if (isUpdated){
        return ResponseEntity.status(200).body(new ApiResponse("article updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Article not updated, id not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticale(@PathVariable String id){

        boolean isDeleted = articleService.deleteNewsArticale(id);
        if (isDeleted){
        return ResponseEntity.status(200).body(new ApiResponse("article deleted"));
        }else return ResponseEntity.status(400).body(new ApiResponse("article not deleted, id not found"));
    }
    @PutMapping("/publish-articale/{id}")
    public ResponseEntity publishNewsArticale(@PathVariable String id){
        boolean isPublished = articleService.publishNewsArticale(id);
        if (isPublished){
            return ResponseEntity.status(200).body(new ApiResponse("articale published"));
        }else return ResponseEntity.status(400).body(new ApiResponse("id doesn't exist or article is already published"));
    }

    @GetMapping("/get-all-Published-NewsArticles")
    public ResponseEntity allPublishedNewsArticles(){
        if (articleService.allPublishedNewsArticles() == null){
            return ResponseEntity.status(400).body(new ApiResponse("There isn't any published article yet."));
        }
        return ResponseEntity.status(200).body(articleService.allPublishedNewsArticles());
    }

    @GetMapping("/get-NewsArticles-by-Category/{category}")
    public ResponseEntity  getNewsArticlesbyCategory(@PathVariable String category){
        if (articleService.getNewsArticlesbyCategory(category) == null){
            return ResponseEntity.status(400).body("Not found a category matching your input, Please enter a valid category either a technology or sports or politics");
        }
        return ResponseEntity.status(200).body(articleService.getNewsArticlesbyCategory(category));
    }

}
