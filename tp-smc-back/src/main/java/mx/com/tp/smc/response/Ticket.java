package mx.com.tp.smc.response;

public class Ticket {
	
	private String refNum;
	private String tenant;
	private String requestedBy;
	private String tfe;
	private String customer;
	private String category;
	private String status;
	private String detail;
	private String priority;
	private String assignee;
	private String group;
	private String isActive;
	private String affectedService;
	private String urgency;
	private String impact;
	private String majorInc;
	private String problem;
	private String symptom;
	private String resolutionCode;
	private String resolutionMethod;
	private String change;
	private String provider;
	private String externalTicket;
	private String radioBase;
	private String afectation;
	private String customerOrgName;
	private String customerOrgNum;
	private String affectedResource;
	private String summary;
	private String timeSpent;
	private String description;
	private String createdVia;
	private String bussinessUnit;
	private String activities;
	private String initialDiagnose;
	private String finalDiagnose;
	private String solution;
	private String solutionTime;
	private String openTime;
	private String lastModTime;
	private String resolveTime;
	private String closeTime;
	private String customerOrganizationPersistentId;
	private String contactName;
	private String contactPhone;
	private String contactMail;
	private String contactLocation;
	private String contactAddress1;
	private String contactAddress2;
	private String contactAddress3;
	private String contactLocationState;
	private String contactLocationZip;
	private String agentName;
	private String agentMail;
	private String agentPhone;
	
	public Ticket() {}

	public Ticket(String refNum) {
		this.refNum = refNum;
	}

	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getTfe() {
		return tfe;
	}
	public void setTfe(String tfe) {
		this.tfe = tfe;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getAffectedService() {
		return affectedService;
	}
	public void setAffectedService(String affectedService) {
		this.affectedService = affectedService;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getMajorInc() {
		return majorInc;
	}
	public void setMajorInc(String majorInc) {
		this.majorInc = majorInc;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getResolutionCode() {
		return resolutionCode;
	}
	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public String getResolutionMethod() {
		return resolutionMethod;
	}
	public void setResolutionMethod(String resolutionMethod) {
		this.resolutionMethod = resolutionMethod;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getExternalTicket() {
		return externalTicket;
	}
	public void setExternalTicket(String externalTicket) {
		this.externalTicket = externalTicket;
	}
	public String getRadioBase() {
		return radioBase;
	}
	public void setRadioBase(String radioBase) {
		this.radioBase = radioBase;
	}
	public String getAfectation() {
		return afectation;
	}
	public void setAfectation(String afectation) {
		this.afectation = afectation;
	}
	public String getCustomerOrgName() {
		return customerOrgName;
	}
	public void setCustomerOrgName(String customerOrgName) {
		this.customerOrgName = customerOrgName;
	}
	public String getCustomerOrgNum() {
		return customerOrgNum;
	}
	public void setCustomerOrgNum(String customerOrgNum) {
		this.customerOrgNum = customerOrgNum;
	}
	public String getAffectedResource() {
		return affectedResource;
	}
	public void setAffectedResource(String affectedResource) {
		this.affectedResource = affectedResource;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedVia() {
		return createdVia;
	}
	public void setCreatedVia(String createdVia) {
		this.createdVia = createdVia;
	}
	public String getBussinessUnit() {
		return bussinessUnit;
	}
	public void setBussinessUnit(String bussinessUnit) {
		this.bussinessUnit = bussinessUnit;
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getInitialDiagnose() {
		return initialDiagnose;
	}
	public void setInitialDiagnose(String initialDiagnose) {
		this.initialDiagnose = initialDiagnose;
	}
	public String getFinalDiagnose() {
		return finalDiagnose;
	}
	public void setFinalDiagnose(String finalDiagnose) {
		this.finalDiagnose = finalDiagnose;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getSolutionTime() {
		return solutionTime;
	}
	public void setSolutionTime(String solutionTime) {
		this.solutionTime = solutionTime;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	public String getResolveTime() {
		return resolveTime;
	}
	public void setResolveTime(String resolveTime) {
		this.resolveTime = resolveTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCustomerOrganizationPersistentId() {
		return customerOrganizationPersistentId;
	}
	public void setCustomerOrganizationPersistentId(String customerOrganizationPersistentId) {
		this.customerOrganizationPersistentId = customerOrganizationPersistentId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactMail() {
		return contactMail;
	}
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	public String getContactLocation() {
		return contactLocation;
	}
	public void setContactLocation(String contactLocation) {
		this.contactLocation = contactLocation;
	}
	public String getContactAddress1() {
		return contactAddress1;
	}
	public void setContactAddress1(String contactAddress1) {
		this.contactAddress1 = contactAddress1;
	}
	public String getContactAddress2() {
		return contactAddress2;
	}
	public void setContactAddress2(String contactAddress2) {
		this.contactAddress2 = contactAddress2;
	}
	public String getContactAddress3() {
		return contactAddress3;
	}
	public void setContactAddress3(String contactAddress3) {
		this.contactAddress3 = contactAddress3;
	}
	public String getContactLocationState() {
		return contactLocationState;
	}
	public void setContactLocationState(String contactLocationState) {
		this.contactLocationState = contactLocationState;
	}
	public String getContactLocationZip() {
		return contactLocationZip;
	}
	public void setContactLocationZip(String contactLocationZip) {
		this.contactLocationZip = contactLocationZip;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentMail() {
		return agentMail;
	}
	public void setAgentMail(String agentMail) {
		this.agentMail = agentMail;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
}