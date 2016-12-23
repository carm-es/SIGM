package es.ieci.tecdoc.fwktd.dir3.api.helper;

import es.ieci.tecdoc.fwktd.dir3.api.vo.DatosBasicosRelacionUnidOrgOficinaVO;
import es.ieci.tecdoc.fwktd.dir3.core.vo.DatosBasicosRelacionUnidOrgOficina;

/**
 * Clase de utilidad para la transformaci�n de objetos con la informaci�n de
 * las relaciones entre las oficionas y las unid. org�nicas.
 *
 * @author Iecisa
 * @version $Revision$
 *
 */
public class RelacionesUnidOrgOficinaHelper {

	public static DatosBasicosRelacionUnidOrgOficina getDatosBasicosRelacionUnidOrgOficina(
			DatosBasicosRelacionUnidOrgOficinaVO datosBasicosRelacionUnidOrgOficinaVO) {

		DatosBasicosRelacionUnidOrgOficina datosBasicosRelacionUnidOrgOficina = null;

		if (datosBasicosRelacionUnidOrgOficinaVO != null) {

			datosBasicosRelacionUnidOrgOficina = new DatosBasicosRelacionUnidOrgOficina();

			datosBasicosRelacionUnidOrgOficina
					.setCodigoOficina(datosBasicosRelacionUnidOrgOficinaVO
							.getCodigoOficina());
			datosBasicosRelacionUnidOrgOficina
					.setCodigoUnidadOrganica(datosBasicosRelacionUnidOrgOficinaVO
							.getCodigoUnidadOrganica());
			datosBasicosRelacionUnidOrgOficina
					.setDenominacionOficina(datosBasicosRelacionUnidOrgOficinaVO
							.getDenominacionOficina());
			datosBasicosRelacionUnidOrgOficina
					.setDenominacionUnidadOrganica(datosBasicosRelacionUnidOrgOficinaVO
							.getDenominacionUnidadOrganica());
			datosBasicosRelacionUnidOrgOficina
					.setEstadoRelacion(datosBasicosRelacionUnidOrgOficinaVO
							.getEstadoRelacion());

		}

		return datosBasicosRelacionUnidOrgOficina;
	}
}
