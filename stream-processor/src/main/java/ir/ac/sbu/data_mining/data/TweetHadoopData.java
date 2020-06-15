package ir.ac.sbu.data_mining.data;

public class TweetHadoopData {
    private int favoriteCount;
    private int retweetCount;
    private String emotion;
    private String screenName;

    public TweetHadoopData(int favoriteCount
        , int retweetCount
        , String emotion
        , String screenName) {
            this.favoriteCount = favoriteCount;
            this.retweetCount = retweetCount;
            this.emotion = emotion;
            this.screenName = screenName;
    }

    public int getFavoriteCount() {
        return this.favoriteCount;
    }

    public int getRetweetCount() {
        return this.retweetCount;
    }

    public String getEmotion() {
        return this.emotion;
    }

    public String getScreenName() {
        return this.screenName;
    }

    @Override
    public String toString() {
        return this.getFavoriteCount() + " "
                + this.getRetweetCount() + " "
                + this.getEmotion().trim() + " "
                + this.getScreenName() + "\n";
    }
}
