package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Bookcase;
import com.ch018.library.util.HibernateUtil;

@Component
public class BookcaseDAOImpl implements BookcaseDAO {
	
	
	@Override
	public void addBokcase(Bookcase bookcase) {
		// TODO Auto-generated method stub
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bookcase);
            session.getTransaction().commit();
        } catch(Exception e){
        }finally{
            try{
                session.close();
            }catch(Exception e){
            }
        }
	}

	@Override
	public void updateBookcase(int id, Bookcase bookcase) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bookcase> getAllBookcases() {
		// TODO Auto-generated method stub
		List<Bookcase> bookcase = new ArrayList<Bookcase>();
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bookcase.addAll(session.createCriteria(Bookcase.class).list());
        } catch(Exception e){
        }finally{
            try{
                session.close();
            }catch(Exception e){
            }
        }
		return bookcase;
	}

	@Override
	public Bookcase getBookcaseById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookcase(Bookcase bookcase) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
