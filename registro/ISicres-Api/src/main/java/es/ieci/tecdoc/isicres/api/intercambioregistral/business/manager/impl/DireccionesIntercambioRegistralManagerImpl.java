/**
 *
 */
package es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager.impl;

import es.ieci.tecdoc.isicres.api.intercambioregistral.business.dao.DireccionesIntercambioRegistralDAO;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager.DireccionesIntercambioRegistralManager;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.CiudadExReg;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.PaisExReg;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.ProvinciaExReg;

/**
 * @author Iecisa
 * @version $Revision$
 *
 */
public class DireccionesIntercambioRegistralManagerImpl implements DireccionesIntercambioRegistralManager {



	protected DireccionesIntercambioRegistralDAO direccionesIntercambioRegistralDAO;

	/**
	 * C�digo del pa�s. Atributo seg�n cat�logo del INE
	 */
	protected final static String CODIGO_PAIS_SPAIN = "724";

	/**
	 * Nombre del pa�s Espa�a
	 */
	private static final String NOMBRE_PAIS_SPAIN = "Espa�a";

	/**
	 * {@inheritDoc}
	 *
	 * @see es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager.DireccionesIntercambioRegistralManager#getProvinciaExRegByCodigo(java.lang.String)
	 */
	public ProvinciaExReg getProvinciaExRegByCodigo(String codigo) {

		return getDireccionesIntercambioRegistralDAO().getProvinciaExRegByCodigo(codigo);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager.DireccionesIntercambioRegistralManager#getCiudadExRegByCodigo(java.lang.String)
	 */
	public CiudadExReg getCiudadExRegByCodigo(String codigoProvincia,
			String codigoMunicipio) {
		return getDireccionesIntercambioRegistralDAO().getCiudadExRegByCodigo(codigoProvincia,
				codigoMunicipio);
	}

	/**
	 *
	 * En esta implementaci�n, el c�digo del pa�s es est�tico. No hay BBDD donde
	 * se almacena la relaci�n entre los c�digos y los nombres.
	 *
	 * {@inheritDoc}
	 *
	 * @see es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager.DireccionesIntercambioRegistralManager#getPaisExRegByCodigo(java.lang.String)
	 */
	public PaisExReg getPaisExRegByCodigo(String codigo) {
		PaisExReg pais = null;

		pais = getDireccionesIntercambioRegistralDAO().getPaisExRegByCodigo(codigo);

		return pais;
	}

	public DireccionesIntercambioRegistralDAO getDireccionesIntercambioRegistralDAO() {
		return direccionesIntercambioRegistralDAO;
	}

	public void setDireccionesIntercambioRegistralDAO(DireccionesIntercambioRegistralDAO direccionesExRegDAO) {
		this.direccionesIntercambioRegistralDAO = direccionesExRegDAO;
	}

}
