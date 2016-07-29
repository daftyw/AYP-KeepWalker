package co.binarywork.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class KeepWalkingActivity extends AppCompatActivity {

    private Button _addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_walking);

        _addButton = (Button) findViewById(R.id.kw_add_button);
        _addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = KWEditorActivity.newIntent(KeepWalkingActivity.this, -1);
                startActivity(intent);
            }
        });

        FragmentManager
                fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.main_container);

        if( f == null ) {
            f = KeepWalkingListFragment.newInstance();

            fm.beginTransaction()
                    .add(R.id.main_container, f)
                    .commit();
        }
    }


}
