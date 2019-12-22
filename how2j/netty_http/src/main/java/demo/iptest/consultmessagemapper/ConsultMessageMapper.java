package demo.iptest.consultmessagemapper;


import demo.iptest.entity.ConsultIPRegion;
import demo.iptest.entity.ConsultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultMessageMapper {
    List<ConsultMessage> listByPrimaryKey(List<ConsultIPRegion> lists);
}