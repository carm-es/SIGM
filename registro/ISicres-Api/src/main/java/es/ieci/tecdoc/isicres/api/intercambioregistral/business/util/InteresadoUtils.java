package es.ieci.tecdoc.isicres.api.intercambioregistral.business.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.ieci.tecdoc.common.isicres.Keys;

import es.ieci.tecdoc.fwktd.sir.core.types.CanalNotificacionEnum;
import es.ieci.tecdoc.fwktd.sir.core.vo.InteresadoVO;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.InteresadoExReg;
/**
 * Clase de utilidades para el uso de los interesados en el intercambio registral
 * @author IECISA
 *
 */
public class InteresadoUtils {

	private static final String BLANK = " ";

	/**
	 * M�todo que obtiene la informaci�n del interesado en un array
	 *
	 * @param interesado
	 *            - {@link InteresadoVO} Datos del interesado
	 * @return {@link ArrayList} de {@link String} Con los datos del interesado
	 *         descompuestos en cadenas: Nombre y Apellidos, Identificaci�n y
	 *         Direcci�n
	 */
	public static List<String> getDatosInteresadoArray(InteresadoExReg interesado) {
		// Inicializamos el array
		List<String> resultDataInteresado = new ArrayList<String>();

		// Obtenemos la cadena con el nombre y los apellidos del interesado
		StringBuffer nombreInteresado = getNombreApellidosInteresado(interesado);

		// si la cadena esta rellenada, se a�ade al array con los datos del
		// interesado.
		if (StringUtils.isNotBlank(nombreInteresado.toString())) {
			resultDataInteresado.add(nombreInteresado.toString());
		}else{
			//sino posee datos de nombre se intenta asignar la razon social
			if (StringUtils.isNotEmpty(interesado.getRazonSocialInteresado())) {
				resultDataInteresado.add(interesado.getRazonSocialInteresado());
			}
		}

		// Obtenemos la informaci�n de la identificaci�n del interesado
		StringBuffer identificacionInteresado = getIdentificacionInteresado(interesado);
		// si la cadena esta rellenada, se a�ade al array con los datos del
		// interesado.
		if (StringUtils.isNotEmpty(identificacionInteresado.toString())) {
			resultDataInteresado.add(identificacionInteresado.toString());
		}

		// Obtenemos la cadena con la direcci�n del interesado
		StringBuffer direccionInteresado = new StringBuffer();
		direccionInteresado.append(getDireccionInteresado(interesado));

		// Validamos si la cadena con la direcci�n esta rellenada
		if (StringUtils.isNotEmpty(direccionInteresado.toString())) {
			// a�adimos los datos de la direcci�n al array que compone los datos
			// del interesado
			addDireccionInteresado(resultDataInteresado,
					direccionInteresado.toString());
		}

		// retornamos listado con los datos del interesado
		return resultDataInteresado;
	}

	/**
	 * M�todo que obtiene la informaci�n del interesado en un array
	 *
	 * @param interesado
	 *            - {@link InteresadoVO} Datos del interesado
	 * @return {@link ArrayList} de {@link String} Con los datos del interesado
	 *         descompuestos en cadenas: Nombre y Apellidos, Identificaci�n y
	 *         Direcci�n
	 */
	public static List<String> getDatosRepresentanteArray(InteresadoExReg interesado) {
		// Inicializamos el array
		List<String> listaCamposRepresentante = new ArrayList<String>();

		// Obtenemos la cadena con el nombre y los apellidos del interesado
		StringBuffer nombre = getNombreApellidosRepresentante(interesado);

		// si la cadena esta rellenada, se a�ade al array con los datos del
		// interesado.
		if (StringUtils.isNotBlank(nombre.toString())) {
			listaCamposRepresentante.add(nombre.toString());
		}else{
			//sino posee datos de nombre se intenta asignar la razon social
			if(StringUtils.isNotEmpty(interesado.getRazonSocialRepresentante())){
				listaCamposRepresentante.add(interesado.getRazonSocialRepresentante());
			}
		}

		// Obtenemos la informaci�n de la identificaci�n del interesado
		StringBuffer identificacion = getIdentificacionRepresentante(interesado);
		// si la cadena esta rellenada, se a�ade al array con los datos del
		// interesado.
		if (StringUtils.isNotEmpty(identificacion.toString())) {
			listaCamposRepresentante.add(identificacion.toString());
		}

		// Obtenemos la cadena con la direcci�n del interesado
		StringBuffer direccion = new StringBuffer().append(getDireccionRepresentante(interesado));

		// Validamos si la cadena con la direcci�n esta rellenada
		if (StringUtils.isNotEmpty(direccion.toString())) {
			// a�adimos los datos de la direcci�n al array que compone los datos
			// del interesado
			addDireccionInteresado(listaCamposRepresentante,
					direccion.toString());
		}

		// retornamos listado con los datos del interesado
		return listaCamposRepresentante;
	}

