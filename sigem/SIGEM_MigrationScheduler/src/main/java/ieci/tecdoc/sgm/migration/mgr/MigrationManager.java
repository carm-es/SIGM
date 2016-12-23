package ieci.tecdoc.sgm.migration.mgr;

import ieci.tecdoc.sgm.core.exception.SigemException;

/**
 * Interfaz para los managers de migraci�n de registros.
 *
 */
public interface MigrationManager {

	/**
	 * Realiza el proceso de migraci�n de registros sobre una entidad. 
	 * @throws SigemException si ocurre alg�n error.
	 */
	public abstract void migrationRegisterEntity()
			throws SigemException;

}