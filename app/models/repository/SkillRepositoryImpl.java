package models.repository;

import models.Skill;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

//TODO: move the query in constant file
public class SkillRepositoryImpl implements SkillRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public SkillRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Stream<Skill>> addSkill(List<Skill> skills){
        return supplyAsync(() -> wrap(em -> insertSkills(em, skills)), executionContext);

    }

    @Override
    public CompletionStage<Stream<Skill>> mergeSkills(List<Skill> skills) {
        return supplyAsync(() -> wrap(em -> mergeSkills(em, skills)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Skill>> getSkills(Long personId){
        return supplyAsync(() -> wrap(em -> getSkills(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Stream<Skill> insertSkills(EntityManager em, List<Skill> skills) {
        skills.forEach(em::persist);
        return skills.stream();
    }

    private Stream<Skill> mergeSkills(EntityManager em, List<Skill> skills) {
        skills.forEach(em::merge);
        return skills.stream();
    }

    private Stream<Skill> getSkills(EntityManager em, Long personId) {
        List<Skill> skills = em.createQuery("select s from  Skill s where s.personId="+personId+" order by s.updatedAt",
                Skill.class).getResultList();
        return skills.stream();
    }

}
