package rus.ramek.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rus.ramek.project.apimodel.LoginResponseModel;
import rus.ramek.project.net.HttpManager;
import rus.ramek.project.state.UserStatus;

public class LoginActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    //Button loginButton;
    ImageView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt = (EditText) findViewById(R.id.etUsername);
        PasswordEt = (EditText) findViewById(R.id.etPassword);

        loginButton = (ImageView) findViewById(R.id.login_button);



        loginButton.setOnClickListener(onLoginButtonClickListener);
    }
    private View.OnClickListener onLoginButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = UsernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            Call<LoginResponseModel> call = HttpManager.getInstance().getService().login(username,password);
            call.enqueue(new LoginCallback());
        }
    };


    private class LoginCallback implements Callback<LoginResponseModel>{

        @Override
        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
            LoginResponseModel result = response.body();
            checkLoginState(result);
        }

        @Override
        public void onFailure(Call<LoginResponseModel> call, Throwable t) {
            Toast.makeText(LoginActivity.this, "Error "+t.getLocalizedMessage(), Toast.LENGTH_LONG  ).show();
        }
    }

    private void checkLoginState(LoginResponseModel result){
        if(result.getError() == 1){
            showToast(getString(R.string.login_invaid));
            return;
        }
        UserStatus userStatus = UserStatus.getStatus(result.getUserStatus());
        if(userStatus == UserStatus.DISABLED){
            showToast(getString(R.string.user_account_disabled));
            return;
        }
        if(userStatus == UserStatus.WAITING_APPROVE){
            showToast(getString(R.string.user_waiting_approve));
            return;
        }
        Intent intent = UserAreaActivity.callingIntent(this,result);
        startActivity(intent);
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
