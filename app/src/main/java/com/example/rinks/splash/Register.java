package com.example.rinks.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Register extends AppCompatActivity {

   /* private TextView header;

    private Typeface logo;

    private EditText fullname;

    private EditText email;

    private EditText password;

    private EditText mobile;

    private Animation slide;

    private Button tologin;

    private Button register;

    private LinearLayout registerpanel;

    private String getname;

    private String  getemail;

    private String getpassword;

    private String getmobile; */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // logo = Typeface.createFromAsset(getAssets(), "fonts/cm.ttf");

        setContentView(R.layout.activity_register);

      /*  header = (TextView) findViewById(R.id.header);

        fullname = (EditText) findViewById(R.id.fullname);

        email = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);

        mobile = (EditText) findViewById(R.id.mobile);

        tologin = (Button) findViewById(R.id.tologin);

        register = (Button) findViewById(R.id.register);

        //register.setOnClickListener(this);

        // tologin.setOnClickListener(this);

        registerpanel = (LinearLayout) findViewById(R.id.container);

        header.setTypeface(logo);

        registerpanel.setBackgroundColor(Color.parseColor("#ED4337"));

        register.setText(getResources().getString(R.string.credentials));

        register.setEnabled(false);

        headerEdit();

        headerSlide();

        statusBar();

        initialInternetCheck();

    }

    private boolean isOnline() {

        final ConnectivityManager connectivityManager = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }

    private void headerEdit() {

        header.setTypeface(logo);

        header.setText(getResources().getString(R.string.app_name));

        header.setTextSize(100);

    }

    private void headerSlide() {

        slide = new TranslateAnimation(0, 0, 500, 0);

        slide.setDuration(1000);

        registerpanel.setAnimation(slide);

        tologin.setAnimation(slide);
    }

    private void statusBar() {

        Window window = getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    public void internetCheck() {

        if (!isOnline()) {

            registerpanel.setBackgroundColor(Color.parseColor("#ED4337"));

            register.setText(getResources().getString(R.string.notconnected));

            register.setEnabled(false);
        } else {

            credentialCheck();
        }
    }

    public void initialInternetCheck() {

        if (!isOnline()) {

            registerpanel.setBackgroundColor(Color.parseColor("#ED4337"));

            register.setText(getResources().getString(R.string.notconnected));

            register.setEnabled(false);
        }
    }

    private void showNotification(boolean isConnected) {

        if (isConnected) {

            registerpanel.setBackgroundColor(Color.parseColor("#8bc34a"));

            register.setText(getResources().getString(R.string.connected));

            register.setEnabled(false);
        }
    }

    private boolean emailCheck(CharSequence email) {

        if (email == null) {

            return false;
        } else {

            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private void credentialCheck() {

        getname = fullname.getText().toString().trim();

        getemail = email.getText().toString().trim();

        getpassword = password.getText().toString().trim();

        getmobile = mobile.getText().toString().trim();

        if (!emailCheck(getemail)) {

            getname = fullname.getText().toString().trim();

            getemail = email.getText().toString().trim();

            getpassword = password.getText().toString().trim();

            getmobile = mobile.getText().toString().trim();
        }

        if (getpassword.length() < 6) {

            registerpanel.setBackgroundColor(Color.parseColor("##ED4337"));

            register.setText(getResources().getString(R.string.shortpassword));

            register.setEnabled(false);
        }

        if (getmobile.length() != 10) {

            registerpanel.setBackgroundColor(Color.parseColor("##ED4337"));

            register.setText(getResources().getString(R.id.invalidmobile));

            register.setEnabled(false);
        }

        if (emailCheck(getemail) && getpassword.length() >= 6 && getmobile.length() == 10) {

            userRegister(getname, getemail, getpassword, getmobile);

        }
    }

    private void userRegister(final String getname, final String getemail, final String getpassword, final String getmobile) {

        class UserLoginClass extends AsyncTask<String, void, String> {

            ProgressBar loading;

            @Override
            protected void onPreExecuted() {
                super.onPreExecute();

                loading = new ProgressBar(Register.this, R.style.MyTheme);
                loading.setCancelable(false);
                loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                loading.dismiss();

                char c s.charAt(23);

                String f = String.valueOf(c);

                if (f.equals("{")) {

                    registerpanel.setBackgroundColor(Color.parseColor("#8bc34a"));

                    register.setText(getResources().getString(R.id.registersuccess));

                    register.setEnabled(false);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i = new Intent(Register.this, MainActivity.class);

                            i.putExtra("mobile", getmobile);

                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(i);

                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


                        }


                    }, 2000);

                } else {

                    registerpanel.setBackgroundColor(Color.parseColor("#ED4337"));

                    register.setText(s);

                    register.setEnabled(false);
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<>();

                data.put("fullname", params[0]);
                data.put("email", params[1]);
                data.put("password", params[2]);
                data.put("mobile", params[3]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(REGISTER_URL, data);

                return result;
            }

        }

        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(getname, getemail, getpassword, getmobile);

    }

    private void updateButton() {

        int nameLength = fullname.getText().length();

        int emailLength = email.getText().length();

        int passwordLength = password.getText().length();

        int mobileLength = mobile.getText().length();

        if (nameLength > 0 && emailLength > 0 && passwordLength > 0 && mobileLength > 0) {

            registerpanel.setBackgroundColor(Color.parseColor("#8bc34a"));

            register.setText(getResources().getString(R.string.register));

            register.setEnabled(true);

        } else {

            registerpanel.setBackgroundColor(Color.parseColor("#ED4337"));

            register.setText(getResources().getString(R.string.credentials));

            register.setEnabled(false);

        }
    }


        @Override
                protected void onResume() {
            super.onResume();

            AppController.getInstance().setConnectivityListener(this);

            TextWatcher check = new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    updateButton();

                }

            };

            fullname.addTextChangedListener(check);

            email.addTextChangedListener(check);

            password.addTextChangedListener(check);

            mobile.addTextChangedListener(check);

        }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        showNotification(isConnected);

    }


    @Override
    public void onClick(View view) {

        if (view == register) {

            internetCheck();

        }

        if (view == tologin) {

            Intent go = new Intent(Register.this, MainActivity.class);

            startActivity(go);

            finish();

        }

    }
}

  } */
    }
}
