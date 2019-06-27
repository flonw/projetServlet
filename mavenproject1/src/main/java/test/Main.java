/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import modele.DAOException;
import modele.DiscountEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.DAO;
import modele.DataSourceFactory;

/**
 *
 * @author basti
 */
public class Main 
{
    DAO dao;
    
    public static void main (String[] args)
    {
        new Main();
    }
    
    public Main()
    {
        try {
            this.dao = new DAO(DataSourceFactory.getDataSource());
            
            for (DiscountEntity d: dao.ListeDesDiscount())
            {
                System.out.println(d.getDiscount_code());
            }} catch (DAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n");
        
        dao.addDiscount("Z", 0.4);
        
        try {
            this.dao = new DAO(DataSourceFactory.getDataSource());
            
            for (DiscountEntity d: dao.ListeDesDiscount())
            {
                System.out.println(d.getDiscount_code());
            }} catch (DAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n");
        dao.deleteDiscount("Z");
        
        try {
            this.dao = new DAO(DataSourceFactory.getDataSource());
            
            for (DiscountEntity d: dao.ListeDesDiscount())
            {
                System.out.println(d.getDiscount_code());
            }} catch (DAOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                
    }
    
}
