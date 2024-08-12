package com.example.practice.article;

import com.example.practice.article.model.Article;
import com.example.practice.article.repo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
   private final ArticleRepository articleRepository;

   public ArticleService(ArticleRepository articleRepository){
       this.articleRepository = articleRepository;
   }

   // create
    public Article create(
            String title,
            String content,
            String writer
    ){
       Article article = new Article(
               title, content, writer
       );
       return articleRepository.save(article);
    }

    // read all
     public List<Article> readAll(){
       return articleRepository.findAll();
     }
    // read one
     public Article readOne (Long id){
       return articleRepository.findById(id).orElse(null);
}
    // update
    public Article update (
            Long id,
            String title,
            String content,
            String writer

            ){
        Optional<Article> target = articleRepository.findById(id);
        if (target.isEmpty()){
            return null;
        }
        Article target2 = target.get();
        target2.setTitle(title);
        target2.setContent(content);
        target2.setWriter(writer);
        return  articleRepository.save(target2);
    }

    // delete
    public void delete(Long id){
       articleRepository.deleteById(id);
    }


}

