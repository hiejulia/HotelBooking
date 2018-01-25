package com.project.hb.billing.domain.repository;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {

    //long Count;
    /**
     *
     * @param id
     * @return
     */
    boolean contains(T id);

    /**
     *
     * @param id
     * @return
     */
    TE get(T id);

    /**
     *
     * @return
     */
    Collection<TE> getAll();
}
