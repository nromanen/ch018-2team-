package com.ch018.library.helper;

import com.ch018.library.entity.Book;
import java.util.List;

/**
 *
 * @author Edd Arazian
 */
public class Page {
    
        private int generalItemsQuantity;
        private int generalPagesQuantity;
        private int currentPageNum;
        private List<Book> books;
        private BookSearch bookSearch;

        public int getGeneralItemsQuantity() {
            return generalItemsQuantity;
        }

        public void setGeneralItemsQuantity(int generalItemsQuantity) {
            this.generalItemsQuantity = generalItemsQuantity;
        }

        public int getCurrentPageNum() {
            return currentPageNum;
        }

        public void setCurrentPageNum(int currentPageNum) {
            this.currentPageNum = currentPageNum;
        }

        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }

        public int getGeneralPagesQuantity() {
            return generalPagesQuantity;
        }

        public void setGeneralPagesQuantity(int generalPagesQuantity) {
            this.generalPagesQuantity = generalPagesQuantity;
        }

        public BookSearch getBookSearch() {
            return bookSearch;
        }

        public void setBookSearch(BookSearch bookSearch) {
            this.bookSearch = bookSearch;
        }

        
}