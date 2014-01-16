/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.validation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Edd Arazian
 */
public class PersonalInfo {
    
        @NotEmpty
        @Size(min = 2, max = 30, message = "name size must be between 2 and 30")
        private String name;
        @NotEmpty
        @Size(min = 2, max = 30, message = "surname size must be between 2 and 30")
        private String surname;
        
        @Pattern(message = "must be in format xxx-xxx-xxxx",regexp = "\\d{3}-\\d{3}-\\d{4}")
        private String cellphone;
        private boolean sms;

        public PersonalInfo() {
        }

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

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

       

        public boolean isSms() {
            return sms;
        }

        public void setSms(boolean sms) {
            this.sms = sms;
        }


}
