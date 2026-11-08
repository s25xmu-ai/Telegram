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

/** Controls the existing iOS-inspired message components without changing their defaults. */
public class IOSUISettingsActivity extends BaseFragment {
    @Override
    public View createView(Context context) {
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle(LocaleController.getString(R.string.XGramIOSUI));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override public void onItemClick(int id) { if (id == -1) finishFragment(); }
        });
        ScrollView root = new ScrollView(context);
        root.setFillViewport(true);
        root.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, getResourceProvider()));
        LinearLayout list = new LinearLayout(context);
        list.setOrientation(LinearLayout.VERTICAL);
        root.addView(list, new ScrollView.LayoutParams(-1, -2));
        TextCheckCell input = addToggle(context, list, R.string.XGramIOSInputField, XGramConfig.isIOSMessageInputField(), true);
        input.setOnClickListener(v -> {
            boolean value = !XGramConfig.isIOSMessageInputField();
            XGramConfig.setIOSMessageInputField(value);
            input.setChecked(value);
        });
        TextCheckCell menu = addToggle(context, list, R.string.XGramIOSMessageMenu, XGramConfig.isIOSMessageMenu(), true);
        menu.setOnClickListener(v -> {
            boolean value = !XGramConfig.isIOSMessageMenu();
            XGramConfig.setIOSMessageMenu(value);
            menu.setChecked(value);
        });
        TextCheckCell header = addToggle(context, list, R.string.XGramIOSChatHeader, XGramConfig.isIOSChatHeaderLayout(), false);
        header.setOnClickListener(v -> {
            boolean value = !XGramConfig.isIOSChatHeaderLayout();
            XGramConfig.setIOSChatHeaderLayout(value);
            header.setChecked(value);
        });
        TextView info = new TextView(context);
        info.setTypeface(AndroidUtilities.getVazirmatnTypeface());
        info.setTextSize(14);
        info.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, getResourceProvider()));
        info.setText(LocaleController.getString(R.string.XGramIOSUIInfo));
        info.setPadding(AndroidUtilities.dp(21), AndroidUtilities.dp(12), AndroidUtilities.dp(21), AndroidUtilities.dp(12));
        list.addView(info, LayoutHelper.createLinear(-1, -2));
        fragmentView = root;
        return fragmentView;
    }
    private TextCheckCell addToggle(Context context, LinearLayout parent, int label, boolean checked, boolean divider) {
        TextCheckCell cell = new TextCheckCell(context);
        cell.setTypeface(AndroidUtilities.getVazirmatnTypeface());
        cell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider()));
        cell.setTextAndCheck(LocaleController.getString(label), checked, divider);
        parent.addView(cell, LayoutHelper.createLinear(-1, -2));
        return cell;
    }
}
