package ieci.tecdoc.sgm.migration.service;

import ieci.tecdoc.sgm.migration.exception.MigrationException;

	/**
	 * Interfaz para el servicio de migracion de registros.
	 * 
	 */
	public interface ServiceMigration {

		/**
		 * Realiza la migraci�n de registros telem�ticos en todas las entidades definidas.
		 * @throws MigrationException si ocurre alg�n error.
		 */
		public void migracion() throws MigrationException;

	}
