package debtechllc.deb.sonderblu.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.constants.VARConstant;
import debtechllc.deb.sonderblu.response.RegistrationVerifyOtpDM;
import debtechllc.deb.sonderblu.view.OnBoardingLandingPagerActivity;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModel;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModelFactory;

import static android.content.Context.MODE_PRIVATE;

public class RegistrationVerityOTPFrg extends Fragment {
    Button sign_up_btn;
    EditText otp_num_one,otp_num_two,otp_num_three,otp_num_four,otp_num_five,otp_num_six;
    String inputOTP = "",userId = "",tokenVal;
    private ProgressDialog progress;
    private SonderBluViewModel registrationVerifyOTPViewModel;
    private boolean mIsAttached = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up_verity_o_t_p_frg, container, false);

        initLayoutViews(view);
        SharedPreferences sonderBluSharedPref = getActivity().getSharedPreferences("MySonderBlu", MODE_PRIVATE);
        tokenVal = sonderBluSharedPref.getString("Token", "");

        return view;
    }

    private void initLayoutViews(View view) {
        sign_up_btn = view.findViewById(R.id.sign_up_btn);
        otp_num_one = view.findViewById(R.id.otp_num_one);
        otp_num_two = view.findViewById(R.id.otp_num_two);
        otp_num_three = view.findViewById(R.id.otp_num_three);
        otp_num_four = view.findViewById(R.id.otp_num_four);
        otp_num_five = view.findViewById(R.id.otp_num_five);
        otp_num_six = view.findViewById(R.id.otp_num_six);

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

        sign_up_btn.setOnClickListener(v -> {
            if (inputOTP.length() == 6){
                initProgress();
                Log.d("TOKENval",tokenVal);
                Log.d("OTP",inputOTP);
                verityOTPApi(tokenVal,inputOTP, progress);
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mIsAttached = true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsAttached)
        {
            registrationVerifyOTPViewModel = new ViewModelProvider(this.getActivity(), new SonderBluViewModelFactory(this.getActivity().getApplication())).get(SonderBluViewModel.class);
            registrationVerifyOTPViewModel.initRegistrationVerifyOTP();
            registrationVerifyOTPViewModel.getRegistrationVerifyOTPResponseLiveData().observe(this.getActivity(), new Observer<RegistrationVerifyOtpDM>() {
                @Override
                public void onChanged(RegistrationVerifyOtpDM registrationVerifyOTP) {
                    if (registrationVerifyOTP.getResponse() != null) {
                        if (registrationVerifyOTP.getResponse().getError().equals("0")) {
                            saveDataInLocal(registrationVerifyOTP);
                            startActivity(new Intent(getActivity(),OnBoardingLandingPagerActivity.class));
                            getActivity().finish();
                        }
                    }

                }
            });

        }
    }
    private void saveDataInLocal(RegistrationVerifyOtpDM registrationVerifyOTP) {
        SharedPreferences sonderBluSharedPref = getActivity().getSharedPreferences("MySonderBlu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sonderBluSharedPref.edit();
        editor.putString("UserName", registrationVerifyOTP.getUser().getUsername());
        editor.putString("Email", registrationVerifyOTP.getUser().getEmail());
        editor.putString("UserID", registrationVerifyOTP.getUser().getId());
        editor.putBoolean("SBLogedIn", true);
        editor.apply();
        VARConstant.USER_ID=registrationVerifyOTP.getUser().getId();
    }

    private void initProgress() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }


    private void verityOTPApi(String token, String inputOTP, ProgressDialog progress) {
        ArrayList<String> arrayList_params = new ArrayList<>();
        arrayList_params.add(token);
        arrayList_params.add(inputOTP);
        registrationVerifyOTPViewModel.getRegistrationVerifyOtp(arrayList_params, progress);
    }

}