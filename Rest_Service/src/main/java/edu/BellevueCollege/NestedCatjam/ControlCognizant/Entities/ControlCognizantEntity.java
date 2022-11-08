package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ControlCognizantEntity {
    UUID getId();
    void setId(UUID id);
    static <T extends ControlCognizantEntity> Optional<T> findEntity(UUID id, List<T> entities) {
        return entities.stream().filter(entity -> entity.getId().equals(id)).findFirst();
    }

    static <T extends ControlCognizantEntity> T save(T entity, List<T> entities) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }

        entities.add(entity);
        return entity;
    }

    static <T extends ControlCognizantEntity> Optional<T> deleteEntityById(UUID id, List<T> entities) {
        var iterator = entities.iterator();
        while(iterator.hasNext()) {
            var Post = iterator.next();
            if(Post.getId().equals(id)) {
                iterator.remove();
                return Optional.of(Post);
            }
        }
        return Optional.empty();
    }
}
