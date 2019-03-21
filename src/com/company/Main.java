package com.company;
import com.company.Controller.DAO.EmployeeDao;
import com.company.Controller.DAO.IEmployeeDao;
import com.company.Controller.MasterController;
import com.company.View.UI;


public class Main {
    public static void main(String[] args) {
            try {
                IEmployeeDao dao = new EmployeeDao();
                MasterController controller = new MasterController(dao);
                UI ui = new UI(controller);
                ui.Run();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


}
