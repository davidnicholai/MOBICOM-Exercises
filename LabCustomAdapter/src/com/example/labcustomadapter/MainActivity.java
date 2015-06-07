package com.example.labcustomadapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		City[] cities = {
				new City("Amsterdam", "Germany", R.drawable.amsterdam),
				new City("Austin", "Texas", R.drawable.austin),
				new City("Barcelona", "Spain", R.drawable.barcelona),
				new City("Berlin", "Germany", R.drawable.berlin),
				new City("Cape Town", "South Africa", R.drawable.cape_town),
				new City("Dublin", "Ireland", R.drawable.dublin),
				new City("London", "England", R.drawable.london),
				new City("New York", "USA", R.drawable.new_york),
				new City("Paris", "France", R.drawable.paris),
				new City("San Francisco", "USA", R.drawable.san_francisco),
				new City("Stockholm", "Sweden", R.drawable.stockholm),
				new City("Sydney", "Australia", R.drawable.sydney),
				new City("Tokyo", "Japan", R.drawable.tokyo),
				new City("Wellington", "New Zealand", R.drawable.wellington), };

		ListView lv = (ListView) findViewById(R.id.listview_cities);
		CustomItemAdapter adapter = new CustomItemAdapter(getBaseContext(),
				R.layout.list_item, cities);

		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
