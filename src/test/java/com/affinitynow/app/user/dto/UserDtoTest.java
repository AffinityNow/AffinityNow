//package com.affinitynow.app.user.dto;
//
//import com.affinitynow.app.model.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class UserDtoTest {
//    @Test
//    void shouldConvertDtoToEntity() {
//        final User src = new User().setId(123L).setPseudo("Test");
//        final UserDto expected = new UserDto().setId(123L).setPseudo("Test");
//        final UserDto actual = UserDto.fromEntity(src);
//        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
//    }
//}
