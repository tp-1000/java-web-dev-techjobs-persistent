package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Employer employer;

    @ManyToMany
    @Valid
    private final List<Skill> skills = new ArrayList<>();


    public Job() {
    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkills(List<Skill> skills) {
        this.skills.addAll(skills);
    }
}
