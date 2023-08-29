package net.javaguides.springboot.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.springboot.model.ServerWithoutAutostop;
import net.javaguides.springboot.service.ServerWithoutAutostopService;

@Controller
public class ServerWithoutAutostopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerWithoutAutostopController.class);
	
    //@Autowired
   // Validator validator;
	
	//@Validated
	
	@Autowired
	private ServerWithoutAutostopService serverWithoutAutostopService;
	
	@InitBinder
	public final void initBinder(final WebDataBinder binder, final Locale locale) {
	    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", locale);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	}	
	
	// display list of serverWithoutAutostops
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "updateOn", "desc", model);		
	}
	
	@GetMapping("/showNewServerWithoutAutostopForm")
	public String showNewServerWithoutAutostopForm(Model model) {
		// create model attribute to bind form data
		ServerWithoutAutostop serverWithoutAutostop = new ServerWithoutAutostop();
		model.addAttribute("serverWithoutAutostop", serverWithoutAutostop);
		return "new_server_without_autostop";
	}


	@PostMapping("/saveServerWithoutAutostop")
	public String saveServerWithoutAutostop(@Valid @ModelAttribute("serverWithoutAutostop") ServerWithoutAutostop serverWithoutAutostop,
			                                BindingResult result,
			                                Model model,
			                                final RedirectAttributes redirectAttributes) {
		// save serverWithoutAutostop to database
		logger.debug("saveServerWithoutAutostop() : {}", serverWithoutAutostop);
		
		if (result.hasErrors()) {
			System.out.println("======>  #####  Form has errors.");
			return "new_server_without_autostop";
		}
        // Add message to flash scope
        redirectAttributes.addFlashAttribute("alert", "success");
		save(serverWithoutAutostop);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get serverWithoutAutostop from the service
		ServerWithoutAutostop serverWithoutAutostop = serverWithoutAutostopService.getServerWithoutAutostopById(id);
		
		// set serverWithoutAutostop as a model attribute to pre-populate the form
		model.addAttribute("serverWithoutAutostop", serverWithoutAutostop);
		return "update_server_without_autostop";
	}
	
	
	
	@GetMapping("/deleteServerWithoutAutostop/{id}")
	public String deleteServerWithoutAutostop(@PathVariable (value = "id") long id) {
		//soft delete.
		//ServerWithoutAutostop serverWithoutAutostop = this.serverWithoutAutostopService.getServerWithoutAutostopById(id);
		//serverWithoutAutostop.setDeleteFlag("TRUE");
         //save(serverWithoutAutostop);
		
		// call delete serverWithoutAutostop method 
		this.serverWithoutAutostopService.deleteServerWithoutAutostopById(id);
		return "redirect:/";
	}
	
	@GetMapping("/user") 
	public String getDataByTeam(@RequestParam(name = "teamName") String teamName) {
		List<ServerWithoutAutostop> getDataByTeam = serverWithoutAutostopService.getDataByTeam(teamName);
		
		return "redirect:/";
	}	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<ServerWithoutAutostop> page = serverWithoutAutostopService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<ServerWithoutAutostop> listServerWithoutAutostop = page.getContent();
		
		model.addAttribute("currentPage", pageNo);

		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		List<ServerWithoutAutostop> listSoftDelete = new ArrayList<ServerWithoutAutostop>();
        for (ServerWithoutAutostop server : listServerWithoutAutostop) {
        	 if (server.getDeleteFlag().equals("FALSE")) {
        		 listSoftDelete.add(server);
        		 
        	 }
        }
        
        int totalElements = listSoftDelete.size();
        int totalPages= (int) Math.ceil(totalElements);
        		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		

//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("totalItems", totalElements);		
		
		model.addAttribute("listServerWithoutAutostop", listServerWithoutAutostop);
		return "index";
	}
	
	
	private void save(ServerWithoutAutostop serverWithoutAutostop) {
		Instant now = Instant.now();
		Date currentDateTime = Date.from(now);
		
		serverWithoutAutostop.setUpdateOn(currentDateTime);
		
		serverWithoutAutostopService.saveServerWithoutAutostop(serverWithoutAutostop);		
	}

	
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=instance_without_autostoptag_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<ServerWithoutAutostop> listServerWithoutAutostop = serverWithoutAutostopService.findAllSortByTeam();
        
        for (ServerWithoutAutostop server : listServerWithoutAutostop) {
        	System.out.println("====>  Server Name:" + server.getAwsInstance() + " Date1=" + server.getAutostopTagRemovedDate());
        }
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Request By", "Team", "awsInstance", "Jira Ticket", 
        		              "Dt Tag Removed", "Dt TurnedBack", "Dt Reimplemented", "Comments"};
        
        String[] nameMapping = {"requestedPerson", "teamName", "awsInstance", "jiraTicket", 
        		                "autostopTagRemovedDate", "autostopTurnedBackDate", "reimplementedDate", "comments"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (ServerWithoutAutostop server : listServerWithoutAutostop) {
            csvWriter.write(server, nameMapping);
        }
         
        csvWriter.close();  
    }
    
    
    /*
     * Testing the application
     */
	
	@GetMapping("/email")
	public String getEmail(Model model) {
		// create model attribute to bind form data
		//ServerWithoutAutostop serverWithoutAutostop = new ServerWithoutAutostop();
		//model.addAttribute("serverWithoutAutostop", serverWithoutAutostop);
		return "email";
	}    
}
