package az.baku.testapibyfatima.service;

import java.util.Collection;

/**
 * @author Fatima Sultanova
 **/

public interface CrudService<R,P>{
    P create(R request);
    P update(long id,R request);
    void delete(long id);
    P getById(long id);
    Collection<P> findAll();
}
