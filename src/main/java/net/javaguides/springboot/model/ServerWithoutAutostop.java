package net.javaguides.springboot.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.*;


@Entity
@Table(name = "server_without_autostop")
public class ServerWithoutAutostop {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "aws_instance")
	private String awsInstance;
	
	
	@Column(name = "requested_person")
	@NotBlank(message = "requestedPerson By cannot be empty.")
	private String requestedPerson;
	
	@Column(name = "jira_ticket")
	@NotEmpty(message = "JiraTicket By cannot be empty.")
	private String jiraTicket;
		
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	@Column(name = "dt_autostop_tag_removed")
	@NotEmpty(message = "AutostopTagRemovedDate By cannot be empty.")
	private Date autostopTagRemovedDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	@Column(name = "dt_autostop_turned_back")
	@NotEmpty(message = "AutostopTagRemovedDate By cannot be empty.")
	private Date autostopTurnedBackDate;	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_reimplemented")
	@NotEmpty(message = "AutostopTagRemovedDate By cannot be empty.")
	private Date reimplementedDate;	
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "team_name")
	@NotEmpty(message = "AutostopTagRemovedDate By cannot be empty.")
	private String teamName;
	
	@Column(name = "update_on")
	private Date updateOn;
	
	@Column(name = "delete_flag")
	private String deleteFlag="FALSE";	
		
	//@Column(name = "create_date")
	//private Date createDate;	  
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

	
	//   ====== form level date fields ==== //

	//private String autostopTagRemovedDateForm;

	//private String autostopTurnedBackDateForm;	

	//private String reimplementedDateForm;	
	//  ================================== //       
	
	public long getId() {
		return id;
	}

	public void setId(long request_id) {
		this.id = request_id;
	}

	public String getAwsInstance() {
		return awsInstance;
	}

	public void setAwsInstance(String awsInstance) {
		this.awsInstance = awsInstance;
	}

	public String getRequestedPerson() {
		return requestedPerson;
	}

	public void setRequestedPerson(String requestedPerson) {
		this.requestedPerson = requestedPerson;
	}

	public String getJiraTicket() {
		return jiraTicket;
	}

	public void setJiraTicket(String jiraTicket) {
		this.jiraTicket = jiraTicket;
	}

	public Date getAutostopTagRemovedDate() {
	//	this.autostopTagRemovedDateForm = simpleDateFormat.format(autostopTagRemovedDate);
		return autostopTagRemovedDate;
	}

	public void setAutostopTagRemovedDate(Date autostopTagRemovedDate) {
  //      try {
   //     	this.autostopTagRemovedDate = simpleDateFormat.parse(autostopTagRemovedDate);
     //   } catch (ParseException e) {
     //       e.printStackTrace();
     //   }
     this.autostopTagRemovedDate = autostopTagRemovedDate;

	}

	public Date getAutostopTurnedBackDate() {
		//this.autostopTurnedBackDateForm = simpleDateFormat.format(autostopTurnedBackDate);
		return autostopTurnedBackDate;
	}

	public void setAutostopTurnedBackDate(Date autostopTurnedBackDate) {
        //try {
        //	this.autostopTagRemovedDate = simpleDateFormat.parse(autostopTurnedBackDate);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}		
		this.autostopTurnedBackDate = autostopTurnedBackDate;
	}

	public Date getReimplementedDate() {
		//this.reimplementedDateForm = simpleDateFormat.format(reimplementedDate);
		return reimplementedDate;
	}

	public void setReimplementedDate(Date reimplementedDate) {
	
        //try {
       // 	this.reimplementedDate = simpleDateFormat.parse(reimplementedDate);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}		
		
		this.reimplementedDate = reimplementedDate;
	}

	
	/*
	
	public String getAutostopTagRemovedDateForm() {
		return autostopTagRemovedDateForm;
	}

	public void setAutostopTagRemovedDateForm(String autostopTagRemovedDateForm) {
		this.autostopTagRemovedDateForm = autostopTagRemovedDateForm;
		
        try {
        	this.autostopTagRemovedDate = simpleDateFormat.parse(autostopTagRemovedDateForm);
        } catch (ParseException e) {
            e.printStackTrace();
        }		
		
	}

	public String getAutostopTurnedBackDateForm() {
		return autostopTurnedBackDateForm;
	}

	public void setAutostopTurnedBackDateForm(String autostopTurnedBackDateForm) {
		this.autostopTurnedBackDateForm = autostopTurnedBackDateForm;
		
        try {
        	this.autostopTurnedBackDate = simpleDateFormat.parse(autostopTurnedBackDateForm);
        } catch (ParseException e) {
            e.printStackTrace();
        }		
	}

	public String getReimplementedDateForm() {
		return reimplementedDateForm;
	}

	public void setReimplementedDateForm(String reimplementedDateForm) {
		this.reimplementedDateForm = reimplementedDateForm;
		
        try {
        	this.reimplementedDate = simpleDateFormat.parse(reimplementedDateForm);
        } catch (ParseException e) {
            e.printStackTrace();
        }		
	}
	*/
	
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
 
	/*

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	*/
	

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
