package debtechllc.deb.sonderblu.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.view.fragment.ResetPasswordOneFrg;
import debtechllc.deb.sonderblu.view.fragment.ResetPasswordThreeFrg;
import debtechllc.deb.sonderblu.view.fragment.ResetPasswordTwoFrg;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        loadResetPasswordOneFrg();
        initLayoutViews();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initLayoutViews() {
        TextView sign_up_tv = findViewById(R.id.sign_up_tv);

        sign_up_tv.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
    public void loadResetPasswordOneFrg(){
        ResetPasswordOneFrg resetPasswordOneFrg = new ResetPasswordOneFrg();
        loadFragTransaction("",resetPasswordOneFrg);
    }
    public void loadResetPasswordTwoFrg(String id){
        ResetPasswordTwoFrg resetPasswordTwoFrg = new ResetPasswordTwoFrg();
        loadFragTransaction(id,resetPasswordTwoFrg);
    }
    public void loadResetPasswordThreeFrg(String id){
        ResetPasswordThreeFrg resetPasswordThreeFrg = new ResetPasswordThreeFrg();
        loadFragTransaction(id,resetPasswordThreeFrg);

    }
    public void resetPassBackControl(){
        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        finish();
    }
    private void loadFragTransaction(String id,Fragment fragment)
    {
        if (!id.equals(""))
        {
            Bundle bundle=new Bundle();
            bundle.putString("userId", id);
            fragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.reset_pass_frame_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }

}