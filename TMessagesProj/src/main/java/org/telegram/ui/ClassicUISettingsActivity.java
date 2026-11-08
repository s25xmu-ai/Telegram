package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.XGramConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.LayoutHelper;

/** Optional interface elements can be combined independently. */
public class ClassicUISettingsActivity extends BaseFragment {
    @Override
    public View createView(Context context) {
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle(LocaleController.getString(R.string.XGramClassicUI));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override public void onItemClick(int id) { if (id == -1) finishFragment(); }
        });
        ScrollView root = new ScrollView(context);
        root.setFillViewport(true);
        root.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, getResourceProvider()));
        LinearLayout list = new LinearLayout(context);
        list.setOrientation(LinearLayout.VERTICAL);
        root.addView(list, new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));
        TextCheckCell menu = addToggle(context, list, R.string.XGramClassicDrawer, XGramConfig.isClassicDrawerEnabled(), true);
        menu.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicDrawerEnabled(); XGramConfig.setClassicDrawerEnabled(value); menu.setChecked(value); });
        TextCheckCell rows = addToggle(context, list, R.string.XGramClassicDialogRows, XGramConfig.isClassicDialogRows(), true);
        rows.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicDialogRows(); XGramConfig.setClassicDialogRows(value); rows.setChecked(value); });
        TextCheckCell messageMenu = addToggle(context, list, R.string.XGramClassicMessageMenu, XGramConfig.isClassicMessageMenu(), true);
        messageMenu.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicMessageMenu(); XGramConfig.setClassicMessageMenu(value); messageMenu.setChecked(value); });
        TextCheckCell input = addToggle(context, list, R.string.XGramClassicMessageInput, XGramConfig.isClassicMessageInput(), true);
        input.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicMessageInput(); XGramConfig.setClassicMessageInput(value); input.setChecked(value); });
        TextCheckCell header = addToggle(context, list, R.string.XGramClassicChatHeaderLayout, XGramConfig.isClassicChatHeaderLayout(), true);
        header.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicChatHeaderLayout(); XGramConfig.setClassicChatHeaderLayout(value); header.setChecked(value); });
        TextCheckCell profile = addToggle(context, list, R.string.XGramClassicProfileDetails, XGramConfig.isClassicProfileDetails(), true);
        profile.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicProfileDetails(); XGramConfig.setClassicProfileDetails(value); profile.setChecked(value); });
        TextCheckCell settingsRows = addToggle(context, list, R.string.XGramClassicSettingsRows, XGramConfig.isClassicSettingsRows(), true);
        settingsRows.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicSettingsRows(); XGramConfig.setClassicSettingsRows(value); settingsRows.setChecked(value); });
        TextCheckCell settingsHeader = addToggle(context, list, R.string.XGramClassicSettingsHeader, XGramConfig.isClassicSettingsHeader(), false);
        settingsHeader.setOnClickListener(v -> { boolean value = !XGramConfig.isClassicSettingsHeader(); XGramConfig.setClassicSettingsHeader(value); settingsHeader.setChecked(value); });
        TextView info = new TextView(context);
        info.setTextSize(14);
        info.setTypeface(AndroidUtilities.getVazirmatnTypeface());
        info.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, getResourceProvider()));
        info.setText(LocaleController.getString(R.string.XGramClassicUIInfo));
        info.setPadding(AndroidUtilities.dp(21), AndroidUtilities.dp(12), AndroidUtilities.dp(21), AndroidUtilities.dp(12));
        list.addView(info, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));
        fragmentView = root;
        return fragmentView;
    }

    private TextCheckCell addToggle(Context context, LinearLayout list, int label, boolean checked, boolean divider) {
        TextCheckCell cell = new TextCheckCell(context);
        cell.setTypeface(AndroidUtilities.getVazirmatnTypeface());
        cell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider()));
        cell.setTextAndCheck(LocaleController.getString(label), checked, divider);
        list.addView(cell, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));
        return cell;
    }
}
