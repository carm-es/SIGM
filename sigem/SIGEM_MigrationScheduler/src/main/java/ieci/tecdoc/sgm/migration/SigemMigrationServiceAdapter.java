package ieci.tecdoc.sgm.migration;

import ieci.tecdoc.sgm.migration.config.Config;
import ieci.tecdoc.sgm.migration.exception.MigrationException;
import ieci.tecdoc.sgm.migration.mgr.MigrationManager;
import ieci.tecdoc.sgm.migration.mgr.impl.MigrationManagerImpl;
import ieci.tecdoc.sgm.migration.service.ServiceMigration;


import org.apache.log4j.Logger;


/**
 * Implementaci�n del servicio de la migraci�n de registros de SIGEM.
 *
 */
public class SigemMigrationServiceAdapter implements ServiceMigration {

	/**
	 * Logger de la clase.
	 */
	private static final Logger logger = Logger.getLogger(SigemMigrationServiceAdapter.class);
	
	/**
	 * Constructor.
	 */
	public SigemMigrationServiceAdapter () {
		super();
	}
	
	/**
	 * Realiza la migraci�n de registros telem�ticos en todas las entidades definidas.
	 * @throws MigracionRegisterException si ocurre alg�n error.
	 */
	public void migracion() throws MigrationException {
		
		try {
			
			new MigrationManagerImpl(Config.getInstance().getOrigenEntity(), 
					Config.getInstance().getDestinoEntity())
				.migrationRegisterEntity();
			
		} catch (MigrationException e){
			logger.error("Error inesperado en la migraci�n de registros", e);
			throw e;
		} catch (Throwable e){
			logger.error("Error inesperado en la migraci�n de registros", e);
			throw new MigrationException(e);
		}
	}
}
