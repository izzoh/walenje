package com.izzoh.walenje;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LandingActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentManager fm = getSupportFragmentManager();
		if (fm.findFragmentById(android.R.id.content) == null) {
			SupermarketListFrag frag = new SupermarketListFrag();
			fm.beginTransaction().add(android.R.id.content, frag).commit();
		}
		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu_super_list, menu);
		return super.onCreateOptionsMenu(menu);
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

	public static class SupermarketAdapter extends ArrayAdapter<Supermarket> {
		public SupermarketAdapter(Context c) {
			//super(c, R.layout.super_list_item_2);
			super(c, android.R.layout.simple_list_item_1);
		}

		public void setData(List<Supermarket> data) {
			clear();
			if (data != null) {
				for (Supermarket i : data) {
					add(i);
				}
			}
		}

		//@Override
		public View getViemw(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			//View v = (convertView == null) ? inflater.inflate(R.layout.super_list_item_2, parent, false) : convertView;
			View v = inflater.inflate(R.layout.super_list_item_2, parent, false);

			Supermarket s = getItem(position);
			((TextView) v.findViewById(R.id.txtCount)).setText(String.valueOf(s
					.getPoints()));
			((TextView) v.findViewById(R.id.txtName)).setText(s.getName());
			if (s.getIcon() != -1 && s.getIcon() != 0)
				((ImageView) v.findViewById(R.id.imgLogo)).setImageResource(s
						.getIcon());
			return v;

		}
	}

	public static class SupermarketLoader extends
			AsyncTaskLoader<List<Supermarket>> {
		public SupermarketLoader(Context c) {
			super(c);
		}

		@Override
		public List<Supermarket> loadInBackground() {
			List<Supermarket> data = new ArrayList<Supermarket>();
			data.add(new Supermarket("Nakumatt", 102, R.drawable.nakumatt));
			data.add(new Supermarket("Tuskys", 1202, R.drawable.tuskys));
			data.add(new Supermarket("Kenchic", 12, R.drawable.kenchic));
			data.add(new Supermarket("Uchumi", 2, R.drawable.uchumi));
			data.add(new Supermarket("Steers", 2, R.drawable.steers));
			return data;
		}
	}

	public static class SupermarketListFrag extends SherlockListFragment
			implements LoaderManager.LoaderCallbacks<List<Supermarket>> {
		private SupermarketAdapter adapter;

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setEmptyText(getResources().getString(R.string.empty_point_list));
			adapter = new SupermarketAdapter(getActivity());
			setListShown(false);
			SupermarketLoader l = (SupermarketLoader) getLoaderManager()
					.initLoader(0, null, this);
			l.forceLoad();
			setListAdapter(adapter);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			Supermarket sp = (Supermarket)getListAdapter().getItem(position);// (Supermarket) l.getSelectedItem();
			//TODO Remove hack to accomodate string
			Intent i = new Intent(getActivity(), ReportActivity.class);
			i.putExtra(ReportActivity.EXTRA_SUPER, sp);
			startActivity(i);

		}

		@Override
		public Loader<List<Supermarket>> onCreateLoader(int arg0, Bundle arg1) {
			return new LandingActivity.SupermarketLoader(getActivity());
		}

		@Override
		public void onLoadFinished(Loader<List<Supermarket>> arg0,
				List<Supermarket> arg1) {
			adapter.setData(arg1);
			setListShown(true);
		}

		@Override
		public void onLoaderReset(Loader<List<Supermarket>> arg0) {
			adapter.notifyDataSetInvalidated();
			adapter.setData(null);
			//
//			setListShown(false);
		}
	}

}
