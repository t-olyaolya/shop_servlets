package com.company.DAO;

import com.company.Model.Bid;
import com.company.Model.Item;
import com.company.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

/**
 * Created by tyuly on 07.02.2017.
 */
public interface DAO {

    SessionFactory configureSessionFactory() throws HibernateException;
    void closeSession();
    void fillDB();
    User createUser(String name, String password);
    Item createItem(String name, User user, String description);
    Bid createBid(User user, Item item);
    List<User> getUsers();
    List<Item> getItems();
    List<Bid> getBids();
    boolean auth(String username, String password);
    boolean checkName(String username, String password);
    User getUser(String name);
    Item getItem(Integer id);
    List<Item> getItemsForBuy(User user);
    void openTransaction();
    void commit();
    List<Bid> getBidsUser(User user);
    void dltItem(Item item);
    public void editItem(Item item, String name, String description);
    void editUser(User us,String name, String password);
    void dltUser(User user);


}
