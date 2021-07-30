package debtechllc.deb.sonderblu.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.view.ResetPasswordActivity;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModel;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModelFactory;

public class ResetPasswordThreeFrg extends Fragment {

    Button reset_pass_btn;
    EditText password_et, confirm_pass_et;

    private ProgressDialog progress;
    private SonderBluViewModel verifyOtpViewModel;

    String userId = "", password = "", confirmPassword = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password_three_frg, container, false);
        initLayoutViews(view);

        userId = getArguments().getString("userId");



        return view;
    }

    private void initLayoutViews(View view) {
        reset_pass_btn = view.findViewById(R.id.reset_pass_btn);
        password_et = view.findViewById(R.id.password_et);
        confirm_pass_et = view.findViewById(R.id.confirm_pass_et);
        final ImageView passVisible = view.findViewById(R.id.pass_visible);
        final ImageView passInVisible = view.findViewById(R.id.pass_invisible);
        final ImageView conPassVisible = view.findViewById(R.id.con_pass_visible);
        final ImageView conPassInVisible = view.findViewById(R.id.con_pass_invisible);

        passVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.INVISIBLE);
            passInVisible.setVisibility(View.VISIBLE);
            password_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        });
        passInVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.VISIBLE);
            passInVisible.setVisibility(View.INVISIBLE);
            password_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });
        conPassVisible.setOnClickListener(v -> {
            conPassVisible.setVisibility(View.INVISIBLE);
            conPassInVisible.setVisibility(View.VISIBLE);
            confirm_pass_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        });
        conPassInVisible.setOnClickListener(v -> {
            conPassVisible.setVisibility(View.VISIBLE);
            conPassInVisible.setVisibility(View.INVISIBLE);
            confirm_pass_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });

        password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
                if (password.equals("")) {
                    password_et.setError("Create password");
                }
                else {
                    checkPassword(true,password);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
                if (password.equals("")) {
                    password_et.setError("Create password");
                }
                else {
                    checkPassword(true,password);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        confirm_pass_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmPassword = charSequence.toString();
                if (confirmPassword.equals("")) {
                    confirm_pass_et.setError("Create password");
                }
                else {
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmPassword = charSequence.toString();
                if (confirmPassword.equals("")) {
                    confirm_pass_et.setError("Create password");
                }
                else {
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        reset_pass_btn.setOnClickListener(v -> {
            initProgress();
            if (password == null || password.equals("")) {
                password_et.setError("Create password");
            }else if (confirmPassword == null || confirmPassword.equals("")){
                confirm_pass_et.setError("Create password");
            }else if (password.equals(confirmPassword)){
                checkPassword(false,password);
            }else {
                confirm_pass_et.setError("Password did not matched!");
            }
        });
    }

    private void initProgress() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }

    public boolean checkPassword(boolean is_text_change,String password) {
        boolean upperCase = !password.equals(password.toLowerCase());
        boolean isAtLeast8 = password.length() >= 8;
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        boolean hasNumber = !password.matches(".*\\d+.*");
        if (!upperCase) {
            password_et.setError("Password must contain at least" + " " + "one capital letter" + "," + "one number" + "," + "one special character" + "," + "eight characters long");
            return true;
        }
        if (hasNumber) {
            password_et.setError("Password must contain at least" + " " + "one number" + "," + "one special character" + "," + "eight characters long");
            return true;
        }
        if (!hasSpecial) {
            password_et.setError("Password must contain at least" + " " + "one number" + "," + "eight characters long");
            return true;
        }
        if (!isAtLeast8) {
            password_et.setError("Password must contain at least" + " " + "eight characters long");
            return true;
        }
        else
        {
           if (!is_text_change)
           {
               updatePasswordApi(password, userId, progress);
           }

            return true;
        }
    }


    private void updatePasswordApi(String password, String userId, ProgressDialog progress) {
        ArrayList<String> arrayList_params = new ArrayList<>();
        arrayList_params.add(password);
        arrayList_params.add(userId);

        verifyOtpViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(getActivity().getApplication())).get(SonderBluViewModel.class);
        verifyOtpViewModel.initUpdatePassword();
        verifyOtpViewModel.getUpdatePassResponseLiveData().observe(getActivity(), updatePasswordResponse -> {
            try {
                if (updatePasswordResponse.getResponse().getError().equals("0")){
                    ((ResetPasswordActivity)getActivity()).resetPassBackControl();
                }else {
                    Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            }catch (RuntimeException e){
                Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        verifyOtpViewModel.getUpdatePass(arrayList_params, progress);
    }

}