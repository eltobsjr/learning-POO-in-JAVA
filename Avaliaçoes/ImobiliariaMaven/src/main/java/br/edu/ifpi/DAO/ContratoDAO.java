package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Contrato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import jakarta.persistence.TypedQuery;
import br.edu.ifpi.JPAUtil;

public class ContratoDAO {
    public void salvar(Contrato contrato) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(contrato);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao salvar contrato: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Contrato contrato = em.find(Contrato.class, id);
            if (contrato != null) {
                em.remove(contrato);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao remover contrato: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public Contrato buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Contrato> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Contrato c";
            TypedQuery<Contrato> query = em.createQuery(jpql, Contrato.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
