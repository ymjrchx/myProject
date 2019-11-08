package demo.iptest.mapper;

import com.example.demo.iptest.entity.ConsultIPRegion;
import com.example.demo.iptest.entity.ConsultMessage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenxin
 * @date 2019/4/8 12:43
 */
@Repository
public interface ConsultIPRegionMapper {
    @Transactional(readOnly = true)
    List<ConsultIPRegion> getList(String date);
    void updateList(ConsultMessage message);
}
