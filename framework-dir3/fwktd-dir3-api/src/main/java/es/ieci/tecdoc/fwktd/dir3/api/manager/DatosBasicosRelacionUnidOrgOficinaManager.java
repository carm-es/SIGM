package es.ieci.tecdoc.fwktd.dir3.api.manager;

import java.util.List;

import es.ieci.tecdoc.fwktd.dir3.api.vo.DatosBasicosRelacionUnidOrgOficinaVO;
import es.ieci.tecdoc.fwktd.dir3.api.vo.relacion.RelacionesUnidOrgOficinaVO;
import es.ieci.tecdoc.fwktd.dir3.core.type.CriterioOficinaEnum;
import es.ieci.tecdoc.fwktd.dir3.core.vo.Criterios;
import es.ieci.tecdoc.fwktd.server.manager.BaseManager;

public interface DatosBasicosRelacionUnidOrgOficinaManager extends
		BaseManager<DatosBasicosRelacionUnidOrgOficinaVO, String> {
	/**
	 * Obtiene el n�mero de relaciones entre las oficinas y las unid. org�nicas
	 * encontradas seg�n los criterios de b�squeda.
	 *
	 * @param criterios
	 *            Criterios de b�squeda.
	 * @return N�mero de relaciones entre las oficinas y las unid. org�nicas
	 *         encontradas.
	 */
	public int countRelaciones(Criterios<CriterioOficinaEnum> criterios);

	/**
	 * Realiza una b�squeda de relaciones entre las oficinas y las unid.
	 * org�nicas.
	 *
	 * @param criterios
	 *            Criterios de b�squeda.
	 * @return Relaciones entre las oficinas y las unid. org�nicas encontradas.
	 */
	public List<DatosBasicosRelacionUnidOrgOficinaVO> findRelaciones(
			Criterios<CriterioOficinaEnum> criterios);

	/**
	 * Metodo que obtiene la informaci�n de relaciones entre las oficinas y las
	 * unid. org�nicas seg�n el id de la oficina
	 *
	 * @param id
	 *            - id de la oficina a buscar
	 * @return {@link DatosBasicosRelacionUnidOrgOficinaVO} o NULO en caso de no
	 *         encontrar nada
	 */
	public DatosBasicosRelacionUnidOrgOficinaVO getRelacionesByOficinaAndUnidad(
			String codOficina, String codUnidad);

	/**
	 * Guarda los datos basicos de las relaciones entre las oficinas y las unid.
	 * org�nicas obtenidas del DCO
	 *
	 * @param oficinasDCO
	 */
	public void saveDatosBasicosRelacionesUnidOrgOficinaVO(
			RelacionesUnidOrgOficinaVO relacionesUnidOrgOficina);

	/**
	 * Actualiza los datos basicos de las relaciones entre las oficinas y las
	 * unid. org�nicas obtenidas del DCO
	 *
	 * @param oficinasDCO
	 */
	public void updateDatosBasicosRelacionesUnidOrgOficinaVO(
			RelacionesUnidOrgOficinaVO relacionesUnidOrgOficina);
}
