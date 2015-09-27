package com.example.michal.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MyActivity extends Activity {
	public String TAG="MyActivity";
	private TextView wysokoscNapiwkuTV, razemDoZaplaty, doZaplatyNaOsobe,wysokoscRachunkuET,liczbaOsobET,innyWyborET,tip;
	public final static String EXTRA_MESSAGE="com.example.michal.myfirstapp.MESSAGE";

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu_my, menu);
		return true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		wysokoscNapiwkuTV = (TextView) findViewById(R.id.wysokosc_napiwku_tv);
		razemDoZaplaty = (TextView) findViewById(R.id.do_zaplaty_tv);
		doZaplatyNaOsobe = (TextView) findViewById(R.id.na_osobe_tv);
		wysokoscRachunkuET = (TextView) findViewById(R.id.wysokosc_rachunku_et);
		liczbaOsobET = ( TextView ) findViewById(R.id.liczba_osob_et);
		innyWyborET = (TextView) findViewById(R.id.inny_wybor_et);
		tip = (TextView) findViewById(R.id.tip_tv);
		wyczyscRachunek(null);
	}

	@Override
	protected void onResume() {
		wysokoscNapiwkuTV.setText(String.valueOf(BillData.wysokoscNapiwku));
		razemDoZaplaty.setText(String.valueOf(BillData.doZaplaty));
		doZaplatyNaOsobe.setText(String.valueOf(BillData.naOsobe));
		super.onResume();
	}

	public void ustalWysokoscNapiwku(View view) {
		EditText innyProcentNapiwku = (EditText) findViewById(R.id.inny_wybor_et);
		int left  = (int) this.getResources().getDimension(R.dimen.padding_left_right_text);
		int right = (int) this.getResources().getDimension(R.dimen.padding_left_right_text);
		int top = (int) this.getResources().getDimension(R.dimen.padding_top_bottom_text);
		int bottom = (int) this.getResources().getDimension(R.dimen.padding_top_bottom_text);
		if (view == findViewById(R.id.radio1)) {
			innyProcentNapiwku.setVisibility(View.INVISIBLE);
			BillData.procentNapiwku = 10;
			(( RadioButton ) findViewById(R.id.radio2)).setChecked(false);
			((RadioButton ) findViewById(R.id.radio3)).setChecked(false);
			tip.setBackground(this.getResources().getDrawable(R.drawable.text_view_background_default_rounded));
			tip.setPadding(left, top, right, bottom);
		} else if (view == findViewById(R.id.radio2)) {
			innyProcentNapiwku.setVisibility(View.INVISIBLE);
			BillData.procentNapiwku = 15;
			((RadioButton ) findViewById(R.id.radio1)).setChecked(false);
			((RadioButton ) findViewById(R.id.radio3)).setChecked(false);
			tip.setBackground(this.getResources().getDrawable(R.drawable.text_view_background_default_rounded));
			tip.setPadding(left, top, right, bottom);

		} else if (view == findViewById(R.id.radio3)) {
			innyProcentNapiwku.setVisibility(View.VISIBLE);
			((RadioButton ) findViewById(R.id.radio2)).setChecked(false);
			((RadioButton ) findViewById(R.id.radio1)).setChecked(false);
			tip.setBackground(this.getResources().getDrawable(R.drawable.text_view_background_default));
			tip.setPadding(left, top, right, bottom);

		}
	}
	public void obliczRachunek(View view) {
		if (wysokoscRachunkuET.getText().toString().isEmpty()) {
			BillData.wysokoscRachunku = 0;
		} else {
			BillData.wysokoscRachunku = Double.valueOf(wysokoscRachunkuET.getText().toString());
		}
		if (liczbaOsobET.getText().toString().isEmpty()) {
			BillData.iloscOsob = 0;
		} else {
			BillData.iloscOsob = Double.valueOf(liczbaOsobET.getText().toString());
		}
		if (innyWyborET.getVisibility() == View.VISIBLE) {
			if (innyWyborET.getText().toString().isEmpty()) {
				BillData.procentNapiwku = 0;
			} else {
				BillData.procentNapiwku = Double.valueOf(innyWyborET.getText().toString());
				Log.d("TAG", innyWyborET.getText().toString());
			}
		}
		BillData.setWysokoscNapiwku();
		BillData.setDoZaplaty();
		BillData.setNaOsobe();
		BillData.poprawKwoteDoZaplaty();
		BillData.poprawKwoteNapiwku();
		DecimalFormat df2 = new DecimalFormat(".##");
		wysokoscNapiwkuTV.setText(df2.format(BillData.wysokoscNapiwku));
		razemDoZaplaty.setText(df2.format(BillData.doZaplaty));
		doZaplatyNaOsobe.setText(df2.format(BillData.naOsobe));
//		wysokoscNapiwkuTV.setText(String.format(, String.valueOf(BillData.wysokoscNapiwku)));
//		razemDoZaplaty.setText(String.format("%.2lf", String.valueOf(BillData.doZaplaty)));
//		doZaplatyNaOsobe.setText(String.format("%.2f", String.valueOf(BillData.naOsobe)));
	}
	public void wyczyscRachunek(View view) {
		BillData.wysokoscNapiwku = 0.0;
		BillData.wysokoscRachunku = 0.0;
		BillData.doZaplaty = 0.0;
		BillData.naOsobe = 0.0;
		BillData.iloscOsob = 0.0;
		BillData.procentNapiwku = 0.0;
		wysokoscNapiwkuTV.setText(String.valueOf(BillData.wysokoscNapiwku));
		razemDoZaplaty.setText(String.valueOf(BillData.doZaplaty));
		doZaplatyNaOsobe.setText(String.valueOf(BillData.naOsobe));
		wysokoscRachunkuET.setText("");
		liczbaOsobET.setText("");
		innyWyborET.setText("");
	}

}
