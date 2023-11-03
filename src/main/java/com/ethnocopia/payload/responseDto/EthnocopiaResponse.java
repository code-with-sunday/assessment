package com.ethnocopia.payload.responseDto;
import com.ethnocopia.payload.requestDto.AppAccountInfoRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EthnocopiaResponse {
    private String responseCode;
    private String responseMessage;
    private AppAccountInfoRequest appAccountInfoRequest;

}