	/**
	 * M�todo que obtiene la cadena con la informaci�n referente a la identificaci�n del interesado
	 * @param interesado {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - cadena con la informaci�n referente a la identificaci�n del interesado
	 */
	private static StringBuffer getIdentificacionInteresado(InteresadoVO interesado) {
		StringBuffer identificacionInteresado = new StringBuffer("");
		//Comprobamos si recibimos el tipo de identificaci�n
		if (interesado.getTipoDocumentoIdentificacionInteresado() != null) {
			//si es asi, a�adimos el tipo de identificacion a la cadena resultante
			identificacionInteresado.append(
					interesado.getTipoDocumentoIdentificacionInteresado()
							.getValue());
		}

		//validamos si esta rellenado el documento identificativo del interesado
		if (!StringUtils.isEmpty(interesado
				.getDocumentoIdentificacionInteresado())) {
			//comprobamos si la cadena de identificacion contiene datos para a�adir el separador
			if(StringUtils.isNotEmpty(identificacionInteresado.toString())){
				identificacionInteresado.append(": ");

			}
			//a�adimos el documento identificativo del interesado a la cadena resultante
			identificacionInteresado.append(
					interesado.getDocumentoIdentificacionInteresado());
		}
		return identificacionInteresado;
	}

	/**
	 * M�todo que obtiene la cadena con la informaci�n referente a la identificaci�n del interesado
	 * @param interesado {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - cadena con la informaci�n referente a la identificaci�n del interesado
	 */
	private static StringBuffer getIdentificacionRepresentante(InteresadoVO interesado) {
		StringBuffer identificacion = new StringBuffer("");
		//Comprobamos si recibimos el tipo de identificaci�n
		if (interesado.getTipoDocumentoIdentificacionRepresentante() != null) {
			//si es asi, a�adimos el tipo de identificacion a la cadena resultante
			identificacion.append(
					interesado.getTipoDocumentoIdentificacionRepresentante()
							.getValue());
		}

		//validamos si esta rellenado el documento identificativo del interesado
		if (!StringUtils.isEmpty(interesado
				.getDocumentoIdentificacionRepresentante())) {
			//comprobamos si la cadena de identificacion contiene datos para a�adir el separador
			if(StringUtils.isNotEmpty(identificacion.toString())){
				identificacion.append(": ");

			}
			//a�adimos el documento identificativo del interesado a la cadena resultante
			identificacion.append(
					interesado.getDocumentoIdentificacionRepresentante());
		}
		return identificacion;
	}

	/**
	 * M�todo que obtiene la cadena con el nombre y los apellidos (o en su caso la raz�n social) del interesado pasado como par�metro
	 * @param interesado - {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - cadena con el nombre y los apellidos (o en su caso la raz�n social) del interesado
	 */
	private static StringBuffer getNombreApellidosInteresado(InteresadoVO interesado) {
		StringBuffer nombreInteresado = new StringBuffer();

		if (!StringUtils.isEmpty(interesado.getNombreInteresado())) {
			nombreInteresado.append(interesado.getNombreInteresado()).append(BLANK);
		}

		if (!StringUtils.isEmpty(interesado.getPrimerApellidoInteresado())) {
			nombreInteresado.append(interesado.getPrimerApellidoInteresado()).append(BLANK);
		}

		if (!StringUtils.isEmpty(interesado.getSegundoApellidoInteresado())) {
			nombreInteresado.append(interesado.getSegundoApellidoInteresado());
		}

		return nombreInteresado;
	}

