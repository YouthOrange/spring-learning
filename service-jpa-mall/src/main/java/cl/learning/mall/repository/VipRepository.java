package cl.learning.mall.repository;

import cl.learning.mall.domain.Vip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : 常亮
 * @Date : 10:08 2019-01-10
 * @Description :
 */
@Repository
public interface VipRepository extends JpaRepository<Vip, Long> {
    Vip findAllById(Long id);

    Vip findAllByName(String name);

    Vip findAllByIdOrName(Long id, String name);

    Vip findByVipId(Long vipId);
}
