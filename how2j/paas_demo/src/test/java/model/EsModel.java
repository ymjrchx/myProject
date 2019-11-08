package model;

import lombok.Data;
import net.dgg.framework.tac.elasticsearch.annotation.DggEsDocument;
import net.dgg.framework.tac.elasticsearch.annotation.DggEsIdentify;

@Data
@DggEsDocument(indexName = "index_001", type = "type_001")
public class EsModel {

    @DggEsIdentify
    private String id;

    private String name;

    private String phone;
}