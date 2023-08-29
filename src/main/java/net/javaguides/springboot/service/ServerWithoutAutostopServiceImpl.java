package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.ServerWithoutAutostop;
import net.javaguides.springboot.repository.ServerWithoutAutostopRepository;

@Service
public class ServerWithoutAutostopServiceImpl implements ServerWithoutAutostopService {

	@Autowired
	private  ServerWithoutAutostopRepository serverWithoutAutostopRepository;
	
 
	@Override
	public List<ServerWithoutAutostop> getAllServerWithoutAutostops() {
		return serverWithoutAutostopRepository.findAll();
	}

	@Override
	public void saveServerWithoutAutostop(ServerWithoutAutostop serverWithoutAutostop) {
		 
		this.serverWithoutAutostopRepository.save(serverWithoutAutostop);
	}

	@Override
	public ServerWithoutAutostop getServerWithoutAutostopById(long id) {
		Optional<ServerWithoutAutostop> optional = serverWithoutAutostopRepository.findById(id);
		ServerWithoutAutostop serverWithoutAutostop = null;
		if (optional.isPresent()) {
			serverWithoutAutostop = optional.get();
		} else {
			throw new RuntimeException(" ServerWithoutAutostop not found for id :: " + id);
		}
		return serverWithoutAutostop;
	}

	@Override
	public void deleteServerWithoutAutostopById(long id) {
	 
		this.serverWithoutAutostopRepository.deleteById(id);
	}

	@Override
	public Page<ServerWithoutAutostop> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.serverWithoutAutostopRepository.findAll(pageable);
	}

	@Override
	public List<ServerWithoutAutostop> getDataByTeam(String teamName) {
		 
		List<ServerWithoutAutostop> listServerWithoutAutostop = serverWithoutAutostopRepository.findAll();
		List<ServerWithoutAutostop> listTeam = new ArrayList<ServerWithoutAutostop>();
		for (ServerWithoutAutostop server : listServerWithoutAutostop) {
		
			if (server.getTeamName().equals(teamName)) {
				listTeam.add(server);
			}
				
		}
		
		return listTeam;
	}
	
	@Override
	public List<ServerWithoutAutostop> findAll() {
		return serverWithoutAutostopRepository.findAll();
	}
	
	@Override
	public List<ServerWithoutAutostop> findAllSortByTeam() {
		return serverWithoutAutostopRepository.findAll(Sort.by("teamName").ascending());
	}
	
	

}
