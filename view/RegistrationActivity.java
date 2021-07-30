package debtechllc.deb.sonderblu.view;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.view.fragment.RegistrationFrgOne;
import debtechllc.deb.sonderblu.view.fragment.RegistrationVerityOTPFrg;

public class RegistrationActivity extends AppCompatActivity {
    ProgressDialog progress;
    private TextView sign_in_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_new);

        initProgress();
        initLayoutViews();
        loadRegistrationFrg1();
    }

    private void initLayoutViews() {
        sign_in_tv = findViewById(R.id.sign_in_tv);
        sign_in_tv.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

     /*Load RegistrationFrgOne For registrion*/
    public void loadRegistrationFrg1(){
        RegistrationFrgOne registrationFrg1 = new RegistrationFrgOne();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.registration_frame_container, registrationFrg1);
        fragmentTransaction.addToBackStack(registrationFrg1.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    /*Load RegistrationVerityOTPFrg For verify OTP for final step of registration*/
    public void loadRegistrationVerifyOTPFrg(){
        RegistrationVerityOTPFrg registrationVerityOTPFrg = new RegistrationVerityOTPFrg();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.registration_frame_container, registrationVerityOTPFrg);
        fragmentTransaction.addToBackStack(registrationVerityOTPFrg.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initProgress() {
        progress = new ProgressDialog(RegistrationActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }

}

