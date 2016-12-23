package ieci.tecdoc.sgm.migration.mgr.impl;

import ieci.tecdoc.sgm.core.exception.SigemException;
import ieci.tecdoc.sgm.core.services.LocalizadorServicios;
import ieci.tecdoc.sgm.core.services.dto.Entidad;
import ieci.tecdoc.sgm.core.services.registro.ServicioRegistro;
import ieci.tecdoc.sgm.core.services.registro.UserInfo;
import ieci.tecdoc.sgm.core.services.repositorio.ServicioRepositorioDocumentosTramitacion;
import ieci.tecdoc.sgm.core.services.telematico.Registro;
import ieci.tecdoc.sgm.core.services.telematico.RegistroConsulta;
import ieci.tecdoc.sgm.core.services.telematico.RegistroEstado;
import ieci.tecdoc.sgm.core.services.telematico.Registros;
import ieci.tecdoc.sgm.core.services.telematico.ServicioRegistroTelematico;
import ieci.tecdoc.sgm.migration.config.Config;
import ieci.tecdoc.sgm.migration.config.SWDeclaration;
import ieci.tecdoc.sgm.migration.email.EmailManager;
import ieci.tecdoc.sgm.migration.mgr.MigrationManager;
import ieci.tecdoc.sgm.migration.mgr.dto.ResultadoMigracionDto;
import ieci.tecdoc.sgm.migration.register.MigrationRegister;
import ieci.tecdoc.sgm.migration.utils.Constantes;
import ieci.tecdoc.sgm.migration.utils.Utils;
import ieci.tecdoc.sgm.migration.utils.UtilsService;
import ieci.tecdoc.sgm.registropresencial.ws.server.FolderSearchCriteria;
import ieci.tecdoc.sgm.registropresencial.ws.server.RegistersInfo;
import ieci.tecdoc.sgm.registropresencial.ws.server.ServicioRegistroWebService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

public class MigrationManagerImpl extends MigrationRegister implements MigrationManager {

	/**
	 * Logger de la clase.
	 */
	private static final Logger logger = Logger.getLogger(MigrationManagerImpl.class);

	/**
	 * Servicio del Registro Presencial de Origen
	 */
	private ServicioRegistroWebService oServicioOrigen = null;
	
	/**
	 * Servicio del Registro Presencial de Destino
	 */
	private ServicioRegistroWebService oServicioDestino = null;
	
	/**
	 * Usuario de registro del sigem de origen
	 */
	private ieci.tecdoc.sgm.registropresencial.ws.server.UserInfo userOrigen = null;
	
	/**
	 * Usuario de registro del sigem de destino
	 */
	private ieci.tecdoc.sgm.registropresencial.ws.server.UserInfo userDestino = null;
	
	/**
	 * C�digo de entidad del sigem de origen
	 */
	private ieci.tecdoc.sgm.core.services.dto.Entidad entidadOrigen = null;
	
	/**
	 * C�digo de entidad del sigem de destino
	 */
	private ieci.tecdoc.sgm.core.services.dto.Entidad entidadDestino = null;
	
	/**
	 * Constructor.
	 * 
	 * @throws SigemException
	 *             si ocurre alg�n error.
	 * @throws ServiceException 
	 */
	public MigrationManagerImpl(String idOrigenEntidad, String idDestinoEntidad) throws SigemException, ServiceException {
		super();

		this.oServicioOrigen = SWDeclaration.getInstance().getoServicioOrigen();
		this.oServicioDestino = SWDeclaration.getInstance().getoServicioDestino();
		this.userOrigen = UtilsService.getOrigenUser();
		this.userDestino = UtilsService.getDestinoUser();
		this.entidadOrigen = UtilsService.getEntidad(idOrigenEntidad);
		this.entidadDestino = UtilsService.getEntidad(idDestinoEntidad);
		
	}

	/**
	 * Realiza el proceso de migraci�n de registros sobre una entidad.
	 * 
	 * @throws SigemException
	 *             si ocurre alg�n error.
	 */
	public void migrationRegisterEntity() throws SigemException {

		ResultadoMigracionDto resultado = new ResultadoMigracionDto();
		
		try {
		
			Utils.trace("\n\nInicio del proceso de migraci�n de registros..");
			// Se recupera la hora de inicio
			long millis = UtilsService.timeInit();
			resultado.setFechaInicio(Utils.getDateProcess(millis));
			
			// Migraci�n de los registros de entrada
			migrarRegistrosLibroEntrada(resultado);
			
			// Migraci�n de los registros de salida
			migrarRegistrosLibroSalida(resultado);
			
			// Envio de correo electr�nico con el resultado de la migraci�n
			envioEmailConResultadoMigracion(resultado, millis);
			
			if (logger.isInfoEnabled()) {
				logger.info("Proceso de consolidaci�n finalizado en entidad " + Config.getInstance().getOrigenEntity());
			}
		
		} catch (Throwable t) {
			logger.error("Error en la consolidaci�n de la entidad " + entidadOrigen.getIdentificador(), t);
		}
	}