	/**
	 * M�todo que obtiene la cadena con el nombre y los apellidos (o en su caso la raz�n social) del interesado pasado como par�metro
	 * @param interesado - {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - cadena con el nombre y los apellidos (o en su caso la raz�n social) del interesado
	 */
	private static StringBuffer getNombreApellidosRepresentante(InteresadoVO interesado) {
		StringBuffer nombreInteresado = new StringBuffer();

		if (!StringUtils.isEmpty(interesado.getNombreRepresentante())) {
			nombreInteresado.append(interesado.getNombreRepresentante()).append(BLANK);
		}

		if (!StringUtils.isEmpty(interesado.getPrimerApellidoRepresentante())) {
			nombreInteresado.append(interesado.getPrimerApellidoRepresentante()).append(BLANK);
		}

		if (!StringUtils.isEmpty(interesado.getSegundoApellidoRepresentante())) {
			nombreInteresado.append(interesado.getSegundoApellidoRepresentante());
		}

		return nombreInteresado;
	}


	/**
	 * M�todo que a�ade la informaci�n de la direccion al listado pasado como
	 * par�metro con los datos del interesado, se valida si la cadena de la
	 * direccion excede el tama�o maximo posible de almacenamiento en BBDD, si
	 * es asi, se descompone la direcci�n en diferentes subcadenas
	 *
	 * @param resultDataInteresado
	 *            - Listado donde se a�aden la cadena/subcadenas con la
	 *            informaci�n de la direccion
	 * @param direccionInteresado
	 *            - StringBuffer con la direccion del interesado
	 *
	 */
	private static void addDireccionInteresado(List<String> resultDataInteresado,
			String direccionInteresado) {
		// comprobamos la longitud de la direccion
		if (direccionInteresado.length() > Keys.MAX_LENGTH_STRING_DATA_INTERESADO) {
			List<String> partesDireccion = recortarCadenaDireccion(direccionInteresado);
			// recorremos el array con las partes de la direccion, en el
			// array con todos los datos del interesado
			for (Iterator<String> it = partesDireccion.iterator(); it.hasNext();) {
				resultDataInteresado.add((String) it.next());
			}
		} else {
			// a�adimos al array de datos del interesado la cadena con los
			// datos del interesado
			resultDataInteresado.add(direccionInteresado.toString());
		}
	}

	/**
	 * M�todo que recorta la dieccion en diversas partes
	 *
	 * @param direccionInteresado
	 *            - StringBuffer con la direccion del interesado
	 */
	private static List<String> recortarCadenaDireccion(String direccionInteresado) {
		List<String> partesDireccion = new ArrayList<String>();
		// comprobamos si contiene algun espacio en blanco
		if ((direccionInteresado.split(BLANK).length-1)>0) {
			// Tratamiento de la cadena con espacios en blanco
			partesDireccion = recortarCadenaConEspacios(direccionInteresado);
		} else {
			// descomponemos la cadena en diferentes partes ya que no contiene
			// espacios, por tanto la cadena con la direccion se recorta sin
			// mas, en diferentes partes
			partesDireccion = obtenerSubCadenasDireccionInteresado(direccionInteresado
					.toString());
		}

		return partesDireccion;
	}

