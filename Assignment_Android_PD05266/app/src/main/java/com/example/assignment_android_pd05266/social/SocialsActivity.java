package com.example.assignment_android_pd05266.social;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_android_pd05266.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialsActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Button btndangxuat, btnchucnang;
    TextView txtname, txtemail, txtfisrtname;
    CallbackManager callbackManager;
    String name, email, firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_socials);

        Anhxa();

        btnchucnang.setVisibility(View.INVISIBLE);
        btndangxuat.setVisibility(View.INVISIBLE);

        txtemail.setVisibility(View.INVISIBLE);
        txtfisrtname.setVisibility(View.INVISIBLE);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        setLogin_Button();
        setLogout_Button();
        chucNang();
    }

    private void chucNang() {
        btnchucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialsActivity.this, FacebookFunctionActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setLogout_Button(){
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btnchucnang.setVisibility(View.INVISIBLE);
                btndangxuat.setVisibility(View.INVISIBLE);

                txtemail.setVisibility(View.INVISIBLE);
                txtfisrtname.setVisibility(View.INVISIBLE);

                txtemail.setText("");

                txtfisrtname.setText("");

                profilePictureView.setProfileId(null);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setLogin_Button(){
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginButton.setVisibility(View.INVISIBLE);

                btnchucnang.setVisibility(View.INVISIBLE);
                btndangxuat.setVisibility(View.INVISIBLE);

                txtemail.setVisibility(View.INVISIBLE);
                txtfisrtname.setVisibility(View.INVISIBLE);

                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    private void result(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");
                    firstname = object.getString("first_name");

                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    txtemail.setText(email);

                    txtfisrtname.setText(firstname);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    public void Anhxa(){
        profilePictureView = (ProfilePictureView) findViewById(R.id.imageprofilepictureview);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        btndangxuat = findViewById(R.id.buttondangxuat);
        btnchucnang = findViewById(R.id.buttonchucnang);
        txtemail = findViewById(R.id.textviewemail);
        txtfisrtname = findViewById(R.id.textviewfirstname);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onStart(){
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}

