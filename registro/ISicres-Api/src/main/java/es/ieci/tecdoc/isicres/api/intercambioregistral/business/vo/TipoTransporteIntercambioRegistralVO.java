package es.ieci.tecdoc.isicres.api.intercambioregistral.business.vo;

/**
 * Mapea la descripci�n del tipo de tranporte en sicres con el c�digo del tipo
 * de transporte en SIR.
 *
 * @author Iecisa
 * @version $Revision$
 *
 */
public class TipoTransporteIntercambioRegistralVO {

	/**
	 * Identificador interno
	 */
	private Long id;

	/**
	 * Identificador del tipo de transporte en sicres
	 */
	private Integer idTipoTransporte;

	/**
	 * C�digo SIR del tipo de transporte
	 */
	private String codigoSIR;

	/**
	 * Descripci�n en SICRES del tipo de transporte
	 */
	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoSIR() {
		return codigoSIR;
	}

	public void setCodigoSIR(String codigoSIR) {
		this.codigoSIR = codigoSIR;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdTipoTransporte() {
		return idTipoTransporte;
	}

	public void setIdTipoTransporte(Integer idTipoTransporte) {
		this.idTipoTransporte = idTipoTransporte;
	}



}
