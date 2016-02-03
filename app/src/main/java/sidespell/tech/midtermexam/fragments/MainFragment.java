package sidespell.tech.midtermexam.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sidespell.tech.midtermexam.BuildConfig;
import sidespell.tech.midtermexam.R;
import sidespell.tech.midtermexam.adapters.AlbumAdapter;
import sidespell.tech.midtermexam.api.AlbumApi;
import sidespell.tech.midtermexam.entities.Album;

public class MainFragment extends Fragment {

    private EditText mSearch;
    private ProgressBar mProgress;
    private TextView mEmpty;
    private int position;
    private List<Album> albums;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // TODO: Find all views here..

        mSearch = (EditText) view.findViewById(R.id.etAlbum);
        mProgress = (ProgressBar) view.findViewById(R.id.progressBar);
        mEmpty = (TextView) view.findViewById(android.R.id.empty);
        position = 0;
        albums = new ArrayList<>();

        mSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    FetchAlbumTask alTask = new FetchAlbumTask();
                    String search = mSearch.getText().toString();
                    alTask.execute(search);
                    return true;
                } else {
                    return false;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // TODO: Perform logic operations here..
//        FetchAlbumTask alTask = new FetchAlbumTask();
//        String search = mSearch.getText().toString();
//        alTask.execute(search);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerListView);
        TextView tvEmpty = (TextView) view.findViewById(android.R.id.empty);

        rv.setHasFixedSize(true);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        AlbumAdapter adapter = new AlbumAdapter(getContext(), R.layout.song_item_cardview, albums);
        rv.setAdapter(adapter);


    }

    public class FetchAlbumTask extends AsyncTask<String, Void, Album> {

        @Override
        protected void onPreExecute() {
            mProgress.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }

        @Override
        protected Album doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String search = params[0];
            Uri builtUri = Uri.parse(AlbumApi.BASE_URL).buildUpon()
                    .appendQueryParameter(AlbumApi.PARAM_METHOD, "album.search")
                    .appendQueryParameter(AlbumApi.PARAM_ALBUM, search)
                    .appendQueryParameter(AlbumApi.PARAM_FORMAT, "json")
                    .appendQueryParameter(AlbumApi.PARAM_API_KEY,
                            BuildConfig.MIDTERM_EXAM_API_KEY)
                    .build();

            return AlbumApi.getAlbums(builtUri, "GET", position);
        }

        @Override
        protected void onPostExecute(Album album) {
            if (album == null) {
                return;
            } else {
                mProgress.setVisibility(View.GONE);
                albums.add(album);
            }
        }
    }
}