	/**
	 * M�todo que realiza el tratamiento de la cadena con la direccion del
	 * interesado y esta contiene espacios
	 *
	 * @param direccionInteresado
	 *            - StringBuffer con la direccion del interesado
	 */
	private static List<String> recortarCadenaConEspacios(String direccionInteresado) {
		//inicializamos el array que contendra las diferentes partes de la direcci�n
		List<String> result = new ArrayList<String>();

		// obtenemos la direccion del interesado, divida con
		// espacios y la dividimos en diferentes partes
		StringTokenizer tokens = new StringTokenizer(
				direccionInteresado.toString());

		//Cadena que contiene cada uno de los token, de la direccion del interesado
		String partesDeDireccionSinEspacio = null;

		// cadena auxiliar que contiene cada una de las partes de la direcci�n
		// hasta sumar el tama�o maximo posible que se puede almacenar en BBDD
		String cadenaCompuestaDePartesDireccion = new String();
		// recorremos la cadena separadas por espacios
		while (tokens.hasMoreTokens()) {
			// obtenemos cada parte y realizamos las siguientes
			// validaciones
			partesDeDireccionSinEspacio = tokens.nextToken();
			// si el token que estas tratando es menor al tama�o al maximo que se puede almacenar en BBDD
			if (partesDeDireccionSinEspacio.length() < Keys.MAX_LENGTH_STRING_DATA_INTERESADO) {
				// realizamos el sigiuiente tratamiento: a�adimos el token a una
				// cadena hasta llegar al tama�o deseado
				cadenaCompuestaDePartesDireccion = tratarTokenDireccionMenorALengthSaveInBBDD(
						result, partesDeDireccionSinEspacio,
						cadenaCompuestaDePartesDireccion);
			} else {
				// si el token supera el tama�o descomponemos dicho token en
				// diferentes partes, sin que excedan el tama�o deseado
				cadenaCompuestaDePartesDireccion = tratarTokenDireccionMayorALengthSaveInBBDD(
						result, partesDeDireccionSinEspacio,
						cadenaCompuestaDePartesDireccion);
			}

		}
		//a�adimos al listado el ultimo elemento tratado
		if(StringUtils.isNotEmpty(cadenaCompuestaDePartesDireccion)){
			result.add(cadenaCompuestaDePartesDireccion);
		}



		return result;
	}

	/**
	 * M�todo que realiza el tratamiento necesario sobre la cadena pasada como
	 * parametro cuando excede el tama�o de los datos del interesados
	 *
	 * @param partesDireccion
	 * @param partesDeDireccionSinEspacio - Listado con las partes de la direccion
	 * @param cadenaCompuestaDePartesDireccion - String con la cadena a tratar
	 * @return
	 */
	private static String tratarTokenDireccionMayorALengthSaveInBBDD(
			List<String> partesDireccion, String partesDeDireccionSinEspacio,
			String cadenaCompuestaDePartesDireccion) {
		//validamos si la cadena no es vacia cadenaCompuestaDePartesDireccion
		if (StringUtils.isNotBlank(cadenaCompuestaDePartesDireccion)) {
			//a�adimos ya los datos tratados hasta el mometo al array con las partes de la direccion
			partesDireccion.add(cadenaCompuestaDePartesDireccion);
			//inicializamos de nuevo la cadena
			cadenaCompuestaDePartesDireccion = new String();
		}

		// si la cadena es mayor al tama�o de la celda,
		// descomponemos en varias partes el token
		List<String> auxDireccion = obtenerSubCadenasDireccionInteresado(partesDeDireccionSinEspacio);

//		// Obtenemos el ultimo elemento de la lista para comprobar si la cadena
//		// es menor al tama�o desea, sino lo devolvemos como cadena a seguir
//		// concatenando los datos
//		if ((auxDireccion.get(auxDireccion.size() - 1).length()) < Keys.MAX_LENGTH_STRING_DATA_INTERESADO) {
//			//a�adimos el ultimo elemento de la lista como cadenCompuesta para seguir concatenando datos
//			cadenaCompuestaDePartesDireccion = auxDireccion.get(auxDireccion.size()-1);
//			//eliminamos el �ltimo elemento de la lista
//			auxDireccion.remove(auxDireccion.size()-1);
//		}else{
//			//inicializamos de nuevo la cadena
//			cadenaCompuestaDePartesDireccion = new String();
//		}

		// a�adimos las diferentes partes de la cadena al
		// array de las partes de la direccion
		partesDireccion.addAll(auxDireccion);

		//retornamos la cadena
		return cadenaCompuestaDePartesDireccion;
	}

