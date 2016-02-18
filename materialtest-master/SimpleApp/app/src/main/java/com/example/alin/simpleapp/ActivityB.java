package com.example.alin.simpleapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ActivityB extends AppCompatActivity {

    private static RelativeLayout mRoot;;
    private static Toolbar mToolBar;
    private static TextInputLayout mEmailInputLayout;
    private static TextInputLayout mPasswordInputLayout;
    private static EditText mEmailInput;
    private static EditText mPasswordInput;
    private static Button mLoginButton;
    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mRoot = (RelativeLayout) findViewById(R.id.root_activity_b);
        mToolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEmailInputLayout = (TextInputLayout) findViewById(R.id.email_input_layout);
        mPasswordInputLayout = (TextInputLayout) findViewById(R.id.password_input_layout);
        mEmailInput = (EditText) findViewById(R.id.email_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);
        mLoginButton = (Button) findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit(){
        if (isEmailEmpty() && isPasswordEmpty()) {
            hideKeyboard();
            Snackbar.make(mRoot, "One Or More Fields Are Blank", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        } else if (isEmailEmpty() && !isPasswordEmpty()) {
            mEmailInputLayout.setError("Email Cannot Be Empty");
            mPasswordInputLayout.setError(null);
        } else if (!isEmailEmpty() && isPasswordEmpty()) {
            mPasswordInputLayout.setError("Password Cannot Be Empty");
            mEmailInputLayout.setError(null);
        }else{
            //login
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }



    private boolean isEmailEmpty() {
        return mEmailInput.getText() == null
                || mEmailInput.getText().toString() == null
                || mEmailInput.getText().toString().isEmpty();

    }

    private boolean isPasswordEmpty() {
        return mPasswordInput.getText() == null
                || mPasswordInput.getText().toString() == null
                || mPasswordInput.getText().toString().isEmpty();

    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
