package com.example.comicviewer;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComicAPI {
    public static final String search= "https://api.pingcc.cn/comic/search/title/";
    public static final String Chapter="https://api.pingcc.cn/comicChapter/search/";
    public static final String Comic_Content = "https://api.pingcc.cn/comicContent/search/";
    public static final String Art_search = "https://api.pingcc.cn/fiction/search/title/";
    public static final String Art_chapter = "https://api.pingcc.cn/fictionChapter/search/";
    public static final String Art_content = "https://api.pingcc.cn/fictionContent/search/";

    private HttpURLConnection connection;
    public List<Comic> fetch(String title){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<Comic> comics = new ArrayList<Comic>();
        HttpURLConnection connection;
        try {
            URL url = new URL(search + title + "/1/10");
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONArray comicdata = albums.getJSONArray("data");
                for(int i=0; i<comicdata.length();i++){
                    JSONObject obj = comicdata.getJSONObject(i);
                    String id = obj.getString("comicId");
                    String tit = obj.getString("title");
                    String author = obj.getString("author");
                    String comicType = obj.getString("comicType");
                    String desc = obj.getString("descs");
                    String coverpath = obj.getString("cover");
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.getString("updateTime"));
                   comics.add(new Comic(id,tit,author,comicType,desc,date,coverpath));
                }
                reader.close();
                return comics;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }

    }

    public List<ComicChapter> chapter_fetch(String id){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<ComicChapter> comicChapters = new ArrayList<ComicChapter>();
        try {
            System.out.println(id);
            URL url = new URL(Chapter + id);
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONObject content = albums.getJSONObject("data");
                JSONArray ChapterList = content.getJSONArray("chapterList");
                for(int i=0; i<ChapterList.length();i++){
                    JSONObject obj = ChapterList.getJSONObject(i);
                    String chid = obj.getString("chapterId");
                    String tit = obj.getString("title");
                    comicChapters.add(new ComicChapter(chid,tit));
                }
                reader.close();
                return comicChapters;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }
    }

    public List<String> content_fetch(String id){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<String> content = new ArrayList<String>();
        try {
            URL url = new URL(Comic_Content + id);
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONArray data = albums.getJSONArray("data");
                for(int i=0; i<data.length();i++){
                    content.add((String) data.get(i));
                }
                reader.close();
                return content;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }
    }


    public List<Article> art_fetch(String title){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<Article> articles = new ArrayList<Article>();
        HttpURLConnection connection;
        try {
            URL url = new URL(Art_search + title + "/1/10");
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONArray comicdata = albums.getJSONArray("data");
                for(int i=0; i<comicdata.length();i++){
                    JSONObject obj = comicdata.getJSONObject(i);
                    String id = obj.getString("fictionId");
                    String tit = obj.getString("title");
                    String author = obj.getString("author");
                    String comicType = obj.getString("fictionType");
                    String desc = obj.getString("descs");
                    String coverpath = obj.getString("cover");
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.getString("updateTime"));
                    articles.add(new Article(id,tit,author,comicType,desc,date,coverpath));
                }
                reader.close();
                return articles;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }
    }

    public List<ArticleChapter> art_chapter_fetch(String id){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<ArticleChapter> articleChapters = new ArrayList<ArticleChapter>();
        try {
            URL url = new URL(Art_chapter + id);
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONObject content = albums.getJSONObject("data");
                JSONArray ChapterList = content.getJSONArray("chapterList");
                for(int i=0; i<ChapterList.length();i++){
                    JSONObject obj = ChapterList.getJSONObject(i);
                    String chid = obj.getString("chapterId");
                    String tit = obj.getString("title");
                    articleChapters.add(new ArticleChapter(chid,tit));
                }
                reader.close();
                return articleChapters;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }
    }

    public List<String> art_content_fetch(String id){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        List<String> content = new ArrayList<String>();
        try {
            URL url = new URL(Art_content + id);
            this.connection = urlconnect(url);
            int status = this.connection.getResponseCode();
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    System.out.println(line);
                }
                reader.close();
                return null;
            }else{
                reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                JSONObject albums = new JSONObject(responseContent.toString());
                JSONArray data = albums.getJSONArray("data");
                for(int i=0; i<data.length();i++){
                    content.add((String) data.get(i));
                }
                reader.close();
                return content;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            this.connection.disconnect();
        }
    }




    private HttpURLConnection urlconnect(URL url){
        try {
            this.connection = (HttpURLConnection) url.openConnection();
            this.connection.setRequestMethod("GET");
            this.connection.setConnectTimeout(5000);
            this.connection.setReadTimeout(50000);
            return connection;
        }catch (Exception e){
            System.out.println(e);
            this.connection.disconnect();
            return null;
        }

    }




}
