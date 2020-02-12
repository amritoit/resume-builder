package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="work_infos")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition="BIGINT(20) NOT NULL")
    private Long id;

    @JsonIgnore
    @Column(name = "person_id", columnDefinition="BIGINT(20) NOT NULL")
    private Long personId;

    @Column(name = "company_name", columnDefinition="VARCHAR(255) NOT NULL")
    private String companyName;

    @Column(name = "company_city", columnDefinition="VARCHAR(50) NOT NULL")
    private String companyCity;

    @Column(name = "company_state", columnDefinition="VARCHAR(50) NOT NULL")
    private String companyState;

    @JsonFormat(timezone = "UTC", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @JsonFormat(timezone = "UTC", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "role", columnDefinition="VARCHAR(50) NOT NULL")
    private String role;

    @Column(name = "currently_working_here", columnDefinition = "default 0")
    private Integer currentlyWorkingHere = 0;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="work_info_id", referencedColumnName="id")
    private List<WorkRoleDescription> workRoleDescriptionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getCurrentlyWorkingHere() {
        return currentlyWorkingHere;
    }

    public void setCurrentlyWorkingHere(Integer currentlyWorkingHere) {
        this.currentlyWorkingHere = currentlyWorkingHere;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<WorkRoleDescription> getWorkRoleDescriptionList() {
        return workRoleDescriptionList;
    }

    public void setWorkRoleDescriptionList(List<WorkRoleDescription> workRoleDescriptionList) {
        this.workRoleDescriptionList = workRoleDescriptionList;
    }
}
