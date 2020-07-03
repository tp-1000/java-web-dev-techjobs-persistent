package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional //https://dzone.com/articles/how-does-spring-transactional
//away to handle multiple potential manipulations to the same set of data.
//test what happens with coding_event and start a few sessions and edit the same data.
public interface JobRepository extends CrudRepository<Job, Integer> {
}
