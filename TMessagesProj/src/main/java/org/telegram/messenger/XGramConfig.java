package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * XGram UI options.
 * Note: Money entry points are only made visible here. Wallet operations
 * (GramWallet, links, transfers) still respect the server "wallet_available" flag.
 */
public final class XGramConfig {

    private XGramConfig() {}

    /** Always show the Money entry points (Settings, chat list menu, attach menu). */
    public static final boolean SHOW_MONEY_ENTRY = true;

    private static final String PREFS = "xgram_config";
    private static final String KEY_IOS_INPUT_FIELD = "ios_message_input_field";
    private static final String KEY_IOS_MESSAGE_MENU = "ios_message_menu";

    private static SharedPreferences prefs() {
        return ApplicationLoader.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static boolean showMoneyEntry(int currentAccount) {
        return SHOW_MONEY_ENTRY || MessagesController.getInstance(currentAccount).config.walletAvailable.get();
    }

    /** iOS-style chat bottom bar (message input field). Enabled by default. */
    public static boolean isIOSMessageInputField() {
        return prefs().getBoolean(KEY_IOS_INPUT_FIELD, true);
    }

    public static void setIOSMessageInputField(boolean value) {
        prefs().edit().putBoolean(KEY_IOS_INPUT_FIELD, value).apply();
    }

    /** iOS-style message context menu. Enabled by default. */
    public static boolean isIOSMessageMenu() {
        return prefs().getBoolean(KEY_IOS_MESSAGE_MENU, true);
    }

    public static void setIOSMessageMenu(boolean value) {
        prefs().edit().putBoolean(KEY_IOS_MESSAGE_MENU, value).apply();
    }
}
