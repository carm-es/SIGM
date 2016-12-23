package com.ieci.tecdoc.isicres.desktopweb.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ieci.tecdoc.common.exception.ValidationException;
import com.ieci.tecdoc.isicres.desktopweb.Keys;
import com.ieci.tecdoc.isicres.session.distribution.DistributionSession;
import com.ieci.tecdoc.isicres.usecase.UseCaseConf;
import com.ieci.tecdoc.isicres.usecase.distribution.xml.DistributionFields;
import com.ieci.tecdoc.isicres.usecase.distribution.xml.DistributionSearchFields;
/**
 *
 * Clase de validaci�n de los diferentes cadenas, para evitar ataques tipo SQL Injection
 *
 * @author IECISA
 *
 */
public class SQLValidator {

    private static Logger _logger = Logger.getLogger(SQLValidator.class);

    private static final String FLD = "@FLD";
    private static final String SPACE_AND_SPACE = " AND ";
    private static final String COMILLA_SPACE_AND = "' AND";
    private static final String COMILLA = "'";

    protected Pattern validacion_estandar;

    public static final String SQLVALIDATOR_VALIDACION_SQL_ESTANDAR = "sqlvalidator.validacion.sql.estandar";

    private static SQLValidator _instance = null;

	public SQLValidator() {
		//obtenemos la cadena SQL con la que vamos a validar las sentencias SQL
		validacion_estandar = Pattern.compile(ValidationRBUtil.getInstance(null)
				.getProperty(SQLVALIDATOR_VALIDACION_SQL_ESTANDAR));

		if (_logger.isDebugEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append("validacion_estandar: ").append(validacion_estandar);
			_logger.debug(sb.toString());
		}
	}

	public synchronized static SQLValidator getInstance() {
		if (_instance == null) {
			_instance = new SQLValidator();
		}

		return _instance;
	}

    /**
     * M�todo que valida una consulta por los valores de distribucion
     *
     * Ejemplo de la cadena a validar:
     *
     * DISTRIBUCION DE ENTRADA:
     *
     * STATE=1 AND
     * DIST_DATE>=to_timestamp('02-02-2012 00:00:00','DD-MM-YYYY HH24:MI:SS') AND
     * DIST_DATE<=to_timestamp('02-02-2012 23:59:59','DD-MM-YYYY HH24:MI:SS') AND
     * STATE_DATE>=to_timestamp('02-02-2012 00:00:00','DD-MM-YYYY HH24:MI:SS')AND
     * STATE_DATE<=to_timestamp('02-02-2012 23:59:59','DD-MM-YYYY HH24:MI:SS')AND
     * @ORIG=1073741827
     *
     * DISTRIBUCION DE SALIDA:
     *
     * STATE=2 AND
     * DIST_DATE>=to_timestamp('02-01-2012 00:00:00','DD-MM-YYYY HH24:MI:SS') AND
     * DIST_DATE<=to_timestamp('02-01-2012 23:59:59','DD-MM-YYYY HH24:MI:SS') AND
     * STATE_DATE>=to_timestamp('02-01-2012 00:00:00','DD-MM-YYYY HH24:MI:SS') AND
     * STATE_DATE<=to_timestamp('02-01-2012 23:59:59','DD-MM-YYYY HH24:MI:SS') AND
     * @DEST=1073741827
     *
     * @param distWhere
     * @throws ValidationException
     */
    public void validateDistributionDistWhere(String distWhere)
            throws ValidationException {
        // si la cadena no es vacia la validamos que sea correcta, para evitar
        // ataques SQL
        if (StringUtils.isNotBlank(distWhere)) {
            // Dividimos la cadena por los AND
            String[] campos = distWhere.split(SPACE_AND_SPACE);
            // Recorremos cada string devuelto para comprobar si corresponde con
            // algun campo
            for (int i = 0; i < campos.length; i++) {
                String cadenaConsulta = campos[i];
                // Validamos si la cadena comienza por alguno de los campos
                // validos
                if (!(cadenaConsulta.startsWith("STATE"))
                        && !(cadenaConsulta.startsWith("DIST_DATE"))
                        && !(cadenaConsulta.startsWith("STATE_DATE"))
                        && !(cadenaConsulta.startsWith("@ORIG"))
                        && !(cadenaConsulta.startsWith("@DEST"))) {
                    // La cadena comienza por un caracter diferente al los
                    // campos validos por tanto se genera una excepci�n de
                    // validaci�n
                    throw new ValidationException(
                            ValidationException.ERROR_VALIDATION_DATA);
                } else {
                    // Validamos el resto de la cadena, para verificar que no
                    // concatenan subconsultas
                    validarCadenaSQLInjection(cadenaConsulta,
                            validacion_estandar);
                }
            }
        }
    }

