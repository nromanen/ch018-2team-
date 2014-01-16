package com.ch018.library.validation;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Edd Arazian
 */
public class Password {

        public static final int MIN_SIZE = 6;
        public static final int MAX_SIZE = 16;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = MIN_SIZE, max = MAX_SIZE, message = "password size must be between 6 and 16")
        private String oldPass;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = MIN_SIZE, max = MAX_SIZE, message = "password size must be between 6 and 16")
        private String newPass;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = MIN_SIZE, max = MAX_SIZE, message = "password size must be between 6 and 16")
        private String rNewPass;

        public String getOldPass() {
            return oldPass;
        }

        public void setOldPass(String oldPass) {
            this.oldPass = oldPass;
        }

        public String getNewPass() {
            return newPass;
        }

        public void setNewPass(String newPass) {
            this.newPass = newPass;
        }

        public String getrNewPass() {
            return rNewPass;
        }

        public void setrNewPass(String rNewPass) {
            this.rNewPass = rNewPass;
        }


    
}
