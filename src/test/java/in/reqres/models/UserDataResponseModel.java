package in.reqres.models;

import lombok.Data;

@Data
public class UserDataResponseModel {
    int id;
    String email, first_name, last_name, avatar;
}
