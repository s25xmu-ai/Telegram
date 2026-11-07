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
    private static final String KEY_HOME_BOTTOM_BAR = "home_bottom_bar";
    private static final String KEY_CLASSIC_DRAWER = "classic_drawer_v2";
    private static final String KEY_CLASSIC_DIALOG_ROWS = "classic_dialog_rows";
    private static final String KEY_CLASSIC_MESSAGE_MENU = "classic_message_menu";
    private static final String KEY_CLASSIC_INPUT = "classic_message_input";
    private static final String KEY_CLASSIC_CHAT_HEADER_LAYOUT = "classic_chat_header_layout";

    private static SharedPreferences prefs() {
        return ApplicationLoader.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static boolean showMoneyEntry(int currentAccount) {
        return SHOW_MONEY_ENTRY || MessagesController.getInstance(currentAccount).config.walletAvailable.get();
    }

    /** The home navigation bar is visible by default; an explicit choice is preserved. */
    public static boolean isHomeBottomBarVisible() {
        return prefs().getBoolean(KEY_HOME_BOTTOM_BAR, true);
    }

    public static void setHomeBottomBarVisible(boolean value) {
        prefs().edit().putBoolean(KEY_HOME_BOTTOM_BAR, value).apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.xgramInterfaceSettingsChanged);
    }

    public static boolean isClassicDrawerEnabled() {
        return prefs().getBoolean(KEY_CLASSIC_DRAWER, false);
    }

    public static void setClassicDrawerEnabled(boolean value) {
        prefs().edit().putBoolean(KEY_CLASSIC_DRAWER, value).apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.xgramInterfaceSettingsChanged);
    }

    public static boolean isClassicDialogRows() { return prefs().getBoolean(KEY_CLASSIC_DIALOG_ROWS, false); }
    public static void setClassicDialogRows(boolean value) {
        prefs().edit().putBoolean(KEY_CLASSIC_DIALOG_ROWS, value).apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.xgramInterfaceSettingsChanged);
    }

    public static boolean isClassicMessageMenu() { return prefs().getBoolean(KEY_CLASSIC_MESSAGE_MENU, false); }
    public static void setClassicMessageMenu(boolean value) { prefs().edit().putBoolean(KEY_CLASSIC_MESSAGE_MENU, value).apply(); }

    public static boolean isClassicMessageInput() { return prefs().getBoolean(KEY_CLASSIC_INPUT, false); }
    public static void setClassicMessageInput(boolean value) { prefs().edit().putBoolean(KEY_CLASSIC_INPUT, value).apply(); }

    public static boolean isClassicChatHeaderLayout() { return prefs().getBoolean(KEY_CLASSIC_CHAT_HEADER_LAYOUT, false); }
    public static void setClassicChatHeaderLayout(boolean value) { prefs().edit().putBoolean(KEY_CLASSIC_CHAT_HEADER_LAYOUT, value).apply(); }

    /** iOS-style chat bottom bar (message input field). Enabled by default. */
    public static boolean isIOSMessageInputField() {
        return prefs().getBoolean(KEY_IOS_INPUT_FIELD, true) && !isClassicMessageInput();
    }

    public static void setIOSMessageInputField(boolean value) {
        SharedPreferences.Editor editor = prefs().edit().putBoolean(KEY_IOS_INPUT_FIELD, value);
        if (value) editor.putBoolean(KEY_CLASSIC_INPUT, false);
        editor.apply();
    }

    /** iOS-style message context menu. Enabled by default. */
    public static boolean isIOSMessageMenu() {
        return prefs().getBoolean(KEY_IOS_MESSAGE_MENU, true) && !isClassicMessageMenu();
    }

    public static void setIOSMessageMenu(boolean value) {
        SharedPreferences.Editor editor = prefs().edit().putBoolean(KEY_IOS_MESSAGE_MENU, value);
        if (value) editor.putBoolean(KEY_CLASSIC_MESSAGE_MENU, false);
        editor.apply();
    }
}
