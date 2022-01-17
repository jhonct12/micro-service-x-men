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

	// Funcion principal, que llama el controlador de mutant
	@Override
	public boolean startProcess(ArrayList<String> dnas) {

		// Invocamos funcion que permite la validacion inicial, validamos si los
		// caracteres y la longitud de es acorde a lo pedido, adicionalmente si la
		// cadena dna es menor a la cantidad de caracteres iguales pero es cuadrada se
		// determina como humano
		boolean isMutant = iValidatorInputDNAService.validateInputDNA(dnas);

		// Si el DNA no es mutante devolvemos false
		if (!isMutant) {
			if (iPersonService.checkDNAExist(dnas)) {
				throw new DnaExistExeption(loadProperties.getMsgDnaExist());
			} else {
				iPersonService.saveNewDna(dnas, isMutant);
			}
			return isMutant;
		}

		// validamos si el dna ya esta registrado
		boolean exist = iPersonService.checkDNAExist(dnas);

		// si esta registrado generamos una exepcion de que ya existe
		if (exist) {
			throw new DnaExistExeption(loadProperties.getMsgDnaExist());
		}

		// si el dna cumple con todas las condiciones llamamos el metodo principal
		boolean resultProcess = initProccess(dnas);

		// independiente del proceso o resultado, se crea un registro del dna
		iPersonService.saveNewDna(dnas, resultProcess);

		return resultProcess;
	}

	private boolean initProccess(ArrayList<String> dnas) {

		// iniciamos esta variable para la cantidad de ocurrencias que estamos
		// encontrando
		int count = 0;

		// primero hacemos una verificacion horizontal donde se encuentren la mayor
		// cantidad de secuencias
		count = iSequenceProcessorService.verifyHorizontal(dnas, count);

		// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
		// mutante, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		// luego realizamos la busqueda de manera vertical, llevando el contador
		// anterior, ya que me interesa la suma de sequencias, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		count = iSequenceProcessorService.verifyVertical(dnas, count);

		// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
		// mutante, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		// luego realizamos la busqueda de las diagonales de izquierda a derecha,
		// llevando el contador
		// anterior, ya que me interesa la suma de sequencias, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		count = iSequenceProcessorService.verifyDiagonalLeftToRight(dnas, count);

		// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
		// mutante, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		// luego realizamos la busqueda de las diagonales de derecha a izquiera,
		// llevando el contador
		// anterior, ya que me interesa la suma de sequencias, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		count = iSequenceProcessorService.verifyDiagonalRightToLeft(dnas, count);

		// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
		// mutante, si ya encuentro mas de
		// las esperadas no es necesario seguir buscando
		if (count >= loadProperties.getNumberRepetitions()) {
			return true;
		}

		// la cantidad no es igual a la esperada la persona es un humano
		return false;
	}

}