    /**
     * M�todo que valida una consulta por los valores del registro
     *
     * Ejemplo de la cadena a validar:
     *
     * DISTRIBUCION DE ENTRADA:
     *
     * @FLD1='201200100000001' AND
     * @FLD2>=to_timestamp('10-04-2012 00:00:00','DD-MM-YYYY HH24:MI:SS') AND
     * @FLD2<=to_timestamp('10-04-2012 23:59:59','DD-MM-YYYY HH24:MI:SS') AND
     * @FLD7=4887 AND
     * @FLD8=4887 AND
     * @FLD16=3 AND
     * @FLD17='PRUEBAS' AND
     * @FLD9='REMITENTES'
     *
     * DISTRIBUCION DE SALIDA:
     *
     * @FLD1='201200100000001' AND
     * @FLD2>=to_timestamp('12-02-2012 00:00:00','DD-MM-YYYY HH24:MI:SS') AND
     * @FLD2<=to_timestamp('12-02-2012 23:59:59','DD-MM-YYYY HH24:MI:SS') AND
     * @FLD7=4887 AND
     * @FLD8=4887 AND
     * @FLD16=3 AND
     * @FLD17='RESUMEN' AND
     * @FLD9='REMITENTE'
     *
     * @param regWhere
     *            - Consulta por campo de un registro
     * @return String - Retornamos la cadena de consulta para los campos
     *
     * @throws Exception
     */
    public String validateDistributionRegWhere(UseCaseConf useCaseConf,
            Integer typeDist, String regWhere) throws Exception {

        if (_logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("RegWhere distribuci�n: ").append(regWhere);
            _logger.debug(sb.toString());
        }

        StringBuffer cadenaConsultaTratada = new StringBuffer();

        // Validamos si el regWhere es distinto de blanco
        if (StringUtils.isNotBlank(regWhere)) {

            // Comprobamos que la cadena de consulta no contenga \' para no romper
            // la consulta
            if (regWhere.contains("\\'")) {
                throw new ValidationException(
                        ValidationException.ERROR_VALIDATION_DATA);
            }

            // Obtenemos la informaci�n de los campos por los que se puede
            // realizar las b�squedas
            HashMap camposDeBusqueda = obtenerInfoCamposConsulta(useCaseConf,
                    typeDist);


            // Dividimos la cadena por los @FLD
            String[] campos = regWhere.split(FLD);

            for (int i = 0; i < campos.length; i++) {
                // Obtenemos cada cadena de consulta
                String cadenaConsulta = campos[i];

                if(StringUtils.isNotBlank(cadenaConsulta)){
                    // comprobamos si la cadena que contiene a la consulta ya tiene
                    // datos a�adimos el operador AND para seguir concatenando
                    // condiciones
                    if (StringUtils.isNotBlank(cadenaConsultaTratada.toString())) {
                        cadenaConsultaTratada.append(SPACE_AND_SPACE);
                    }

                    // validamos si los campos indicados son correctos y generamos
                    // la consulta
                    validarCamposRegWhereDistribution(cadenaConsultaTratada,
                            camposDeBusqueda, cadenaConsulta);
                }
            }
        }

        if (_logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("Consulta distribuci�n: ").append(
                    cadenaConsultaTratada.toString());
            _logger.debug(sb.toString());
        }

        // retornamos la consulta
        return cadenaConsultaTratada.toString();
    }

