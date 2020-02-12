package models.repository;

import com.google.inject.ImplementedBy;
import models.Skill;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(SkillRepositoryImpl.class)
public interface SkillRepository {
    CompletionStage<Skill> addSkill(Skill Skill);
    CompletionStage<Stream<Skill>> getSkills(Long personId);
}
