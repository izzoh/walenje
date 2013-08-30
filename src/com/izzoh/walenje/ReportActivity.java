package com.izzoh.walenje;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ReportActivity extends SherlockActivity {

    private Supermarket  supermarket;
    public static String EXTRA_SUPER = ReportActivity.class.getCanonicalName()
                                             + ".super";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.)
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.containsKey(EXTRA_SUPER)) {
                supermarket = (Supermarket) b.get(EXTRA_SUPER);
                if (supermarket != null) {
                    setTitle(supermarket.getName());
                    if (supermarket.getIcon() != -1
                            && supermarket.getIcon() != 0)
                        getSupportActionBar().setIcon(supermarket.getIcon());
                } else {
                    Toast.makeText(this, "supermarket not selected==null",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "supermarket not selected contains",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "supermarket not selected bundle==null",
                    Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.menu_report, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
