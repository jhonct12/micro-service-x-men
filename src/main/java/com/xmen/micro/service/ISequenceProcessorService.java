package com.xmen.micro.service;

import java.util.ArrayList;

public interface ISequenceProcessorService {

	int verifyHorizontal(ArrayList<String> dnas, int count);

	int verifyVertical(ArrayList<String> dnas, int count);

	int verifyDiagonalLeftToRight(ArrayList<String> dnas, int count);
	
	int verifyDiagonalRightToLeft(ArrayList<String> dnas, int count);
}
