package blackson.n.zeilschooldewaai.managers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.activities.BaseActivity;

/**
 * manages the toolbar_layout and holds its views
 */
public class ToolbarManager {

    private TextView startText;

    private TextView endText;

    private TextView title;

    private ImageView mDrawerImage;

    public ToolbarManager(@NonNull Activity pActivity) {
        endText = pActivity.findViewById(R.id.end_text);
        title = pActivity.findViewById(R.id.title);
        startText = pActivity.findViewById(R.id.start_text);
        mDrawerImage = pActivity.findViewById(R.id.drawer_icon);
    }

    public void reset(final BaseActivity pBaseActivity) {
        if (title == null) {
            // if there is no title view there is no toolbar layout or the activity is not active
            return;
        }

        setTitle("");
        setEndText("");
        setStartText("");
        setEndIconListener(null);
        mDrawerImage.setImageResource(R.drawable.ic_drawer_icon);
        mDrawerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pBaseActivity.openDrawer();
            }
        });
        setDrawerVisibility(View.VISIBLE);
    }

    public void setTitle(String pTitle) {
        title.setText(pTitle);
    }

    public void setEndText(String text) {
        endText.setText(text);
    }

    public void setEndIconListener(View.OnClickListener pEndIconListener) {
        endText.setOnClickListener(pEndIconListener);
    }

    private void setStartText(String pText) {
        startText.setText(pText);
    }

    public void setDrawerVisibility(final int pDrawerVisibility) {
        mDrawerImage.setVisibility(pDrawerVisibility);
    }

    public void setDrawerListener(View.OnClickListener pListener) {
        mDrawerImage.setOnClickListener(pListener);
    }

    public void initBackButton(View.OnClickListener pListener) {
        mDrawerImage.setImageResource(R.drawable.ic_back_btn);
        mDrawerImage.setOnClickListener(pListener);
    }
}
