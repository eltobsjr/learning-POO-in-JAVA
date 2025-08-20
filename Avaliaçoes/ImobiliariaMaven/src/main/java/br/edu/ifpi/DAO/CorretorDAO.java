
package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Corretor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import br.edu.ifpi.JPAUtil;

public class CorretorDAO {
    public java.util.List<Corretor> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Corretor c";
            TypedQuery<Corretor> query = em.createQuery(jpql, Corretor.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public void salvar(Corretor corretor) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(corretor);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public Corretor buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Corretor.class, id);
        } finally {
            em.close();
        }
    }
    public Corretor buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Corretor c WHERE c.cpf = :cpf";
            TypedQuery<Corretor> query = em.createQuery(jpql, Corretor.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