     /**
     * M�todo que valida si el criterio de b�squeda es correcto y no ha sido
     * manipulado
     *
     * @param vldQuery
     *            - Cadena con el campo por el que se busca m�s el operador
     * @param vldQueryValue
     *            - Cadena que contiene el valor por el que se realiza la
     *            b�squeda
     *
     * @return Cadena compuesta entre vldQuery y vldQueryValue, tratada para
     *         evitar SQL Injection
     *
     * @throws ValidationException
     */
    public String validateQueryCamposValidados(String vldQuery,
            String vldQueryValue) throws ValidationException {

        // Para validar la consulta, comprobamos si se realiza por alg�n
        // criterio
        if (StringUtils.isNotBlank(vldQueryValue)) {

            if (_logger.isDebugEnabled()) {
                StringBuffer sb = new StringBuffer();
                sb.append("ValidateQueryCamposValidados: vldQuery ")
                        .append(vldQuery).append(" vldQueryValue: ")
                        .append(vldQueryValue);
                _logger.debug(sb.toString());
            }

            // Verificamos que no recibamos la cadena \' para evitar ataques
            if (vldQueryValue.contains("\\'")) {
                throw new ValidationException(
                        ValidationException.ERROR_VALIDATION_DATA);
            }

            // Obtenemos el array de los campos por los que se puede realizar
            // b�squedas
            String stringFieldName[] = Keys.STRING_FIELD_NAME; // {"CODE",
                                                                // "ACRON",
                                                                // "NAME",
                                                                // "TRANSPORT",
                                                                // "MATTER"};
            // Variable que indica si el campo es correcto
            boolean campoCorrecto = false;
            for (int i = 0; i < stringFieldName.length; i++) {
                String campoConsulta = stringFieldName[i];
                // Comprobamos si el criterio de b�squeda comienza por
                // cualquiera de los campos permitidos y de esta forma se
                // verifica que la cadena no ha sido modifica
                if (vldQuery.startsWith(campoConsulta)) {
                    // El campo es correcto
                    campoCorrecto = true;
                }
            }

            // El campo no es correcto por tanto se eleva excepcion de
            // validacion de datos
            if (!campoCorrecto) {
                throw new ValidationException(
                        ValidationException.ERROR_VALIDATION_DATA);
            }

            // Se entrecomilla la consulta para evitar que se llegue codigo
            // malicioso
            vldQueryValue = COMILLA
                    + StringEscapeUtils.escapeSql(vldQueryValue) + COMILLA;

            if (_logger.isDebugEnabled()) {
                StringBuffer sb = new StringBuffer();
                sb.append(
                        "ValidateQueryCamposValidados adaptados a SQL: vldQuery ")
                        .append(vldQuery).append(" vldQueryValue: ")
                        .append(vldQueryValue);
                _logger.debug(sb.toString());
            }
        }
        return vldQueryValue;
    }


    public void validateQueryInteresados(String cadenaConsulta){

    }



    /**
     * M�todo que valida los campos seg�n el ID del campo
     *
     * @param cadenaConsultaTratada
     *            - Cadena con la consulta a ejecutar y validada
     * @param camposDeBusqueda
     *            - HashMap con los datos de los campos de la consulta
     * @param cadenaConsulta
     *            - String con la consulta recibida
     *
     * @throws ValidationException
     */
    private void validarCamposRegWhereDistribution(
            StringBuffer cadenaConsultaTratada, HashMap camposDeBusqueda,
            String cadenaConsulta) throws ValidationException {

        // Obtenemos el id del campo a tratar
        int idCampo = getIDCampoWhereDistribution(cadenaConsulta);

        //Comprobamos que el tipo de idCampo sea uno v�lido
		if ((idCampo == 1) || (idCampo == 2) || (idCampo == 7)
				|| (idCampo == 8) || (idCampo == 9) || (idCampo == 16)
				|| (idCampo == 17)) {
			// Validamos la cadena seg�n el campo a tratar
			cadenaConsultaTratada
					.append(validarCampo(Integer.toString(idCampo),
							camposDeBusqueda, cadenaConsulta));
		} else {
			// Campo indicado no es v�lido
			exceptionCampoNoValido(cadenaConsulta);
		}
    }


    /**
     * M�todo que valida que el Order By que recibimos de la pantalla sea valido, y no haya sufrido modificaciones
     * @param orderBy - Cadena con el order by de la consulta, ejemplo: FLD1 DESC,FLD3 ASC
     * @throws ValidationException
     *
     */
	public void validateOrderQueryRegister(String orderBy)
			throws ValidationException {
		// verificamos la cadena de ordenaci�n
		if (StringUtils.isNotBlank(orderBy)) {
			// Descomponemos la ordenaci�n para verificar cada campo;
			StringTokenizer camposOrdenados = new StringTokenizer(orderBy,
					Keys.COMA);
			while (camposOrdenados.hasMoreTokens()) {
				String campoOrdenado = camposOrdenados.nextToken();

				String[] partesCampoOrdenado = campoOrdenado.split(" ");
				String cadenaAux;
				// Recorremos la cadena que compone la ordenacion de UN solo campo para validar si es correcto todo
				for(int posicionCadena =0; posicionCadena<partesCampoOrdenado.length; posicionCadena++){
					cadenaAux = partesCampoOrdenado[posicionCadena];

					if(posicionCadena == 0){
						// Si la posicion de la cadena es la primera, validamos
						// si es el nombre del campo, lo parseamos a may�sculas
						validarFLDName(cadenaAux.toUpperCase());
					}

					if(posicionCadena == 1){
						// Si la posicion de la cadena es la segunda,
						// validamos porque es el orden, lo parseamos a may�sculas
						validateCadenaOrder(cadenaAux.toUpperCase());
					}

					// mas elementos en la cadena es error
					if(posicionCadena > 1){
						throw new ValidationException(
								ValidationException.ERROR_VALIDATION_DATA);
					}
				}
			}
		}
	}

