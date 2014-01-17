package com.ch018.library.helper;

import com.ch018.library.entity.Book;

/**
 *
 * @author Edd Arazian
 */
public class BookSearch extends Book {

        private Integer yearStart;
        private Integer yearEnd;
        private Integer bookPageStart;
        private Integer bookPageEnd;
        private Integer genreId;
        private Integer viewPageNum = 1;
        private Integer lowBorder;
        private Integer highBorder;
        private Integer booksOnPage = 20;
        private String query;
        private String sort = "title";
        private Boolean order = false;
        
        public void setBorders() {
            highBorder = viewPageNum * booksOnPage;
            lowBorder = highBorder - booksOnPage;
        }

        public Integer getYearStart() {
            return yearStart;
        }

        public void setYearStart(Integer yearStart) {
            this.yearStart = yearStart;
        }

        public Integer getYearEnd() {
            return yearEnd;
        }

        public void setYearEnd(Integer yearEnd) {
            this.yearEnd = yearEnd;
        }

        public Integer getBookPageStart() {
            return bookPageStart;
        }

        public void setBookPageStart(Integer bookPageStart) {
            this.bookPageStart = bookPageStart;
        }

        public Integer getBookPageEnd() {
            return bookPageEnd;
        }

        public void setBookPageEnd(Integer bookPageEnd) {
            this.bookPageEnd = bookPageEnd;
        }

        public Integer getGenreId() {
            return genreId;
        }

        public void setGenreId(Integer genreId) {
            this.genreId = genreId;
        }

        public Integer getViewPageNum() {
            return viewPageNum;
        }

        public void setViewPageNum(Integer viewPageNum) {
            this.viewPageNum = viewPageNum;
        }

        public Integer getLowBorder() {
            return lowBorder;
        }

        public void setLowBorder(Integer lowBorder) {
            this.lowBorder = lowBorder;
        }

        public Integer getHighBorder() {
            return highBorder;
        }

        public void setHighBorder(Integer highBorder) {
            this.highBorder = highBorder;
        }

        public Integer getBooksOnPage() {
            return booksOnPage;
        }

        public void setBooksOnPage(Integer booksOnPage) {
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

        public Boolean getOrder() {
            return order;
        }

        public void setOrder(Boolean order) {
            this.order = order;
        }


        @Override
        public String toString() {
            return "BookSearch{"  + "yearStart=" + yearStart + ", yearEnd=" + yearEnd + ", bookPageStart=" + bookPageStart + ", bookPageEnd=" + bookPageEnd + ", genreId=" + genreId + ", viewPageNum=" + viewPageNum + ", lowBorder=" + lowBorder + ", highBorder=" + highBorder + ", booksOnPage=" + booksOnPage + ", query=" + query + ", sort=" + sort + ", order=" + order + '}';
        }

        
}
