package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.ServerWithoutAutostop;

public interface ServerWithoutAutostopService {
	List<ServerWithoutAutostop> getAllServerWithoutAutostops();
	
	void saveServerWithoutAutostop(ServerWithoutAutostop serverWithoutAutostop);
	
	ServerWithoutAutostop getServerWithoutAutostopById(long id);
	
	void deleteServerWithoutAutostopById(long id);
	
	Page<ServerWithoutAutostop> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	List<ServerWithoutAutostop> getDataByTeam(String teamName);
	
	List<ServerWithoutAutostop> findAll();
	
    List<ServerWithoutAutostop> findAllSortByTeam();
}
