package es.ieci.tecdoc.fwktd.dir3.api.dao;

import java.util.List;

import es.ieci.tecdoc.fwktd.dir3.api.vo.DatosBasicosRelacionUnidOrgOficinaVO;
import es.ieci.tecdoc.fwktd.dir3.core.type.CriterioOficinaEnum;
import es.ieci.tecdoc.fwktd.dir3.core.vo.Criterios;
import es.ieci.tecdoc.fwktd.server.dao.BaseDao;

/**
 * Interfaz de los DAOs de datos b�sicos de la relaci�n entre unid. org�nicas y
 * las oficinas.
 *
 * @author Iecisa
 * @version $Revision$
 *
 */
public interface DatosBasicosRelacionUnidOrgOficinaDao extends
		BaseDao<DatosBasicosRelacionUnidOrgOficinaVO, String> {
	/**
	 * Obtiene el n�mero de relaciones encontradas seg�n los criterios de
	 * b�squeda.
	 *
	 * @param criterios
	 *            Criterios de b�squeda.
	 * @return N�mero de oficinas encontradas.
	 */
	public int countRelacionesUnidOrgOficina(
			Criterios<CriterioOficinaEnum> criterios);

	/**
	 * Realiza una b�squeda de la relaciones se
	 *
	 * @param criterios
	 *            Criterios de b�squeda.
	 * @return Oficinas encontradas.
	 */
	public List<DatosBasicosRelacionUnidOrgOficinaVO> findRelacionesUnidOrgOficina(
			Criterios<CriterioOficinaEnum> criterios);

	/**
	 * Borra la relaci�n de la unid. org�nica y la oficina indicada como
	 * par�metro
	 *
	 * @param codOficina
	 * @param codUnidOrg
	 */
	public void deleteRelacionesUnidOrgOficina(String codOficina,
			String codUnidOrg);
}
