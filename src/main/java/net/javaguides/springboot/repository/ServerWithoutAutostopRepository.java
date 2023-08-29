package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import net.javaguides.springboot.model.ServerWithoutAutostop;

@Repository
public interface ServerWithoutAutostopRepository extends JpaRepository<ServerWithoutAutostop, Long>{

}
 