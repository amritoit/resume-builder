package dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import org.json.JSONObject;

import java.util.List;


public class ResumeDTO {

    private Person basic;
    private Contact contact;
    private List<Skill> skills;
    private List<Education> education;
    private List<Work> works;
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

    public List<Work> getWorks() { return works; }

    public void setWorks(List<Work> works) { this.works = works; }

    public List<Education> getEducation() { return education; }

    public void setEducation(List<Education> education) { this.education = education; }

    public List<AdditionalInformation> getAdditionalInfo() { return additionalInfo; }

    public void setAdditionalInfo(List<AdditionalInformation> additionalInfo) { this.additionalInfo = additionalInfo; }
}
