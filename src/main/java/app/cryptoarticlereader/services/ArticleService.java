package app.cryptoarticlereader.services;

import app.cryptoarticlereader.model.ArticleResponse;
import app.cryptoarticlereader.model.Status;
import app.cryptoarticlereader.model.TypeOfWord;
import app.cryptoarticlereader.model.Word;
import app.cryptoarticlereader.repos.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private WordRepo wordRepo;

    private String fetchArticle(String url) {
        try {

            URL url1 = new URL(url);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));

            String text = "";
            String line;
            while ((line = in.readLine()) != null) {
                text = text + line;
                System.out.println(line);
            }

            in.close();
            return text;

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
      throw new RuntimeException();
    }

    public Status checkArticle(String linkArticle){

      String text =  fetchArticle(linkArticle);

      List<Word> all = wordRepo.findAll();

        List<String> plus = new ArrayList<>();
        List<String> minus = new ArrayList<>();

        for (Word a : all) {
            if (a.getTypeOfWord().equals(TypeOfWord.POSITIVE)){
                plus.add(a.getValue());
            } else {
                minus.add(a.getValue());
            }
        }

        String[] words = text.split("\\W");
        List<String> articleWords = Arrays.asList(words);


        int positive = 0;
        int negative = 0;

        for (String p : plus){
            for (String a : articleWords) {
               if (p.equals(a)){
                   positive++;
               }
            }
        }

        for (String m : minus) {
            for (String a : articleWords) {
                if (m.equals(a)){
                    negative++;
                }
            }
        }
        System.out.println(positive);

        System.out.println(positive);
        System.out.println(negative);

     if (positive > negative) {
         return Status.POSITIVE;
     } else if (negative > positive) {
         return Status.NEGATIVE;
     }

        return Status.POSITIVE;

    }
}
