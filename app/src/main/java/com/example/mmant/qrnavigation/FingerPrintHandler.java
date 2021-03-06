package com.example.mmant.qrnavigation;

import android.os.Handler;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by sonu on 24/03/17.
 */

/*  Implement Fingerprint Authentication Callback to get access to Fingerprint methods  */
@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private TextView errorText;

    public FingerPrintHandler(Context mContext, TextView errorText) {
        context = mContext;
        this.errorText = errorText;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    /*  Method will call on Fingerprint Auth Error  */
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Błąd skanowania\n" + errString);
    }

    /*  Method will call on Fingerprint Auth have some help  */
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Pomoc:\n" + helpString);
    }

    /*  Method will call on Fingerprint Auth Failed  */
    @Override
    public void onAuthenticationFailed() {
        this.update("Skanowanie zakończone niepowodzeniem. Spróbuj ponownie.");
    }

    /*  Method will call on Fingerprint Auth Succeeded  */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
           Log.d("Authentication", "Skanowanie zakończone sukcesem.");
        this.update("Skanowanie zakończone sukcesem.");
           Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    onAuthSuccess();
                }
            }, 3000);
    }

    /*  Trigger this method on FingerPrint get Success  */
    private void onAuthSuccess() {
        context.startActivity(new Intent(context, SecretContent.class));
        ((AppCompatActivity) context).finish();
    }


    /*  Method to update Error text message on Auth failed  */
    public void update(String e) {
        errorText.setText(e);
    }

}
