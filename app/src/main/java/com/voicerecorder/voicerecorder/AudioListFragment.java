package com.voicerecorder.voicerecorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.io.File;

public class AudioListFragment extends Fragment {
    /**
     * comment1.
     */
    private ConstraintLayout playerSheet;
    /**
     * comment2.
     */
    private BottomSheetBehavior bottomSheetBehavior;
    /**
     * comment3.
     */
    private RecyclerView audioList;
    /**
     * comment4.
     */
    private File[] allFiles;
    /**
     * comment5.
     */
    private AudioListAdapter audioListAdapter;

    /**
     * comment6.
     */
    public AudioListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio_list, container, false);
    }

    @Override
    public void onViewCreated(final @NonNull View view,
                              final @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerSheet = view.findViewById(R.id.player_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(playerSheet);
        audioList = view.findViewById(R.id.audio_List_view);

        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        allFiles = directory.listFiles();


        audioListAdapter = new AudioListAdapter(allFiles);
        audioList.setHasFixedSize(true);
        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
        audioList.setAdapter(audioListAdapter);

        bottomSheetBehavior.addBottomSheetCallback(
                new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(final @NonNull View bottomSheet,
                                               final int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            bottomSheetBehavior.setState(
                                    BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    }

                    @Override
                    public void onSlide(final @NonNull View bottomSheet,
                                        final float slideOffset) {

                    }
                });
    }
}