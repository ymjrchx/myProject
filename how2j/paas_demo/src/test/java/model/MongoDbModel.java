package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "aeskey_log")
public class MongoDbModel {

    @Id
    String _id;
    String aes_keyid;
    String app_name;
    Date create_time;
    String create_user;
}
