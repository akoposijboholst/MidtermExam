package sidespell.tech.midtermexam.entities;

/**
 * Created by Eugene Boholst on 2/2/2016.
 */
public class Album {
    public String mName;
    private String mSinger;

    public Album(){}

    public Album(String name, String singer){
        mName = name;
        mSinger = singer;
    }

    public String getmSinger() {
        return mSinger;
    }

    public Album setmSinger(String mSinger) {
        this.mSinger = mSinger;
        return this;
    }

    public String getmName() {

        return mName;
    }

    public Album setmName(String mName) {
        this.mName = mName;
        return this;
    }
}
