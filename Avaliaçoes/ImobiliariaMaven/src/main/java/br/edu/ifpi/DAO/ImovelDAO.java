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
            throw new RuntimeException("Erro ao salvar imóvel: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void atualizar(Imovel imovel) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(imovel);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao atualizar imóvel: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Imovel imovel = em.find(Imovel.class, id);
            if (imovel != null) {
                em.remove(imovel);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao remover imóvel: " + e.getMessage(), e);
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
    
    public List<Imovel> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM Imovel i";
            TypedQuery<Imovel> query = em.createQuery(jpql, Imovel.class);
            return query.getResultList();
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
    
    public List<Imovel> buscarPorCidade(String cidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM Imovel i WHERE LOWER(i.endereco.cidade) LIKE LOWER(:cidade)";
            TypedQuery<Imovel> query = em.createQuery(jpql, Imovel.class);
            query.setParameter("cidade", "%" + cidade + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
