package dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import org.json.JSONObject;

import java.util.List;


public class ResumeDao {

    private Person basic;
    private Contact contact;
    private List<SocialLink> social;
    private List<Skill> skill;
    private List<Education> education;

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

    public List<SocialLink> getSocial() {
        return social;
    }

    public void setSocial(List<SocialLink> social) {
        this.social = social;
    }

    public List<Skill> getSkill() { return skill; }

    public void setSkill(List<Skill> skill) { this.skill = skill; }

    public List<Education> getEducation() { return education; }

    public void setEducation(List<Education> education) { this.education = education; }
}
