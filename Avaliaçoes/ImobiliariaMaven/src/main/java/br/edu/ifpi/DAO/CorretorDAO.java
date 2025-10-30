
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
            throw new RuntimeException("Erro ao salvar corretor: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void atualizar(Corretor corretor) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(corretor);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao atualizar corretor: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Corretor corretor = em.find(Corretor.class, id);
            if (corretor != null) {
                em.remove(corretor);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao remover corretor: " + e.getMessage(), e);
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
