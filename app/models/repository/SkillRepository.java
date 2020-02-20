package models.repository;

import com.google.inject.ImplementedBy;
import models.Skill;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(SkillRepositoryImpl.class)
public interface SkillRepository {
    CompletionStage<Stream<Skill>> addSkill(List<Skill> Skill);
    CompletionStage<Stream<Skill>> mergeSkills(List<Skill> Skill);
    CompletionStage<Stream<Skill>> getSkills(Long personId);
}
