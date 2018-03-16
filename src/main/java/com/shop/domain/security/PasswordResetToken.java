package com.shop.domain.security;


import com.shop.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PasswordResetToken {
    private static final int EXPIRATION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate expiryDate;

    public PasswordResetToken(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);

    }

    private LocalDate calculateExpiryDate(final int expiryTimeInDay) {
      return LocalDate.now().plus(Period.ofDays(expiryTimeInDay));
    }

    public void uptadeToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

}
