package com.ch018.library.helper;

import java.util.Date;

/**
 *
 * @author Edd Arazian
 */
public class OrderDays {
    
        private Date minOrderDate;
        private long daysAvailable;

        public OrderDays() {
        }

        public OrderDays(Date minOrderDate, long daysAvailable) {
            this.minOrderDate = minOrderDate;
            this.daysAvailable = daysAvailable;
        }

        public Date getMinOrderDate() {
            return minOrderDate;
        }

        public void setMinOrderDate(Date minOrderDate) {
            this.minOrderDate = minOrderDate;
        }

        public long getDaysAvailable() {
            return daysAvailable;
        }

        public void setDaysAvailable(long daysAvailable) {
            this.daysAvailable = daysAvailable;
        }


    
}