	/*
	 * M�todo que muestra por consola el resultado de la migraci�n de registros 
	 * @param resultado - Objeto que contiente los contadores de registros y la fecha de inicio
	 * @param millis - Fecha de inicio del proceso en milisegundos
	 * @throws SigemException - Si ocurre alg�n error se lanza esta exception
	 */
	
	private void envioEmailConResultadoMigracion(ResultadoMigracionDto resultado, long millis) throws SigemException {
		
		millis = UtilsService.timeStop(millis);
		resultado.setFechaFin(Utils.getDateProcess(millis));
		
		// Si no existen registros migrados no se env�a el correo
		// El cliente ha solicitado este cambio, siempre se env�an correos (04-02-2012)
		// int total = resultado.getTotalConsolidadosEntrada() + resultado.getTotalConsolidadosSalida() + resultado.getTotalNoConsolidadosEntrada() + resultado.getTotalNoConsolidadosEntrada();
		//if( total > 0) { 
		sendEmailConsolidacion(getMessage(resultado), getAsunto(resultado));
		//}
		
		// Muestra en el log el resultado de la migraci�n
		Utils.trace("\n\n");
		Utils.trace("RESULTADO MIGRACION: ");
		Utils.trace("====================");
		Utils.trace("[INICIO]: " + resultado.getFechaInicio());
		Utils.trace("Consolidados de Entrada: " + resultado.getTotalConsolidadosEntrada());
		Utils.trace("Consolidados de Salida: " + resultado.getTotalConsolidadosSalida());
		Utils.trace("NO Consolidados de Entrada: " + resultado.getTotalNoConsolidadosEntrada());
		Utils.trace("NO Consolidados de Salida: " + resultado.getTotalNoConsolidadosSalida());
		Utils.trace("[FIN]: " + resultado.getFechaFin());
		
		
	}
	
