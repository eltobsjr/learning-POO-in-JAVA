package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Imovel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import br.edu.ifpi.JPAUtil;

public class ImovelDAO {
    public void salvar(Imovel imovel) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(imovel);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public Imovel buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Imovel.class, id);
        } finally {
            em.close();
        }
    }
    public List<Imovel> listarDisponiveis() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM Imovel i WHERE i.disponivel = true";
            TypedQuery<Imovel> query = em.createQuery(jpql, Imovel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
