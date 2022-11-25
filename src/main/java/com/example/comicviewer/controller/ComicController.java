package com.example.comicviewer.controller;

import com.example.comicviewer.Comic;
import com.example.comicviewer.ComicAPI;
import com.example.comicviewer.ComicChapter;
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
    @RequestMapping("/Comic")
    public String comicindex(){
        return "index";
    }

    @GetMapping(path = "/search")
    public String search(Model model, @RequestParam(name="search")String title){
        ComicAPI api = new ComicAPI();
        List<Comic> comics = api.fetch(URLEncoder.encode(title, StandardCharsets.UTF_8));
       model.addAttribute("Comics",comics);
       System.out.println(comics.get(0).getTitle());
        return "search";
    }

    @GetMapping(path = "/Chapter")
    public String Chapter(Model model,@RequestParam(name="id") String id){
        ComicAPI api = new ComicAPI();
        ComicController.chapters = api.chapter_fetch(id);
        model.addAttribute("Chapters",chapters);
        return "Chapter";
    }

    @GetMapping(path="/ComicContent")
    public String Content(Model model,@RequestParam(name="id") String id){
        ComicAPI api = new ComicAPI();
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


}
