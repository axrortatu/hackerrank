package model.dto.receive;

import common.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReceiveDto implements BaseReceive {
    private Long chatId;
    private String username;
    private String fullName;
    private String phoneNumber;

}