	/**
	 * M�todo que comprueba que la cadena indicada sea "ASC" o "DESC"
	 *
	 * @param cadenaAux
	 *            - Cadena
	 *
	 * @throws ValidationException
	 */
	private void validateCadenaOrder(String cadenaAux)
			throws ValidationException {
		//validamos si es vacia
		if(StringUtils.isNotBlank(cadenaAux)){
			// Si la cadena no es igual a ASC o DESC la cadena no es valida
			if (!("ASC".equalsIgnoreCase(cadenaAux)) && !("DESC"
					.equalsIgnoreCase(cadenaAux))) {
				throw new ValidationException(
						ValidationException.ERROR_VALIDATION_DATA);
			}
		}
	}

	/**
	 * M�todo que valida que el nombre del campo esta correctamente formado,
	 * "FLD" + ID del campo (num�rico)
	 *
	 * @param nombreCampo
	 *            - Nombre del campo
	 * @throws ValidationException
	 */
	private void validarFLDName(String nombreCampo) throws ValidationException {
		// Comprobamos si el campo comienza por FLD, si es otro
		// tipo de cadena es erronea
		if (nombreCampo.startsWith("FLD")) {
			try {
				// Comprobamos que el id del campo es correcto
				Integer.parseInt(nombreCampo.substring("FLD".length(),
						nombreCampo.length()));
			} catch (NumberFormatException nFE) {
				throw new ValidationException(
						ValidationException.ERROR_VALIDATION_DATA);
			}
		} else {
			throw new ValidationException(
					ValidationException.ERROR_VALIDATION_DATA);
		}
	}


	/**
	 * M�todo que utilizamos para obtener el ID del campo de los campos del
	 * registro por los que se realiza la consulta
	 *
	 * @param cadenaConsulta
	 *            - Cadena de consulta
	 *
	 * @return int - ID del campo
	 */
	private int getIDCampoWhereDistribution(String cadenaConsulta) {
		int idCampo;
        try{
		//intentamos obtener el id del campo, primero probamos con dos digitos
            idCampo = Integer.parseInt(cadenaConsulta.substring(0, 2));
        }catch(NumberFormatException nFE){
		// ha saltado una excepci�n por tanto el id del campo es de un digito
            idCampo = Integer.parseInt(cadenaConsulta.substring(0, 1));
        }
		return idCampo;
	}

    /**
     * M�todo que eleva una excepci�n, ya que no cumple
     * con las validaciones de campo valido
     *
     * @param cadenaConsulta
     *            - Cadena que comprobamos si es nula
     * @throws ValidationException
     */
    private void exceptionCampoNoValido(String cadenaConsulta)
            throws ValidationException {

        if (_logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("El campo indicado no es v�lido: ").append(
                    cadenaConsulta);
            _logger.debug(sb.toString());
        }

        // Al comenzar por un caracter diferente al ID del
        // campo admitidos, lanzamos una excepcion de validaci�n de datos
        throw new ValidationException(
                ValidationException.ERROR_VALIDATION_DATA);
    }

    /**
     * M�todo que obtiene la informaci�n de los campos de consulta del registro
     * en la distribuci�n
     *
     * @param useCaseConf
     *            - Datos de configuraci�n
     * @param typeDist
     *            - Tipo de distribuci�n
     * @return HashMap con los datos de los campos, (fldX, DistributionFields)
     * @throws Exception
     */
    private HashMap obtenerInfoCamposConsulta(UseCaseConf useCaseConf,
            Integer typeDist) throws Exception {
        // Obtenemos el tipo de BBDD
        String dataBaseType = DistributionSession.getDataBaseType(useCaseConf
                .getSessionID());

        // Obtenemos los campos de b�squeda de las distribuciones
        DistributionSearchFields distributionSearchFields = new DistributionSearchFields(
                new Integer(2), typeDist, useCaseConf.getLocale(), dataBaseType);

        // Obtenemos unicamente la informaci�n con los campos
        List fieldSearch = distributionSearchFields.getResult();

        // Generamos un HashMap con la informaci�n de los campos
        HashMap camposDeBusqueda = new HashMap();
        DistributionFields datosCampo = null;
        for (Iterator it = (Iterator) fieldSearch.iterator(); it.hasNext();) {
            datosCampo = (DistributionFields) it.next();
            camposDeBusqueda.put(datosCampo.getFieldName(), datosCampo);
        }

        // retornamos el hashMap con los datos de los campos
        return camposDeBusqueda;
    }

