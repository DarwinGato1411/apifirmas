package com.ec.alpha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ec.alpha.entidad.Solicitud;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query("SELECT u FROM Solicitud u WHERE  u.idSolicitud =:idSolicitud AND u.idUsuario.idUsuario=:idUsuario ")
	Solicitud buscarPorIdSolicitud(@Param("idSolicitud") Integer idSolicitud, @Param("idUsuario") Integer idUsuario);

}
