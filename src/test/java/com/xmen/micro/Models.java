
package com.xmen.micro;

import java.util.ArrayList;

import com.xmen.micro.domain.SingleDNA;

public class Models {

	public static SingleDNA singleDNA() {
		SingleDNA singleDNA = new SingleDNA();

		ArrayList<String> lisDna = new ArrayList<String>();

		lisDna.add("ATGCGA");
		lisDna.add("CAGTGC");
		lisDna.add("TTATGT");
		lisDna.add("AGAAGG");
		lisDna.add("CCCCTA");
		lisDna.add("TCACTG");

		singleDNA.setDna(lisDna);

		return singleDNA;
	}

	public static SingleDNA rightToLeft() {
		SingleDNA singleDNA = new SingleDNA();

		ArrayList<String> lisDna = new ArrayList<String>();

		lisDna.add("AGTCAGCCAG");
		lisDna.add("TCGATCGCTC");
		lisDna.add("AGCTAGCTCG");
		lisDna.add("TCGATCGATC");
		lisDna.add("AGCTAGCTAG");
		lisDna.add("AGTCAGTCAG");
		lisDna.add("TAGATCGATC");
		lisDna.add("AGATAGCTAG");
		lisDna.add("TCGATCGATC");
		lisDna.add("AGCTAGCTAG");

		singleDNA.setDna(lisDna);

		return singleDNA;
	}

	public static SingleDNA leftToRight() {
		SingleDNA singleDNA = new SingleDNA();

		ArrayList<String> lisDna = new ArrayList<String>();

		lisDna.add("AGTCAGTCAG");
		lisDna.add("TCCATCGATC");
		lisDna.add("ACCTAGCTAG");
		lisDna.add("CCGATCGATC");
		lisDna.add("AGCTAGCTAG");
		lisDna.add("AGTCAGTCAG");
		lisDna.add("TCGATCGATC");
		lisDna.add("AGCTAGCTCG");
		lisDna.add("TCGATCGCTC");
		lisDna.add("AGCTAGCTAG");

		singleDNA.setDna(lisDna);

		return singleDNA;
	}
}