    /**
     * M�todo que valida y trata la cadena de consulta seg�n el campo
     *
     * @param campo
     *            - ID del campo
     * @param camposDeBusqueda
     *            - HashMap con los datos de los campos por los que se puede
     *            consultar
     * @param cadenaConsulta
     *            - Cadena de consulta para el campo pasado como par�metro
     *
     * @return Cadena tratada para evitar ataques SQL Injections
     *
     * @throws ValidationException
     */
    private String validarCampo(String campo, HashMap camposDeBusqueda,
            String cadenaConsulta) throws ValidationException {

        String result = "";

        // Se obtiene la informaci�n del campo de consulta
        DistributionFields datosCampo = (DistributionFields) camposDeBusqueda
                .get(FLD + campo);

        // Obtenemos la cadena de consulta si el id del campo, la cual ser�
        // tratada
        String cadenaConsultaAux = cadenaConsulta.substring(campo.length());
        // Si hay un espacio en blanco entre el operador y el ID del campo lo
        // omitimos
        if (cadenaConsultaAux.startsWith(Keys.SPACE)) {
            cadenaConsultaAux = cadenaConsultaAux.substring(1,
                    cadenaConsultaAux.length());
        }

        // obtenemos los operadores posibles para el campo indicado
        Map operadores = datosCampo.getOperators();

        String key = null;
        String valueOperator = null;
        String whereCampo = null;

        // Si el campo es de tipo fecha, los operadores que tenemos no podemos
        // compararlos por tanto validamos que la cadena no contenga SQL
        // malicioso
        if (datosCampo.getFieldType() == 2) {
            whereCampo = validateCampoTipoFecha(cadenaConsultaAux);
            // componemos la consulta
            result = FLD + campo + whereCampo;
        } else {
            if ((datosCampo.getFieldType() == 0)
                    || (datosCampo.getFieldType() == 1)) {
                result = validateCamposStringOrNumeric(campo, datosCampo,
                        cadenaConsultaAux, operadores);
            }
        }

        // Comprobamos que el result (la cadena con la consulta), no sea vac�a
        // porque eso quiere decir que el operador indicado no es valido
        if (StringUtils.isBlank(result)) {
            throw new ValidationException(
                    ValidationException.ERROR_VALIDATION_DATA);
        }

        return result;
    }

    /**
     * M�todo que valida los campos, si son de tipo String o de tipo num�rico
     *
     * @param campo
     *            - ID del campo
     * @param datosCampo
     *            - Datos del campo
     * @param cadenaConsultaAux
     *            - Cadena de consulta
     * @param operadores
     *            - Operadores validos para el campo
     *
     * @return String con la cadena de consulta verificada y tratada para evitar
     *         SQL Injection
     *
     * @throws ValidationException
     */
    private String validateCamposStringOrNumeric(String campo,
            DistributionFields datosCampo, String cadenaConsultaAux,
            Map operadores) throws ValidationException {

        String result = null;
        String key;
        String valueOperator;
        String whereCampo;
        // Para el resto de campos realizamos las validaciones pertienentes
        // comprobamos a ver que operador se esta utilizando para el campo
        for (Iterator it = (Iterator) operadores.keySet().iterator(); it
                .hasNext();) {
            key = (String) it.next();
            valueOperator = (String) operadores.get(key);

            // comprobamos si la cadena comienza por un operador valido del
            // campo
            if (cadenaConsultaAux.startsWith(valueOperator)) {
                whereCampo = cadenaConsultaAux
                        .substring(valueOperator.length());

                // Si el campo es de tipo String
                if (datosCampo.getFieldType() == 0) {
                    // Obtenemos el criterio de busqueda para los campos de tipo
                    // string
                    String criterioBusqueda = obtenerCriterioDeBusquedaParaString(whereCampo);

                    // Tratamos la cadena para escapar cualquier caracter, y se
                    // omiten las comillas que nos llegan
                    whereCampo = COMILLA
                            + StringEscapeUtils.escapeSql(criterioBusqueda)
                            + COMILLA;
                } else {
                    // Validamos si el campo es num�rico
                    validateCampoTipoNumerico(datosCampo, whereCampo);
                }

                // Componemos la consulta tratada
                result = FLD + campo + Keys.SPACE + valueOperator + Keys.SPACE
                        + whereCampo;
                break;
            }
        }
        return result;
    }

