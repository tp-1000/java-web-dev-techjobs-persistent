package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam(required = false) int employerId, @RequestParam(required = false) List<Integer> skills) {

        Optional<Employer> employer = employerRepository.findById(employerId);
        if(employer.isEmpty() | skills == null) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("errormsg", "Select a Skill");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        List<Skill> skillList = (List<Skill>) skillRepository.findAllById(skills);

        newJob.setEmployer(employer.get());
        newJob.addSkills(skillList);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        jobRepository.save(newJob);

        return "redirect:view/" + newJob.getId();
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()) {
            model.addAttribute("title", "Job Not Found");
            //TODO fix bad return-- breaks if id doesn't match a job
            return "view";
        }
        model.addAttribute("job", job.get());
        return "view";
    }


}
