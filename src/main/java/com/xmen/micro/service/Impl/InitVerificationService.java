package com.xmen.micro.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.mapper.exeption.DnaExistExeption;
import com.xmen.micro.service.IInitVerificationService;
import com.xmen.micro.service.IPersonService;
import com.xmen.micro.service.ISequenceProcessorService;
import com.xmen.micro.service.IValidatorInputDNAService;

@Service
public class InitVerificationService implements IInitVerificationService {

	@Autowired
	private IValidatorInputDNAService iValidatorInputDNAService;

	@Autowired
	private ISequenceProcessorService iSequenceProcessorService;

	@Autowired
	private LoadProperties loadProperties;

	@Autowired
	private IPersonService iPersonService;

	@Override
	public boolean startProcess(ArrayList<String> dnas) {

		boolean isMutant = iValidatorInputDNAService.validateInputDNA(dnas);

		if (!isMutant) {
			return isMutant;
		}

		boolean exist = iPersonService.checkDNAExist(dnas);

		if (exist) {
			throw new DnaExistExeption(loadProperties.getMsgDnaExist());
		}

		boolean resultProcess = initProccess(dnas);

		iPersonService.saveNewDna(dnas, resultProcess);

		return resultProcess;
	}

	private boolean initProccess(ArrayList<String> dnas) {
		int count = 0;

		count = iSequenceProcessorService.verifyHorizontal(dnas, count);

		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}
		count = iSequenceProcessorService.verifyVertical(dnas, count);

		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		count = iSequenceProcessorService.verifyDiagonalLeftToRight(dnas, count);

		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		count = iSequenceProcessorService.verifyDiagonalRightToLeft(dnas, count);

		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}
		return false;
	}

}
