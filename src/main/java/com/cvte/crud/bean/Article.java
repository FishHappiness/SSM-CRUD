package com.cvte.crud.bean;

import java.util.Date;

public class Article {
    private String articleId;

    private String author;

    private String title;

    private String content;

    private Date publicationTime;

    public Article() {
    }

    public Article(String articleId, String author, String title, String content, Date publicationTime) {
        this.articleId = articleId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.publicationTime = publicationTime;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }
}