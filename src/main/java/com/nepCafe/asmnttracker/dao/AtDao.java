package com.nepCafe.asmnttracker.dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.dto.ATResponseDTO;
import com.nepCafe.asmnttracker.repo.AtRepo;
import org.springframework.util.ObjectUtils;

@Component
public class AtDao {

    private EntityManager em;
    private AtRepo repo;

    @Autowired
    public AtDao(AtRepo repo, EntityManager em) {
        this.repo = repo;
        this.em = em;
    }

    public ATResponseDTO saveAndFlush(ATResponseDTO entity) {
        return repo.saveAndFlush(entity);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public ATResponseDTO save(ATResponseDTO entity) {

        entity.setLastUpdatedOn(Timestamp.from(Instant.now()));
        return repo.save(entity);
    }

    public ATResponseDTO find(Long id) {
        Optional<ATResponseDTO> optional = repo.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Page<ATResponseDTO> findAll(SearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(searchCriteria.getPage(), searchCriteria.getSize());

        String SELECT = "SELECT a FROM ASSIGNMENT a where 1=1";
        String COUNT = "SELECT count(a.atIt) FROM ASSIGNMENT a where 1=1";

        ATBody body = searchCriteria.getAtBody();


        if (body != null) {

            String status = body.getStatus();
            if (status != null && !status.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String st : status.split(",")) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append("'" + st + "'");
                }
                SELECT += " and a.status in ( " + sb.toString() + " )";
                COUNT += " and a.status in ( " + sb.toString() + " )";

            }

            String assignmentType = body.getAssignmentType();
            if (assignmentType != null && !assignmentType.isEmpty()) {
                SELECT += " and a.assignmentType = '" + assignmentType + "'";
                COUNT += " and a.assignmentType = '" + assignmentType + "'";

            }

            String postedBy = body.getPostedBy() != null
                    ? String.valueOf(body.getPostedBy().getId())
                    : null;

            if (postedBy != null && !postedBy.isEmpty()) {
                SELECT += " and a.postedBy.id = " + postedBy;
                COUNT += " and a.postedBy.id = " + postedBy;
            }

            String assignedTo = body.getAssignee() != null
                    ? String.valueOf(body.getAssignee().getId())
                    : null;

            if (assignedTo != null && !assignedTo.isEmpty()) {
                SELECT += " and a.assignee.id = " + assignedTo;
                COUNT += " and a.assignee.id = " + assignedTo;
            }

            String techResource = body.getTechResource() != null
                    ? String.valueOf(body.getTechResource().getId())
                    : null;

            if (techResource != null && !techResource.isEmpty()) {
                SELECT += " and a.techResource.id = " + techResource;
                COUNT += " and a.techResource.id = " + techResource;
            }
        }

        String searchTerm = searchCriteria.getSearchTerm();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            SELECT += " and (a.atIt LIKE '%"
                    + searchTerm + "%' OR a.desc LIKE '%"
                    + searchTerm + "%' OR a.title LIKE '%"
                    + searchTerm + "%')";

            COUNT += " and (a.atIt LIKE '%"
                    + searchTerm + "%' OR a.desc LIKE '%"
                    + searchTerm + "%' OR a.title LIKE '%"
                    + searchTerm + "%')";
        }
        String expiresFrom = searchCriteria.getExpiresFrom();
        if (expiresFrom != null && !expiresFrom.isEmpty()) {
            SELECT += " and a.expiresOn >= TO_DATE('" + expiresFrom + "', 'yyyy-mm-dd')";
            COUNT += " and a.expiresOn >= TO_DATE('" + expiresFrom + "', 'yyyy-mm-dd')";
        }
        String expiresTo = searchCriteria.getExpiresTo();
        if (expiresTo != null && !expiresTo.isEmpty()) {
            SELECT += " and a.expiresOn <= TO_DATE('" + expiresTo + "', 'yyyy-mm-dd')";
            COUNT += " and a.expiresOn <= TO_DATE('" + expiresTo + "', 'yyyy-mm-dd')";
        }


        String orderBy = searchCriteria.getOrderBy();
        if (ObjectUtils.isEmpty(orderBy)) {
            orderBy = "atIt";
        }
        int sortBy = searchCriteria.getSortBy();
        if (sortBy == 0) {
            SELECT += " order by a." + orderBy + " asc";
        } else {
            SELECT += " order by a." + orderBy + " desc";
        }

        Query q = em.createQuery(SELECT, ATResponseDTO.class);
        q.setFirstResult(searchCriteria.getPage() * searchCriteria.getSize());
        q.setMaxResults(searchCriteria.getSize());

        Query countQuery = em.createQuery(COUNT, Long.class);
        long total = (long) countQuery.getSingleResult();
        return new PageImpl<>((List<ATResponseDTO>) (q.getResultList()), pageable, total);

    }

    public List<Object[]> getCount(String status, String postedBy, String assignedTo, String searchTerm,
                                   String expiresFrom, String expiresTo) {

        String sql = "SELECT  a.status, COUNT(a.atIt) FROM ASSIGNMENT a where 1=1";

        if (status != null && !status.isEmpty()) {
            sql += " and a.status = '" + status + "'";
        }
        if (postedBy != null && !postedBy.isEmpty()) {
            sql += " and a.postedBy.id = " + postedBy;
        }
        if (assignedTo != null && !assignedTo.isEmpty()) {
            sql += " and a.assignee.id = " + assignedTo;
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql += " and (a.atIt LIKE '%" + searchTerm + "%' OR a.title LIKE '%" + searchTerm + "%')";
        }
        if (expiresFrom != null && !expiresFrom.isEmpty()) {
            sql += " and a.expiresOn >= TO_DATE('" + expiresFrom + "', 'yyyy-mm-dd') ";
        }
        if (expiresTo != null && !expiresTo.isEmpty()) {
            sql += " and a.expiresOn <= TO_DATE('" + expiresTo + "', 'yyyy-mm-dd') ";
        }
        sql += " GROUP BY  a.status";

        TypedQuery<Object[]> q = em.createQuery(sql, Object[].class);

        return q.getResultList();
    }
}
