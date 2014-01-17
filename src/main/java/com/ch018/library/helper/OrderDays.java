package com.ch018.library.helper;

import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Orders;

/**
 *
 * @author Edd Arazian
 */
public class OrderDays {
    
        private Date minOrderDate;
        private long daysAvailable;
        private List<Orders> orders;

        public OrderDays() {
        }

        public OrderDays(Date minOrderDate, long daysAvailable) {
            this.minOrderDate = minOrderDate;
            this.daysAvailable = daysAvailable;
        }

        public OrderDays(Date minOrderDate, long daysAvailable, List<Orders> orders) {
            this.minOrderDate = minOrderDate;
            this.daysAvailable = daysAvailable;
            this.orders = orders;
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

        public List<Orders> getOrders() {
            return orders;
        }

        public void setOrders(List<Orders> orders) {
            this.orders = orders;
        }
    
}
