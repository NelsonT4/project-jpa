/**
 * 
 */
package com.nelsont4.projectjpa.dao;

import java.util.List;

import com.nelsont4.projectjpa.entity.Disquera;

/**
 * @author NelsonT4
 */
public interface DisqueraDAO {
	
	void guardar(Disquera disquera);
	
	void actualizar(Disquera disquera);
	
	void Eliminar(Long id);
	
	List<Disquera> consultar();
	
	Disquera consultarById(Long id);

}
