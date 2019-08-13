package com.studio.suku.made.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ReleaseResults {

    /**
     * page : 1
     * total_results : 12
     * total_pages : 1
     * results : [{"vote_count":7,"id":492713,"video":false,"vote_average":0,"title":"Nereus","popularity":17.755,"poster_path":"/isX1Uitnb6rFd4267Mk4JmSP7be.jpg","original_language":"en","original_title":"Nereus","genre_ids":[27,9648,53],"backdrop_path":"/tDPmahOXYTocdA3f5lrUPecs5Th.jpg","adult":false,"overview":"During a visit to friends, Sara begins having visions and is attacked by an unearthly creature in her friend\u2019s swimming pool; she soon discovers that anyone who comes into contact with the water is in danger and she is driven to confront the mystical and malevolent creature lurking in the depths","release_date":"2019-08-13"},{"vote_count":0,"id":616262,"video":false,"vote_average":0,"title":"All Come From Dust","popularity":16.953,"poster_path":null,"original_language":"ar","original_title":"All Come From Dust","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"A loop of edgeless bend. You were its doom, he was its bloom. You were its tomb, he was its womb. You were its gloom, he was its loom. For Heaven and Hell, were words made of fume.","release_date":"2019-08-13"},{"vote_count":0,"id":616299,"video":false,"vote_average":0,"title":"Ivana the Terrible","popularity":14.322,"poster_path":null,"original_language":"ro","original_title":"Ivana cea Groaznica","genre_ids":[18],"backdrop_path":null,"adult":false,"overview":"It's based on the story of a crisis that took place in the summer of 2017 in the life of Mladenovic, a Serbian-born director living in Romania.","release_date":"2019-08-13"},{"vote_count":0,"id":617084,"video":false,"vote_average":0,"title":"Kasiterit","popularity":14.175,"poster_path":null,"original_language":"id","original_title":"Kasiterit","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"Natasha, a solar-powered A.I. voice machine, traces its genealogy and the truth of its origin. This investigation leads Natasha to meet its ancestor: the inorganic tin extracted from Bangka Island.","release_date":"2019-08-13"},{"vote_count":0,"id":616309,"video":false,"vote_average":0,"title":"The Cold Raising The Cold","popularity":14.175,"poster_path":null,"original_language":"zh","original_title":"Lengmo weiyang lengmo","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"In a chronically stressed society, a teenage spree killer is born. Based on a true story.","release_date":"2019-08-13"},{"vote_count":0,"id":617101,"video":false,"vote_average":0,"title":"Those That, at a Distance, Resemble Another","popularity":14.175,"poster_path":null,"original_language":"es","original_title":"Those That, at a Distance, Resemble Another","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"With an elephant\u2019s tusk as the protagonist, the film meditates on the endless tactility of conservation.","release_date":"2019-08-13"},{"vote_count":0,"id":616606,"video":false,"vote_average":0,"title":"Murghab","popularity":14.084,"poster_path":null,"original_language":"de","original_title":"Murghab","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"","release_date":"2019-08-13"},{"vote_count":0,"id":622045,"video":false,"vote_average":0,"title":"American Bistro","popularity":1.96,"poster_path":"/dYafBJeexgcWfxxpQsjAI0M9vyP.jpg","original_language":"en","original_title":"American Bistro","genre_ids":[35,18],"backdrop_path":"/5jLsSrEb8JvXu6vIN3lxEw2jvSA.jpg","adult":false,"overview":"A heartfelt adventure about a milquetoast accountant, Medor, whose perfect life is ruined when he discovers his wife having an affair with his boss. Having lost everything, he finds refuge in his estranged, deadbeat nephew and together, they chase his old, forgotten dream: opening a restaurant.","release_date":"2019-08-13"},{"vote_count":0,"id":594265,"video":false,"vote_average":0,"title":"Teacher","popularity":0.6,"poster_path":"/mWiqqAmmVKjZQsjjF6ot9mgHc3T.jpg","original_language":"en","original_title":"Teacher","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"A high school English teacher goes to disturbing lengths to protect his favorite students from bullies.","release_date":"2019-08-13"},{"vote_count":0,"id":615916,"video":false,"vote_average":0,"title":"The Mummy: Rebirth","popularity":0.6,"poster_path":"/aD5lS1D4tDGMhDdQLV991zHp2IM.jpg","original_language":"en","original_title":"The Mummy: Rebirth","genre_ids":[27,28],"backdrop_path":null,"adult":false,"overview":"Two treasure hunters uncover a sealed tomb and awaken a mummy that has waited years to come back and wipe humanity from the face of the Earth. It's a race against time as they try to stop the Mummy from wreaking havoc on the modern world.","release_date":"2019-08-13"},{"vote_count":0,"id":622897,"video":false,"vote_average":0,"title":"Woodstock or Bust","popularity":0,"poster_path":"/s3y2GAWTKp7Ei7dg8abeNf7Kka3.jpg","original_language":"en","original_title":"Woodstock or Bust","genre_ids":[],"backdrop_path":null,"adult":false,"overview":"Two teen song-writers saddle up their West coast '65 Mustang convertible and head East, answering the call to Yasgur's farm, to debut their original music at the Woodstock Music Festival.","release_date":"2019-08-13"},{"vote_count":0,"id":622773,"video":false,"vote_average":0,"title":"Shadow - Medo Sombrio","popularity":0,"poster_path":"/rUHVIz15syQQJkfokk7xCUtNvyB.jpg","original_language":"pt","original_title":"Shadow - Medo Sombrio","genre_ids":[27,35],"backdrop_path":null,"adult":false,"overview":"A girl is chased by a mysterious shadow.","release_date":"2019-08-13"}]
     */

    private int page;
    private int total_results;
    private int total_pages;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * vote_count : 7
         * id : 492713
         * video : false
         * vote_average : 0
         * title : Nereus
         * popularity : 17.755
         * poster_path : /isX1Uitnb6rFd4267Mk4JmSP7be.jpg
         * original_language : en
         * original_title : Nereus
         * genre_ids : [27,9648,53]
         * backdrop_path : /tDPmahOXYTocdA3f5lrUPecs5Th.jpg
         * adult : false
         * overview : During a visit to friends, Sara begins having visions and is attacked by an unearthly creature in her friend’s swimming pool; she soon discovers that anyone who comes into contact with the water is in danger and she is driven to confront the mystical and malevolent creature lurking in the depths
         * release_date : 2019-08-13
         */

        private int vote_count;
        private int id;
        private boolean video;
        private int vote_average;
        private String title;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private List<Integer> genre_ids;

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public int getVote_average() {
            return vote_average;
        }

        public void setVote_average(int vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.vote_count);
            dest.writeInt(this.id);
            dest.writeByte(this.video ? (byte) 1 : (byte) 0);
            dest.writeInt(this.vote_average);
            dest.writeString(this.title);
            dest.writeDouble(this.popularity);
            dest.writeString(this.poster_path);
            dest.writeString(this.original_language);
            dest.writeString(this.original_title);
            dest.writeString(this.backdrop_path);
            dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
            dest.writeString(this.overview);
            dest.writeString(this.release_date);
            dest.writeList(this.genre_ids);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.vote_count = in.readInt();
            this.id = in.readInt();
            this.video = in.readByte() != 0;
            this.vote_average = in.readInt();
            this.title = in.readString();
            this.popularity = in.readDouble();
            this.poster_path = in.readString();
            this.original_language = in.readString();
            this.original_title = in.readString();
            this.backdrop_path = in.readString();
            this.adult = in.readByte() != 0;
            this.overview = in.readString();
            this.release_date = in.readString();
            this.genre_ids = new ArrayList<Integer>();
            in.readList(this.genre_ids, Integer.class.getClassLoader());
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }
}
