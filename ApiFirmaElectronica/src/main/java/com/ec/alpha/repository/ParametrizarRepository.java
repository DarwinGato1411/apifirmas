package com.ec.alpha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ec.alpha.entidad.Parametrizar;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParametrizarRepository extends JpaRepository<Parametrizar, Integer> {

	@Query("SELECT u FROM Parametrizar u WHERE  u.parActivo =:parActivo")
	Parametrizar buscarActivo(@Param("parActivo") Boolean idSolicitud);

}
