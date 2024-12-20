/**
 * 
 */
package com.nelsont4.projectjpa.dao.impl;

import java.util.List;

import com.nelsont4.projectjpa.dao.DisqueraDAO;
import com.nelsont4.projectjpa.entity.Disquera;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 * @author NelsonT4 Calse que Implementación de la interfaz para la tabla de la
 *         disquera
 */
public class DisqueraDAOImpl implements DisqueraDAO {

	/**
	 * es estatica no cambia valor y final por que es una constante. Referenciamos al
	 * nombre de la unidad de persistencia
	 */
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("persistenceNelsonT4");

	@Override
	public void guardar(Disquera disquera) {
		// TODO Auto-generated method stub
		EntityManager em= ENTITY_MANAGER_FACTORY.createEntityManager();
		
		/**
		 * Simepre que vayamos a realizar un transacción debemos usar el getTransaction
		 */
		EntityTransaction et =em.getTransaction();
		et.begin();//Inicia la transacción para poder modifcar la BD
		
		try {
			/**
			 * Se coloca un TRY en caso que exista un problema a la hora de insertar los datos
			 */
			em.persist(disquera);
			et.commit();//Se inserta el dato en Tabla solo se puede quitar a menos que se elimine
		} catch (Exception e) {
			if (et !=null) {
				//si la transacción sigue activa revierta los cambios para no almacenar elemetos residuales
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			//Siempre se cierra la base de datos cuando se finaliza
			em.close();
		}
		
	}

	@Override
	public void actualizar(Disquera disquera) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		/**
		 * Simepre que vayamos a realizar un transacción debemos usar el getTransaction
		 */
		EntityTransaction et =em.getTransaction();
		et.begin();//Inicia la transacción para poder modifcar la BD
		
		try {
			/**
			 * Se coloca un TRY en caso que exista un problema a la hora de insertar los datos
			 */
			em.merge(disquera);
			et.commit();//Se inserta el dato en Tabla solo se puede quitar a menos que se elimine
		} catch (Exception e) {
			if (et !=null) {
				//si la transacción sigue activa revierta los cambios para no almacenar elemetos residuales
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			//Siempre se cierra la base de datos cuando se finaliza
			em.close();
		}

	}

	@Override
	public void Eliminar(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		Disquera disqueraConultar=  em.find(Disquera.class, id);
		EntityTransaction et =em.getTransaction();
		et.begin();//Inicia la transacción para poder modifcar la BD
		
		try {
			/**
			 * Se coloca un TRY en caso que exista un problema a la hora de insertar los datos
			 */
			em.remove(disqueraConultar);
			et.commit();//Se inserta el dato en Tabla solo se puede quitar a menos que se elimine
		} catch (Exception e) {
			if (et !=null) {
				//si la transacción sigue activa revierta los cambios para no almacenar elemetos residuales
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			//Siempre se cierra la base de datos cuando se finaliza
			em.close();
		}

	}

	@Override
	public List<Disquera> consultar() {
		EntityManager em= ENTITY_MANAGER_FACTORY.createEntityManager();
		/**
		 * Se coloca el nombre de la entidad  no el de la tabla y se omite el selec *
		 * PAra consultar todos los datos de la tabla 
		 */
		TypedQuery<Disquera> queryDisquera= (TypedQuery<Disquera>) em.createQuery("FROM Disquera ORDER BY descripcion");
		return queryDisquera.getResultList();
	}

	@Override
	public Disquera consultarById(Long id) {
		
		EntityManager em= ENTITY_MANAGER_FACTORY.createEntityManager();
		
		Disquera disqueraConsultado =em.find(Disquera.class, id);
		if(disqueraConsultado == null) {
			throw new EntityNotFoundException("La disquera con id " + id + " no se encontro");
		}
		return disqueraConsultado;
	}

}
