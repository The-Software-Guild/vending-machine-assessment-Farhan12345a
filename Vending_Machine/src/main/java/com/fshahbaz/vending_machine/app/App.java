
package com.fshahbaz.vending_machine.app;

import com.fshahbaz.vending_machine.controller.Vending_Machine_Controller;
import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao_FileImpl;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao_File_Impl;
import com.fshahbaz.vending_machine.ui.UserIO;
import com.fshahbaz.vending_machine.ui.UserIOConsoleImpl;
import com.fshahbaz.vending_machine.ui.Vending_Machine_View;
import com.fshahbaz.vending_machine_service.Vending_Machine_Service_Layer;
import com.fshahbaz.vending_machine_service.Vending_Machine_Service_Layer_FileImpl;

/**
 *
 * @author farhanshahbaz
 */
public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException{
        
        UserIO myIo = new UserIOConsoleImpl();
        
        Vending_Machine_View myView = new Vending_Machine_View(myIo);
        
        Vending_Machine_Dao myDao = new Vending_Machine_Dao_File_Impl();
        
        Vending_Machine_Audit_Dao  myAuditDao = new Vending_Machine_Audit_Dao_FileImpl();
        
        //Instantiating our controller and calling the run method
        Vending_Machine_Service_Layer myService = new Vending_Machine_Service_Layer_FileImpl(myDao, myAuditDao);
        
        Vending_Machine_Controller controller = new Vending_Machine_Controller(myService, myView);
        
        controller.run();
        
        }
    }
    

