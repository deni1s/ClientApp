package com.startandroid.client.Responses;

import java.util.List;

/**
 * Created by Денис on 31.07.2016.
 */
public class MovieResponse {

    /**
     * id : 771398975
     * title : Jason Bourne
     * year : 2016
     * mpaaRating : PG-13
     * runtime : 123
     * criticsConsensus :
     * releaseDates : {"theater":"2016-07-29"}
     * ratings : {"critics_rating":"Rotten","critics_score":57,"audience_rating":"Upright","audience_score":65}
     * synopsis : The next chapter of Universal Pictures' Bourne franchise, which finds the CIA's most lethal former operative drawn out of the shadows.
     * posters : {"thumbnail":"https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw","profile":"https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw","detailed":"https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw","original":"https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw"}
     * abridgedast : [{"name":"Matt Damon","id":"162653499","characters":["Jason Bourne"]},{"name":"Tommy Lee Jones","id":"162666788","characters":["CIA Director Robert Dewey"]},{"name":"Alicia Vikander","id":"771083890","characters":["Heather Lee"]},{"name":"Vincent Cassel","id":"162655150","characters":["Asset"]},{"name":"Julia Stiles","id":"162660056","characters":["Nicky Parsons"]}]
     * links : {"self":"http://api.rottentomatoes.com/api/public/v1.0/movies/771398975.json","alternate":"http://www.rottentomatoes.com/m/jason_bourne/","cast":"http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/cast.json","reviews":"http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/reviews.json","similar":"http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/similar.json"}
     */

    private List<MoviesBean> movies;

    public List<MoviesBean> getMovies() {
        return movies;
    }


    public void setMovies(List<MoviesBean> movies) {
        this.movies = movies;
    }

    public static class MoviesBean {
        private String id;
        private String title;
        private int year;
        private String mpaaRating;
        private int runtime;
        private String criticsConsensus;
        /**
         * theater : 2016-07-29
         */

        private ReleaseDatesBean releaseDates;
        /**
         * critics_rating : Rotten
         * critics_score : 57
         * audience_rating : Upright
         * audience_score : 65
         */

        private RatingsBean ratings;
        private String synopsis;
        /**
         * thumbnail : https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw
         * profile : https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw
         * detailed : https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw
         * original : https://resizing.flixster.com/o_HtYPUlMu0jVL94xfnI7Oc2Lpw=/51x81/v1.bTsxMTU2MDMzODtqOzE3MTYxOzIwNDg7MzE1ODs1MDAw
         */

        private PostersBean posters;
        /**
         * self : http://api.rottentomatoes.com/api/public/v1.0/movies/771398975.json
         * alternate : http://www.rottentomatoes.com/m/jason_bourne/
         * cast : http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/cast.json
         * reviews : http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/reviews.json
         * similar : http://api.rottentomatoes.com/api/public/v1.0/movies/771398975/similar.json
         */

        private LinksBean links;
        /**
         * name : Matt Damon
         * id : 162653499
         * characters : ["Jason Bourne"]
         */

        private List<AbridgedastBean> abridgedast;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getMpaaRating() {
            return mpaaRating;
        }

        public void setMpaaRating(String mpaaRating) {
            this.mpaaRating = mpaaRating;
        }

        public int getRuntime() {
            return runtime;
        }

        public void setRuntime(int runtime) {
            this.runtime = runtime;
        }

        public String getCriticsConsensus() {
            return criticsConsensus;
        }

        public void setCriticsConsensus(String criticsConsensus) {
            this.criticsConsensus = criticsConsensus;
        }

        public ReleaseDatesBean getReleaseDates() {
            return releaseDates;
        }

        public void setReleaseDates(ReleaseDatesBean releaseDates) {
            this.releaseDates = releaseDates;
        }

        public RatingsBean getRatings() {
            return ratings;
        }

        public void setRatings(RatingsBean ratings) {
            this.ratings = ratings;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public PostersBean getPosters() {
            return posters;
        }

        public void setPosters(PostersBean posters) {
            this.posters = posters;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public List<AbridgedastBean> getAbridgedast() {
            return abridgedast;
        }

        public void setAbridgedast(List<AbridgedastBean> abridgedast) {
            this.abridgedast = abridgedast;
        }

        public static class ReleaseDatesBean {
            private String theater;

            public String getTheater() {
                return theater;
            }

            public void setTheater(String theater) {
                this.theater = theater;
            }
        }

        public static class RatingsBean {
            private String critics_rating;
            private int critics_score;
            private String audience_rating;
            private int audience_score;

            public String getCritics_rating() {
                return critics_rating;
            }

            public void setCritics_rating(String critics_rating) {
                this.critics_rating = critics_rating;
            }

            public int getCritics_score() {
                return critics_score;
            }

            public void setCritics_score(int critics_score) {
                this.critics_score = critics_score;
            }

            public String getAudience_rating() {
                return audience_rating;
            }

            public void setAudience_rating(String audience_rating) {
                this.audience_rating = audience_rating;
            }

            public int getAudience_score() {
                return audience_score;
            }

            public void setAudience_score(int audience_score) {
                this.audience_score = audience_score;
            }
        }

        public static class PostersBean {
            private String thumbnail;
            private String profile;
            private String detailed;
            private String original;

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }

            public String getDetailed() {
                return detailed;
            }

            public void setDetailed(String detailed) {
                this.detailed = detailed;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }
        }

        public static class LinksBean {
            private String self;
            private String alternate;
            private String cast;
            private String reviews;
            private String similar;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }

            public String getAlternate() {
                return alternate;
            }

            public void setAlternate(String alternate) {
                this.alternate = alternate;
            }

            public String getCast() {
                return cast;
            }

            public void setCast(String cast) {
                this.cast = cast;
            }

            public String getReviews() {
                return reviews;
            }

            public void setReviews(String reviews) {
                this.reviews = reviews;
            }

            public String getSimilar() {
                return similar;
            }

            public void setSimilar(String similar) {
                this.similar = similar;
            }
        }

        public static class AbridgedastBean {
            private String name;
            private String id;
            private List<String> characters;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<String> getCharacters() {
                return characters;
            }

            public void setCharacters(List<String> characters) {
                this.characters = characters;
            }
        }
    }
}
