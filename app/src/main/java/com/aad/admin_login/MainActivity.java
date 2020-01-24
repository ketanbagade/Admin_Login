package com.aad.admin_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static ServiceApi serviceApi;
    EditText passwordInput,loginInput;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        loginInput=findViewById(R.id.loginInput);
        passwordInput=findViewById(R.id.passwordInput);

        login =findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginInput.getText().toString();
                String password = passwordInput.getText().toString();
                if (validate_input(login, password)){
                    adding_user(login, password);
                }
            }
        });

    }



    private void adding_user(String login,String password) {

        Call<User> userCall = MainActivity.serviceApi.add_user(login,password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Success" + response.message(), Toast.LENGTH_SHORT).show();
                loginInput.setText("");
                passwordInput.setText("");

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                loginInput.setText("");
                passwordInput.setText("");

            }
        });


    }
    private boolean validate_input(String login, String password) {
        if (login.equals("")){
            Toast.makeText(this, "Enter valid Service", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
