package com.ethnocopia.payload.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppAccountInfoRequest{
    @Schema(
            name = "User App Name"
    )
    private String username;

    @Schema(
            name = "User App message"
    )
    private String message;
}
