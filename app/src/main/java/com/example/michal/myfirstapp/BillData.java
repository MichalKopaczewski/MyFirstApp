package com.example.michal.myfirstapp;

public class BillData {
	public static double wysokoscRachunku=0.0,doZaplaty=0.0, naOsobe=0.0, wysokoscNapiwku=0.0;
	public static double iloscOsob=0.0, procentNapiwku=0.0;

	public static void setWysokoscNapiwku() {
		wysokoscNapiwku = wysokoscRachunku * procentNapiwku / 100;
		wysokoscNapiwku = (double)((int) Math.floor(BillData.wysokoscNapiwku * 100)) / 100;

	}
	public static void setDoZaplaty() {
		doZaplaty = wysokoscNapiwku + wysokoscRachunku;
		doZaplaty = (double)((int) Math.round(BillData.doZaplaty * 100)) / 100;
	}
	public static void setNaOsobe() {
		naOsobe = doZaplaty / iloscOsob;
		naOsobe = (double) ((int) Math.ceil(BillData.naOsobe * 100)) / 100;

	}
	public static void poprawKwoteDoZaplaty() {
		doZaplaty = naOsobe * iloscOsob;
	}
	public static void poprawKwoteNapiwku() {
		wysokoscNapiwku = doZaplaty - wysokoscRachunku;
	}
}
