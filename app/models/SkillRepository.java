package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(SkillRepositoryImpl.class)
public interface SkillRepository {
    CompletionStage<Skill> addSkill(Skill Skill);
    CompletionStage<Stream<Skill>> getSkills(Long personId);
}
