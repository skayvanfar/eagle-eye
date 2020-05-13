package ir.sk.eagleeye.zuulsvr.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
@Data
@RequiredArgsConstructor
public class UserInfo {
    String organizationId;
    String userId;
}
