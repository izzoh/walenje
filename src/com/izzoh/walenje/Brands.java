package com.izzoh.walenje;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.izzoh.walenje.LandingActivity.SupermarketAdapter;

public class Brands extends SherlockListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing_list_frag);
		SupermarketAdapter sa = null;
		ArrayAdapter<Supermarket> aa = new ArrayAdapter<Supermarket>(this,
				android.R.layout.simple_list_item_1);
		getListView().setAdapter(aa);
		aa.add(new LandingActivity.SupermarketLoader(this).loadInBackground().get(0));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(this, "Am Alive Man", Toast.LENGTH_SHORT).show();
	}

}