	private static List<String> obtenerSubCadenasDireccionInteresado(
			String direccionInteresado) {
		List<String> subCadenasDireccion = new ArrayList<String>();

		int init = 0;
		int end;

		if (Keys.MAX_LENGTH_STRING_DATA_INTERESADO > direccionInteresado.length()) {
			end = direccionInteresado.length();
		} else {
			end = Keys.MAX_LENGTH_STRING_DATA_INTERESADO;
		}

		do{
			//a�adimos al listado de partes, la parte obtenida de la direcci�n
			subCadenasDireccion.add(StringUtils.substring(direccionInteresado, init, end));
			init = end;

			//calculamos el fin de la siguiente parte de la cadena
			//si la posicion final de la cadena anterior mas el tama�o posible excenden el tama�o de la Direcci�n
			if((end + Keys.MAX_LENGTH_STRING_DATA_INTERESADO) > direccionInteresado.length()){
				//asignamos como final de la cadena, la maxima longitud de la Direcci�n recibida como param�tro
				end = direccionInteresado.length();
			}else{
				//sino sumamaos a la cadena otro bloque m�s
				end = end + Keys.MAX_LENGTH_STRING_DATA_INTERESADO;
			}
			// el proceso se repite mientras la cadena obtenida tenga un tama�o
			// mayor al valor posible para almacenar
		}while((direccionInteresado.length() - end)>Keys.MAX_LENGTH_STRING_DATA_INTERESADO);

		//a�adimos el �ltimo token al listado de partes
		if(StringUtils.isNotEmpty(StringUtils.substring(direccionInteresado, init, end))){
			subCadenasDireccion.add(StringUtils.substring(direccionInteresado, init, end));
		}

		return subCadenasDireccion;
	}

	private static String tratarTokenDireccionMenorALengthSaveInBBDD(
			List<String> partesDireccion, String partesDeDireccionSinEspacio,
			String cadenaCompuestaDePartesDireccion) {
		// validamos si el token a tratar, mas la cadena ya tratada
		// hasta el momento mas un caractes de espacio, suman mas del
		// tama�o de la celda
		if (cadenaCompuestaDePartesDireccion.length() + BLANK.length()
				+ partesDeDireccionSinEspacio.length() > Keys.MAX_LENGTH_STRING_DATA_INTERESADO) {
			// a�adimos la cadena con los token tratados hasta el momento, al
			// array que contiene las diferentes partes que componen la
			// direcci�n
			partesDireccion.add(cadenaCompuestaDePartesDireccion);
			// inicializamos la cadena de nuevo con el token tratado
			cadenaCompuestaDePartesDireccion = partesDeDireccionSinEspacio;
		} else {
			// a�adimos el token a la cadena auxiliar hasta
			// sumar el tama�o de la celda
			cadenaCompuestaDePartesDireccion = obtenerCadenaCompuestaDireccion(
					partesDeDireccionSinEspacio,
					cadenaCompuestaDePartesDireccion);
		}

		return cadenaCompuestaDePartesDireccion;
	}

	/**
	 * M�todo que obtiene la cadena compuesta de la direcci�n, si la cadena
	 * cadenaCompuestaDePartesDireccion es vacia/nula lo igualamos a la cadena
	 * pasada como parametro, sino concatenmos con las dos cadenas con un
	 * espacio
	 *
	 * @param partesDeDireccionSinEspacio - Parte con la informaci�n de la cadena
	 * @param cadenaCompuestaDePartesDireccion - String con la informaci�n de la direcci�n
	 * @return String - Cadena con la informaci�n de la direccion
	 */
	private static String obtenerCadenaCompuestaDireccion(
			String partesDeDireccionSinEspacio,
			String cadenaCompuestaDePartesDireccion) {
		// Si el String cadenaCompuestaDePartes es distinto de nulo/vacio
		// a�adimos el espacio en blanco para separar cadenas
		if (StringUtils.isNotBlank(cadenaCompuestaDePartesDireccion)) {
			cadenaCompuestaDePartesDireccion = cadenaCompuestaDePartesDireccion
					+ BLANK + partesDeDireccionSinEspacio;
		} else {
			// el String cadenaCompuestaDePartes como es vacio lo igualamos a la
			// cadena pasada como parametro partesDeDireccionSinEspacio
			cadenaCompuestaDePartesDireccion = partesDeDireccionSinEspacio;
		}
		return cadenaCompuestaDePartesDireccion;
	}

