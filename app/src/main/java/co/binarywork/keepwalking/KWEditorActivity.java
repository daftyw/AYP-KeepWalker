package co.binarywork.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class KWEditorActivity extends AppCompatActivity {

    private static final String WALKING_ITEM = "KW.Walking.Item";

    public static Intent newIntent(Context c, Integer itemIndex) {
        Intent intent = new Intent(c, KWEditorActivity.class);
        intent.putExtra(WALKING_ITEM, itemIndex);
        return intent;
    }

    /////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_editor);

        Intent intent = getIntent();
        Integer walkingItemIndex = intent.getIntExtra(WALKING_ITEM, -1);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.walking_editor_container);

        if(f == null) {
            f = KWEditorFragment.newInstance(walkingItemIndex);

            fm.beginTransaction()
                    .add(R.id.walking_editor_container, f).commit();
        }
    }
}
