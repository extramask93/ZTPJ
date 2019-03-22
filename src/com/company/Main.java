package com.company;
import DAO.EmployeeDao;
import DAO.IEmployeeDao;
import com.company.Controller.MasterController;
import com.company.View.UI;


public class Main {
    public static void main(String[] args) {

            try {
                IEmployeeDao dao = null;//new EmployeeDao();
                MasterController controller = new MasterController(dao);
                UI ui = new UI(controller);
                ui.Run();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


}
