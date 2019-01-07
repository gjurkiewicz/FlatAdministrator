package com.jurkiewicz.grzegorz.FlatApp2.repository;

import com.jurkiewicz.grzegorz.FlatApp2.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository <Invoice, Long> {
}
