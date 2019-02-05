package com.sanguebom.group.sanguebomsd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sanguebom.group.sanguebomsd.Atividades.ActivityCategorias;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    CallbackManager callbackManager;
    TextView textMail,  textbirthday, textFriends;
    ProgressDialog mDialog;
    ImageView imgAvatar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();

        textbirthday = (TextView) findViewById(R.id.txtBirtthday);
        textMail = (TextView)findViewById(R.id.txtEmail);
        textFriends = (TextView) findViewById(R.id.txtFriends);

        imgAvatar = (ImageView) findViewById(R.id.avatar);



        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList( "public_profile","email","user_birthday","user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();


                String acesstoken = loginResult.getAccessToken().getToken();




                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response",response.toString());
                        getData(object);
                        startActivity(new Intent(MainActivity.this,ActivityCategorias.class));

                    }
                });
                //Request Graph API
                Bundle paramenters = new Bundle();
                paramenters.putString("fields","id,email,birthday,friends");
                request.setParameters(paramenters);
                request.executeAsync();
startActivity(new Intent(MainActivity.this,ActivityCategorias.class));

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if already login

        if(AccessToken.getCurrentAccessToken()!= null)
        {
            //just set User id
            textMail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }


    }

    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height==250" );

            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);
            textMail.setText(object.getString("email"));
            textbirthday.setText(object.getString("birthday"));
            textFriends.setText("Friends:"+object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }













    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.sanguebom.group.sanguebomsd", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md =  MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

    }

}

