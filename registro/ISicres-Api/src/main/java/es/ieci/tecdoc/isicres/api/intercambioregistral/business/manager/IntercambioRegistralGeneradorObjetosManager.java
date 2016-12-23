package es.ieci.tecdoc.isicres.api.intercambioregistral.business.manager;

import es.ieci.tecdoc.fwktd.sir.core.vo.AsientoRegistralFormVO;
import es.ieci.tecdoc.fwktd.sir.core.vo.AsientoRegistralVO;
import es.ieci.tecdoc.fwktd.sir.core.vo.InteresadoVO;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.InfoAsientoRegistralVO;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.IntercambioRegistralSalidaVO;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.InteresadoExReg;
import es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo.UnidadTramitacionIntercambioRegistralVO;

/**
 * Manager para construir objetos necesitados por el SIR
 */
public interface IntercambioRegistralGeneradorObjetosManager {

	/**
	 * Compone el <code>AsientoRegistralFormVO</code> necesario para enviar un intercambio registral
	 * La unidad de tramitaci�n destino se obtiene a partir del destino del registro
	 * No deber�a ser necesario utilizarlo
	 * @deprecated
	 * @param intercambioSalidaVO
	 * @param entidad
	 * @return
	 */
	public AsientoRegistralFormVO getAsientoRegistralIntercambioRegistralVO(IntercambioRegistralSalidaVO intercambioSalidaVO, String entidad);

	/**
	 * Compone el <code>AsientoRegistralFormVO</code> necesario para enviar un intercambio registral
	 * @param intercambioSalidaVO
	 * @param unidadTramitacionDestino
	 * @return
	 */
	public AsientoRegistralFormVO getAsientoRegistralIntercambioRegistralVO(
			IntercambioRegistralSalidaVO intercambioSalidaVO, UnidadTramitacionIntercambioRegistralVO unidadTramitacionDestino);

	/**
	 *
	 * Devuelve un objeto <code>InteresadoExReg</code> (que contiene informaci�n
	 * del pa�s, provincia y municipio) a partir de un objeto
	 * <code>InteresadoVO</code>.
	 *
	 * @param interesado
	 * @param interesadoExReg
	 */
	public InteresadoExReg getInteresadoExReg(InteresadoVO interesado);

	/**
	 * Devuelve un objeto <code>InfoAsientoRegistralVO</code> a partir de un objeto <code>AsientoRegistralVO</code>
	 * @param asientoRegistralVO
	 * @return
	 */
	public InfoAsientoRegistralVO getInfoAsientoRegistralVO(AsientoRegistralVO asientoRegistralVO);

}
