/**
 *
 */
package es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager;

import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.CiudadExReg;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.PaisExReg;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.ProvinciaExReg;

/**
 * @author Iecisa
 * @version $Revision$
 *
 */
public interface DireccionesIntercambioRegistralManager {

	/**
	 * Devuelve una provincia a partir del c�digo que se utiliza en el intercambio registral.
	 *
	 * @param codigo
	 * @return Provincia
	 */
	public ProvinciaExReg getProvinciaExRegByCodigo(String codigo);

	/**
	 * Devuelve una ciudad a partir del c�digo que se utiliza en el intercambio registral.
	 *
	 * @param codigoProvincia
	 * @param codigoMunicipio
	 * @return Ciudad
	 */
	public CiudadExReg getCiudadExRegByCodigo(String codigoProvincia, String codigoMunicipio);

	/**
	 * Devuelve una ciudad a partir del c�digo del pa�s que se utiliza en el intercambio registral.
	 *
	 * @param codigo
	 * @return Pais
	 */
	public PaisExReg getPaisExRegByCodigo(String codigo);
}
