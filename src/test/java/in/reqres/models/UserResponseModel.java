package in.reqres.models;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseModel {
    int page, per_page, total, total_pages;
    List<UserDataResponseModel> data;
    UserSupportResponseModel support;
}
