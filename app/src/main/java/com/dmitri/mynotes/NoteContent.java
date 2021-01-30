package com.dmitri.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NoteContent extends Fragment {

    static final String ARG_INDEX = "index";
    private int index;

    public static NoteContent newInstance(int index) {
        NoteContent fragment = new NoteContent();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        EditText editText = view.findViewById(R.id.edit_text);
        TypedArray et = getResources().obtainTypedArray(R.array.note_content);
        editText.setBackgroundResource(et.getResourceId(index, -1));
        return view;
    }
}