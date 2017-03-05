package com.application.subitoit.githubstargazers.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.subitoit.githubstargazers.MainActivity;
import com.application.subitoit.githubstargazers.R;
import com.application.subitoit.githubstargazers.application.StargazersApplication;

public class RepoOwnerDataView extends RelativeLayout implements TextWatcher {
    Button findButton;
    TextInputLayout ownerTextInputLayout;
    TextInputLayout repoTextInputLayout;

    public RepoOwnerDataView(Context context) {
        super(context);
        initView();
    }

    public RepoOwnerDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RepoOwnerDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public RepoOwnerDataView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.repo_owner_textinput_layout, this);
        findButton = (Button) view.findViewById(R.id.findButtonId);
        ownerTextInputLayout = (TextInputLayout) view.findViewById(R.id.ownerTextInputLayoutId);
        repoTextInputLayout = (TextInputLayout) view.findViewById(R.id.repoTextInputLayoutId);
        ownerTextInputLayout.getEditText().addTextChangedListener(this);
        repoTextInputLayout.getEditText().addTextChangedListener(this);
    }

    /**
     *
     * @param listener
     */
    public void setFindButtonOnClickListener(OnClickListener listener) {
        findButton.setOnClickListener(listener);
    }

    public String getOwner() {
        return ownerTextInputLayout.getEditText().getText().toString();
    }

    public String getRepo() {
        return repoTextInputLayout.getEditText().getText().toString();
    }

    public boolean isValidInputData() {
        return !repoTextInputLayout.getEditText().getText().toString().equals("") &&
                !ownerTextInputLayout.getEditText().getText().toString().equals("");
    }

    public void setErrorInputData() {
        if (repoTextInputLayout.getEditText().getText().toString().equals(""))
            repoTextInputLayout.setError("no data");

        if (ownerTextInputLayout.getEditText().getText().toString().equals(""))
            ownerTextInputLayout.setError("no data");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //clear error set on input text layout on error
        repoTextInputLayout.setError(null);
        ownerTextInputLayout.setError(null);
    }
}
