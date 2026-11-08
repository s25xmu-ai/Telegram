package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/** A single entry point for the temporary wallet outage notice. */
public final class XGramWalletUnavailable {
    private XGramWalletUnavailable() { }

    public static void show(Context context, Theme.ResourcesProvider resourcesProvider) {
        if (context == null) return;
        new AlertDialog.Builder(context, resourcesProvider)
                .setTitle(LocaleController.getString(R.string.XGramWalletLabel))
                .setMessage(LocaleController.getString(R.string.XGramWalletUnavailable))
                .setPositiveButton(LocaleController.getString(R.string.OK), null)
                .show();
    }
}
