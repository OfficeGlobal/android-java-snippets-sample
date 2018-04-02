/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.graph.snippets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.graph.snippets.util.SharedPrefsUtil;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.Logger;
import com.microsoft.identity.client.MsalClientException;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.MsalServiceException;
import com.microsoft.identity.client.MsalUiRequiredException;
import com.microsoft.identity.client.User;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.microsoft.graph.snippets.R.id.layout_diagnostics;
import static com.microsoft.graph.snippets.R.id.o365_signin;
import static com.microsoft.graph.snippets.R.id.view_diagnosticsdata;
import static com.microsoft.graph.snippets.R.layout.activity_signin;
import static com.microsoft.graph.snippets.R.string.sign_in_err;
import static com.microsoft.graph.snippets.R.string.warning_client_id_redirect_uri_incorrect;

public class SignInActivity
        extends BaseActivity
        implements MSALAuthenticationCallback {

    private boolean mEnablePiiLogging = false;
    private static final String TAG = "SignInActivity";

    @InjectView(layout_diagnostics)
    protected View mDiagnosticsLayout;

    @InjectView(view_diagnosticsdata)
    protected TextView mDiagnosticsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_signin);
        setTitle(R.string.app_name);

        ButterKnife.inject(this);
    }

    @OnClick(o365_signin)
    public void onSignInO365Clicked() {
        try {
            authenticate();
        } catch (IllegalArgumentException e) {
            warnBadClient();
        }
    }

    @Override
    public void onSuccess(AuthenticationResult authenticationResult) {
        // reset anything that may have gone wrong...
        mDiagnosticsLayout.setVisibility(INVISIBLE);
        mDiagnosticsTxt.setText("");

        // get rid of this Activity so that users can't 'back' into it
        finish();

        // save our auth token to use later
        SharedPrefsUtil.persistAuthToken(authenticationResult);

        // get the user display name
        final String userDisplayableId =
                authenticationResult
                        .getUser()
                        .getDisplayableId();

        // get the index of their '@' in the name (to determine domain)
        final int at = userDisplayableId.indexOf("@");

        // parse-out the tenant
        final String tenant = userDisplayableId.substring(at + 1);

        SharedPrefsUtil.persistUserTenant(tenant);
        SharedPrefsUtil.persistUserID(authenticationResult);

        // go to our main activity
        start();
    }

    @Override
    public void onError(MsalException e) {
        e.printStackTrace();

        //Show the localized message supplied with the exception or
        //or a default message from the string resources if a
        //localized message cannot be obtained
        String msg;
        if (null == (msg = e.getLocalizedMessage())) {
            msg = getString(sign_in_err);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            mDiagnosticsTxt.setText(msg);
            mDiagnosticsLayout.setVisibility(VISIBLE);
        }

        if (e instanceof MsalClientException) {
            // This means errors happened in the sdk itself, could be network, Json parse, etc. Check MsalError.java
            // for detailed list of the errors.

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        } else if (e instanceof MsalServiceException) {
            // This means something is wrong when the sdk is communication to the service, mostly likely it's the client
            // configuration.
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        } else if (e instanceof MsalUiRequiredException) {
            // This explicitly indicates that developer needs to prompt the user, it could be refresh token is expired, revoked
            // or user changes the password; or it could be that no token was found in the token cache.
            AuthenticationManager mgr = AuthenticationManager.getInstance();
            mgr.callAcquireToken(SignInActivity.this, this);
        }

    }

    private void warnBadClient() {
        Toast.makeText(this,
                warning_client_id_redirect_uri_incorrect,
                Toast.LENGTH_LONG)
                .show();
    }

    private void authenticate() throws IllegalArgumentException {
        validateOrganizationArgs();
        connect();
        //AuthenticationManager.getInstance().
    }
    private void connect() {

        // The sample app is having the PII enable setting on the MainActivity. Ideally, app should decide to enable Pii or not,
        // if it's enabled, it should be  the setting when the application is onCreate.
        if (mEnablePiiLogging) {
            Logger.getInstance().setEnablePII(true);
        } else {
            Logger.getInstance().setEnablePII(false);
        }

        AuthenticationManager mgr = AuthenticationManager.getInstance();

        /* Attempt to get a user and acquireTokenSilent
         * If this fails we do an interactive request
         */
        List<User> users = null;

        try {
            users = mgr.getPublicClient().getUsers();

            if (users != null && users.size() == 1) {
                /* We have 1 user */
                mUser = users.get(0);
                mgr.callAcquireTokenSilent(
                        mUser,
                        true,
                        this);
            } else {
                /* We have no user */

                /* Let's do an interactive request */
                mgr.callAcquireToken(
                        this,
                        this);
            }
        } catch (MsalClientException e) {
            Log.d(TAG, "MSAL Exception Generated while getting users: " + e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();



        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "User at this position does not exist: " + e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();


        }catch (IllegalStateException e) {
            Log.d(TAG, "MSAL Exception Generated: " + e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void validateOrganizationArgs() throws IllegalArgumentException {
        UUID.fromString(ServiceConstants.CLIENT_ID);
        URI.create(ServiceConstants.REDIRECT_URI);
    }

    private void start() {
        Intent appLaunch = new Intent(this, SnippetListActivity.class);
        startActivity(appLaunch);
    }
    public User mUser;

    @Override
    public void onError(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "User cancelled the flow.", Toast.LENGTH_SHORT).show();
    }
}
