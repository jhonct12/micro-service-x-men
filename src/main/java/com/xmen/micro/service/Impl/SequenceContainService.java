package com.xmen.micro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.service.ISequenceContainService;

@Service
public class SequenceContainService implements ISequenceContainService {

	@Autowired
	private LoadProperties loadProperties;

	@Override
	public int isContain(String sequenceDNA, int count) {
		for (String sequenceMutant : loadProperties.getMutanSequence()) {
			String auxSequenceDNA = String.valueOf(sequenceDNA);
			while (auxSequenceDNA.indexOf(sequenceMutant) > -1) {

				auxSequenceDNA = auxSequenceDNA.substring(
						auxSequenceDNA.indexOf(sequenceMutant) + sequenceMutant.length(), auxSequenceDNA.length());
				count++;
				if (count >= loadProperties.getNumberRepetitions()) {
					break;
				}
			}
			if (count >= loadProperties.getNumberRepetitions()) {
				break;
			}
		}
		return count;
	}

}
