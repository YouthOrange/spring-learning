package cl.learning.mall.repository;

import cl.learning.mall.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 10:09 2019-01-10
 * @Description :
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByVipIdOrderByCreateAt(Long vipId);
}
