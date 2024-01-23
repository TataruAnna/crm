package com.sales.crm.repository;

import com.sales.crm.model.QuotationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationItemRepository extends JpaRepository<QuotationItem, Long> {
}
