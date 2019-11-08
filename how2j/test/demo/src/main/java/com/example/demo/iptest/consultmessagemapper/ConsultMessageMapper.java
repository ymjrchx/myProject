package com.example.demo.iptest.consultmessagemapper;


import com.example.demo.iptest.entity.ConsultIPRegion;
import com.example.demo.iptest.entity.ConsultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultMessageMapper {
    List<ConsultMessage> listByPrimaryKey(List<ConsultIPRegion> lists);
}