    /**
     * M�todo que valida por una expresi�n regular si los datos para el campo
     * fecha son correctos
     *
     * @param cadenaConsultaAux
     *            - Cadena de Consulta
     *
     * @return String cadena de consulta verificada
     *
     * @throws ValidationException
     */
    private String validateCampoTipoFecha(String cadenaConsultaAux)
            throws ValidationException {
        String whereCampo;
        whereCampo = cadenaConsultaAux;

        // recortamos la cadena quitando el operador AND ya que corresponde a la
        // uni�n de diversos criterios de b�squeda
        if (whereCampo.endsWith(SPACE_AND_SPACE)) {
            whereCampo = whereCampo.substring(0, whereCampo.length()
                    - SPACE_AND_SPACE.length());
        }

        // Validamos el resto de la cadena, para verificar que no
        // concatenan subconsultas mediante una expresi�n regular
        validarCadenaSQLInjection(whereCampo, validacion_estandar);
        return whereCampo;
    }

    /**
     * M�todo que valida un campo de tipo num�rico
     *
     * @param datosCampo
     *            - Datos del campo
     * @param whereCampo
     *            - String con la consulta a realizar
     *
     * @throws ValidationException
     */
    private void validateCampoTipoNumerico(
            DistributionFields datosCampo, String whereCampo)
            throws ValidationException {
        // Si el tipo de campo es num�rico
        // Si el tipo de campo es num�rico
        if (datosCampo.getFieldType() == 1) {
            // recortamos la cadena quitando el operador AND
            if (whereCampo.endsWith(SPACE_AND_SPACE)) {
                whereCampo = whereCampo.substring(0, whereCampo.length()
                        - SPACE_AND_SPACE.length());
            }

            try {
                // intentamos parsearlo a un entero, si falla el
                // proceso, es que el valor no es un numero, por
                // tanto no pasa la validaci�n
                Integer.parseInt(whereCampo);
            } catch (NumberFormatException nFE) {
                throw new ValidationException(
                        ValidationException.ERROR_VALIDATION_DATA);
            }

        }
    }

    /**
     * M�todo que nos retorna el criterio de b�squeda para los campos de tipo
     * string
     *
     * @param whereCampo
     *            - Cadena de la que se obtiene el criterio de b�squeda
     * @return String - Cadena con el criterio de b�squeda
     */
    private String obtenerCriterioDeBusquedaParaString(String whereCampo) {
        String criterioBusqueda;

        // Comprobamos si la cadena comienza con un espacio en blanco
        if (whereCampo.startsWith(Keys.SPACE)) {
            // recortamos la cadena quitando las comillas y el espacio en blanco
            // inicial
            criterioBusqueda = whereCampo.substring(2, whereCampo.length()
                    - Keys.SPACE.length());
        } else {
            // recortamos la cadena quitando las comillas
            criterioBusqueda = whereCampo.substring(1, whereCampo.length()
                    - Keys.SPACE.length());
        }

        // recortamos la cadena quitando el operador AND
        if (criterioBusqueda.endsWith(COMILLA_SPACE_AND)) {
            criterioBusqueda = criterioBusqueda.substring(0,
                    criterioBusqueda.length() - COMILLA_SPACE_AND.length());
        }
        // retornamos la cadena
        return criterioBusqueda;
    }

    /**
     * M�todo que valida si la cadena pasada como par�metro cumple con la
     * expresi�n regular indicada
     *
     * @param cadenaConsulta
     * @throws ValidationException
     */
    private void validarCadenaSQLInjection(String cadenaConsulta,
            Pattern patronValidacion) throws ValidationException {
        // Validamos la cadena
        Matcher matcher = patronValidacion
                .matcher(cadenaConsulta.toLowerCase());
        boolean valido = !matcher.find();
        // si la validaci�n no es correcta, se eleva una excepcion de validaci�n
        // de datos
        if (!valido) {
            throw new ValidationException(
                    ValidationException.ERROR_VALIDATION_DATA);
        }
    }
}
