package br.com.docket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.docket.model.docket; 





@Repository
public interface docketRepository extends JpaRepository<docket, Integer> {

} 