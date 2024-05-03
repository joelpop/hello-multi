package org.joelpop.hellomulti.jpaclient.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface LongKeyRepository<T> extends Repository<T, Long>, JpaSpecificationExecutor<T> {
}
