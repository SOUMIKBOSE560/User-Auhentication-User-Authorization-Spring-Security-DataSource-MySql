package net.springsecurity.SpringSecurityRoleBasedAuth.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class APIResponse <T> {
    private boolean success;
    private HttpStatus status;
    private T message;
}
