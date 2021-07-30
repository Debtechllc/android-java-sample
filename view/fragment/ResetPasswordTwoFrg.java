package debtechllc.deb.sonderblu.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.view.ResetPasswordActivity;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModel;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModelFactory;

public class ResetPasswordTwoFrg extends Fragment {

    Button reset_pass_btn;
    EditText otp_num_one,otp_num_two,otp_num_three,otp_num_four,otp_num_five,otp_num_six;
    String inputOTP = "",userId = "";
    private ProgressDialog progress;
    private SonderBluViewModel verifyOtpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password_two_frg, container, false);
        iniLayoutViews(view);

        return view;
    }

    private void iniLayoutViews(View view) {
        reset_pass_btn = view.findViewById(R.id.reset_pass_btn);
        otp_num_one = view.findViewById(R.id.otp_num_one);
        otp_num_two = view.findViewById(R.id.otp_num_two);
        otp_num_three = view.findViewById(R.id.otp_num_three);
        otp_num_four = view.findViewById(R.id.otp_num_four);
        otp_num_five = view.findViewById(R.id.otp_num_five);
        otp_num_six = view.findViewById(R.id.otp_num_six);

        userId = getArguments().getString("userId");

        otp_num_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_one.getText().toString().length() == 1) {
                    inputOTP = otp_num_one.getText().toString();
                    otp_num_two.requestFocus();
                }else if (otp_num_one.getText().toString().isEmpty()){
                    inputOTP = "";
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        otp_num_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_two.getText().toString().length() == 1) {
                    inputOTP += otp_num_two.getText().toString();
                    otp_num_three.requestFocus();
                }else if (otp_num_two.getText().toString().isEmpty()){
                    otp_num_one.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        otp_num_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_three.getText().toString().length() == 1) {
                    inputOTP += otp_num_three.getText().toString();
                    otp_num_four.requestFocus();
                }else if (otp_num_three.getText().toString().isEmpty()){
                    otp_num_two.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        otp_num_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_four.getText().toString().length() == 1) {
                    inputOTP += otp_num_four.getText().toString();
                    otp_num_five.requestFocus();
                }else if (otp_num_four.getText().toString().isEmpty()){
                    otp_num_three.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        otp_num_five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_five.getText().toString().length() == 1){
                    inputOTP += otp_num_five.getText().toString();
                    otp_num_six.requestFocus();
                }else if (otp_num_five.getText().toString().isEmpty()){
                    otp_num_four.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        otp_num_six.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp_num_six.getText().toString().length() == 1){
                    inputOTP += otp_num_six.getText().toString();
                    //otp_num_six.requestFocus();
                }else if (otp_num_six.getText().toString().isEmpty()){
                    otp_num_five.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        reset_pass_btn.setOnClickListener(v -> {
            if (inputOTP.length() == 6){
                initProgress();
                passwordRecoverApi(inputOTP, userId, progress);
            }
        });

    }

    private void initProgress() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }


    private void passwordRecoverApi(String inputOTP, String userId, ProgressDialog progress) {
        ArrayList<String> arrayList_params = new ArrayList<>();
        arrayList_params.add(inputOTP);
        arrayList_params.add(userId);
        verifyOtpViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(getActivity().getApplication())).get(SonderBluViewModel.class);
        verifyOtpViewModel.initVerifyOtpPass();
        verifyOtpViewModel.getVerifyOtpResponseLiveData().observe(getActivity(), verifyOtpResponse -> {
            try {
                if (verifyOtpResponse.getResponse().getError().equals("0")){
                    ((ResetPasswordActivity)getActivity()).loadResetPasswordThreeFrg(userId);
                }else {
                    Toast.makeText(getActivity(), "OTP did not matched", Toast.LENGTH_LONG).show();
                }
            }catch (RuntimeException e){
                Toast.makeText(getActivity(), "OTP did not matched", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
        verifyOtpViewModel.getVerifyOtp(arrayList_params, progress);
    }

}