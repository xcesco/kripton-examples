package com.abubusoft.kripton.movie.kriptonmovie.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import com.abubusoft.kripton.movie.kriptonmovie.R;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.DirectorsViewModel;

public class DirectorSaveDialogFragment extends DialogFragment {
    private Context context;
    private String directorFullNameExtra;

    private static final String EXTRA_DIRECTOR_FULL_NAME = "director_full_name";
    public static final String TAG_DIALOG_DIRECTOR_SAVE = "dialog_director_save";
    private DirectorsViewModel directorsViewModel;

    public static DirectorSaveDialogFragment newInstance(String directorFullName) {
        DirectorSaveDialogFragment fragment = new DirectorSaveDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_DIRECTOR_FULL_NAME, directorFullName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        directorFullNameExtra = args.getString(EXTRA_DIRECTOR_FULL_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_director, null);
        final EditText directorEditText = view.findViewById(R.id.etDirectorFullName);
        if (directorFullNameExtra != null) {
            directorEditText.setText(directorFullNameExtra);
            directorEditText.setSelection(directorFullNameExtra.length());
        }
        directorsViewModel = ViewModelProviders.of(this).get(DirectorsViewModel.class);

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_director_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveDirector(directorEditText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        return alertDialogBuilder.create();
    }

    private void saveDirector(String fullName) {
        if (TextUtils.isEmpty(fullName)) {
            return;
        }

        directorsViewModel.insertOrUpdateDirector(directorFullNameExtra, fullName);
    }
}