	/**
	 * M�todo que obtiene la direcci�n del interesado
	 * @param interesado - {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - Direcci�n del interesado
	 */
	public static String getDireccionInteresado(InteresadoExReg interesado) {
		StringBuffer direccionInteresado = new StringBuffer("");
		//valida el tipo de direccion, si es postal obtiene la direcci�n
		if (interesado.getCanalPreferenteComunicacionInteresado() == CanalNotificacionEnum.DIRECCION_POSTAL) {
			if (StringUtils.isNotBlank(interesado.getNombrePaisInteresado())) {
				direccionInteresado.append(
						interesado.getNombrePaisInteresado());
			}
			if (StringUtils.isNotBlank(interesado.getCodigoProvinciaInteresado())) {
				direccionInteresado.append(" - ").append(
						interesado.getNombreProvinciaInteresado());
			}

			if (StringUtils.isNotBlank(interesado.getNombreMunicipioInteresado())) {
				direccionInteresado.append(" - ").append(
						interesado.getNombreMunicipioInteresado());
			}

			if (StringUtils.isNotBlank(interesado.getCodigoPostalInteresado())) {
				direccionInteresado.append(" - ").append(
						interesado.getCodigoPostalInteresado());
			}
			if (StringUtils.isNotBlank(interesado.getDireccionInteresado())) {
				direccionInteresado.append(" - ").append(
						interesado.getDireccionInteresado());
			}
		} else {
			//sino obtiene el valor de la direccion electr�nica habilitada
			if (StringUtils.isNotBlank(interesado
					.getDireccionElectronicaHabilitadaInteresado())) {
				direccionInteresado.append(" ").append(
						interesado
								.getDireccionElectronicaHabilitadaInteresado());
			}
			if (StringUtils.isNotBlank(interesado
					.getCorreoElectronicoInteresado())){
				direccionInteresado.append(" ").append(
						interesado
								.getCorreoElectronicoInteresado());
			}
			if (StringUtils.isNotBlank(interesado.getTelefonoInteresado())){
				direccionInteresado.append(" ").append(
						interesado
								.getTelefonoInteresado());
			}
		}
		return direccionInteresado.toString();
	}

	/**
	 * M�todo que obtiene la direcci�n del representante
	 * @param interesado - {@link InteresadoVO} Datos del interesado
	 * @return StringBuffer - Direcci�n del interesado
	 */
	public static String getDireccionRepresentante(InteresadoExReg interesado) {
		StringBuffer direccionRepresentante = new StringBuffer("");
		//valida el tipo de direccion, si es postal obtiene la direcci�n
		if (interesado.getCanalPreferenteComunicacionRepresentante() == CanalNotificacionEnum.DIRECCION_POSTAL) {
			if (StringUtils.isNotBlank(interesado.getNombrePaisRepresentante())) {
				direccionRepresentante.append(
						interesado.getNombrePaisRepresentante());
			}
			if (StringUtils.isNotBlank(interesado.getNombreProvinciaRepresentante())) {
				direccionRepresentante.append(" - ").append(
						interesado.getNombreProvinciaRepresentante());
			}

			if (StringUtils.isNotBlank(interesado.getNombreMunicipioRepresentante())) {
				direccionRepresentante.append(" - ").append(
						interesado.getNombreMunicipioRepresentante());
			}

			if (StringUtils.isNotBlank(interesado.getCodigoPostalRepresentante())) {
				direccionRepresentante.append(" - ").append(
						interesado.getCodigoPostalRepresentante());
			}
			if (StringUtils.isNotBlank(interesado.getDireccionRepresentante())) {
				direccionRepresentante.append(" - ").append(
						interesado.getDireccionRepresentante());
			}
		} else {
			//sino obtiene el valor de la direccion electr�nica habilitada
			if (StringUtils.isNotBlank(interesado
					.getDireccionElectronicaHabilitadaRepresentante())) {
				direccionRepresentante.append(" ").append(
						interesado
								.getDireccionElectronicaHabilitadaRepresentante());
			}
			if (StringUtils.isNotBlank(interesado
					.getCorreoElectronicoRepresentante())){
				direccionRepresentante.append(" ").append(
						interesado
								.getCorreoElectronicoRepresentante());
			}
			if (StringUtils.isNotBlank(interesado.getTelefonoRepresentante())){
				direccionRepresentante.append(" ").append(
						interesado
								.getTelefonoRepresentante());
			}
		}
		return direccionRepresentante.toString();
	}
}
