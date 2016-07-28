package co.binarywork.keepwalking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class KeepWalkingActivity extends AppCompatActivity {

    public static final int REQUEST_NEW = 29192;

    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_walking);

        mAddButton = (Button) findViewById(R.id.kw_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = WalkingEditorActivity.newIntent(KeepWalkingActivity.this, new WalkingItem());
                startActivityForResult(intent, REQUEST_NEW);
            }
        });

        FragmentManager
                fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.main_container);

        if( f == null ) {
            f = new KeepWalkingListFragment();

            fm.beginTransaction()
                    .add(R.id.main_container, f)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_NEW) {
            
        }
    }
}
