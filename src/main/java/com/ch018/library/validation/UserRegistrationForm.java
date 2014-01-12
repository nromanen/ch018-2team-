package com.ch018.library.validation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Edd Arazian
 */
public class UserRegistrationForm {

        public static final int MIN_NAME = 2;
        public static final int MAX_NAME = 30;
        public static final int MIN_PASS = 2;
        public static final int MAX_PASS = 16;


        @NotEmpty
        @Size(min = MIN_NAME, max = MAX_NAME)
        private String name;

        @NotEmpty
        @Size(min = MIN_NAME, max = MAX_NAME)
        private String surname;

        @NotEmpty
        @Email
        private String email;

        @NotEmpty
        @Email
        private String rEmail;

        @NotEmpty
        @Size(min = MIN_PASS, max = MAX_PASS)
        private String password;

        @NotEmpty
        @Size(min = MIN_PASS, max = MAX_PASS)
        private String rPassword;

        @NotEmpty
        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}")
        private String cellPhone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getrEmail() {
            return rEmail;
        }

        public void setrEmail(String rEmail) {
            this.rEmail = rEmail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getrPassword() {
            return rPassword;
        }

        public void setrPassword(String rPassword) {
            this.rPassword = rPassword;
        }

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }

}
