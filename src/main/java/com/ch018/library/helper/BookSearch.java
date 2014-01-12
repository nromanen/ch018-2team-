/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.helper;

import com.ch018.library.entity.Book;

/**
 *
 * @author Edd Arazian
 */
public class BookSearch extends Book {

        private int yearStart;
        private int yearEnd;
        private int bookPageStart;
        private int bookPageEnd;
        private int genreId;
        private int viewPageNum;
        private int lowBorder;
        private int highBorder;
        private int booksOnPage;
        private String query;
        private String sort = "title";
        private boolean order;
        
        public void setBorders() {
            highBorder = viewPageNum * booksOnPage;
            lowBorder = highBorder - booksOnPage;
        }

        public int getYearStart() {
            return yearStart;
        }

        public void setYearStart(int yearStart) {
            this.yearStart = yearStart;
        }

        public int getYearEnd() {
            return yearEnd;
        }

        public void setYearEnd(int yearEnd) {
            this.yearEnd = yearEnd;
        }

        public int getBookPageStart() {
            return bookPageStart;
        }

        public void setBookPageStart(int bookPageStart) {
            this.bookPageStart = bookPageStart;
        }

        public int getBookPageEnd() {
            return bookPageEnd;
        }

        public void setBookPageEnd(int bookPageEnd) {
            this.bookPageEnd = bookPageEnd;
        }

        public int getGenreId() {
            return genreId;
        }

        public void setGenreId(int genreId) {
            this.genreId = genreId;
        }



        public int getViewPageNum() {
            return viewPageNum;
        }

        public void setViewPageNum(int viewPageNum) {
            this.viewPageNum = viewPageNum;
        }

        public int getBooksOnPage() {
            return booksOnPage;
        }

        public void setBooksOnPage(int booksOnPage) {
            this.booksOnPage = booksOnPage;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public boolean isOrder() {
            return order;
        }

        public void setOrder(boolean order) {
            this.order = order;
        }

        public int getLowBorder() {
            return lowBorder;
        }

        public void setLowBorder(int lowBorder) {
            this.lowBorder = lowBorder;
        }

        public int getHighBorder() {
            return highBorder;
        }

        public void setHighBorder(int highBorder) {
            this.highBorder = highBorder;
        }

    @Override
    public String toString() {
        return super.toString() +  " BookSearch{"  + "yearStart=" + yearStart + ", yearEnd=" + yearEnd + ", bookPageStart=" + bookPageStart + ", bookPageEnd=" + bookPageEnd + ", genreId=" + genreId + ", viewPageNum=" + viewPageNum + ", lowBorder=" + lowBorder + ", highBorder=" + highBorder + ", booksOnPage=" + booksOnPage + ", query=" + query + ", sort=" + sort + ", order=" + order + '}';
    }

        
}
