package debtechllc.deb.sonderblu.view.fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.lifecycle.ViewModelProvider;
import debtechllc.deb.sonderblu.R;
import debtechllc.deb.sonderblu.response.RegistrationDM;
import debtechllc.deb.sonderblu.view.RegistrationActivity;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModel;
import debtechllc.deb.sonderblu.view_model.SonderBluViewModelFactory;
import static android.content.Context.MODE_PRIVATE;

public class RegistrationFrgOne extends Fragment {
    EditText name_et, user_email_et, user_name_et, user_pass_et;
    Button sign_up_btn;
    ProgressDialog progress;
    String name = "",email = "",userName = "",password = "";
    private SonderBluViewModel sonderBluViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_sign_up_frg_one, container, false);

         initLayoutViews(view);
         initProgress();

         return view;
    }

    private void initLayoutViews(View view) {
        sign_up_btn = view.findViewById(R.id.sign_up_btn);
        name_et = view.findViewById(R.id.name_et);
        user_email_et = view.findViewById(R.id.user_email_et);
        user_name_et = view.findViewById(R.id.user_name_et);
        user_pass_et = view.findViewById(R.id.user_pass_et);
        final ImageView passVisible = view.findViewById(R.id.pass_visible);
        final ImageView passInVisible = view.findViewById(R.id.pass_invisible);
        passVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.INVISIBLE);
            passInVisible.setVisibility(View.VISIBLE);
            user_pass_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        });
        passInVisible.setOnClickListener(v -> {
            passVisible.setVisibility(View.VISIBLE);
            passInVisible.setVisibility(View.INVISIBLE);
            user_pass_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });
        name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = charSequence.toString();
                if (name.equals("")) {
                    name_et.setError("Enter your name");
                } else {
                    name_et.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = charSequence.toString();
                if (name.equals("")) {
                    name_et.setError("Enter your name");
                } else {
                    name_et.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        user_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userName = charSequence.toString();
                if (userName.equals("")) {
                    user_name_et.setError("Enter your name");
                } else {
                    user_name_et.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userName = charSequence.toString();
                if (userName.equals("")) {
                    user_name_et.setError("Enter your name");
                } else {
                    user_name_et.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        user_email_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = charSequence.toString();
                if (email.equals("")) {
                    user_email_et.setError("Enter email id");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_email_et.setError("Enter valid email id");
                } else {
                    user_email_et.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = charSequence.toString();
                if (email.equals("")) {
                    user_email_et.setError("Enter email id");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_email_et.setError("Enter valid email id");
                } else {
                    user_email_et.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        user_pass_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
                if (password.equals("")) {
                    user_pass_et.setError("Create password");
                }
                else {
                    checkPassword(true,password);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
                if (password.equals("")) {
                    user_pass_et.setError("Create password");
                }
                else {
                    checkPassword(true,password);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        sign_up_btn.setOnClickListener(v -> {
            if (name == null || name.equals(""))
            {
                name_et.setError("Enter first name");
                Toast.makeText(getActivity(), "Enter first name", Toast.LENGTH_LONG).show();
            }
            if (userName == null || userName.equals(""))
            {
                user_name_et.setError("Enter user name");
                Toast.makeText(getActivity(), "Enter user name", Toast.LENGTH_LONG).show();
            }
            else if (email == null || email.equals(""))
            {
                user_email_et.setError("Enter email id");
                Toast.makeText(getActivity(), "Enter email id", Toast.LENGTH_LONG).show();
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_email_et.setError("Enter valid email id");
                Toast.makeText(getActivity(), "Enter valid email id", Toast.LENGTH_LONG).show();
            }

            else if (password == null || password.equals(""))
            {
                user_pass_et.setError("Create password");
                Toast.makeText(getActivity(), "Create password", Toast.LENGTH_LONG).show();
            }
            else
            {
                checkPassword(false,password);
            }
        });
    }

    private void initProgress() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }

    public boolean checkPassword(boolean text_change_type,String password) {
        boolean upperCase = !password.equals(password.toLowerCase());
        boolean isAtLeast8 = password.length() >= 8;
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        boolean hasNumber = !password.matches(".*\\d+.*");
        if (!upperCase) {
            user_pass_et.setError("Password must contain at least" + " " + "one capital letter" + "," + "one number" + "," + "one special character" + "," + "eight characters long");
            return true;
        }
        if (hasNumber) {
            user_pass_et.setError("Password must contain at least" + " " + "one number" + "," + "one special character" + "," + "eight characters long");
            return true;
        }
        if (!hasSpecial) {
            user_pass_et.setError("Password must contain at least" + " " + "one number" + "," + "eight characters long");
            return true;
        }
        if (!isAtLeast8) {
            user_pass_et.setError("Password must contain at least" + " " + "eight characters long");
            return true;
        }
        else
        {
            if (!text_change_type){
                postUserRegistration();
            }

            return true;
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void saveDataInLocal(RegistrationDM registration) {
        SharedPreferences sonderBluSharedPref = getActivity().getSharedPreferences("MySonderBlu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sonderBluSharedPref.edit();
        editor.putString("Token", registration.getToken());
        editor.apply();
    }

/*For user registration server api using view model, Live data, Retrofit*/
    private void postUserRegistration() {
        ArrayList<String> arrayList_params=new ArrayList<>();
        arrayList_params.add(name);
        arrayList_params.add(userName);
        arrayList_params.add(email);
        arrayList_params.add(password);

        sonderBluViewModel = new ViewModelProvider(this, new SonderBluViewModelFactory(getActivity().getApplication())).get(SonderBluViewModel.class);
        sonderBluViewModel.initRegistration();
        sonderBluViewModel.getRegistrationResponseLiveData().observe(getActivity(), registrationResponse -> {
            assert registrationResponse != null;
            RegistrationFrgOne.this.saveDataInLocal(registrationResponse);
            ((RegistrationActivity) RegistrationFrgOne.this.getActivity()).loadRegistrationVerifyOTPFrg();
        });

        sonderBluViewModel.getRegistrationUser(arrayList_params, progress);
    }

}