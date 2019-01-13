package org.xbee.project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xbee.project.model.IOLineState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class IOLineStateRepositoryImpl implements IOLineStateRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public IOLineState get(Integer id) {
        return em.find(IOLineState.class, id);
    }

    @Override
    public List<IOLineState> getByDeviceId(Integer deviceId) {
        return em.createNamedQuery(IOLineState.GET_BY_DEVICE_ID, IOLineState.class).setParameter("deviceId", deviceId).getResultList();
    }

    @Override
    @Transactional
    public IOLineState save(IOLineState state) {
        if (state.isNew()){
            em.persist(state);
            return state;
        }
        else return em.merge(state);
    }

    @Override
    public Integer getLineId(String atCommand) {
        return (Integer) em.createNativeQuery("SELECT id FROM lines WHERE atCommand = :at_command").setParameter("at_command", atCommand).getSingleResult();
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return em.createNamedQuery(IOLineState.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

}