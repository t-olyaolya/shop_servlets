package com.company.DAO;

import com.company.Model.Bid;
import com.company.Model.Item;
import com.company.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by tyuly on 07.02.2017.
 */
public class DAOImpl implements DAO {

    private static SessionFactory sessionFactory= null;
    private static ServiceRegistry serviceRegistry= null;
    private static Session session = null;
    private static Transaction tx = null;

    public DAOImpl() {
      createSession();
    }

    public SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        serviceRegistry= new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory= configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public void createSession()  {
        if (session == null) {
            sessionFactory = configureSessionFactory();
            session = sessionFactory.openSession();
            fillDB();
        }
        if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }
       // tx = session.beginTransaction();
    }

    public void openTransaction() {
        tx = session.beginTransaction();
    }

    public void commit() {
        tx.commit();
        session.flush();;
    }
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    public void fillDB()  {
        User user1 = createUser("User1", "1111");
        User user2 = createUser("User2", "2222");
        Item item1 = createItem("Item1", user1, "1");
        Item item2 = createItem("Item2", user2, "2");
        Item item3 = createItem("Item3", user1, "3");
        Bid bid = createBid(user1, item2);
        Bid bid2 = createBid(user2, item1);
    }

    public User createUser(String name, String password){
        User user = new User();
        try {
            tx = session.beginTransaction();
            user.setName(name);
            user.setPassword(password);
            session.persist(user);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return user;
    }



    public Item createItem(String name, User user, String description) {
        Item item = new Item();
        try {
            createSession();
            tx = session.beginTransaction();
            item.setName(name);
            item.setDescription(description);
            item.setUser(user);
            item.setBuy(false);
            user.setItem(item);
            session.persist(item);
            session.persist(user);
            tx.commit();
            session.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return item;
    }

    public Bid createBid(User user, Item item)  {
        Bid bid = new Bid();
        try {
            tx = session.beginTransaction();
            bid.setUser(user);
            bid.setItem(item);
            session.persist(bid);
            user.setBid(bid);
            session.persist(user);
            item.setBuy(true);
            session.persist(item);
            session.flush();

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return bid;
    }


    public List<User> getUsers() {
        List<User> userList = null;
        try {
            tx = session.beginTransaction();
            userList = session.createCriteria(User.class).list();
            session.flush();
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return userList;
    }

    public  List<Item> getItems() {
        //tx = session.beginTransaction();
        List<Item> itemList = null;
        try {
            itemList = session.createCriteria(Item.class).list();
            session.flush();
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return itemList;
    }

    public  List<Item> getItemsForBuy(User user) {
        List<Item> list = getItems();
        List <Item> listForBuy = new ArrayList<>();
        for (Item item:list) {
            if (!item.getBuy() && !item.getUser().equals(user)) {
                listForBuy.add(item);
            }
        }
        return listForBuy;
    }

    public  List<Bid> getBids() {
        List<Bid> bidList = null;
        try {
            tx = session.beginTransaction();
            bidList = session.createCriteria(Bid.class).list();
            session.flush();
            tx.commit();

        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return bidList;
    }

    public  List<Bid> getBidsUser(User user) {
        List<Bid> bidList = null;
        List<Bid> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            bidList = session.createCriteria(Bid.class).list();
            for (Bid bid: bidList) {
                if (bid.getUser().getName().equals(user.getName())) {
                    list.add(bid);
                }

            }
            session.flush();
            tx.commit();

        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return list;
    }

    public boolean auth(String username, String password) {
        try {
            tx = session.beginTransaction();
            List<User> userList = session.createCriteria(User.class).list();
            for (User user : userList) {
                if ((user.getName().equals(username)) && (user.getPassword().equals(password))) {
                    return true;
                }
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return false;
    }

    public User getUser(String name)  {
        try {
          //  tx = session.beginTransaction();
            List<User> users = session.createCriteria(User.class).list();
            for (User user : users) {
                if (user.getName().equals(name)) {
                    session.flush();
            //        tx.commit();
                    return user;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return null;
    }

    @Override
    public Item getItem(Integer id) {
        try {
            //tx = session.beginTransaction();
            List<Item> items = session.createCriteria(Item.class).list();
            for (Item item:items) {
                if (item.getId().equals(id)) {
                    session.flush();
                 //   tx.commit();
                    return item;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return null;
    }


    public boolean checkName(String username, String password) {
        try {
            tx = session.beginTransaction();
            List<User> userList = session.createCriteria(User.class).list();
            for (User user : userList) {
                if ((user.getName().equals(username))) {
                    return true;
                }
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return false;
    }

    public void dltItem(Item item) {
        try {
            tx = session.beginTransaction();
            session.delete(getItem(item.getId()));
            session.flush();
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    public void dltUser(User user) {
        try {
            tx = session.beginTransaction();
            session.delete(getUser(user.getName()));
            session.flush();
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }



    public void editItem(Item it, String name, String description) {
        tx = session.beginTransaction();
        Item item = getItem(it.getId());
        item.setName(name);
        item.setDescription(description);
        session.flush();
        tx.commit();
    }

    public void editUser(User us,String name, String password) {
        tx = session.beginTransaction();
        User user = getUser(us.getName());
        user.setName(name);
        user.setPassword(password);
        session.flush();
        tx.commit();
    }

}
