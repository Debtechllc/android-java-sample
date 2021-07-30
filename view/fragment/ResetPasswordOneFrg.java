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

public class ResetPasswordOneFrg extends Fragment {

    Button reset_pass_btn;
    EditText email_et;
    String email = "";
    private SonderBluViewModel recoverPassViewModel;
    private ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password_one_frg, container, false);

        initLayoutViews(view);

        return view;
    }

    private void initLayoutViews(View view) {
        reset_pass_btn = view.findViewById(R.id.reset_pass_btn);
        email_et = view.findViewById(R.id.email_et);

        email_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = charSequence.toString();
                if (email.equals("")) {
                    email_et.setError("Enter email id");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_et.setError("Enter valid email id");
                } else {
                    email_et.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = charSequence.toString();
                if (email.equals("")) {
                    email_et.setError("Enter email id");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_et.setError("Enter valid email id");
                } else {
                    email_et.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        reset_pass_btn.setOnClickListener(v -> {
            if (email == null || email.equals("")) {
                email_et.setError("Enter email id");
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_et.setError("Enter valid email id");
            }else {
                initProgress();
                passwordRecoverApi("", email, progress);
            }
        });
    }

    private void initProgress() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
    }

    private void passwordRecoverApi(String token, String email, ProgressDialog progress) {
        ArrayList<String> arrayList_params=new ArrayList<>();
        arrayList_params.add(token);
        arrayList_params.add(email);

        recoverPassViewModel = new ViewModelProvider(requireActivity(), new SonderBluViewModelFactory(getActivity().getApplication())).get(SonderBluViewModel.class);
        recoverPassViewModel.initRecoverPass();
        recoverPassViewModel.getRecoverPassResponseLiveData().observe(getActivity(), recoverPassResponse -> {
            try {
                if (recoverPassResponse.getResponse().getId() != null){
                    ((ResetPasswordActivity)getActivity()).loadResetPasswordTwoFrg(recoverPassResponse.getResponse().getId());
                }else {
                    Toast.makeText(getActivity(), "This email did not registered yet!", Toast.LENGTH_LONG).show();
                }
            }catch (RuntimeException e){
                Toast.makeText(getActivity(), recoverPassResponse.getResponse().getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        recoverPassViewModel.getRecoverPass(arrayList_params, progress);
    }

}