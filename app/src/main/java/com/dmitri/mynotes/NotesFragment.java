package com.dmitri.mynotes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private int currentPosition = 0;
    private boolean isLandscape;

    public NotesFragment() {
    }

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_NOTE, 0);
        }
        if (isLandscape) {
            showLandNoteContent(0);
        }
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        String[] notes = getResources().getStringArray(R.array.head_for_notes);
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(26);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentPosition = fi;
                showNotesContents(currentPosition);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTE, currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void showNotesContents(int index) {
        if (isLandscape) {
            showLandNoteContent(index);
        } else {
            showPortNoteContent(index);
        }
    }

    private void showLandNoteContent(int index) {
        NoteContent detail = NoteContent.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.edit_text, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNoteContent(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteOpenActivity.class);
        intent.putExtra(NoteContent.ARG_INDEX, index);
        startActivity(intent);
    }

    private void setNote(String name, String description, String date) {

    }
}