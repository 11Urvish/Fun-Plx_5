package com.fundplex.mainrestapi.Model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotEmpty
    @Size(min = 4, max = 10, message = "firstname shuold be minimum 4 and maximum 10 character long")
    public String firstName;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Lastname shuold be minimum 4 and maximum 10 character long")
    public String lastName;

    public String userType;

    @NotNull
    public String status;

    @NotEmpty
    @Size(min = 10, max = 10, message = "Mobile number is not valid")
    public String mobile;

    @Email(message = "Email address is not valid!!")
    public String email;

    @NotEmpty
    // @Size(min = 6, message = "Password atleast 6 characters long and must contain
    // a digit, upper case alphabet and lowercase alphabet")
    // @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}", message =
    // "Password atleast 6 characters long and must contain a digit, upper case
    // alphabet and lowercase alphabet")
    public String password;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "company_id")
    // @NotFound(action = NotFoundAction.IGNORE)
    // public Company company;

    public String roles;

    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user",
    // referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
    // "roles", referencedColumnName = "id"))
    // public List<Role> roles = new ArrayList<>();

    private List<GrantedAuthority> getGrantedAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userType);

        return List.of(simpleGrantedAuthority);
    }

}
