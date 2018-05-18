package com.example.xz.mytelegraphapp.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 16/05/2018.
 */

public class MovieBean implements Serializable {
    private List<MovieItemBean> collection;

    public List<MovieItemBean> getCollection() {
        return collection;
    }

    public void setCollection(List<MovieItemBean> collection) {
        this.collection = collection;
    }


    //
    public class MovieItemBean implements Serializable {
        private Integer id;
        @SerializedName("website-url")
        private String website_url;
        private String headline;
        private String description;
        private Integer ratings;
        @SerializedName("picture-url")
        private String picture_url;
        private List<String> actors;
        private String director;
        private List<String> genre;
        private String synopsis;
        @SerializedName("release-date")
        private String release_date;
        private String duration;
        @SerializedName("published-date")
        private String published_date;
        private Author author;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWebsite_url() {
            return website_url;
        }

        public void setWebsite_url(String website_url) {
            this.website_url = website_url;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getRatings() {
            return ratings;
        }

        public void setRatings(Integer ratings) {
            this.ratings = ratings;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public List<String> getActors() {
            return actors;
        }

        public void setActors(List<String> actors) {
            this.actors = actors;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public List<String> getGenre() {
            return genre;
        }

        public void setGenre(List<String> genre) {
            this.genre = genre;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPublished_date() {
            return published_date;
        }

        public void setPublished_date(String published_date) {
            this.published_date = published_date;
        }

        public class Author implements Serializable {
            private String name;
            private String headshot;
            private String twitter;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadshot() {
                return headshot;
            }

            public void setHeadshot(String headshot) {
                this.headshot = headshot;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }
        }


    }

}
