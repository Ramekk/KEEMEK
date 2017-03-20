package rus.ramek.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.parceler.Parcels;

import rus.ramek.project.apimodel.LoginResponseModel;

public class UserAreaActivity extends AppCompatActivity {
    private static final String INTENT_PARAM_LOGIN_MODEL = "INTENT_PARAM_LOGIN_MODEL";
    //Button logoutButton;
    ImageView logoutButton;;


    private LoginResponseModel loginModel;
    ImageButton scanQrCodeButton;
    ImageButton testButton;

    public static Intent callingIntent(Context context, LoginResponseModel model){
        Intent intent = new Intent(context,UserAreaActivity.class);
        intent.putExtra(INTENT_PARAM_LOGIN_MODEL, Parcels.wrap(model));
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        loginModel = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_PARAM_LOGIN_MODEL));
        scanQrCodeButton = (ImageButton) findViewById(R.id.scanQrCode_button);
        testButton = (ImageButton) findViewById(R.id.testButton);
        scanQrCodeButton.setOnClickListener(onScanQrCodeClickListener);
        testButton.setOnClickListener(onOpenWebViewClickListener);
        logoutButton = (ImageView) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });



    }//onCreate

    private View.OnClickListener onScanQrCodeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scanBarcodeFrontCamera();
        }
    };

    private View.OnClickListener onOpenWebViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = AssetDetailWebViewActivity.callingIntent(UserAreaActivity.this,"http://csnonrmutsb.com/web/asset_system/");
            startActivity(intent);
        }
    };

    public void scanBarcodeFrontCamera() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a QR Code");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    // Parsing bar code reader result
                    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
                    openDetailUi(result.getContents());
                    //Toast.makeText(this, "QR Code Result = "+result.getContents(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openDetailUi(String url){
        String fullUrl = url+"&u_id="+loginModel.getUserId();
        Log.d("UserAreaActivity","Open url :"+fullUrl);
        Intent intent = AssetDetailWebViewActivity.callingIntent(UserAreaActivity.this,fullUrl);
        startActivity(intent);
    }


}
