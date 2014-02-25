package com.ch018.library.validation;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ch018.library.util.Constants;

/**
 *
 * @author Edd Arazian
 */
public class Password {

        public static final int MIN_SIZE = 6;
        public static final int MAX_SIZE = 16;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = Constants.MIN_PASSWORD_LENGTH, max = Constants.MAX_PASSWORD_LENGTH
        			, message = "password size must be between 6 and 16")
        private String oldPass;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = Constants.MIN_PASSWORD_LENGTH, max = Constants.MAX_PASSWORD_LENGTH
        			, message = "password size must be between 6 and 16")
        private String newPass;

        @NotEmpty(message = "password field can't be empty")
        @Size(min = Constants.MIN_PASSWORD_LENGTH, max = Constants.MAX_PASSWORD_LENGTH
        			, message = "password size must be between 6 and 16")
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((newPass == null) ? 0 : newPass.hashCode());
			result = prime * result
					+ ((oldPass == null) ? 0 : oldPass.hashCode());
			result = prime * result
					+ ((rNewPass == null) ? 0 : rNewPass.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Password other = (Password) obj;
			if (newPass == null) {
				if (other.newPass != null)
					return false;
			} else if (!newPass.equals(other.newPass))
				return false;
			if (oldPass == null) {
				if (other.oldPass != null)
					return false;
			} else if (!oldPass.equals(other.oldPass))
				return false;
			if (rNewPass == null) {
				if (other.rNewPass != null)
					return false;
			} else if (!rNewPass.equals(other.rNewPass))
				return false;
			return true;
		}

        
    
}
