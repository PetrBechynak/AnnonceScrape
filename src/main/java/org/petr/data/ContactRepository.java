package org.petr.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by petr on 21.10.16.
 */
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    @Modifying
    @Transactional
    @Query("delete from Contact u where u.toBeDeleted = true")
    void deleteMarked();

}
