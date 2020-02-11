package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SkillRepositoryImpl implements SkillRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public SkillRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Skill> addSkill(Skill Skill){
        return supplyAsync(() -> wrap(em -> insertSkill(em, Skill)), executionContext);

    }

    @Override
    public CompletionStage<Stream<Skill>> getSkills(Long personId){
        return supplyAsync(() -> wrap(em -> getSkills(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Skill insertSkill(EntityManager em, Skill skill) {
        em.persist(skill);
        return skill;
    }

    private Stream<Skill> getSkills(EntityManager em, Long personId) {
        List<Skill> skills = em.createQuery("select s from  Skill s where s.personId="+personId+" order by s.updatedAt",
                Skill.class).getResultList();
        return skills.stream();
    }

}
