package debtechllc.deb.sonderblu.view;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.CallbackManager;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.response.LoginResponseDM;
import debtechllc.deb.sonderblu.response.SocialRegisterResponseDM;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModel;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModelFactory;


public class LoginActivity extends AppCompatActivity  {
    private EditText user_email_et, user_pass_et;
    private TextView forgot_pass_tv;
    private Button login_btn;
    private ProgressDialog progress;
    private SonderBluViewModel loginViewModel,socialRegisterViewModel,subscriptionPlansExistViewModel;
    private String device_id,TAG = "loginActivityLog";
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;
    private RelativeLayout facebook_rl, google_rl, twitter_rl;
    private LoginButton login_facebook_btn;
    private int SOCIAL_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private TwitterLoginButton login_twitter_btn;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginManager.getInstance().logOut();
        InitTwitter();
        /*For layout Initialisation*/
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        device_id = getDeviceId();
        /*Initialise progress dialog*/
        initProgress();
        /*Initialise  views from layout*/
        initLayoutViews();
        /*Initialise  firebaseAuth for social login*/
        mAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null){
               // startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        };
        /*calling manual login  server API using view model,Live data, retrofit */
        loginViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(this.getApplication())).get(SonderBluViewModel.class);
        loginViewModel.initLogin();
        loginViewModel.getLoginResponseLiveData().observe(this, loginResponse -> {
            Log.d("loginLiveDataResponse",loginResponse.getToken());
            if (loginResponse.getToken() != null && loginResponse.getUser() != null)
            {
                saveDataInLocal(loginResponse);
                getSubscriptionExistingPlans(loginResponse.getToken(),progress);
                Toast.makeText(LoginActivity.this,"Login successfull",Toast.LENGTH_LONG).show();
            }
        });

        /*For facebook login */
        login_facebook_btn.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        login_facebook_btn.setLoginBehavior(LoginBehavior.NATIVE_ONLY);
        login_facebook_btn.setLoginBehavior(LoginBehavior.WEB_ONLY);
        facebook_rl.setOnClickListener(v -> login_facebook_btn.performClick());
        login_facebook_btn.setReadPermissions(Arrays.asList(
                "public_profile", "email"));
        mCallbackManager = CallbackManager.Factory.create();
        login_facebook_btn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Wait...Authorising App...", Toast.LENGTH_LONG).show();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        (object, response) -> {
                            String email = object.optString("email");
                            String name = object.optString("name");
                            String id = object.optString("id");
                            getSocialRegister(name,email,"facebook",id);

                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                updateUI(null);
            }
        });
        /*For Google login */
        configureGoogleClient();
        google_rl.setOnClickListener(v -> signInGoogle());

        /*For Twitter login */
        twitter_rl.setOnClickListener(v -> login_twitter_btn.performClick());
        login_twitter_btn.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result_twitter_session) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        signInToFirebaseWithTwitterSession(result_twitter_session.data, result.data);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(LoginActivity.this, "Login failed. No internet or No Twitter app found on your phone", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void InitTwitter(){
        TwitterAuthConfig mTwitterAuthConfig = new TwitterAuthConfig(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));
        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(mTwitterAuthConfig)
                .build();
        Twitter.initialize(twitterConfig);
    }
    /*For getting Existing Subscription Plan */
    private void getSubscriptionExistingPlans(String token, ProgressDialog progress) {
        Log.d("tokenSubc",token);
        ArrayList<String> arrayList_params=new ArrayList<>();
        arrayList_params.add(token);
           subscriptionPlansExistViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(this.getApplication())).get(SonderBluViewModel.class);
           subscriptionPlansExistViewModel.initSubscriptionExistPlans();
           subscriptionPlansExistViewModel.getSubscriptionExistPlansResponseLiveData().observe(this, subscriptionExistingPlansResponse -> {
         try {
             if (subscriptionExistingPlansResponse.getStatusClass() != null) {
                 if (subscriptionExistingPlansResponse.getStatusClass().getStatus() != null) {
                     Log.d("statusSubscription", subscriptionExistingPlansResponse.getStatusClass().getStatus());

                     if (subscriptionExistingPlansResponse.getStatusClass().getStatus().equals("active")) {
                         SharedPreferences sonderBluSharedPref = getSharedPreferences("MySonderBlu", MODE_PRIVATE);
                         SharedPreferences.Editor editor = sonderBluSharedPref.edit();
                         editor.putString("Status", subscriptionExistingPlansResponse.getStatusClass().getStatus());
                         editor.apply();
                         passActivityData();
                     } else {
                         Intent intent = new Intent(LoginActivity.this, OnBoardingLandingPagerActivity.class);
                         startActivity(intent);
                         finish();
                     }
                 } else {
                     Intent intent = new Intent(LoginActivity.this, OnBoardingLandingPagerActivity.class);
                     startActivity(intent);
                     finish();
                 }
             }
             else {
                 Intent intent = new Intent(LoginActivity.this, OnBoardingLandingPagerActivity.class);
                 startActivity(intent);
                 finish();
             }

         }catch (Exception e) {
             Intent intent=new Intent(LoginActivity.this,OnBoardingLandingPagerActivity.class);
             startActivity(intent);
             finish();
         }
        });
        subscriptionPlansExistViewModel.getSubscriptionExistingPlans(arrayList_params, progress);

    }

    private void configureGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestScopes(new Scope(Scopes.EMAIL))
                .build();
        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signInToFirebaseWithTwitterSession(TwitterSession session,String email){
        AuthCredential credential = TwitterAuthProvider.getCredential(session.getAuthToken().token,
                session.getAuthToken().secret);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    Toast.makeText(LoginActivity.this,"Wait...Authorising App...", Toast.LENGTH_LONG).show();
                 if (task.getResult() != null)
                 {
                     if (task.getResult().getUser() != null)
                     {
                         getSocialRegister(task.getResult().getUser().getDisplayName(),email,"twitter",task.getResult().getUser().getUid());
                     }
                 }
                    if (!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Authorising twitter failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void signInGoogle() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, SOCIAL_SIGN_IN);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
        mAuth.addAuthStateListener(mAuthListener);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SOCIAL_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    getSocialRegister(account.getDisplayName(),account.getEmail(),"google",account.getId());
                }
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
        else if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            // TWITTER
            login_twitter_btn.onActivityResult(requestCode, resultCode, data);
        }
        else
        {
            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }
    private void updateUI(FirebaseUser user) {
      /*  hideProgressBar();
        if (user != null) {
            mBinding.status.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
            mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            mBinding.buttonFacebookLogin.setVisibility(View.GONE);
            mBinding.buttonFacebookSignout.setVisibility(View.VISIBLE);
        } else {
            mBinding.status.setText(R.string.signed_out);
            mBinding.detail.setText(null);

            mBinding.buttonFacebookLogin.setVisibility(View.VISIBLE);
            mBinding.buttonFacebookSignout.setVisibility(View.GONE);
        }*/
    }
    private void passActivityData()
    {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void saveDataInLocal(LoginResponseDM loginResp) {
        SharedPreferences sonderBluSharedPref = getSharedPreferences("MySonderBlu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sonderBluSharedPref.edit();
        editor.putBoolean("SBLogedIn", true);
        editor.putString("Token", loginResp.getToken());
        editor.putString("UserID", loginResp.getUser().getId());
        editor.putString("UserName", loginResp.getUser().getUsername());
        editor.apply();


    }
    private void saveSocialDataInLocal(SocialRegisterResponseDM socialRegister) {
        SharedPreferences sonderBluSharedPref = getSharedPreferences("MySonderBlu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sonderBluSharedPref.edit();
        editor.putBoolean("SBLogedIn", true);
        editor.putString("Token", socialRegister.getToken());
        editor.putString("UserID", socialRegister.getUser().getId());
        editor.putString("UserName", socialRegister.getUser().getUsername());
        editor.apply();
    }
    private void initProgress() {
        progress = new ProgressDialog(LoginActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }
    private void initLayoutViews() {
        google_rl = findViewById(R.id.google_rl);
        facebook_rl = findViewById(R.id.facebook_rl);
        twitter_rl = findViewById(R.id.twitter_rl);
        login_twitter_btn = findViewById(R.id.twitter_login_button);
        login_facebook_btn = findViewById(R.id.login_button_facebook);
        user_pass_et = findViewById(R.id.edt_user_pass);
        forgot_pass_tv = findViewById(R.id.forgot_pass_tv);
        final ImageView passVisible = findViewById(R.id.pass_visible);
        final ImageView passInVisible = findViewById(R.id.pass_invisible);
        TextView sign_up_tv = findViewById(R.id.sign_up_tv);

        login_btn = findViewById(R.id.login_btn);
        user_email_et = findViewById(R.id.edt_user_email);
        /*Sending to RegistrationActivity Class for new Registration */
        sign_up_tv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        });
        /*For Password Visibility*/
        passVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.INVISIBLE);
            passInVisible.setVisibility(View.VISIBLE);
            user_pass_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        });
        /*For Password Invisibility*/
        passInVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.VISIBLE);
            passInVisible.setVisibility(View.INVISIBLE);
            user_pass_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });
        /*For Manual Login*/
        login_btn.setOnClickListener(v -> {
            String user_login=user_email_et.getText().toString();
            String user_login_pass=user_pass_et.getText().toString();
            if (!user_email_et.getText().toString().isEmpty() && !user_pass_et.getText().toString().isEmpty()) {
                getUserLogin(user_login,user_login_pass);
            } else {
                Toast.makeText(LoginActivity.this, "Please fill up correctly!", Toast.LENGTH_SHORT).show();
            }
        });
        /*Sending to ResetPasswordActivity Class for Forget Password */
        forgot_pass_tv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });

    }

    private String getDeviceId() {
       String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
       return deviceId;
    }

    private void getUserLogin(String username,String password) {
        ArrayList<String> arrayList_params=new ArrayList<>();
        arrayList_params.add(username);
        arrayList_params.add(password);
        arrayList_params.add(device_id);
        arrayList_params.add("android");
        loginViewModel.getUserLogin(arrayList_params, progress);
    }

    private void getSocialRegister(String name,String email,String provider,String social_id) {
        ArrayList<String> arrayList_params=new ArrayList<>();
        arrayList_params.add(name);
        arrayList_params.add(email);
        arrayList_params.add(provider);
        arrayList_params.add(social_id);
        socialRegisterViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(this.getApplication())).get(SonderBluViewModel.class);
        socialRegisterViewModel.initSocialRegister();
        socialRegisterViewModel.getSocialRegisterResponseLiveData().observe(this, new Observer<SocialRegisterResponseDM>() {
            @Override
            public void onChanged(SocialRegisterResponseDM loginResponse) {
                try {
                    if (loginResponse.getToken() != null && loginResponse.getUser() != null)
                    {
                        LoginActivity.this.saveSocialDataInLocal(loginResponse);
                       // passActivitySocialData(loginResponse);
                        getSubscriptionExistingPlans(loginResponse.getToken(),progress);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        socialRegisterViewModel.getSocialRegister(arrayList_params, progress);
    }

}