	/**
	 * M�todo que env�a el correo electr�nico con el resultado de la migraci�n 
	 * @param message - Texto con el contenido del mensaje
	 * @param asunto - Texto con el asunto del mensaje
	 * @throws SigemException - Si se produce alg�n error se lanza esta excepci�n
	 */
	private void sendEmailConsolidacion(String message, String asunto) throws SigemException {
		if (!isBlank(Config.getInstance().getEmailOrigen()) && 
			!isBlank(Config.getInstance().getEmailDestino())) {

			StringBuffer content = new StringBuffer();
			content.append(message+"\n");
			
			if (logger.isInfoEnabled()) {
				logger.info("Se procede a enviar la notificaci�n de la sincronizaci�n de los registros electr�nicos.\n E-mail Origen: "
						+ Config.getInstance().getEmailOrigen()
						+ "\n E-mail Destino: "
						+ Config.getInstance().getEmailDestino()
						+ "\n Asunto E-mail: "
						+ asunto
						+ "\n Contenido E-mail: "
						+content.toString());
			}
			// Enviar el e-mail de error de consolidaci�n
			String[] emailDestinoArray = {Config.getInstance().getEmailOrigen()};
			EmailManager emailMgr = new EmailManager();
			emailMgr.sendMail(Config.getInstance().getEmailDestino(), 
					emailDestinoArray, null, null,
					asunto, content.toString(), null);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("No se env�a el e-mail de notificaci�n de la sincronizaci�n de los registros electr�nicos porque as� se ha configurado al dejar vac�os las propiedades emailOrigen, emailDestino y emailAsunto");
			}
		}
	}
	
	
	/**
	 * M�todo que se encarga de hacer la migraci�n de los registros de Entrada desde Sigem Housing a Sigem UAM
	 * @param resultado - Objeto donde se establece la totalidad de registros consolidados y no consolidados
	 * @throws SigemException - Si se produce alg�n error se lanza esta excepcion
	 * @throws RemoteException - Si se produce alg�n error en la conexi�n con los SW se lanza esta excepci�n
	 */
	public void migrarRegistrosLibroEntrada(ResultadoMigracionDto resultado) throws SigemException, RemoteException {
		
		// Obtener los Registros No Consolidados
		FolderSearchCriteria folderQuery = UtilsService.getFolderCriteriaEntrada();
		RegistersInfo registersInfo = oServicioOrigen.findFolder(userOrigen, folderQuery, entidadOrigen);
		
		int contadorRegistrosConsolidados = 0;
		int contadorRegistrosNoConsolidados = 0;
		List<String> numerosRegistrosConsolidadosEntrada = new ArrayList<String>();
		List<String> numerosRegistrosNoConsolidadosEntrada = new ArrayList<String>();
		
		if(!UtilsService.isResponeOK(registersInfo.getErrorCode(), registersInfo.getReturnCode())) {
			if(registersInfo.getReturnCode().equalsIgnoreCase(Constantes.RETURN_CODE_ERROR)
				&& registersInfo.getErrorCode().equalsIgnoreCase(String.valueOf(Constantes.COD_ERROR_EMPTY_REGISTERS))) 
			Utils.trace("No existen registros de ENTRADA sin consolidar");
			
		} else {
			Utils.trace("ReturnCode: " + registersInfo.getReturnCode());
			Utils.trace("Total Registros de ENTRADA: " + registersInfo.getRegisters().length);
			for(int i = 0; i < registersInfo.getRegisters().length; i++) {
				try {
					registerProcess(oServicioOrigen, oServicioDestino, userOrigen, userDestino, entidadOrigen, entidadDestino, registersInfo.getRegisters()[i]);
					numerosRegistrosConsolidadosEntrada.add(registersInfo.getRegisters()[i].getNumber());
					contadorRegistrosConsolidados++;
				} catch (Exception e) {
					contadorRegistrosNoConsolidados++;
					numerosRegistrosNoConsolidadosEntrada.add(registersInfo.getRegisters()[i].getNumber());
					logger.error("Error en el proceso de consolidaci�n del registro  '" + registersInfo.getRegisters()[i].getNumber()+ "': " + e.getMessage());
					e.printStackTrace();
				}
			}	
		}
		// Se establecen la totalidad de registros consolidados y no consolidados
		resultado.setTotalConsolidadosEntrada(contadorRegistrosConsolidados);
		resultado.setTotalNoConsolidadosEntrada(contadorRegistrosNoConsolidados);
		resultado.setNumbersRegistersConsolidadosEntrada(numerosRegistrosConsolidadosEntrada);
		resultado.setNumbersRegistersNoConsolidadosEntrada(numerosRegistrosNoConsolidadosEntrada);
	}
	
	
	/**
	 * M�todo que se encarga de hacer la migraci�n de los registros de Salida desde Sigem Housing a Sigem UAM
	 * @param resultado - Objeto donde se establece la totalidad de registros consolidados y no consolidados
	 * @throws SigemException - Si se produce alg�n error se lanza esta excepcion
	 * @throws RemoteException - Si se produce alg�n error en la conexi�n con los SW se lanzaesta excepci�n
	 */
	public void migrarRegistrosLibroSalida (ResultadoMigracionDto resultado) throws SigemException, RemoteException {
		
		// Obtener los Registros No Consolidados
		FolderSearchCriteria folderQuery = UtilsService.getFolderCriteriaSalida();
		RegistersInfo registersInfo = oServicioOrigen.findFolder(userOrigen, folderQuery, entidadOrigen);
		
		int contadorRegistrosConsolidados = 0;
		int contadorRegistrosNoConsolidados = 0;
		
		List<String> numerosRegistrosConsolidadosSalida = new ArrayList<String>();
		List<String> numerosRegistrosNoConsolidadosSalida = new ArrayList<String>();
		
		if(!UtilsService.isResponeOK(registersInfo.getErrorCode(), registersInfo.getReturnCode())) {
			if(registersInfo.getReturnCode().equalsIgnoreCase(Constantes.RETURN_CODE_ERROR)
				&& registersInfo.getErrorCode().equalsIgnoreCase(String.valueOf(Constantes.COD_ERROR_EMPTY_REGISTERS))) 
			Utils.trace("No existen registros de SALIDA sin consolidar");
			
		} else {
			Utils.trace("ReturnCode: " + registersInfo.getReturnCode());
			Utils.trace("Total Registros de SALIDA: " + registersInfo.getRegisters().length);
			for(int i = 0; i < registersInfo.getRegisters().length; i++) {
				try {
					registerProcess(oServicioOrigen, oServicioDestino, userOrigen, userDestino, entidadOrigen, entidadDestino, registersInfo.getRegisters()[i]);
					numerosRegistrosConsolidadosSalida.add(registersInfo.getRegisters()[i].getNumber());
					contadorRegistrosConsolidados++;
				} catch (Exception e) {
					contadorRegistrosNoConsolidados++;
					numerosRegistrosNoConsolidadosSalida.add(registersInfo.getRegisters()[i].getNumber());
					logger.error("Error en el proceso de consolidaci�n del registro  '" + registersInfo.getRegisters()[i].getNumber()+ "': " + e.getMessage());
					e.printStackTrace();
				}
			}	
		}
		// Se establecen la totalidad de registros consolidados y no consolidados
		resultado.setTotalConsolidadosSalida(contadorRegistrosConsolidados);
		resultado.setTotalNoConsolidadosSalida(contadorRegistrosNoConsolidados);
		resultado.setNumbersRegistersConsolidadosSalida(numerosRegistrosConsolidadosSalida);
		resultado.setNumbersRegistersNoConsolidadosSalida(numerosRegistrosNoConsolidadosSalida);
	}
	
	/*
	 * M�todo que valida que un campo no sea null o cadena vac�a
	 * @param cadena - Dato a validar
	 * @return - (boolean) Devuelve true si el dato es null o cadena vac�a y false si no lo es
	 */
	public boolean isBlank(String cadena){
		if (cadena == null || cadena.equals(""))
			return true;
		return false;
	}	
}
