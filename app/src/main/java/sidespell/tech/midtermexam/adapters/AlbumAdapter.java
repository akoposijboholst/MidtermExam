package sidespell.tech.midtermexam.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sidespell.tech.midtermexam.R;
import sidespell.tech.midtermexam.entities.Album;

/**
 * Created by Eugene Boholst on 2/2/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private Context mContext;
    private int mLayoutId;
    private List<Album> mAlbums;

    public AlbumAdapter(Context mContext, int mLayoutId, List<Album> mAlbums) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mAlbums = mAlbums;
    }

    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.AlbumViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        //public ImageView imgMovie;
        public TextView albumName;
        public TextView albumSinger;

        public AlbumViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            //imgMovie = (ImageView) view.findViewById(R.id.imgMovie);
            albumName = (TextView) view.findViewById(R.id.albumName);
            albumSinger = (TextView) view.findViewById(R.id.albumSinger);
        }
    }

}
