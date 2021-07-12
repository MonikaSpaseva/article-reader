package app.cryptoarticlereader.controllers;

import app.cryptoarticlereader.model.ArticleResponse;
import app.cryptoarticlereader.model.Status;
import app.cryptoarticlereader.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/check")
    @ResponseBody
    public ArticleResponse checkArticle(@RequestParam("q") String article) {

       Status status = articleService.checkArticle(article);
       ArticleResponse articleResponse = new ArticleResponse();
       articleResponse.setStatus(status);
       return articleResponse;
    }
}
