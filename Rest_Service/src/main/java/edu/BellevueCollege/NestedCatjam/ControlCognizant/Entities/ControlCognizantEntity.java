package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ControlCognizantEntity {
    Long getId();
    static <T extends ControlCognizantEntity> Optional<T> findEntity(long id, List<T> entities) {
        return entities.stream().filter(entity -> entity.getId().equals(id)).findFirst();
    }

    static <T extends ControlCognizantEntity> Optional<T> deleteEntityById(long id, List<T> entities) {
        var iterator = entities.iterator();
        while(iterator.hasNext()) {
            var Post = iterator.next();
            if(Post.getId() == id) {
                iterator.remove();
                return Optional.of(Post);
            }
        }
        return Optional.empty();
    }
}
