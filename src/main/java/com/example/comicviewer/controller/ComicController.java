package com.example.comicviewer.controller;

import com.example.comicviewer.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ComicController {
    static List<ComicChapter> chapters;
    static List<ArticleChapter> articleChapters;
    static final ComicAPI api = new ComicAPI();
    @RequestMapping("/Comic")
    public String comicindex(){
        return "index";
    }

    @GetMapping(path = "/search")
    public String search(Model model, @RequestParam(name="search")String title,@RequestParam(name="type") String type){
        List<?> modellist = new ArrayList<>();
        System.out.println(type);
        switch (type){
            case "Comic":
                List<Comic> comics = api.fetch(URLEncoder.encode(title, StandardCharsets.UTF_8));
                modellist = comics;
                break;
            case "article":
                List<Article> articles = api.art_fetch(URLEncoder.encode(title, StandardCharsets.UTF_8));
                modellist = articles;
                break;

        }
        model.addAttribute("Comics",modellist);
        model.addAttribute("chatype",type);
        return "search";
    }

    @GetMapping(path = "/Chapter")
    public String Chapter(Model model,@RequestParam(name="id") String id,@RequestParam(name="type") String type){
        List<?> dynamicmodel = new ArrayList<>();
        switch (type){
            case "Comic":
                ComicController.chapters = api.chapter_fetch(id);
                dynamicmodel = chapters;
                break;
            case "article":
                ComicController.articleChapters = api.art_chapter_fetch(id);
                dynamicmodel = articleChapters;
                break;
        }
        model.addAttribute("Chapters",dynamicmodel);
        model.addAttribute("type",type);
        return "Chapter";
    }

    @GetMapping(path="/ComicContent")
    public String Content(Model model,@RequestParam(name="id") String id){
        List<String> content = api.content_fetch(id);
        if(chapters != null){
            String nextindex;
            String preindex;
            for(ComicChapter c:chapters){
                if(c.getCid().equals(id)){
                    int currentindex = chapters.indexOf(c);
                    model.addAttribute("currentindex",currentindex);
                    if(currentindex > 0){
                        nextindex = chapters.get(currentindex+1).getCid();
                        preindex = chapters.get(currentindex-1).getCid();
                        model.addAttribute("nextindex",nextindex);
                        model.addAttribute("preindex",preindex);
                        break;
                    }else{
                        nextindex = chapters.get(currentindex+1).getCid();
                        model.addAttribute("nextindex",nextindex);
                        break;
                    }
                }
            }
        }
        model.addAttribute("Content",content);
        return "Content";
    }

    @GetMapping(path="/ArticleContent")
    public String artcontent(Model model,@RequestParam(name="id") String id){
        List<String> content = api.art_content_fetch(id);
        if(articleChapters != null){
            String nextindex;
            String preindex;
            for(ArticleChapter c:articleChapters){
                if(c.getCid().equals(id)){
                    int currentindex = articleChapters.indexOf(c);
                    model.addAttribute("currentindex",currentindex);
                    if(currentindex > 0){
                        nextindex = articleChapters.get(currentindex+1).getCid();
                        preindex = articleChapters.get(currentindex-1).getCid();
                        model.addAttribute("nextindex",nextindex);
                        model.addAttribute("preindex",preindex);
                        break;
                    }else{
                        nextindex = articleChapters.get(currentindex+1).getCid();
                        model.addAttribute("nextindex",nextindex);
                        break;
                    }
                }
            }
        }
        model.addAttribute("Content",content);
        return "ArtContent";
    }


}
