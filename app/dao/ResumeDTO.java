package dao;

import models.*;

import java.util.List;


public class ResumeDTO {

    private Person basic;
    private Contact contact;
    private List<Skill> skills;
    private List<Education> education;
    private List<Workinfo> workinfos;
    private List<AdditionalInformation> additionalInfo;

    public Person getBasic() {
        return basic;
    }

    public void setBasic(Person basic) {
        this.basic = basic;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Skill> getSkills() { return skills; }

    public void setSkills(List<Skill> skills) { this.skills = skills; }

    public List<Workinfo> getWorkinfos() { return workinfos; }

    public void setWorkinfos(List<Workinfo> workinfos) { this.workinfos = workinfos; }

    public List<Education> getEducation() { return education; }

    public void setEducation(List<Education> education) { this.education = education; }

    public List<AdditionalInformation> getAdditionalInfo() { return additionalInfo; }

    public void setAdditionalInfo(List<AdditionalInformation> additionalInfo) { this.additionalInfo = additionalInfo; }
}
