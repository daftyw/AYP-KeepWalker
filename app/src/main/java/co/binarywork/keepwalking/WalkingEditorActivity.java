package co.binarywork.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WalkingEditorActivity extends AppCompatActivity {


    private static final String WALKING_ITEM = "KW.Walking.Item";

    public static Intent newIntent(Context c, WalkingItem walkingItem) {
        Intent intent = new Intent(c, WalkingEditorActivity.class);
        intent.putExtra(WALKING_ITEM, walkingItem);

        return intent;
    }

    /////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_editor);

        WalkingItem walkingItem = (WalkingItem) getIntent().getSerializableExtra(WALKING_ITEM);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.walking_editor_container);

        if(f == null) {
            f = KWEditorFragment.newInstance(walkingItem);

            fm.beginTransaction()
                    .add(R.id.walking_editor_container, f).commit();
        }
    }
}
